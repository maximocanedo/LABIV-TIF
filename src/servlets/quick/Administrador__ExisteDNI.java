package servlets.quick;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Administrador;
import logicImpl.AdministradorLogicImpl;
import max.data.Response;

/**
 * Servlet implementation class Administrador__ExisteDNI
 */
@WebServlet("/api/quick/admin/dni/*")
public class Administrador__ExisteDNI extends servlets.BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public Administrador__ExisteDNI() {
        super();
        // TODO Auto-generated constructor stub
    }
    private AdministradorLogicImpl AL = new AdministradorLogicImpl();

    public void getAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String dni = getPathParameter(request);
    	Response<Administrador> result = AL.DNIExists(dni);
    	response.setStatus(result.http);
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
