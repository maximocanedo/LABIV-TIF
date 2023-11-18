package api;

import entity.Administrador;
import entity.Cliente;
import entity.SolicitudCambioClave;
import logicImpl.AuthManager;
import logicImpl.RequestC01LogicImpl;
import max.Response;
import servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/v2/clients/passwordsRequests/*")
public class PasswordChangeRequest extends BaseServlet {
    private RequestC01LogicImpl logic = new RequestC01LogicImpl();

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_s = getPathParameter(request);
        Integer id = -1;
        try {
            id = Integer.parseInt(id_s);
        } catch(NumberFormatException e) {
            response.setStatus(400);
            return;
        }
        Response<SolicitudCambioClave> resRCF = logic.getById(id);
        //write(response, new Gson().toJson(resRCF));
        if(resRCF.listReturned.isEmpty()) {
            response.setStatus(404);
            return;
        }
        SolicitudCambioClave solicitudCambioClave = resRCF.listReturned.get(0);
        /* Verificar si es cliente o admin */
        AuthManager.TokenData td = AuthManager.readToken(request);
        if(td != null) {
            switch(td.role) {
                case AuthManager.ADMIN:
                    write(response, resRCF.toFinalJSON());
                    return;
                case AuthManager.CLIENT:
                    Cliente cliente = AuthManager.getActualClient(request, response);
                    if(cliente == null) return;
                    if(solicitudCambioClave.getIssuer().getDNI().equals(cliente.getDNI())) {
                        write(response, resRCF.toFinalJSON());
                        response.setStatus(200);
                        return;
                    } else {
                        response.setStatus(403);
                        return;
                    }
                default:
                    response.setStatus(403);
                    break;
            }
        } else {
            response.setStatus(401);
        }
        return;
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_s = getPathParameter(request);
        Integer id = -1;
        try {
            id = Integer.parseInt(id_s);
        } catch(NumberFormatException e) {
            response.setStatus(400);
            return;
        }
        Response<SolicitudCambioClave> resRCF = logic.getById(id);
        //write(response, new Gson().toJson(resRCF));
        if(resRCF.listReturned.isEmpty()) {
            response.setStatus(404);
            return;
        }
        //RequestC01 requestC01 = resRCF.listReturned.get(0);
        Administrador admin = AuthManager.getActualAdmin(request, response);
        if(admin != null) {
            String ress = logic.close(id).toFinalJSON();
            write(response, ress);
            return;
        }
    }
    @Override
    protected String[] getAllowedMethods() {
        return new String[] { GET, PUT };
    }
}
