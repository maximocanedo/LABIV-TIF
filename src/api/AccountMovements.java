package api;

import entity.*;
import logicImpl.AuthManager;
import logicImpl.CuentaLogicImpl;
import logicImpl.MovimientoLogicImpl;
import max.Response;
import servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({
        "/v2/accounts/CBU/*/movements",
        "/v2/accounts/No/*/movements"
})
public class AccountMovements extends BaseServlet {
    public AccountMovements() { super(); }
    private final CuentaLogicImpl CLI = new CuentaLogicImpl();
    private final MovimientoLogicImpl MLI = new MovimientoLogicImpl();
    public Cuenta getSpecifiedAccount(HttpServletRequest req, HttpServletResponse res) {
        String key = getPathParameter(req, 2);
        String val = getPathParameter(req, 1);
        Response<Cuenta> resc = new Response<>();
        switch (key) {
            case "CBU":
                resc = CLI.getByCBU(val);
                if(resc.listReturned != null && !resc.listReturned.isEmpty()) {
                    Cuenta cuenta = resc.listReturned.get(0);
                    return cuenta;
                }
                res.setStatus(404);
                break;
            case "No":
                resc = CLI.getById(val);
                if(resc.listReturned != null && !resc.listReturned.isEmpty()) {
                    Cuenta cuenta = resc.listReturned.get(0);
                    return cuenta;
                }
                res.setStatus(404);
                break;
            default:
                res.setStatus(400);
                return null;
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        AuthManager.TokenData td = AuthManager.readToken(req);
        if(td == null) {
            res.setStatus(401);
            return;
        }
        Cuenta cuenta = getSpecifiedAccount(req, res);
        Paginator paginator = getPaginator(req);
        if(cuenta == null) return;
        Response<Movimiento> rc = MLI.getAll(cuenta, paginator);
        if(td.role.equals(AuthManager.ADMIN)) {
            Administrador admin = AuthManager.getActualAdmin(req, res);
            if(admin == null) return;
            write(res, rc.toFinalJSON());
        } else {
            Cliente cliente = AuthManager.getActualClient(req, res);
            if(cliente == null) return;
            if(cuenta.getCliente().getDNI().equals(cliente.getDNI())) {
                write(res, rc.toFinalJSON());
            } else {
                res.setStatus(403);
                return;
            }
        }
    }

    @Override
    protected String[] getAllowedMethods() {
        return new String[] { GET, OPTIONS };
    }
}
