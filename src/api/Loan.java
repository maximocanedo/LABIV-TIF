package api;

import entity.Cliente;
import entity.PrestamosCliente;
import logicImpl.AuthManager;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthManager.TokenData td = AuthManager.readToken(request);
        PrestamosCliente loan = getSpecifiedLoan(request, response);
        if(td != null) {
            switch(td.role) {
                case AuthManager.ADMIN:
                    response.setStatus(200);
                    if(loan == null) return;
                    write(response, loan.toJSON());
                    return;
                case AuthManager.CLIENT:
                    Cliente cliente = AuthManager.getActualClient(request, response);
                    if(cliente == null) return;
                    if(loan.getCliente().getDNI().equals(cliente.getDNI())) {
                        if(loan == null) return;
                        write(response, loan.toJSON());
                    } else {
                        response.setStatus(403);
                    }
                default:
                    response.setStatus(403);
                    break;
            }
        } else {
            response.setStatus(401);
        }

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
