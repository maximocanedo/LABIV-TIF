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
import logicImpl.CuentaLogic;
import max.data.*;

/**
 * Servlet implementation class Cuentas
 */
@WebServlet("/api/accounts")
public class Cuentas extends BaseServlet implements Servlet {
	private static final long serialVersionUID = 1L;
   
    public Cuentas() {
        super();
    }
    
    private CuentaLogic CL = new CuentaLogic();
    
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	protected String[] getAllowedMethods() {
		// TODO Auto-generated method stub
		return null;
	}

}
