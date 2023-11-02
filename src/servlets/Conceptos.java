package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entity.Concepto;
import logic.ConceptoLogic;
import max.data.LogicResponse;

@WebServlet("/api/conceptos/list")
public class Conceptos extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public Conceptos() {
		super();
	}
	
	private static ConceptoLogic lgcon = new ConceptoLogic();
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		LogicResponse<Concepto> lg = new LogicResponse<>();
		Gson gson = new Gson();
		
		lg = lgcon.getAll();
		String finalJSON = gson.toJson(lg);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(finalJSON);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		doGet(request,response);
	}
}
