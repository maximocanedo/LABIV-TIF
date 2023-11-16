package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Provincia;
import logicImpl.MovimientoLogicImpl;
import logicImpl.ProvinciaLogicImpl;
import max.Response;


@WebServlet("/informes58")
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
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("administradores/reporteTransacciones.jsp");
        dispatcher.forward(request, response);
        
    }
	
	protected void cargarProvincias(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProvinciaLogicImpl logic = new ProvinciaLogicImpl();
		Response<Provincia> data = logic.getAll();
		ArrayList<Provincia> list = (ArrayList<Provincia>) data.listReturned;
		
		request.setAttribute("provincias", list);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("administradores/MovimientosProvincia.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "GET", "OPTIONS" };
	}

}
