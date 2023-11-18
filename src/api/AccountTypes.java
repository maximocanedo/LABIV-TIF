package api;

import entity.TipoCuenta;
import logicImpl.TipoCuentaLogicImpl;
import max.Response;
import servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/v2/accounts/types")
public class AccountTypes extends BaseServlet {
    public AccountTypes() { super(); }
    private final TipoCuentaLogicImpl TCLI = new TipoCuentaLogicImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Response<TipoCuenta> res = TCLI.getAll();
        write(resp, res.toFinalJSON());
    }

    @Override
    protected String[] getAllowedMethods() {
        return new String[] { GET, OPTIONS };
    }
}
