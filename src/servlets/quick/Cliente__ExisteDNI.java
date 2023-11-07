package servlets.quick;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Cliente;
import logicImpl.ClienteLogic;
import max.data.Response;

/**
 * Servlet implementation class Cliente__ExisteDNI
 */
@WebServlet("/api/quick/client/dni/*")
public class Cliente__ExisteDNI extends servlets.BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public Cliente__ExisteDNI() {
        super();
        // TODO Auto-generated constructor stub
    }
    private ClienteLogic AL = new ClienteLogic();

    public void getAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String dni = getPathParameter(request);
    	Response<Cliente> result = AL.DNIExists(dni);
    	response.setStatus(result.http);
    	sendEmptyResponse(response, result.http);
    	return; 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getAdmin(request, response);
	}


	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "GET", "OPTIONS" };
	}

}
