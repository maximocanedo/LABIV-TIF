package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.TipoMovimiento;
import logicImpl.TipoMovimientoLogicImpl;
import max.Response;


@WebServlet("/api/tipomovimientos/list")
public class TipoMovimientos extends BaseServlet {
	
private static final long serialVersionUID = 1L;
	
	public TipoMovimientos() {
		super();
	}
	
	private TipoMovimientoLogicImpl lgtpm = new TipoMovimientoLogicImpl();
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Response<TipoMovimiento> lg = lgtpm.getAll();
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