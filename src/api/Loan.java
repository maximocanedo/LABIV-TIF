package api;

import entity.PrestamosCliente;
import logicImpl.PrestamoClienteLogicImpl;
import max.Response;
import servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/v2/loans/id/*")
public class Loan extends BaseServlet {
    public Loan() { super(); }
    private final PrestamoClienteLogicImpl PCLI = new PrestamoClienteLogicImpl();
    protected PrestamosCliente getSpecifiedLoan(HttpServletRequest req, HttpServletResponse res) {
        String strid = getPathParameter(req, 0);
        if(strid == null) {
            res.setStatus(400);
            return null;
        }
        int id;
        try {
            id = Integer.parseInt(strid);
        } catch(NumberFormatException e) {
            res.setStatus(400);
            return null;
        }
        Response<PrestamosCliente> data = PCLI.getById(id);
        if(data == null || data.listReturned == null) {
            res.setStatus(404);
            return null;
        }
        return data.listReturned.get(0);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        PrestamosCliente loan = getSpecifiedLoan(req, resp);
        if(loan == null) return;
        write(resp, loan.toJSON());
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        getSpecifiedLoan(req, resp);
    }

    @Override
    protected String[] getAllowedMethods() {
        return new String[] { GET, HEAD };
    }
}
