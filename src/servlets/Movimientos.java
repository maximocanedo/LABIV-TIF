package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entity.Movimiento;
import logic.MovimientoLogic;
import max.data.LogicResponse;

@WebServlet("/api/movimientos/list")
public class Movimientos extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public Movimientos() {
		super();
	}
	
	private static MovimientoLogic lgm = new MovimientoLogic();
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		LogicResponse<Movimiento> lg = lgm.getAll();
		Gson gson = new Gson();
		
		String finalJSON = gson.toJson(lg);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(finalJSON);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		doGet(request,response);
	}

}