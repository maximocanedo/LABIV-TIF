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
 * Servlet implementation class Cliente__FirstPerson
 */
@WebServlet("/api/client")
public class Cliente__FirstPerson extends BaseServlet implements Servlet {
	private static final long serialVersionUID = 1L;
    protected ClienteLogic CL = new ClienteLogic();
    public Cliente__FirstPerson() {
        super();
    }
    
    /**
     * Mostrar datos del usuario en sesión. Sólo ejecutar tras haber autenticado.
     * @param request
     * @param response
     * @param actualUser
     * @throws IOException
     */
    protected void onAuthenticated__ShowPersonalData(HttpServletRequest request, HttpServletResponse response, Cliente actualUser) throws IOException {
    	write(response, actualUser.toJSON());
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtener parámetros
    	Dictionary parameters = getParameters(request);
    	// Si no hay parámetros o están mal organizados, enviar un HTTP 400.
		if(parameters == null) {
			die(response, false, 400, "Bad request");
			return;
		}
		// Si todo está bien, intentar crear cuenta de administrador con los datos recibidos.
		LogicResponse<Cliente> finalRes = CL.createAccount(parameters);
		// Enviamos el código recibido y la respuesta recibida.
		response.setStatus(finalRes.status ? 201 : 400);
        write(response, finalRes.toFinalJSON());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente cliente = AuthManager.getActualClient(request, response);
		if(cliente != null) {
			onAuthenticated__ShowPersonalData(request, response, cliente);
		}
		return;
	}

	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "GET", "POST" };
	}

}
