package servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
     * Mostrar datos del usuario en sesi�n. S�lo ejecutar tras haber autenticado.
     * @param request
     * @param response
     * @param actualUser
     * @throws IOException
     */
    protected void onAuthenticated__ShowPersonalData(HttpServletRequest request, HttpServletResponse response, Cliente actualUser) throws IOException {
    	write(response, actualUser.toJSON());
    }
    
    /**
     * Deshabilitar usuario en sesi�n. S�lo ejecutar tras haber autenticado.
     * @param request
     * @param response
     * @param actualUser
     * @throws IOException
     */
    protected void onAuthenticated__DisableAccount(HttpServletRequest request, HttpServletResponse response, Cliente actualUser) throws IOException {
    	LogicResponse<Cliente> result = CL.delete(actualUser);
    	response.setStatus(result.http);
    	write(response, result.toFinalJSON());
    }

	/**
	 * M�todo POST: Crear cuenta de usuario
	 * 
	 * Posibles respuestas:
	 * 201: Se cre� correctamente
	 * 400: Error de validaciones o con los par�metros
	 * 500: Error en la base de datos
	 * 
	 * Devuelve un LogicResponse, en cuya propiedad ObjectReturned estar�n el usuario y contrase�a asignados.
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtener par�metros
    	Dictionary parameters = getParameters(request);
    	// Si no hay par�metros o est�n mal organizados, enviar un HTTP 400.
		if(parameters == null) {
			die(response, false, 400, "Bad request");
			return;
		}
		// Si todo est� bien, intentar crear cuenta de administrador con los datos recibidos.
		LogicResponse<Cliente> finalRes = CL.createAccount(parameters);
		// Enviamos el c�digo recibido y la respuesta recibida.
		response.setStatus(finalRes.status ? 201 : 500);
        write(response, finalRes.toFinalJSON());
	}

	/**
	 * M�todo GET: Obtener informaci�n del usuario en sesi�n.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente cliente = AuthManager.getActualClient(request, response);
		if(cliente != null) {
			onAuthenticated__ShowPersonalData(request, response, cliente);
		}
		return;
	}
	
	/**
	 * M�todo DELETE: Eliminar usuario en sesi�n.
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente cliente = AuthManager.getActualClient(request, response);
		if(cliente != null) {
			onAuthenticated__DisableAccount(request, response, cliente);
		}
		return;
	}

	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "GET", "POST", "DELETE" };
	}

}
