package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entity.TipoMovimiento;
import logic.TipoMovimientoLogic;
import max.data.LogicResponse;


@WebServlet("/api/tipomovimientos/list")
public class TipoMovimientos extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
	public TipoMovimientos() {
		super();
	}
	
	private static TipoMovimientoLogic lgtpm = new TipoMovimientoLogic();
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		LogicResponse<TipoMovimiento> lg = lgtpm.getAll();
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