package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Administrador;
import logic.AdministradorLogic;
import logic.AuthManager;
import max.data.Dictionary;
import max.data.LogicResponse;

import servlets.Utils;

/**
 * Endpoints para realizar acciones sobre objetos de tipo Administrador
 * 
 * @author M�ximo
 *
 */
@WebServlet("/api/admin")
public class Administrador_Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private AdministradorLogic AL = new AdministradorLogic();
    
    public Administrador_Main() { super(); }
    
    /**
     * Crear cuenta de administrador. S�lo ejecutar tras haber autenticado.
     * @param request 
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void onAuthenticated__CreateAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtener par�metros
    	Dictionary parameters = Utils.getParameters(request);
    	// Si no hay par�metros o est�n mal organizados, enviar un HTTP 400.
		if(parameters == null) {
			Utils.status(response, 400);
			Utils.sendMessage(response, false, "Bad request");
			return;
		}
		// Si todo est� bien, intentar crear cuenta de administrador con los datos recibidos.
		LogicResponse<Administrador> finalRes = AL.createAccount(parameters);
		// Enviamos el c�digo recibido y la respuesta recibida.
		Utils.status(response, finalRes.status ? 201 : 400);
        Utils.write(response, finalRes.toFinalJSON());
	}
    
    /**
     * Mostrar datos del usuario en sesi�n. S�lo ejecutar tras haber autenticado.
     * @param request
     * @param response
     * @param actualUser
     * @throws IOException
     */
    protected void onAuthenticated__ShowPersonalData(HttpServletRequest request, HttpServletResponse response, Administrador actualUser) throws IOException {
    	Utils.write(response, actualUser.toJSON());
    }
    
    /**
     * Dar de baja el usuario en sesi�n. S�lo ejecutar tras haber autenticado.
     * @param request
     * @param response
     * @param actualUser
     * @throws IOException 
     */
    protected void onAuthenticated__DeleteAccount(HttpServletRequest request, HttpServletResponse response, Administrador actualUser) throws IOException {
    	LogicResponse<Administrador> result = AL.deleteOne(actualUser);
    	Utils.status(response, result.http);
    	Utils.write(response, result.toFinalJSON());
    }
    
    
    
    
    /**
     * M�TODOS FUNCIONANDO: GET, POST
     * M�TODOS EN PROCESO: DELETE
     * M�TODOS POR IMPLEMENTAR: PATCH, PUT
     */
    
    /**
     * M�todo GET. Obtener informaci�n sobre la cuenta actual.
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Administrador admin = AuthManager.getActualAdmin(request, response);
		if(admin != null) {
			onAuthenticated__ShowPersonalData(request, response, admin);
			return;
		} else return;
	}

	/**
	 * M�todo POST. Crear cuenta de administrador.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Administrador admin = AuthManager.getActualAdmin(request, response);
		if(admin != null) {
			onAuthenticated__CreateAccount(request, response);
			return;
		} else return;
	}
	
	/**
	 * M�todo PUT. Modificar datos de la cuenta actual.
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("No handler for PUT method. ");
	}
	
	/**
	 * M�todo DELETE. Dar de baja la cuenta actual.
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Administrador admin = AuthManager.getActualAdmin(request, response);
		if(admin != null) {
			onAuthenticated__DeleteAccount(request, response, admin);
		}
		return;
	}
	
	/**
	 * M�todo PATCH. Actualizar contrase�a del usuario actual.
	 */
	protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("PATCH at: ").append(request.getContextPath());
	}

}
