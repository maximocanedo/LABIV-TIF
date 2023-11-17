package api;

import entity.Administrador;
import logicImpl.AdministradorLogicImpl;
import logicImpl.AuthManager;
import max.Response;
import servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({
        "/v2/admins/user/*",
        "/v2/admins/dni/*",
        "/v2/admins/cuil/*"
})
public class Admin extends BaseServlet {
    public Admin() { super(); }

    protected final AdministradorLogicImpl ALI = new AdministradorLogicImpl();

    protected Administrador getSpecifiedAdmin(HttpServletRequest req, HttpServletResponse resp) {
        String key = getPathParameter(req, 1);
        String val = getPathParameter(req, 0);
        Response<Administrador> res;
        switch(key) {
            case "dni":
                res = ALI.getByDNI(val);
                break;
            case "cuil":
                res = ALI.getByCUIL(val);
                break;
            case "user":
                res = ALI.getById(val);
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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Administrador admin = getSpecifiedAdmin(req, resp);
        if(admin == null) return;
        write(resp, admin.toJSON());
        return;
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Administrador admin = getSpecifiedAdmin(req, resp);
        if(admin == null) return;
        if(AuthManager.getActualAdmin(req, resp) == null) return;
        Response<Administrador> result = ALI.delete(admin);
        resp.setStatus(result.http);
        write(resp, result.toFinalJSON());
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getSpecifiedAdmin(req, resp);
    }

    @Override
    protected String[] getAllowedMethods() {
        return new String[] { "GET", "DELETE", "HEAD" };
    }
}
