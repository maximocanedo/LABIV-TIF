package api;

import entity.Administrador;
import entity.Cliente;
import entity.Cuenta;
import entity.Paginator;
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

@WebServlet("/v2/accounts")
public class Accounts extends BaseServlet {
    public Accounts() { super(); }
    private final CuentaLogicImpl CL = new CuentaLogicImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthManager.TokenData td = AuthManager.readToken(request);
        if(td == null) { // Si nadie inició sesión
            response.setStatus(401);
            return;
        }
        if(td.role.equals(AuthManager.ADMIN)) {
            Administrador admin = AuthManager.getActualAdmin(request, response);
            Paginator paginator = getPaginator(request);
            if(admin != null) {
                Response<Cuenta> res = CL.getAll(paginator);
                response.setStatus(res.http);
                write(response, res.toFinalJSON());
            }
        } else {
            Cliente cliente = AuthManager.getActualClient(request, response);
            if(cliente != null) {
                Response<Cuenta> res = CL.getAllFor(cliente);
                response.setStatus(res.http);
                write(response, res.toFinalJSON());
            }
        }
        return;
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cliente cliente = AuthManager.getActualClient(request, response);
        if(cliente == null) return;
        Dictionary parameters = getParameters(request);
        if(parameters == null) {
            die(response, false, 400, "Bad request");
            return;
        }
        parameters.put("Dni_Cl_CxC", cliente.getDNI());
        Cuenta nuevaCuenta= CL.convert(parameters);
        Response<Cuenta> res = CL.insert(nuevaCuenta);
        response.setStatus(res.http);
        write(response, res.toFinalJSON());

    }
    @Override
    protected String[] getAllowedMethods() {
        return new String[] { GET, POST, OPTIONS };
    }
}
