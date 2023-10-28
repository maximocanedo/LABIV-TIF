package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Administrador;
import logic.AdministradorLogic;
import logic.AuthManager;
import max.data.Dictionary;
import max.data.LogicResponse;

/**
 * Endpoints para realizar acciones sobre cuentas de administrador AJENAS a la cuenta en sesión.
 * 
 * @author Máximo
 *
 */
@WebServlet("/api/admin/user/*")
public class Administrador__ThirdPerson extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
    private AdministradorLogic AL = new AdministradorLogic();
    
    public Administrador__ThirdPerson() { super(); }
    
    public final String[] allowedMethods = {"GET", "POST"};

    
    public Administrador getAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String username = getPathParameter(request);
    	LogicResponse<Administrador> result = AL.getById(username);
    	if(result.listReturned != null && result.listReturned.size() > 0) {
    		return result.listReturned.get(0);
    	}
    	result.die(false, 404, "No user with that username. ");
    	response.setStatus(404);
    	write(response, result.toFinalJSON());
    	return null; 
    }

    /**
     * Mostrar datos del usuario. Sólo ejecutar tras haber autenticado.
     * @param request
     * @param response
     * @param actualUser
     * @throws IOException
     */
    protected void onAuthenticated__ShowPersonalData(HttpServletRequest request, HttpServletResponse response, Administrador actualUser) throws IOException {
    	write(response, actualUser.toJSON());
    }
    
    /**
     * Dar de baja el usuario. Sólo ejecutar tras haber autenticado.
     * @param request
     * @param response
     * @param actualUser
     * @throws IOException 
     */
    protected void onAuthenticated__DeleteAccount(HttpServletRequest request, HttpServletResponse response, Administrador actualUser) throws IOException {
    	LogicResponse<Administrador> result = AL.delete(actualUser);
    	response.setStatus(result.http);
    	write(response, result.toFinalJSON());
    }
    
    /**
     * Actualizar la contraseña del usuario. Sólo ejecutar tras haber autenticado.
     * @param request
     * @param response
     * @param actualUser
     * @throws IOException
     */
    protected void onAuthenticated__UpdatePassword(HttpServletRequest request, HttpServletResponse response, Administrador actualUser) throws IOException {
    	LogicResponse<Administrador> result = new LogicResponse<Administrador>();
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
     * MÉTODOS: GET, DELETE
     */
    
    /**
     * Método GET. Obtener información sobre la cuenta.
     * 
     * Códigos de respuesta posibles:
     * 200: Acción realizada sin ningún problema.
	 * 401: No inició sesión.
	 * 403: El usuario actual fue deshabilitado, no existe, o no tiene los permisos suficientes para realizar la acción.
	 * 404: El usuario target fue deshabilitado o no existe.
	 * 500: Error en la base de datos.
     * 
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Administrador admin = getAdmin(request, response);
		if(admin != null) {
			onAuthenticated__ShowPersonalData(request, response, admin);
			return;
		} else return;
	}

	/**
	 * Método DELETE. Dar de baja la cuenta de alguien más.
	 * 
     * Códigos de respuesta posibles:
     * 200: Cuenta deshabilitada sin ningún problema.
	 * 401: No inició sesión.
	 * 403: El usuario actual fue deshabilitado, no existe, o no tiene los permisos suficientes para realizar la acción.
	 * 404: El usuario target fue deshabilitado o no existe.
	 * 406: Intento fallido de eliminar al usuario root.
	 * 500: Error en la base de datos.
     * 
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Administrador admin = AuthManager.getActualAdmin(request, response);
		Administrador adminToDelete = getAdmin(request, response);
		if(admin != null && adminToDelete != null) {
			onAuthenticated__DeleteAccount(request, response, adminToDelete);
			
		} 
		return;
	}

	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "GET", "DELETE" };
	}
	
	

	
	
}
