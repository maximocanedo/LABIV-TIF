package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Administrador;
import entity.Cliente;
import logicImpl.AuthManager;
import logicImpl.ClienteLogicImpl;
import logicImpl.AuthManager.TokenData;
import max.data.Dictionary;
import max.data.Response;

/**
 * Servlet implementation class Cliente__ThirdPerson
 */
@WebServlet("/api/client/user/*")
public class Cliente__ThirdPerson extends servlets.BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cliente__ThirdPerson() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private ClienteLogicImpl CL = new ClienteLogicImpl();
   // private AdministradorLogic AL = new AdministradorLogic();

    /**
     * Obtiene el cliente a partir del usuario pasado por parámetro en la URL.
     */
    public Cliente getClient(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String username = getPathParameter(request);
    	Response<Cliente> result = CL.getById(username);
    	if(result.listReturned != null && result.listReturned.size() > 0) {
    		return result.listReturned.get(0);
    	}
    	result.die(false, 404, "No user with that username. ");
    	response.setStatus(404);
    	write(response, result.toFinalJSON());
    	return null; 
    }
    
    /**
     * Muestra información completa del cliente. Sólo ejecutar post-autenticación.
     * Accesible para administradores.
     * @param req
     * @param res
     * @throws IOException
     */
    public void admin__getClient(HttpServletRequest req, HttpServletResponse res) throws IOException {
    	AuthManager.getActualAdmin(req, res);
    	Cliente cliente = getClient(req, res);
    	if(cliente != null) {
    		res.setStatus(200);
    		String result = cliente.toJSON();
    		write(res, result);
    	}
    	return;
    	
    }
    
    /**
     * Muestra información básica y parcial del cliente. Sólo ejecutar post-autenticación.
     * Accesible para clientes.
     * @param req
     * @param res
     * @throws IOException
     */
    public void client__getClient(HttpServletRequest req, HttpServletResponse res) throws IOException {
    	AuthManager.getActualClient(req, res);
		Cliente cliente = getClient(req, res);
		if(cliente != null) {
			res.setStatus(200);
			String result = cliente.toMinimumDictionary().toJSON();
			write(res, result);
		}
		return;
    }
    
    /**
     * Elimina el cliente. Sólo ejecutar post-autenticación.
     * @param request
     * @param response
     * @param cliente
     * @throws ServletException
     * @throws IOException
     */
    protected void onAuthenticated__DisableAccount(HttpServletRequest request, HttpServletResponse response, Cliente cliente) throws ServletException, IOException {
    	Response<Cliente> result = CL.delete(cliente);
    	response.setStatus(result.http);
    	write(response, result.toFinalJSON());
    }
    
    /**
     * Actualiza los datos editables del usuario en sesión. Sólo ejecutar tras haber autenticado.
     * @param request
     * @param response
     * @param actualUser
     * @throws IOException
     */
    protected void onAuthenticated__ModifyAccount(HttpServletRequest request, HttpServletResponse response, Cliente actualUser) throws IOException {
    	Dictionary params = getParameters(request);
    	if(params != null) {
        	Response<Cliente> res = CL.modify(params, actualUser);
        	write(response, res.toFinalJSON());
    	} else {
    		response.setStatus(400);
    	}
    }
    
    // MÉTODOS
    
    /**
     * Método GET: Muestra información sobre el cliente en cuestión.
     * Si el usuario en sesión es cliente, muestra algunos datos.
     * Si el usuario en sesión es administrador, muestra más datos.
     * 
     * Salidas posibles:
     * 
     * 200: OK
     * 401: No hay usuario en sesión.
     * 403: Prohibido el acceso.
     * 404: No hay cliente con el usuario especificado.
     * 500: Error en la base de datos.
     * 
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Verificar si es cliente o admin */
		TokenData td = AuthManager.readToken(request);
		if(td != null) {
			switch(td.role) {
			case AuthManager.ADMIN:
				admin__getClient(request, response);
				break;
			case AuthManager.CLIENT:
				client__getClient(request, response);
				break;
			default:
				response.setStatus(403);
				break;
			}
		} else {
			response.setStatus(401);
		}
		return;
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
		Cliente cliente = getClient(request, response);
		if(admin != null) {
			onAuthenticated__ModifyAccount(request, response, cliente);
		}
		return;
	}

	/**
	 * Método DELETE: Deshabilita el cliente en cuestión.
	 * Sólo accesible para administradores.
	 * 
	 * Salidas posibles:
     * 200: Cuenta deshabilitada sin ningún problema.
	 * 401: No inició sesión.
	 * 403: El administrador fue deshabilitado, no existe, o no tiene los permisos suficientes para realizar la acción.
	 * 404: El cliente target fue deshabilitado o no existe.
	 * 500: Error en la base de datos.
	 * 
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Administrador actualAdmin = AuthManager.getActualAdmin(request, response);
		Cliente cliente = getClient(request, response);
		if(actualAdmin != null) {
			onAuthenticated__DisableAccount(request, response, cliente);
		}
		return;
	}

	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "GET", "DELETE", "PUT" };
	}

}
