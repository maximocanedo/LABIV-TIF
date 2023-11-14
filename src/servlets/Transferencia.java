package servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Administrador;
import entity.Cliente;
import entity.Movimiento;
import entity.Telefono;
import logicImpl.AdministradorLogicImpl;
import logicImpl.AuthManager;
import logicImpl.TelefonoLogicImpl;
import logicImpl.AuthManager.TokenData;
import logicImpl.ClienteLogicImpl;
import logicImpl.CuentaLogicImpl;
import max.Dictionary;
import max.Response;

/**
 * Servlet implementation class Transferencia
 */
@WebServlet("/api/client/transferencia")
public class Transferencia extends BaseServlet implements Servlet {
	private static final long serialVersionUID = 1L;
   
    public Transferencia() {
        super();
    }
    
    private AdministradorLogicImpl ADlogic = new AdministradorLogicImpl();
    private CuentaLogicImpl CuentaLogic = new CuentaLogicImpl();
    
    /**
     * Método GET: Listar transferencias 
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TokenData td = AuthManager.readToken(request);
		/*if(td == null) {
			response.setStatus(401);  para validar que solo sea admin
			return;
		}*/
		if(td != null) {
			try {
				if(td.role.equals(AuthManager.ADMIN)) {
					Administrador admin = AuthManager.getActualAdmin(request, response);
		 
					if(admin != null) {
						//EN PROCESO
						//Response<Movimiento> res = ADlogic.getAllTransfer();
						//response.setStatus(res.http);
						//write(response, res.toFinalJSON());
					}
				} else {
					Cliente cliente = AuthManager.getActualClient(request, response);
					
					if(cliente != null) {
						//EN PROCESO
						//Response<Movimiento> res = CuentaLogic.getAllForTransfer(cliente);
						//response.setStatus(res.http);
						//write(response, res.toFinalJSON());
					}
				}
			}catch(Exception e) {
				e.getMessage();
			}
		}
		return;
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente cliente = AuthManager.getActualClient(request, response);
		if(cliente == null) return;
		Dictionary parameters = getParameters(request);
		if(parameters == null) {
			die(response, false, 400, "Bad request");
			return;
		}
		
		entity.Transferencia nuevaTransferencia = CuentaLogic.convertTransfer(parameters);
		Response<entity.Transferencia> res = CuentaLogic.insertTransfer(nuevaTransferencia);
		response.setStatus(res.http);
		write(response, res.toFinalJSON());
				
	}

	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "GET", "POST", "OPTIONS" };
	}

}
