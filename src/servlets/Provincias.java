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
public class Provincias extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public Provincias() {
        super();
    }
    private static ProvinciaLogic logic = new ProvinciaLogic();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LogicResponse<Provincia> res = logic.getAll();
		Gson gson = new Gson();
		String finalJSON = gson.toJson(res.listReturned);
		response.getWriter().append(finalJSON);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
