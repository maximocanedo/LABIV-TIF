package api;

import entity.Administrador;
import entity.Cliente;
import entity.Telefono;
import logicImpl.AuthManager;
import logicImpl.ClienteLogicImpl;
import logicImpl.TelefonoLogicImpl;
import max.Response;
import servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet({
        "/v2/me/phones",
        "/v2/clients/user/*/phones",
        "/v2/clients/dni/*/phones",
        "/v2/clients/cuil/*/phones",
})
public class Phones extends BaseServlet {
    public Phones() { super(); }
    protected final ClienteLogicImpl CLI = new ClienteLogicImpl();
    protected final TelefonoLogicImpl TLI = new TelefonoLogicImpl();
    protected Cliente getURLClient(HttpServletRequest req, HttpServletResponse resp) {
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
    public Cliente getSpecifiedClient(HttpServletRequest request, HttpServletResponse response) {
        String key = getPathParameter(request, 1);
        if (key == "me") return AuthManager.getActualClient(request, response);
        Administrador admin = AuthManager.getActualAdmin(request, response);
        if(admin == null) return null;
        return getURLClient(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cliente cliente = getSpecifiedClient(req, resp);
        if(cliente == null) {
            resp.setStatus(403);
            return;
        }
        String key = getPathParameter(req, 1);
        if(!Objects.equals(key, "me")) AuthManager.getActualAdmin(req, resp);
        Response<Telefono> res = TLI.getAllFor(cliente);
        resp.setStatus(res.http);
        write(resp, res.toFinalJSON());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cliente cliente = AuthManager.getActualClient(req, resp);
        if(cliente == null || req.getParameter("tel") == null) {
            resp.setStatus(400);
            return;
        }
        Telefono tel = new Telefono();
        tel.setTelefono(req.getParameter("tel"));
        tel.setDNI_Usuario(cliente.getDNI());
        tel.setActivo(true);
        Response<Telefono> res = TLI.insert(tel);
        resp.setStatus(res.http);
        write(resp, res.toFinalJSON());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(202);
    }

    @Override
    protected String[] getAllowedMethods() {
        return new String[] { GET, POST, DELETE };
    }
}
