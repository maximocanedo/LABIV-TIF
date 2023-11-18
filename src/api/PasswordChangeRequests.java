package api;

import entity.Administrador;
import entity.SolicitudCambioClave;
import logicImpl.AuthManager;
import logicImpl.RequestC01LogicImpl;
import max.Response;
import servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/v2/clients/passwordRequests")
public class PasswordChangeRequests extends BaseServlet {
    public PasswordChangeRequests() { super(); }
    private final RequestC01LogicImpl logic = new RequestC01LogicImpl();
    /**
     * Método GET: Ver todas las solicitudes sin aprobar.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Administrador admin = AuthManager.getActualAdmin(request, response);
        if(admin != null) {
            Response<SolicitudCambioClave> fet = logic.getAll();
            write(response, fet.toFinalJSON());
            response.setStatus(fet.http);
        }
        return;
    }
    @Override
    protected String[] getAllowedMethods() {
        return new String[] { GET };
    }
}
