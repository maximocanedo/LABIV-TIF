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
		Response<entity.SolicitudPrestamo> resSL;
		
		/* Verificar si es cliente o admin */
		TokenData td = AuthManager.readToken(request);
		if(td != null) {
			switch(td.role) {
			case AuthManager.ADMIN:
				resSL = logic.getAll();
				 
				if(resSL.listReturned.isEmpty()) {
					response.setStatus(404);
					return;
				}
				write(response, resSL.toFinalJSON());
				
				return;
			case AuthManager.CLIENT:
				Cliente cliente = AuthManager.getActualClient(request, response);
				if(cliente!=null) {
					resSL = logic.getById(cliente.getUsuario());
					
					if(resSL.listReturned.isEmpty()) {
						response.setStatus(404);
						return;
					}
					
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
		TokenData td = AuthManager.readToken(request);
		if(td != null) {
			switch(td.role) {
			case AuthManager.ADMIN:
				Administrador admin = AuthManager.getActualAdmin(request, response);
				
				if (admin==null)return;
				Dictionary parametersAdmin = getParameters(request);
				if(parametersAdmin == null) {
					die(response, false, 400, "Bad request");
					return;
				}
				
				entity.SolicitudPrestamo prestamo= logic.convert(parametersAdmin);
				logic.modify(prestamo);
				response.setStatus(200);
				
				
				
			case AuthManager.CLIENT:
				Cliente cliente = AuthManager.getActualClient(request, response);
				if(cliente!=null) {
					Dictionary parameters = getParameters(request);
					if(parameters == null) {
						die(response, false, 400, "Bad request");
						return;
					}
					
					entity.SolicitudPrestamo nuevaSolicitud= logic.convert(parameters);
					logic.insert(nuevaSolicitud);
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


	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "GET", "POST" };
	}
}
