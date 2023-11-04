package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entity.Administrador;
import entity.Cliente;
import entity.RequestC01;
import logic.AuthManager;
import logic.AuthManager.TokenData;
import logic.RequestC01Logic;
import max.data.Response;

/**
 * Servlet implementation class SingularRequestC01
 */
@WebServlet("/api/requests/C01/*")
public class SingularRequestC01 extends servlets.BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingularRequestC01() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private RequestC01Logic logic = new RequestC01Logic();

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
		Response<RequestC01> resRCF = logic.getById(id);
		//write(response, new Gson().toJson(resRCF));
		if(resRCF.listReturned.isEmpty()) {
			response.setStatus(404);
			return;
		}
		RequestC01 requestC01 = resRCF.listReturned.get(0);
		/* Verificar si es cliente o admin */
		TokenData td = AuthManager.readToken(request);
		if(td != null) {
			switch(td.role) {
			case AuthManager.ADMIN:
				write(response, new Gson().toJson(resRCF));
				return;
			case AuthManager.CLIENT:
				Cliente cliente = AuthManager.getActualClient(request, response);
				if(requestC01.getIssuer().getDNI().equals(cliente.getDNI())) {
					write(response, new Gson().toJson(resRCF));
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_s = getPathParameter(request);
		Integer id = -1;
		try {
			id = Integer.parseInt(id_s);
		} catch(NumberFormatException e) {
			response.setStatus(400);
			return;
		}
		Response<RequestC01> resRCF = logic.getById(id);
		//write(response, new Gson().toJson(resRCF));
		if(resRCF.listReturned.isEmpty()) {
			response.setStatus(404);
			return;
		}
		RequestC01 requestC01 = resRCF.listReturned.get(0);
		Administrador admin = AuthManager.getActualAdmin(request, response);
		if(admin != null) {
			String ress = new Gson().toJson(logic.close(id));
			write(response, ress);
			return;
		}
	}

	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "GET", "PUT" };
	}

}
