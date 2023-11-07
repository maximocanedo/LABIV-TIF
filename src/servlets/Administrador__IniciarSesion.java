package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Administrador;
import logicImpl.AdministradorLogicImpl;
import logicImpl.AuthManager;
import max.Dictionary;
import max.Response;

/**
 * Servlet implementation class Administrador__IniciarSesion
 */
@WebServlet("/api/admin/login")
public class Administrador__IniciarSesion extends BaseServlet {
private AdministradorLogicImpl AL = new AdministradorLogicImpl();
	

	
	protected void status(HttpServletResponse res, int code) {
		res.setStatus(code);
	}
	protected void sendMessage(HttpServletResponse res, boolean status, String content) throws IOException {
		Response<Object> r = new Response<Object>();
		r.status = status;
		r.message = content;
		res.getWriter().append(r.toFinalJSON());
	}
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Administrador__IniciarSesion() {
        super();
        // TODO Auto-generated constructor stub
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dictionary parameters = getParameters(request);
		Response<Administrador> finalRes = AL.login(parameters);
		if(finalRes.status) {
			String tk = finalRes.eField;
			if(tk != null) {
				AuthManager.sendToken(response, tk);
			}
		}
		status(response, finalRes.http);
		finalRes.clean(true);
        response.getWriter().append(finalRes.toFinalJSON());
	}
	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "POST" };
	}

}
