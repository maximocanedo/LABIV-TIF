package api;

import entity.Administrador;
import entity.Movimiento;
import entity.Paginator;
import logicImpl.AuthManager;
import logicImpl.MovimientoLogicImpl;
import max.Response;
import servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/v2/transactions")
public class Transactions extends BaseServlet {
    public Transactions() { super(); }
    private final MovimientoLogicImpl MLI = new MovimientoLogicImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Administrador admin = AuthManager.getActualAdmin(req, resp);
        if(admin == null) return;
        Paginator paginator = getPaginator(req);
        Response<Movimiento> res = MLI.getAll(paginator);
        resp.setStatus(res.http);
        write(resp, res.toFinalJSON());
    }

    @Override
    protected String[] getAllowedMethods() {
        return new String[] { GET, OPTIONS };
    }
}
