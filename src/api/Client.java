package api;

import entity.Administrador;
import entity.Cliente;
import logicImpl.AuthManager;
import logicImpl.ClienteLogicImpl;
import max.Response;
import servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({
        "/v2/clients/dni/*",
        "/v2/clients/cuil/*",
        "/v2/clients/user/*"
})
public class Client extends BaseServlet {

    protected final ClienteLogicImpl CLI = new ClienteLogicImpl();

    protected Cliente getSpecifiedClient(HttpServletRequest req, HttpServletResponse resp) {
        String key = getPathParameter(req, 1);
        String val = getPathParameter(req, 0);
        Response<Cliente> res;
        switch(key) {
            case "dni":
                res = CLI.getByDNI(val);
                break;
            case "cuil":
                res = CLI.getByCUIL(val);
                break;
            case "user":
                res = CLI.getById(val);
                break;
            default:
                resp.setStatus(400);
                return null;
        }
        if(res.listReturned != null && !res.listReturned.isEmpty()) {
            return res.listReturned.get(0);
        }
        resp.setStatus(404);
        return null;
    }

    public Client() { super(); }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cliente client = getSpecifiedClient(req, resp);
        if(client == null) return;
        write(resp, client.toJSON());
        return;
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cliente client = getSpecifiedClient(req, resp);
        if(client == null) return;
        if(AuthManager.getActualAdmin(req, resp) == null) return;
        Response<Cliente> result = CLI.delete(client);
        resp.setStatus(result.http);
        write(resp, result.toFinalJSON());
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) {
        getSpecifiedClient(req, resp);
    }

    @Override
    protected String[] getAllowedMethods() {
        return new String[] { "GET", "DELETE", "HEAD" };
    }

}
