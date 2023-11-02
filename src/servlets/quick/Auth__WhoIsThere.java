package servlets.quick;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entity.Administrador;
import entity.Cliente;
import logic.AuthManager;
import logic.AuthManager.TokenData;
import max.data.LogicResponse;
import servlets.BaseServlet;

/**
 * Servlet implementation class Auth__WhoIsThere
 */
@WebServlet("/api/whoami")
public class Auth__WhoIsThere extends BaseServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see BaseServlet#BaseServlet()
     */
    public Auth__WhoIsThere() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Verificar si es cliente o admin */
		LogicResponse<Cliente> cl = new LogicResponse<Cliente>();
		LogicResponse<Administrador> ad = new LogicResponse<Administrador>();
		TokenData td = AuthManager.readToken(request);
		if(td != null) {
			System.out.println(td.role + " - " + td.username);
			
			switch(td.role) {
			case AuthManager.ADMIN:
				Administrador admin = AuthManager.getActualAdmin(request, response);
				if(admin == null) return;
				ad.fill(admin);
				ad.die(true, 200, "ADMIN");
				write(response, new Gson().toJson(ad));
				break;
			case AuthManager.CLIENT:
				Cliente cliente = AuthManager.getActualClient(request, response);
				if(cliente == null) return;
				cl.fill(cliente);
				cl.die(true, 200, "CLIENT");
				write(response, new Gson().toJson(cl));
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
		return new String[] { "GET" };
	}

}
