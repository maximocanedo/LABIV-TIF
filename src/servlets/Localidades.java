package servlets;

import java.io.IOException;
import java.sql.Types;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import max.data.Dictionary;
import max.data.LogicResponse;
import max.oops.SchemaValidationException;
import max.schema.*;
import entity.*;
import logic.*;
/**
 * Servlet implementation class Localidades
 */
@WebServlet("/api/locality/list")
public class Localidades extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Localidades() {
        super();
        // TODO Auto-generated constructor stub
    }

    private Schema parameterSchema = new Schema(
    	new SchemaProperty("provinceId") {{
    		required = true;
    		type = Types.INTEGER;
    		ref = Provincia._model.ref("id_provincia");
    	}}
    );
    // No se compila el modelo porque no se pretenden guardar datos en una base de datos. 
    private IModel parameterModel = new MySQLSchemaModel("localidades", "tif", parameterSchema);
    
    private static class Response {
    	public boolean status;
    	public String message;
    	public List<Localidad> data;
    	public Response() {}
    }
    
    private LocalidadLogic logic = new LocalidadLogic();
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		Response r = new Response();
		int pid = -1;
		try {
			if(request.getParameter("provinceId") != null) pid = Integer.parseInt(request.getParameter("provinceId"));
		} catch(NumberFormatException e) {
			r.status = false;
		}
		Dictionary parameters = Dictionary.fromArray("provinceId", pid);
		try {
			boolean b = parameterModel.validate(parameters);
			r.status = b;
		} catch (SchemaValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			r.status = false;
			r.message = e.getMessage();
			response.getWriter().append(gson.toJson(r));
			return;
		}
		if(r.status) {
			Provincia pp = new Provincia();
			pp.setId(pid);
			LogicResponse<Localidad> result = logic.filterByProvince(pp);
			r.status = result.status;
			r.message = result.message;
			r.data = result.listReturned;
		}
		// TODO Auto-generated method stub
		response.getWriter().append(gson.toJson(r));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
