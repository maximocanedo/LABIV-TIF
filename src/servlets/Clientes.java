package servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Administrador;
import entity.Cliente;
import logic.ClienteLogic;
import max.data.Response;

/**
 * Servlet implementation class Clientes
 */
@WebServlet("/api/clients")
public class Clientes extends BaseServlet implements Servlet {
	private static final long serialVersionUID = 1L;

    public Clientes() {
        super();
    }

    protected ClienteLogic CL = new ClienteLogic();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Administrador admin = new Administrador(); // AuthManager.getActualAdmin(request, response);
		if(admin != null) {
			Response<Cliente> gac = CL.getAll();
			response.setStatus(gac.http);
			write(response, gac.toFinalJSON());
		}
		return;
	}

	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "GET", "OPTIONS" };
	}

}
