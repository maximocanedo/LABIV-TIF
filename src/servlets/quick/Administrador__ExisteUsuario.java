package servlets.quick;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Administrador;
import logic.AdministradorLogic;
import max.data.LogicResponse;

/**
 * Servlet implementation class Administrador__ExisteUsuario
 */
@WebServlet("/api/quick/admin/u/*")
public class Administrador__ExisteUsuario extends servlets.BaseServlet {
	private static final long serialVersionUID = 1L;
       

    public Administrador__ExisteUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }
    private AdministradorLogic AL = new AdministradorLogic();

    
    public String getUsername(HttpServletRequest request) {
    	String requestURI = request.getRequestURI();
        String[] segments = requestURI.split("/");
        String username = segments[segments.length - 1];
    	return username;
    }
    
    public void getAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String username = getUsername(request);
    	LogicResponse<Administrador> result = AL.exists(username);
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
