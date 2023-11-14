package servlets.quick;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Cuenta;
import logicImpl.CuentaLogicImpl;
import max.Response;
import servlets.BaseServlet;

/**
 * Servlet implementation class CuentaPorCBU
 */
@WebServlet("/api/quick/account/data/No/*")
public class CuentaPorNo extends BaseServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see BaseServlet#BaseServlet()
     */
    public CuentaPorNo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String CBU = getPathParameter(request);
		Response<Cuenta> res = (new CuentaLogicImpl()).getById(CBU);
		response.setStatus(res.http);
		if(res.nonEmptyResult()) {
			Cuenta obj = res.listReturned.get(0);
			String ready = obj.toSimpleJsonObject().toString();
			write(response, ready);
		} 
		
		
	}

	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "GET", "DELETE" };
	}

}
