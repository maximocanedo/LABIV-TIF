package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entity.Concepto;
import logicImpl.ConceptoLogicImpl;
import max.data.Response;

@WebServlet("/api/conceptos/list")
public class Conceptos extends BaseServlet {
	
	ConceptoLogicImpl lgCon = new ConceptoLogicImpl();

	private static final long serialVersionUID = 1L;
	
	public Conceptos() {
		super();
	}
	
	private ConceptoLogicImpl lgcon = new ConceptoLogicImpl();
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Response<Concepto> lg = new Response<>();
		lg = lgcon.getAll();
		String finalJSON = lg.toFinalJSON();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(finalJSON);
	}	
	

	@Override
	protected String[] getAllowedMethods() {
		
		return new String[] {"GET"};
	}
}

