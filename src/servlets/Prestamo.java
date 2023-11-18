package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Cliente;
import logicImpl.AuthManager;
import logicImpl.PrestamoClienteLogicImpl;
import max.Response;

/**
 * Servlet implementation class Prestamo
 */
@WebServlet("/api/loans/*")
public class Prestamo extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prestamo() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    PrestamoClienteLogicImpl logic = new PrestamoClienteLogicImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String strLoanId = getPathParameter(request);
    	Integer loanId = -1;
    	try {
    		loanId = Integer.parseInt(strLoanId);
    	} catch(NumberFormatException e) {
    		response.setStatus(400);
    		e.printStackTrace();
    		return;
    	}
    	if(loanId < 0) {
    		response.setStatus(400);
    		return;
    	}
    	Cliente cliente = AuthManager.getActualClient(request, response);
    	if(cliente == null) {
    		response.setStatus(401);
    		return;
    	}
    	Response<entity.Prestamo> res = (new PrestamoClienteLogicImpl()).getById(loanId);
    	response.setStatus(res.http);
    	if(res.nonEmptyResult()) {
    		entity.Prestamo loan = res.listReturned.get(0);
    		if(loan == null) {
    			response.setStatus(503);
    			return;
    		}
    		if(loan.getCliente().getUsuario().equals(cliente.getUsuario())) {
    	    	write(response, res.toFinalJSON());
    	    	return;
    		} else {
    			response.setStatus(403);
    			return;
    		}    		
    	} else {
    		response.setStatus(404);
    	}
    	    	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    // DEPRECATED. 
	protected void deprecatedGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "GET" };
	}
}
