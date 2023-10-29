package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Administrador;
import entity.Cliente;
import logic.AuthManager;
import logic.AuthManager.TokenData;
import logic.ClienteLogic;
import max.data.LogicResponse;

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
    
    private ClienteLogic CL = new ClienteLogic();

    /**
     * Obtiene el cliente a partir del usuario pasado por par�metro en la URL.
     */
    public Cliente getClient(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String username = getPathParameter(request);
    	LogicResponse<Cliente> result = CL.getById(username);
    	if(result.listReturned != null && result.listReturned.size() > 0) {
    		return result.listReturned.get(0);
    	}
    	result.die(false, 404, "No user with that username. ");
    	response.setStatus(404);
    	write(response, result.toFinalJSON());
    	return null; 
    }
    
    /**
     * Muestra informaci�n completa del cliente. S�lo ejecutar post-autenticaci�n.
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
     * Muestra informaci�n b�sica y parcial del cliente. S�lo ejecutar post-autenticaci�n.
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
     * Elimina el cliente. S�lo ejecutar post-autenticaci�n.
     * @param request
     * @param response
     * @param cliente
     * @throws ServletException
     * @throws IOException
     */
    protected void onAuthenticated__DisableAccount(HttpServletRequest request, HttpServletResponse response, Cliente cliente) throws ServletException, IOException {
    	LogicResponse<Cliente> result = CL.delete(cliente);
    	response.setStatus(result.http);
    	write(response, result.toFinalJSON());
    }
    
    // M�TODOS
    
    /**
     * M�todo GET: Muestra informaci�n sobre el cliente en cuesti�n.
     * Si el usuario en sesi�n es cliente, muestra algunos datos.
     * Si el usuario en sesi�n es administrador, muestra m�s datos.
     * 
     * Salidas posibles:
     * 
     * 200: OK
     * 401: No hay usuario en sesi�n.
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
	 * M�todo DELETE: Deshabilita el cliente en cuesti�n.
	 * S�lo accesible para administradores.
	 * 
	 * Salidas posibles:
     * 200: Cuenta deshabilitada sin ning�n problema.
	 * 401: No inici� sesi�n.
	 * 403: El administrador fue deshabilitado, no existe, o no tiene los permisos suficientes para realizar la acci�n.
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
		return new String[] { "GET", "DELETE" };
	}

}
