package api;

import entity.Cliente;
import entity.Cuenta;
import entity.Paginator;
import entity.SolicitudPrestamo;
import logicImpl.AuthManager;
import logicImpl.CuentaLogicImpl;
import logicImpl.SolicitudPrestamoLogicImpl;
import max.Dictionary;
import max.Response;
import servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/v2/loans/requests")
public class LoanRequests extends BaseServlet {
    public LoanRequests() { super(); }
    private final SolicitudPrestamoLogicImpl logic = new SolicitudPrestamoLogicImpl();
    private final CuentaLogicImpl CLI = new CuentaLogicImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Response<SolicitudPrestamo> resSL;
        Paginator paginator = getPaginator(request);
        /* Verificar si es cliente o admin */
        AuthManager.TokenData td = AuthManager.readToken(request);
        if(td != null) {
            switch(td.role) {
                case AuthManager.ADMIN:
                    resSL = logic.getAll(paginator);
                    write(response, resSL.toFinalJSON());

                    return;
                case AuthManager.CLIENT:
                    Cliente cliente = AuthManager.getActualClient(request, response);
                    if(cliente!=null) {
                        resSL = logic.getAllForClientByDNI(cliente.getUsuario(), paginator);
                        write(response, resSL.toFinalJSON());
                        response.setStatus(201);
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
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cliente cliente = AuthManager.getActualClient(request, response);
        if(cliente!=null) {
            Dictionary parameters = getParameters(request);
            if(parameters == null) {
                die(response, false, 400, "Bad request");
                return;
            }
            Cuenta cuenta = CLI.convert(parameters);
            entity.SolicitudPrestamo nuevaSolicitud = logic.convert(parameters);
            nuevaSolicitud.setCliente(cliente);
            nuevaSolicitud.setCuenta(cuenta);
            Response<entity.SolicitudPrestamo> res = logic.insert(nuevaSolicitud);
            response.setStatus(res.http);
            write(response, res.toFinalJSON());
            return;
        } else {
            response.setStatus(403);
            return;
        }
    }

    @Override
    protected String[] getAllowedMethods() {
        return new String[] { GET, POST };
    }
}
