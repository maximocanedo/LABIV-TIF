package servlets.quick;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entity.Administrador;
import entity.Cliente;
import logicImpl.AuthManager;
import logicImpl.ClienteLogicImpl;
import logicImpl.AuthManager.TokenData;
import max.data.Response;
import servlets.BaseServlet;

/**
 * Servlet implementation class WhoAmI
 */
@WebServlet("/api/whoami")
public class WhoAmI extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public WhoAmI() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected ClienteLogicImpl CL = new ClienteLogicImpl();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Response<Cliente> lc = new Response<Cliente>();
		Response<Administrador> la = new Response<Administrador>();
		/* Verificar si es cliente o admin */
		TokenData td = AuthManager.readToken(request);
		if(td != null) {
			switch(td.role) {
			case AuthManager.ADMIN:
				Administrador admin = AuthManager.getActualAdmin(request, response);
				if(admin == null) return;
				la.fill(admin);
				la.message = AuthManager.ADMIN;
				write(response, la.toFinalJSON());
				break;
			case AuthManager.CLIENT:
				Cliente cliente = AuthManager.getActualClient(request, response);
				if(cliente == null) return;
				lc.fill(cliente);
				lc.message = AuthManager.CLIENT;
				write(response, lc.toFinalJSON());
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
		// TODO Auto-generated method stub
		return new String[] { "GET", "OPTIONS" };
	}

}
