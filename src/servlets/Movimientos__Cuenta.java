package servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Cliente;
import entity.Cuenta;
import entity.Movimiento;
import entity.Paginator;
import logicImpl.AuthManager;
import logicImpl.MovimientoLogicImpl;
import logicImpl.AuthManager.TokenData;
import logicImpl.CuentaLogicImpl;
import max.Response;

/**
 * Servlet implementation class Movimientos__Cuenta
 */
@WebServlet("/api/movements/account/*")
public class Movimientos__Cuenta extends BaseServlet implements Servlet {
	private static final long serialVersionUID = 1L;

    private static MovimientoLogicImpl logic = new MovimientoLogicImpl();
    private static CuentaLogicImpl cuentaLogic = new CuentaLogicImpl();
    /**
     * @see BaseServlet#BaseServlet()
     */
    public Movimientos__Cuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Método GET: Ver movimientos de una cuenta (Admin). Ver movimientos de mi cuenta (Cliente)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String numeroCuenta = getPathParameter(request);
		Response<Cuenta> resCuenta = cuentaLogic.getById(numeroCuenta);
		if(!resCuenta.nonEmptyResult()) {
			response.setStatus(404);
			return;
		}
		Cuenta cuenta = resCuenta.listReturned.get(0);
		Paginator paginator = getPaginator(request);
		TokenData td = AuthManager.readToken(request);
		if(td == null) {
			response.setStatus(401);
			return;
		}
		if(!td.role.equals(AuthManager.ADMIN)) {
			Cliente cliente = AuthManager.getActualClient(request, response);
			if(cliente != null) {
				if(!cliente.getDNI().equals(cuenta.getCliente().getDNI())) {
					response.setStatus(403);
					return;
				}
			}
		} else {
			AuthManager.getActualAdmin(request, response);
		}
		Response<Movimiento> res = logic.getAll(cuenta, paginator);
		response.setStatus(res.http);
		write(response, res.toFinalJSON());
		return;
	}

	

	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "GET", "OPTIONS" };
	}

}
