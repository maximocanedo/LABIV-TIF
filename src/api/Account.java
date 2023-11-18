package api;

import entity.*;
import logicImpl.AuthManager;
import logicImpl.CuentaLogicImpl;
import max.Dictionary;
import max.Response;
import servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({
        "/v2/accounts/CBU/*",
        "/v2/accounts/No/*"
})
public class Account extends BaseServlet {
    public Account() { super(); }
    private final CuentaLogicImpl CLI = new CuentaLogicImpl();
    public Cuenta getSpecifiedAccount(HttpServletRequest req, HttpServletResponse res) {
        String key = getPathParameter(req, 1);
        String val = getPathParameter(req, 0);
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
        if(cuenta == null) return;
        if(td.role.equals(AuthManager.ADMIN)) {
            Administrador admin = AuthManager.getActualAdmin(req, res);
            if(admin == null) return;
            write(res, cuenta.toJSON());
        } else {
            Cliente cliente = AuthManager.getActualClient(req, res);
            if(cliente == null) return;
            if(cuenta.getCliente().getDNI().equals(cliente.getDNI())) {
                write(res, cuenta.toJSON());
            } else {
                write(res, cuenta.toSimpleJsonObject().toString());
            }
        }
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setStatus(200);
        getSpecifiedAccount(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cliente cliente = AuthManager.getActualClient(request, response);
        if(cliente == null) return;
        Dictionary parameters = getParameters(request);
        if(parameters == null) {
            die(response, false, 400, "Bad request");
            return;
        }
        Cuenta cuenta = getSpecifiedAccount(request, response);
        if(cuenta == null) return;
        parameters.put("CBUDestino", cuenta.getCBU());
        String origenCBU = parameters.$("CBUOrigen");
        Response<Cuenta> rogn = CLI.getByCBU(origenCBU);
        if(rogn != null && rogn.listReturned != null && !rogn.listReturned.isEmpty()) {
            Cuenta origen = rogn.listReturned.get(0);
            if(origen.getCliente().getDNI().equals(cliente.getDNI())) {
                Transferencia nuevaTransferencia = CLI.convertTransfer(parameters);
                Response<entity.Transferencia> res = CLI.insertTransfer(nuevaTransferencia);
                response.setStatus(res.http);
                write(response, res.toFinalJSON());
            } else {
                response.setStatus(403);
            }
        } else {
            response.setStatus(404);
        }

    }

    @Override
    protected String[] getAllowedMethods() {
        return new String[] { GET, POST, HEAD };
    }
}
