package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Administrador;
import logicImpl.AdministradorLogic;
import logicImpl.AuthManager;
import max.data.Dictionary;
import max.data.Response;

/**
 * Endpoints para realizar acciones sobre objetos de tipo Administrador
 * 
 * @author M�ximo
 *
 */
@WebServlet("/api/admin")
public class Administrador__FirstPerson extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
    private AdministradorLogic AL = new AdministradorLogic();
    
    public Administrador__FirstPerson() { super(); }
    
    /**
     * Crear cuenta de administrador. S�lo ejecutar tras haber autenticado.
     * @param request 
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void onAuthenticated__CreateAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtener par�metros
    	Dictionary parameters = getParameters(request);
    	// Si no hay par�metros o est�n mal organizados, enviar un HTTP 400.
		if(parameters == null) {
			die(response, false, 400, "Bad request");
			return;
		}
		// Si todo est� bien, intentar crear cuenta de administrador con los datos recibidos.
		Response<Administrador> finalRes = AL.createAccount(parameters);
		// Enviamos el c�digo recibido y la respuesta recibida.
		response.setStatus(finalRes.status ? 201 : 400);
        write(response, finalRes.toFinalJSON());
	}
    
    /**
     * Mostrar datos del usuario en sesi�n. S�lo ejecutar tras haber autenticado.
     * @param request
     * @param response
     * @param actualUser
     * @throws IOException
     */
    protected void onAuthenticated__ShowPersonalData(HttpServletRequest request, HttpServletResponse response, Administrador actualUser) throws IOException {
    	write(response, actualUser.toJSON());
    }
    
    /**
     * Dar de baja el usuario en sesi�n. S�lo ejecutar tras haber autenticado.
     * @param request
     * @param response
     * @param actualUser
     * @throws IOException 
     */
    protected void onAuthenticated__DeleteAccount(HttpServletRequest request, HttpServletResponse response, Administrador actualUser) throws IOException {
    	Response<Administrador> result = AL.delete(actualUser);
    	response.setStatus(result.http);
    	write(response, result.toFinalJSON());
    }
    
    /**
     * Actualizar la contrase�a del usuario en sesi�n. S�lo ejecutar tras haber autenticado.
     * @param request
     * @param response
     * @param actualUser
     * @throws IOException
     */
    protected void onAuthenticated__UpdatePassword(HttpServletRequest request, HttpServletResponse response, Administrador actualUser) throws IOException {
    	Response<Administrador> result = new Response<Administrador>();
    	Dictionary params = getParameters(request);
    	if(params != null) {
    		result = AL.updatePassword(actualUser, params);
    		response.setStatus(result.http);
    		write(response, result.toFinalJSON());
    		return;
    	}
    	result.message = "There are no parameters. ";
    	response.setStatus(400);
    	write(response, result.toFinalJSON());
    	return;
    }
    
    /**
     * Actualiza los datos editables del usuario en sesi�n. S�lo ejecutar tras haber autenticado.
     * @param request
     * @param response
     * @param actualUser
     * @throws IOException
     */
    protected void onAuthenticated__ModifyAccount(HttpServletRequest request, HttpServletResponse response, Administrador actualUser) throws IOException {
    	Dictionary params = getParameters(request);
    	if(params != null) {
        	Response<Administrador> res = AL.modify(params, actualUser);
        	write(response, res.toFinalJSON());
    	} else {
    		response.setStatus(400);
    	}
    }
    

    /// M�TODOS

    /**
     * M�todo GET. Obtener informaci�n sobre la cuenta actual.
     * 
     * C�digos de respuesta posibles:
     * 200: Muestra informaci�n sobre la cuenta actual.
	 * 401: No inici� sesi�n.
	 * 403: El usuario no tiene los permisos suficientes para crear una cuenta de administrador.
	 * 404: El usuario fue deshabilitado o no existe.
	 * 500: Error en la base de datos.
     * 
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
	 * 
	 * C�digos de respuesta posibles:
	 * 201: Se cre� con �xito la cuenta.
	 * 400: Error de validaci�n, o falta de par�metros requeridos.
	 * 401: No inici� sesi�n.
	 * 403: El usuario fue deshabilitado, no existe, o no tiene el rol requerido para realizar esta acci�n.
	 * 500: Error en la base de datos.
	 * 
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
	 * 
	 * C�digos de respuesta posibles:
	 * 200: Se modific� todo correctamente.
	 * 400: Error de validaci�n, o en los par�metros enviados.
	 * 401: No inici� sesi�n.
	 * 403: El usuario fue deshabilitado, no existe, o no tiene el rol requerido para realizar esta acci�n.
	 * 500: Error en la base de datos.
	 * 
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Administrador admin = AuthManager.getActualAdmin(request, response);
		if(admin != null) {
			onAuthenticated__ModifyAccount(request, response, admin);
		}
		return;
	}
	
	/**
	 * M�todo DELETE. Dar de baja la cuenta actual.
	 * 
	 * C�digos de respuesta posibles:
	 * 200: Se deshabilit� la cuenta correctamente.
	 * 401: No inici� sesi�n.
	 * 403: El usuario fue deshabilitado, no existe, o no tiene el rol requerido para realizar esta acci�n.
	 * 406: Intento fallido de eliminar al usuario root.
	 * 500: Error en la base de datos.
	 * 
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
	 * 
	 * C�digos de respuesta posibles:
	 * 200: Se actualiz� la contrase�a correctamente.
	 * 400: Error de validaci�n, o el par�metro requerido no fue enviado.
	 * 401: No inici� sesi�n.
	 * 403: El usuario fue deshabilitado, no existe, o no tiene el rol requerido para realizar esta acci�n.
	 * 500: Error en la base de datos.
	 * 
	 */
	protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Administrador admin = AuthManager.getActualAdmin(request, response);
		if(admin != null) {
			onAuthenticated__UpdatePassword(request, response, admin);
		}
		return;
	}
	
	/**
	 * M�todo OPTIONS. Informar sobre m�todos disponibles.
	 */
	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Allow", "GET, POST, PUT, DELETE, PATCH, OPTIONS");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

	}
	

	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS" };
	}

}
