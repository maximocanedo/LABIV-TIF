package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entity.*;
import logic.*;
import max.data.*;

@WebServlet("/api/provinces/list")
public class Provincias extends BaseServlet {
	private static final long serialVersionUID = 1L;
     
    public Provincias() {
        super();
    }
    private static ProvinciaLogic logic = new ProvinciaLogic();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LogicResponse<Provincia> res = logic.getAll();
		String finalJSON = new Gson().toJson(res);
		write(response, finalJSON);
	}




	@Override
	protected String[] getAllowedMethods() {
		return new String[] {"GET"};
	}

}
