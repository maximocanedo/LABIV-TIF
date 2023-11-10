package servlets;

import java.io.IOException;

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
import logicImpl.AuthManager.TokenData;
import max.Response;

@WebServlet("/api/movements")
public class Movimientos extends BaseServlet {
	
	private static final long serialVersionUID = 1L;

	public Movimientos() {
		super();
	}
	
	private static MovimientoLogicImpl lgm = new MovimientoLogicImpl();
	
	/**
	 * Método GET: Mostrar TODOS los movimientos (Admin), o Mostrar todos mis movimientos (Cliente)
	 */
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		Paginator paginator = getPaginator(request);
		TokenData td = AuthManager.readToken(request);
		if(td == null) {
			response.setStatus(401);
			return;
		}
		if(td.role.equals(AuthManager.ADMIN)) {
			Administrador admin = AuthManager.getActualAdmin(request, response);
			if(admin != null) {
				Response<Movimiento> res = lgm.getAll(paginator);
				response.setStatus(res.http);
				write(response, res.toFinalJSON());
			}
		} else {
			Cliente cliente = AuthManager.getActualClient(request, response);
			if(cliente != null) {
				Response<Movimiento> res = lgm.getAll(cliente, paginator);
				response.setStatus(res.http);
				write(response, res.toFinalJSON());
			}
		}
		return;
	}
	
	/**
	 * Método POST: Agregar movimiento. 
	 * TODO PENDIENTE!!!!
	 */
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		doGet(request,response);
	}

	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "GET", "POST", "OPTIONS" };
	}

}