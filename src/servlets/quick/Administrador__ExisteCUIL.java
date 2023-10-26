package servlets.quick;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Administrador;
import logic.AdministradorLogic;
import max.data.LogicResponse;
import servlets.Utils;

/**
 * Servlet implementation class Administrador__ExisteCUIL
 */
@WebServlet("/api/quick/admin/cuil/*")
public class Administrador__ExisteCUIL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Administrador__ExisteCUIL() {
        super();
        // TODO Auto-generated constructor stub
    }

    private AdministradorLogic AL = new AdministradorLogic();

    public void getAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String dni = Utils.getPathParameter(request);
    	LogicResponse<Administrador> result = AL.CUILExists(dni);
    	Utils.status(response, result.http);
    	return; 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getAdmin(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utils.status(response, 405);
	}

}
