package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Administrador;
import entity.Cliente;
import entity.PrestamosCliente;
import logicImpl.AuthManager;
import logicImpl.PrestamoClienteLogicImpl;
import logicImpl.AuthManager.TokenData;
import max.Response;

/**
 * Servlet implementation class Prestamos
 */
@WebServlet("/api/loans")
public class Prestamos extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prestamos() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    PrestamoClienteLogicImpl logic = new PrestamoClienteLogicImpl();
    
    protected void getLoans__Admin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Response<PrestamosCliente> res = logic.getAll();
		write(response, res.toFinalJSON());
    }
    
    protected void getLoans__Client(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Cliente cliente = AuthManager.getActualClient(request, response);
		Response<PrestamosCliente> res = logic.getById(cliente.getUsuario().toString());
		write(response, res.toFinalJSON());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Verificar si es cliente o admin */
		TokenData td = AuthManager.readToken(request);
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
		return new String[] { "GET", "OPTIONS" };
	}

}
