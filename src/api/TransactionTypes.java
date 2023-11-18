package api;

import entity.Movimiento;
import entity.TipoMovimiento;
import logicImpl.MovimientoLogicImpl;
import logicImpl.TipoMovimientoLogicImpl;
import max.Response;
import servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/v2/transactions/types")
public class TransactionTypes extends BaseServlet {
    public TransactionTypes() { super(); }
    private final TipoMovimientoLogicImpl MLI = new TipoMovimientoLogicImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Response<TipoMovimiento> res = MLI.getAll();
        write(resp, res.toFinalJSON());
    }
    @Override
    protected String[] getAllowedMethods() {
        return new String[] { GET, OPTIONS };
    }
}
