package api;

import entity.Cliente;
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

@WebServlet("/v2/me/transactions")
public class MyTransactions extends BaseServlet {
    public MyTransactions() { super(); }
    private final MovimientoLogicImpl MLI = new MovimientoLogicImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cliente cliente = AuthManager.getActualClient(req, resp);
        Paginator paginator = getPaginator(req);
        if(cliente == null) return;
        Response<Movimiento> res = MLI.getAll(cliente, paginator);
        write(resp, res.toFinalJSON());
    }

    @Override
    protected String[] getAllowedMethods() {
        return new String[] { GET, OPTIONS };
    }
}
