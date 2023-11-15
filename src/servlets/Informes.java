package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logicImpl.MovimientoLogicImpl;

public class Informes extends BaseServlet implements Servlet{
	
	private static final long serialVersionUID = 1L;

	public Informes() {
		super();
	}

	private MovimientoLogicImpl ML = new MovimientoLogicImpl();
	
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        MovimientoLogicImpl logic = new MovimientoLogicImpl();
        ArrayList<Integer> data = logic.getInforme();

        request.setAttribute("informe", data);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("reporteTransacciones.jsp");
        dispatcher.forward(request, response);
        
    }
	
	
	@Override
	protected String[] getAllowedMethods() {
		// TODO Auto-generated method stub
		return null;
	}

}
