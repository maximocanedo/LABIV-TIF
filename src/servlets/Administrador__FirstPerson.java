package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.AdministradorDao;
import entity.Administrador;
import logic.AdministradorLogic;
import logic.AuthManager;
import max.data.Dictionary;
import max.data.LogicResponse;
import max.schema.Schema;
import servlets.Utils;

/**
 * Endpoints para realizar acciones sobre objetos de tipo Administrador
 * 
 * @author M�ximo
 *
 */
@WebServlet("/api/admin")
public class Administrador__FirstPerson extends HttpServlet {
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
     * Actualizar la contrase�a del usuario en sesi�n. S�lo ejecutar tras haber autenticado.
     * @param request
     * @param response
     * @param actualUser
     * @throws IOException
     */
    protected void onAuthenticated__UpdatePassword(HttpServletRequest request, HttpServletResponse response, Administrador actualUser) throws IOException {
    	LogicResponse<Administrador> result = new LogicResponse<Administrador>();
    	Dictionary params = Utils.getParameters(request);
    	if(params != null) {
    		result = AL.updatePassword(actualUser, params);
    		Utils.status(response, result.http);
    		Utils.write(response, result.toFinalJSON());
    		return;
    	}
    	result.message = "There are no parameters. ";
    	Utils.status(response, 400);
    	Utils.write(response, result.toFinalJSON());
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
    	Dictionary params = Utils.getParameters(request);
    	if(params != null) {
        	LogicResponse<Administrador> res = AL.modify(params, actualUser);
        	Utils.write(response, res.toFinalJSON());
    	} else {
    		Utils.status(response, 400);
    	}
    }
    
    /**
     * M�TODOS FUNCIONANDO: GET, POST, DELETE, PATCH
     * M�TODOS POR IMPLEMENTAR: PUT (Complicado porque implica traer todos los datos.)
     * Idea: Obtener Dictionary completo y sobreescribir valores.
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
		Administrador admin = AuthManager.getActualAdmin(request, response);
		if(admin != null) {
			onAuthenticated__ModifyAccount(request, response, admin);
		}
		return;
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
		Administrador admin = AuthManager.getActualAdmin(request, response);
		if(admin != null) {
			onAuthenticated__UpdatePassword(request, response, admin);
		}
		return;
	}

	/**
	 * Sobreescribe el m�todo service para que sea compatible con los m�todos HTTP PATCH.
	 */
	@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        if (!method.equals("PATCH")) {
            super.service(req, resp);
            return;
        }
        this.doPatch(req, resp);
    }
	
}
