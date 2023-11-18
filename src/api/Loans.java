package api;

import entity.Cliente;
import entity.Prestamo;
import logicImpl.AuthManager;
import logicImpl.PrestamoClienteLogicImpl;
import max.Response;
import servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/v2/loans")
public class Loans extends BaseServlet {
    public Loans() { super(); }
    private final PrestamoClienteLogicImpl logic = new PrestamoClienteLogicImpl();
    protected void getLoans__Admin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Response<Prestamo> res = logic.getAll();
        write(response, res.toFinalJSON());
    }

    protected void getLoans__Client(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cliente cliente = AuthManager.getActualClient(request, response);
        if(cliente == null) return;
        Response<Prestamo> res = logic.getById(cliente.getUsuario().toString());
        write(response, res.toFinalJSON());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthManager.TokenData td = AuthManager.readToken(request);
        if(td != null) {
            switch(td.role) {
                case AuthManager.ADMIN:
                    AuthManager.getActualAdmin(request, response);
                    getLoans__Admin(request, response);
                    break;
                case AuthManager.CLIENT:
                    getLoans__Client(request, response);
                    break;
                default:
                    response.setStatus(403);
                    break;
            }
        } else {
            response.setStatus(401);
        }
        return;
    }

    @Override
    protected String[] getAllowedMethods() {
        return new String[] { GET, OPTIONS };
    }
}
