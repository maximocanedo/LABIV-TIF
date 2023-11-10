package servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Administrador;
import entity.Cliente;
import entity.Movimiento;
import entity.Paginator;
import logicImpl.AuthManager;
import logicImpl.MovimientoLogicImpl;
import max.Response;

/**
 * Servlet implementation class Movimientos__DNI
 */
@WebServlet("/movements/dni/*")
public class Movimientos__DNI extends BaseServlet implements Servlet {
	private static final long serialVersionUID = 1L;
    private static MovimientoLogicImpl logic = new MovimientoLogicImpl();
    public Movimientos__DNI() {
        super();
    }

	/**
	 * Método GET: Listar movimientos de un DNI dado. 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Paginator paginator = getPaginator(request);
		Administrador admin = AuthManager.getActualAdmin(request, response);
		String dni = getPathParameter(request);
		if(admin != null) {
			Response<Movimiento> res = logic.getAll(new Cliente() {{ setDNI(dni); }}, paginator);
			response.setStatus(res.http);
			write(response, res.toFinalJSON());
		}
	}


	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "GET", "OPTIONS" };
	}

}
