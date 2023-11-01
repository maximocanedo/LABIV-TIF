package servlets;

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
import logic.RequestC01Logic;
import max.data.LogicResponse;

/**
 * Servlet implementation class RequestC01
 */
@WebServlet("/api/requests/C01")
public class RequestsC01 extends BaseServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see BaseServlet#BaseServlet()
     */
    public RequestsC01() {
        super();
        // TODO Auto-generated constructor stub
    }

    RequestC01Logic logic = new RequestC01Logic();
	/**
	 * Método GET: Ver todas las solicitudes sin aprobar.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Administrador admin = AuthManager.getActualAdmin(request, response);
		if(admin != null) {
			LogicResponse<entity.RequestC01> fet = logic.getAll();
			write(response, new Gson().toJson(fet));
			response.setStatus(fet.http);
		}
		return;
	}

	/**
	 * Método POST: Solicitar cambio de clave.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente client = AuthManager.getActualClient(request, response);
		if(client != null) {
			LogicResponse<entity.RequestC01> fet = logic.issue(client);
			write(response, new Gson().toJson(fet));
			response.setStatus(fet.http);
		}
		return;
	}

	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "GET", "POST" };
	}

}
