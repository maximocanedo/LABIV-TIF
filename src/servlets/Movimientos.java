package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Movimiento;
import logicImpl.MovimientoLogicImpl;
import max.Response;

@WebServlet("/api/movimientos/list")
public class Movimientos extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public Movimientos() {
		super();
	}
	
	private static MovimientoLogicImpl lgm = new MovimientoLogicImpl();
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Response<Movimiento> lg = lgm.getAll();
		
		String finalJSON = lg.toFinalJSON();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(finalJSON);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		doGet(request,response);
	}

}