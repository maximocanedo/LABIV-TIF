package servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entity.Administrador;
import entity.Cliente;
import entity.SolicitudCambioClave;
import logicImpl.AuthManager;
import logicImpl.RequestC01LogicImpl;
import max.Response;

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

    RequestC01LogicImpl logic = new RequestC01LogicImpl();
	/**
	 * Método GET: Ver todas las solicitudes sin aprobar.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Administrador admin = AuthManager.getActualAdmin(request, response);
		if(admin != null) {
			Response<SolicitudCambioClave> fet = logic.getAll();
			write(response, fet.toFinalJSON());
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
			Response<SolicitudCambioClave> fet = logic.issue(client);
			write(response, fet.toFinalJSON());
			response.setStatus(fet.http);
		}
		return;
	}

	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "GET", "POST" };
	}

}
