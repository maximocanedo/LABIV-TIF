package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entity.*;
import logicImpl.*;
import max.data.*;

@WebServlet("/api/provinces/list")
public class Provincias extends BaseServlet {
	private static final long serialVersionUID = 1L;
     
    public Provincias() {
        super();
    }
    private static ProvinciaLogicImpl logic = new ProvinciaLogicImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Response<Provincia> res = logic.getAll();
		String finalJSON = res.toFinalJSON();
		write(response, finalJSON);
	}




	@Override
	protected String[] getAllowedMethods() {
		return new String[] {"GET"};
	}

}
