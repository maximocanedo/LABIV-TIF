package servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Administrador;
import entity.Cliente;
import logic.AuthManager;
import logic.ClienteLogic;
import max.data.Dictionary;
import max.data.LogicResponse;

/**
 * Servlet implementation class Cliente__IniciarSesion
 */
@WebServlet("/api/client/login")
public class Cliente__IniciarSesion extends BaseServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	protected ClienteLogic CL = new ClienteLogic();
       
    public Cliente__IniciarSesion() {
        super();
    }

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dictionary parameters = getParameters(request);
		LogicResponse<Cliente> finalRes = CL.login(parameters);
		if(finalRes.status) {
			String tk = finalRes.eField;
			if(tk != null) {
				AuthManager.sendToken(response, tk);
			}
		}
		response.setStatus(finalRes.http);
		finalRes.clean(true);
        write(response, finalRes.toFinalJSON());
	}

	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "POST" };
	}

}
