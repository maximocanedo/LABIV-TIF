package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entity.Pais;
import logicImpl.PaisLogicImpl;
import max.Response;

/**
 * Servlet implementation class Pais
 */
@WebServlet("/api/countries/list")
public class Paises extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Paises() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    PaisLogicImpl logic = new PaisLogicImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Response<Pais> res = logic.getAll();
		String json = res.toFinalJSON();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json);
	}

	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "GET" };
	}



}
