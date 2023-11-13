package servlets.quick;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Cuenta;
import logicImpl.CuentaLogicImpl;
import max.Response;


@WebServlet("/api/quick/accounts/cbu/*")
public class Cuentas__ExisteCBU extends servlets.BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public Cuentas__ExisteCBU() {
		super();
	}

	private CuentaLogicImpl AL = new CuentaLogicImpl();

    public void getAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String CBU = getPathParameter(request);
    	Response<Cuenta> result = AL.CBUExists(CBU);
    	sendEmptyResponse(response, result.http);
    	return; 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getAdmin(request, response);
	}


	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "GET" };
	}

}