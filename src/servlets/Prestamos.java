package servlets;

import java.io.IOException;
import java.sql.Types;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.PrestamosCliente;
import logic.PrestamosClienteLogic;
import max.data.Response;

/**
 * Servlet implementation class Prestamos
 */
@WebServlet("/api/admin/prestamos")
public class Prestamos extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prestamos() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    PrestamosClienteLogic logic = new PrestamosClienteLogic();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Response<PrestamosCliente> res = logic.getAll();
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
