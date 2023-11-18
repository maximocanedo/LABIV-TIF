package api;

import entity.Administrador;
import entity.Cliente;
import entity.RequestC01;
import logicImpl.AdministradorLogicImpl;
import logicImpl.AuthManager;
import logicImpl.ClienteLogicImpl;
import logicImpl.RequestC01LogicImpl;
import max.Dictionary;
import max.Response;
import servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/v2/me")
public class Me extends BaseServlet {
    protected final ClienteLogicImpl CL = new ClienteLogicImpl();

    private final AdministradorLogicImpl AL = new AdministradorLogicImpl();
    public Me() { super(); }
    protected void admin__UpdatePassword(HttpServletRequest request, HttpServletResponse response, Administrador actualUser) throws IOException {
        Response<Administrador> result = new Response<>();
        Dictionary params = getParameters(request);
        if(params != null) {
            result = AL.updatePassword(actualUser, params);
            response.setStatus(result.http);
            write(response, result.toFinalJSON());
            return;
        }
        result.message = "There are no parameters. ";
        response.setStatus(400);
        write(response, result.toFinalJSON());
    }
    protected void admin__DisableAccount(HttpServletResponse response, Administrador actualUser) throws IOException {
        Response<Administrador> result = AL.delete(actualUser);
        response.setStatus(result.http);
        write(response, result.toFinalJSON());
    }
    protected void admin__ModifyAccount(HttpServletRequest request, HttpServletResponse response, Administrador actualUser) throws IOException {
        Dictionary params = getParameters(request);
        if(params != null) {
            Response<Administrador> res = AL.modify(params, actualUser);
            write(response, res.toFinalJSON());
        } else {
            response.setStatus(400);
        }
    }
    protected void admin__Data(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Administrador admin = AuthManager.getActualAdmin(request, response);
        if(admin != null) write(response, admin.toJSON());
        else {
            response.setStatus(404);
        }
    }
    protected void client__ModifyAccount(HttpServletRequest request, HttpServletResponse response, Cliente actualUser) throws IOException {
        Dictionary params = getParameters(request);
        if(params != null) {
            Response<Cliente> res = CL.modify(params, actualUser);
            write(response, res.toFinalJSON());
        } else {
            response.setStatus(400);
        }
    }
    protected void client__DisableAccount(HttpServletResponse response, Cliente actualUser) throws IOException {
        Response<Cliente> result = CL.delete(actualUser);
        response.setStatus(result.http);
        write(response, result.toFinalJSON());
    }
    protected void client__Data(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cliente cliente = AuthManager.getActualClient(request, response);
        if(cliente == null) {
            response.setStatus(404);
            return;
        }
        write(response, cliente.toJSON());
        response.setStatus(200);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthManager.TokenData td = AuthManager.readToken(request);
        if(td != null) {
            switch(td.role) {
                case AuthManager.ADMIN:
                    admin__Data(request, response);
                    break;
                case AuthManager.CLIENT:
                    client__Data(request, response);
                    break;
                default:
                    response.setStatus(403);
                    break;
            }
        } else {
            response.setStatus(401);
        }


    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AuthManager.TokenData td = AuthManager.readToken(request);
        write(response, "");
        if(td != null) {
            switch(td.role) {
                case AuthManager.ADMIN:
                    Administrador admin = AuthManager.getActualAdmin(request, response);
                    if(admin != null) {
                        admin__ModifyAccount(request, response, admin);
                    } else {
                        response.setStatus(404);
                        return;
                    }
                    break;
                case AuthManager.CLIENT:
                    Cliente cliente = AuthManager.getActualClient(request, response);
                    if(cliente != null) {
                        client__ModifyAccount(request, response, cliente);
                    } else {
                        response.setStatus(404);
                        return;
                    }
                    break;
                default:
                    response.setStatus(403);
                    break;
            }
        } else {
            response.setStatus(401);
        }
    }
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AuthManager.TokenData td = AuthManager.readToken(request);
        if(td != null) {
            switch(td.role) {
                case AuthManager.ADMIN:
                    Administrador admin = AuthManager.getActualAdmin(request, response);
                    if(admin != null) {
                        admin__DisableAccount(response, admin);
                    }
                    break;
                case AuthManager.CLIENT:
                    Cliente cliente = AuthManager.getActualClient(request, response);
                    if(cliente != null) {
                        client__DisableAccount(response, cliente);
                    } else {
                        response.setStatus(404);
                    }
                    break;
                default:
                    response.setStatus(403);
                    break;
            }
        } else {
            response.setStatus(401);
        }

    }

    @Override
    protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AuthManager.TokenData td = AuthManager.readToken(request);
        if(td != null) {
            switch(td.role) {
                case AuthManager.ADMIN:
                    Administrador admin = AuthManager.getActualAdmin(request, response);
                    if(admin != null) {
                        admin__UpdatePassword(request, response, admin);
                    }
                    break;
                case AuthManager.CLIENT:
                    Cliente cliente = AuthManager.getActualClient(request, response);
                    if(cliente != null)
                        client__RequestNewPassword(request, response, cliente);
                    break;
                default:
                    response.setStatus(403);
                    break;
            }
        } else {
            response.setStatus(401);
        }
    }

    private void client__RequestNewPassword(HttpServletRequest request, HttpServletResponse response, Cliente cliente) throws IOException {
        RequestC01LogicImpl logic = new RequestC01LogicImpl();
        Response<RequestC01> fet = logic.issue(cliente);
        write(response, fet.toFinalJSON());
        response.setStatus(fet.http);
    }

    @Override
    protected String[] getAllowedMethods() {
        return new String[] { GET, PUT, DELETE, PATCH };
    }

}
