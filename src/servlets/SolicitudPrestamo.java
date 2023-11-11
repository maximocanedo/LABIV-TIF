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
import logicImpl.AuthManager;
import logicImpl.SolicitudPrestamoLogicImpl;
import logicImpl.AuthManager.TokenData;
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_s = getPathParameter(request);
		Integer id = -1;
		try {
			id = Integer.parseInt(id_s);
		} catch(NumberFormatException e) {
			response.setStatus(400);
			return;
		}
		Response<entity.SolicitudPrestamo> resSL = logic.getById(id);
 
		if(resSL.listReturned.isEmpty()) {
			response.setStatus(404);//id= -1
			return;
		}
		entity.SolicitudPrestamo requestSL = resSL.listReturned.get(0);
		/* Verificar si es cliente o admin */
		TokenData td = AuthManager.readToken(request);
		if(td != null) {
			switch(td.role) {
			case AuthManager.ADMIN:
				write(response, resSL.toFinalJSON());
				return;
			case AuthManager.CLIENT:
				Cliente cliente = AuthManager.getActualClient(request, response);
				if(requestSL.getCliente().getDNI().equals(cliente.getDNI())) {
					write(response, resSL.toFinalJSON());
					response.setStatus(200);
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
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		/* Verificar si es cliente o admin */
		String id_s = getPathParameter(request);
		Integer id = -1;
		try {
			id = Integer.parseInt(id_s);
		} catch(NumberFormatException e) {
			response.setStatus(400);
			return;
		}
		Response<entity.SolicitudPrestamo> resSL = logic.getById(id);
 
		if(resSL.listReturned.isEmpty()) {
			response.setStatus(404);//id= -1
			return;
		}
		entity.SolicitudPrestamo requestSL = resSL.listReturned.get(0);
		TokenData td = AuthManager.readToken(request);
		if(td != null) {
			switch(td.role) {
			case AuthManager.ADMIN:
				Administrador admin = AuthManager.getActualAdmin(request, response);
				if (admin==null)return;
				
				
				
			case AuthManager.CLIENT:
				Cliente cliente = AuthManager.getActualClient(request, response);
				if(requestSL.getCliente().getDNI().equals(cliente.getDNI())) {
					Dictionary parameters = getParameters(request);
					if(parameters == null) {
						die(response, false, 400, "Bad request");
						return;
					}
					
					entity.SolicitudPrestamo nuevaSolicitud= logic.convert(parameters);
					logic.insert(nuevaSolicitud);
					
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


	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "GET", "POST" };
	}
}
