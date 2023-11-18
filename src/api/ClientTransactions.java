package api;

import entity.*;
import logicImpl.AuthManager;
import logicImpl.ClienteLogicImpl;
import logicImpl.MovimientoLogicImpl;
import max.Response;
import servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({
        "/v2/clients/dni/*/transactions",
        "/v2/clients/cuil/*/transactions",
        "/v2/clients/user/*/transactions"
})
public class ClientTransactions extends BaseServlet {
    public ClientTransactions() { super(); }
    protected final ClienteLogicImpl CLI = new ClienteLogicImpl();
    private final MovimientoLogicImpl MLI = new MovimientoLogicImpl();
    protected Cliente getSpecifiedClient(HttpServletRequest req, HttpServletResponse resp) {
        String key = getPathParameter(req, 2);
        String val = getPathParameter(req, 1);
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Paginator paginator = getPaginator(request);
        Administrador admin = AuthManager.getActualAdmin(request, response);
        if(admin == null) return;
        Cliente cliente = getSpecifiedClient(request, response);
        if(cliente != null) {
            Response<Movimiento> res = MLI.getAll(cliente, paginator);
            response.setStatus(res.http);
            write(response, res.toFinalJSON());
        }
    }

    @Override
    protected String[] getAllowedMethods() {
        return new String[] { GET, OPTIONS };
    }
}
