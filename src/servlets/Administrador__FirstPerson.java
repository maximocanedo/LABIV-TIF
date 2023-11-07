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
 * @author Máximo
 *
 */
@WebServlet("/api/admin")
public class Administrador__FirstPerson extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
    private AdministradorLogic AL = new AdministradorLogic();
    
    public Administrador__FirstPerson() { super(); }
    
    /**
     * Crear cuenta de administrador. Sólo ejecutar tras haber autenticado.
     * @param request 
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void onAuthenticated__CreateAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtener parámetros
    	Dictionary parameters = getParameters(request);
    	// Si no hay parámetros o están mal organizados, enviar un HTTP 400.
		if(parameters == null) {
			die(response, false, 400, "Bad request");
			return;
		}
		// Si todo está bien, intentar crear cuenta de administrador con los datos recibidos.
		Response<Administrador> finalRes = AL.createAccount(parameters);
		// Enviamos el código recibido y la respuesta recibida.
		response.setStatus(finalRes.status ? 201 : 400);
        write(response, finalRes.toFinalJSON());
	}
    
    /**
     * Mostrar datos del usuario en sesión. Sólo ejecutar tras haber autenticado.
     * @param request
     * @param response
     * @param actualUser
     * @throws IOException
     */
    protected void onAuthenticated__ShowPersonalData(HttpServletRequest request, HttpServletResponse response, Administrador actualUser) throws IOException {
    	write(response, actualUser.toJSON());
    }
    
    /**
     * Dar de baja el usuario en sesión. Sólo ejecutar tras haber autenticado.
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
     * Actualizar la contraseña del usuario en sesión. Sólo ejecutar tras haber autenticado.
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
     * Actualiza los datos editables del usuario en sesión. Sólo ejecutar tras haber autenticado.
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
    

    /// MÉTODOS

    /**
     * Método GET. Obtener información sobre la cuenta actual.
     * 
     * Códigos de respuesta posibles:
     * 200: Muestra información sobre la cuenta actual.
	 * 401: No inició sesión.
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
	 * Método POST. Crear cuenta de administrador.
	 * 
	 * Códigos de respuesta posibles:
	 * 201: Se creó con éxito la cuenta.
	 * 400: Error de validación, o falta de parámetros requeridos.
	 * 401: No inició sesión.
	 * 403: El usuario fue deshabilitado, no existe, o no tiene el rol requerido para realizar esta acción.
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
	 * Método PUT. Modificar datos de la cuenta actual.
	 * 
	 * Códigos de respuesta posibles:
	 * 200: Se modificó todo correctamente.
	 * 400: Error de validación, o en los parámetros enviados.
	 * 401: No inició sesión.
	 * 403: El usuario fue deshabilitado, no existe, o no tiene el rol requerido para realizar esta acción.
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
	 * Método DELETE. Dar de baja la cuenta actual.
	 * 
	 * Códigos de respuesta posibles:
	 * 200: Se deshabilitó la cuenta correctamente.
	 * 401: No inició sesión.
	 * 403: El usuario fue deshabilitado, no existe, o no tiene el rol requerido para realizar esta acción.
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
	 * Método PATCH. Actualizar contraseña del usuario actual.
	 * 
	 * Códigos de respuesta posibles:
	 * 200: Se actualizó la contraseña correctamente.
	 * 400: Error de validación, o el parámetro requerido no fue enviado.
	 * 401: No inició sesión.
	 * 403: El usuario fue deshabilitado, no existe, o no tiene el rol requerido para realizar esta acción.
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
	 * Método OPTIONS. Informar sobre métodos disponibles.
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
