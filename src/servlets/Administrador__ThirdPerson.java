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
 * @author Máximo
 *
 */
@WebServlet("/api/admin/user/*")
public class Administrador__ThirdPerson extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private AdministradorLogic AL = new AdministradorLogic();
    
    public Administrador__ThirdPerson() { super(); }
    
    public String getUsername(HttpServletRequest request) {
    	String requestURI = request.getRequestURI();
        String[] segments = requestURI.split("/");
        String username = segments[segments.length - 1];
    	return username;
    }
    public Administrador getAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String username = getUsername(request);
    	LogicResponse<Administrador> result = AL.getById(username);
    	if(result.listReturned != null && result.listReturned.size() > 0) {
    		return result.listReturned.get(0);
    	}
    	result.die(false, 404, "No user with that username. ");
    	Utils.status(response, 404);
    	Utils.write(response, result.toFinalJSON());
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
    	Utils.write(response, actualUser.toJSON());
    }
    
    /**
     * Dar de baja el usuario. Sólo ejecutar tras haber autenticado.
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
     * Actualizar la contraseña del usuario. Sólo ejecutar tras haber autenticado.
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
     * MÉTODOS: GET, DELETE
     */
    
    /**
     * Método GET. Obtener información sobre la cuenta.
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
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Administrador admin = AuthManager.getActualAdmin(request, response);
		Administrador adminToDelete = getAdmin(request, response);
		if(admin != null && adminToDelete != null) {
			onAuthenticated__DeleteAccount(request, response, adminToDelete);
			
		} 
		return;
	}
	
	/**
	 * Método POST. Sin función en este contexto.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utils.status(response, 405);
		Utils.write(response, "Cannot POST. ");
		return;
	}
	
	/**
	 * Método PUT. Sin función en este contexto.
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utils.status(response, 405);
		response.getWriter().append("Cannot PUT. ");
		return;
	}
	
	/**
	 * Método PATCH. Sin función en este contexto.
	 */
	protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utils.status(response, 405);
		Utils.write(response, "Cannot PATCH.");
		return;
	}

	/**
	 * Sobreescribe el método service para que sea compatible con los métodos HTTP PATCH.
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
