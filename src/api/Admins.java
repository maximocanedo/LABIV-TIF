package api;

import entity.Administrador;
import logicImpl.AdministradorLogicImpl;
import logicImpl.AuthManager;
import max.Dictionary;
import max.Response;
import servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/v2/admins")
public class Admins extends BaseServlet {

    private final AdministradorLogicImpl AL = new AdministradorLogicImpl();
    protected void onAuthenticated__CreateAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Obtener parámetros
        Dictionary parameters = getParameters(request);
        // Si no hay parámetros o están mal organizados, enviar un HTTP 400.
        if(parameters == null) {
            die(response, false, 400, "Bad request");
            return;
        }
        // Si todo está bien, intentar crear cuenta de administrador con los datos recibidos.
        Response<Administrador> finalRes = AL.createAccount(parameters);
        // Enviamos el código recibido y la respuesta recibida.
        response.setStatus(finalRes.status ? 201 : 400);
        write(response, finalRes.toFinalJSON());
    }

    public Admins() { super(); }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(202);
        write(resp, "This endpoint's currently not available. ");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Administrador admin = AuthManager.getActualAdmin(req, resp);
        if(admin != null) onAuthenticated__CreateAccount(req, resp);
    }

    @Override
    protected String[] getAllowedMethods() {
        return new String[] { GET, POST, OPTIONS };
    }
}
