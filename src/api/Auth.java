package api;

import entity.Administrador;
import entity.Cliente;
import logicImpl.AdministradorLogicImpl;
import logicImpl.AuthManager;
import logicImpl.ClienteLogicImpl;
import max.Dictionary;
import max.Response;
import servlets.BaseServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/v2/auth")
public class Auth extends BaseServlet {
	private final AdministradorLogicImpl AL = new AdministradorLogicImpl();
	protected final ClienteLogicImpl CL = new ClienteLogicImpl();
	public Auth() {
        super();
    }
	protected void loginAsAdmin(HttpServletResponse response, Dictionary parameters) throws IOException {
		Response<Administrador> finalRes = AL.login(parameters);
		if(finalRes.status) {
			String tk = finalRes.eField;
			if(tk != null) {
				AuthManager.sendToken(response, tk);
			}
		}
		response.setStatus(finalRes.http);
		finalRes.clean(true);
		response.getWriter().append(finalRes.toFinalJSON());
	}
	protected void loginAsClient(HttpServletResponse response, Dictionary parameters) throws IOException {
		Response<Cliente> finalRes = CL.login(parameters);
		if(finalRes.status) {
			String tk = finalRes.eField;
			if(tk != null) {
				AuthManager.sendToken(response, tk);
			}
		}
		response.setStatus(finalRes.http);
		write(response, finalRes.toFinalJSON());
	}

	/**
	 * POST /auth
	 * Iniciar sesión
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dictionary parameters = getParameters(request);
		if(parameters != null && parameters.$("role") != null) {
			switch(parameters.$("role").toString()) {
				case AuthManager.ADMIN:
					loginAsAdmin(response, parameters);
					return;
				case AuthManager.CLIENT:
					loginAsClient(response, parameters);
					return;
				default:
					response.setStatus(400);
			}
		} else {
			response.setStatus(400);
		}
	}

	@Override
	protected String[] getAllowedMethods() {
		return new String[] { POST, OPTIONS };
	}

}
