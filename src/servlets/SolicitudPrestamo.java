package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Administrador;
import entity.Cliente;
import entity.Cuenta;
import entity.Paginator;
import logicImpl.AuthManager;
import logicImpl.SolicitudPrestamoLogicImpl;
import logicImpl.AuthManager.TokenData;
import logicImpl.CuentaLogicImpl;
import max.Dictionary;
import max.Response;

/**
 * Servlet implementation class SolicitudPrestamo
 */
@WebServlet("/api/solicitudprestamo")
public class SolicitudPrestamo extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SolicitudPrestamo() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private SolicitudPrestamoLogicImpl logic = new SolicitudPrestamoLogicImpl();
    private CuentaLogicImpl cuentaL = new CuentaLogicImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Response<entity.SolicitudPrestamo> resSL;
		Paginator paginator = getPaginator(request);
		/* Verificar si es cliente o admin */
		TokenData td = AuthManager.readToken(request);
		if(td != null) {
			switch(td.role) {
			case AuthManager.ADMIN:
				resSL = logic.getAll(paginator);				
				write(response, resSL.toFinalJSON());
				
				return;
			case AuthManager.CLIENT:
				Cliente cliente = AuthManager.getActualClient(request, response);
				if(cliente!=null) {
					resSL = logic.getAllForClientByDNI(cliente.getUsuario(), paginator);
					write(response, resSL.toFinalJSON());
					response.setStatus(201);
					return;
				} else {
					response.setStatus(403);
					return;
				}
			default:
				response.setStatus(403);
				break;
			}
		} else {
			response.setStatus(401);
		}
		return;
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		Cliente cliente = AuthManager.getActualClient(request, response);
		if(cliente!=null) {
			Dictionary parameters = getParameters(request);
			if(parameters == null) {
				die(response, false, 400, "Bad request");
				return;
			}
			Cuenta cuenta = cuentaL.convert(parameters);
			entity.SolicitudPrestamo nuevaSolicitud = logic.convert(parameters);
			nuevaSolicitud.setCliente(cliente);
			nuevaSolicitud.setCuenta(cuenta);
			Response<entity.SolicitudPrestamo> res = logic.insert(nuevaSolicitud);
			response.setStatus(res.http);
			write(response, res.toFinalJSON());
			return;
		} else {
			response.setStatus(403);
			return;
		}
	}
	protected void doPut(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		Administrador admin = AuthManager.getActualAdmin(request, response);
		Dictionary parametersAdmin = getParameters(request);
		if (admin == null) {
			response.setStatus(403);
			return;
		}
		if(parametersAdmin == null) {
			response.setStatus(400);
			return;
		}
		
		entity.SolicitudPrestamo prestamo = logic.convert(parametersAdmin);
		Response<entity.SolicitudPrestamo> res = logic.approve(prestamo);
		response.setStatus(res.http);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Administrador admin = AuthManager.getActualAdmin(request, response);
		Dictionary parametersAdmin = getParameters(request);
		if (admin == null) {
			response.setStatus(403);
			return;
		}
		if(parametersAdmin == null) {
			response.setStatus(400);
			return;
		}
		
		entity.SolicitudPrestamo prestamo = logic.convert(parametersAdmin);
		Response<entity.SolicitudPrestamo> res = logic.reject(prestamo);
		response.setStatus(res.http);
	}


	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "GET", "POST", "PUT", "DELETE" };
	}
}
