package servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Administrador;
import entity.Cliente;
import entity.Paginator;
import entity.filter.ClienteFilter;
import logicImpl.ClienteLogicImpl;
import max.Response;

/**
 * Servlet implementation class Clientes
 */
@WebServlet("/api/clients")
public class Clientes extends BaseServlet implements Servlet {
	private static final long serialVersionUID = 1L;

    public Clientes() {
        super();
    }

    
    protected ClienteLogicImpl CL = new ClienteLogicImpl();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Administrador admin = new Administrador(); // AuthManager.getActualAdmin(request, response);
		ClienteFilter filter = new ClienteFilter();
		filter.q = request.getParameter("q");
		filter.provinceId = request.getParameter("province");
		filter.localtyId = request.getParameter("localty");
		filter.sex = request.getParameter("sex");
		String s = request.getParameter("status");		
		filter.status = s == null ? (s == "1" || !(s == "0")) : null;
		filter.countryId = request.getParameter("country");
		if(admin != null) {
			Paginator paginator = getPaginator(request);
			Response<Cliente> gac = CL.search(filter, paginator);
			response.setStatus(gac.http);
			write(response, gac.toFinalJSON());
		}
		return;
	}

	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "GET", "OPTIONS" };
	}

}
