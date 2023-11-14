package servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.Administrador;
import entity.Cliente;
import entity.Telefono;
import logicImpl.AuthManager;
import logicImpl.TelefonoLogicImpl;
import logicImpl.AuthManager.TokenData;
import max.Dictionary;
import max.Response;

/**
 * Servlet implementation class Telefonos
 */
@WebServlet("/api/Telefonos/list")
public class Telefonos extends BaseServlet implements Servlet {
	private static final long serialVersionUID = 1L;
   
    public Telefonos() {
        super();
    }
    
    private TelefonoLogicImpl TEL = new TelefonoLogicImpl();
    
    /**
     * Método GET: Listar telefonos 
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TokenData td = AuthManager.readToken(request);
		/*if(td == null) {
			response.setStatus(401);  para validar que solo sea admin
			return;
		}*/
		if(td != null) {
			try {
				if(td.role.equals(AuthManager.ADMIN)) {
					Administrador admin = AuthManager.getActualAdmin(request, response);
		 
					if(admin != null) {
						Response<Telefono> res = TEL.getAll();
						response.setStatus(res.http);
						write(response, res.toFinalJSON());
					}
				} else {
					Cliente cliente = AuthManager.getActualClient(request, response);
					
					if(cliente != null) {
						Response<Telefono> res = TEL.getAllFor(cliente);
						response.setStatus(res.http);
						write(response, res.toFinalJSON());
					}
				}
			}catch(Exception e) {
				e.getMessage();
			}
		}
		return;
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente cliente = AuthManager.getActualClient(request, response);
		if(cliente == null) return;
		Dictionary parameters = getParameters(request);
		if(parameters == null) {
			die(response, false, 400, "Bad request");
			return;
		}
		
		parameters.put("Dni", cliente.getDNI());
		
		Telefono nuevoTelefono = TEL.convert(parameters);
		Response<Telefono> res = TEL.insert(nuevoTelefono);
		response.setStatus(res.http);
		write(response, res.toFinalJSON());
				
	}

	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "GET", "POST", "OPTIONS" };
	}

}
