package api;

import entity.Administrador;
import entity.Cliente;
import entity.Paginator;
import entity.filter.ClienteFilter;
import logicImpl.AuthManager;
import logicImpl.ClienteLogicImpl;
import max.Dictionary;
import max.Response;
import servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/v2/clients")
public class Clients extends BaseServlet {

    public Clients() { super(); }

    protected ClienteLogicImpl CL = new ClienteLogicImpl();

    protected ClienteFilter getFilters(HttpServletRequest request) {
        ClienteFilter filter = new ClienteFilter();
        filter.q = request.getParameter("q");
        filter.provinceId = request.getParameter("province");
        filter.localtyId = request.getParameter("localty");
        filter.sex = request.getParameter("sex");
        String s = request.getParameter("status");
        filter.status = s != null ? (s == "1" ? true : (s == "0" ? false : true)) : null;
        filter.countryId = request.getParameter("country");
        return filter;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Administrador admin = AuthManager.getActualAdmin(request, response);
        if (null == admin) return;
        Paginator paginator = getPaginator(request);
        ClienteFilter filter = getFilters(request);
        Response<Cliente> gac = CL.search(filter, paginator);
        response.setStatus(gac.http);
        write(response, gac.toFinalJSON());
    }

    /**
     * Método POST: Crear cuenta de usuario
     *
     * Posibles respuestas:
     * 201: Se creó correctamente
     * 400: Error de validaciones o con los parámetros
     * 500: Error en la base de datos
     *
     * Devuelve un LogicResponse, en cuya propiedad ObjectReturned estarán el usuario y contraseña asignados.
     *
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener parámetros
        Dictionary parameters = getParameters(request);
        System.out.println(parameters);
        // Si no hay parámetros o están mal organizados, enviar un HTTP 400.
        if(parameters == null) {
            die(response, false, 400, "Bad request");
            return;
        }
        // Si está bien, intentar crear cuenta de administrador con los datos recibidos.
        Response<Cliente> finalRes = CL.createAccount(parameters);
        // Enviamos el código recibido y la respuesta recibida.
        response.setStatus(finalRes.status ? 201 : 500);
        write(response, finalRes.toFinalJSON());
    }

    @Override
    protected String[] getAllowedMethods() {
        return new String[] { "GET", "POST", "OPTIONS" };
    }


}
