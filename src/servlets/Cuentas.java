package servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Cliente;
import entity.Cuenta;
import logicImpl.AuthManager;
import logicImpl.CuentaLogicImpl;
import max.Dictionary;
import max.Response;

/**
 * Servlet implementation class Cuentas
 */
@WebServlet("/api/accounts")
public class Cuentas extends BaseServlet implements Servlet {
	private static final long serialVersionUID = 1L;
   
    public Cuentas() {
        super();
    }
    
    private CuentaLogicImpl CL = new CuentaLogicImpl();
    
    /**
     * Método GET: Listar cuentas (Admin only)
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente cliente = AuthManager.getActualClient(request, response);
		if(cliente != null) {
			Response<Cuenta> res = CL.getAllFor(cliente);
			response.setStatus(res.http);
			write(response, res.toFinalJSON());
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dictionary parameters = getParameters(request);
		if(parameters == null) {
			die(response, false, 400, "Bad request");
			return;
		}
		Cuenta nuevaCuenta= CL.convert(parameters);
		CL.insert(nuevaCuenta);
				
	}

	@Override
	protected String[] getAllowedMethods() {
		// TODO Auto-generated method stub
		return null;
	}

}
