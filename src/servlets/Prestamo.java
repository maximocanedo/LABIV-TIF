package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Cliente;
import entity.PrestamosCliente;
import logicImpl.AuthManager;
import logicImpl.PrestamoClienteLogicImpl;
import max.data.Response;

/**
 * Servlet implementation class Prestamo
 */
@WebServlet("/api/client/Prestamo")
public class Prestamo extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prestamo() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    PrestamoClienteLogicImpl logic = new PrestamoClienteLogicImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente cliente = AuthManager.getActualClient(request, response);
		Response<PrestamosCliente> res = logic.getById(cliente.getUsuario().toString());
		String json = res.toFinalJSON();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json);
	}

	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "GET" };
	}
}
