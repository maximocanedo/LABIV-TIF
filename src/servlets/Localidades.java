package servlets;

import java.io.IOException;
import java.sql.Types;

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
    		ref = data.ProvinciaDao._model.ref("id_provincia");
    	}}
    );
    // No se compila el modelo porque no se pretenden guardar datos en una base de datos. 
    private IModel parameterModel = new MySQLSchemaModel("localidades", "tif", parameterSchema);
    
   
    
    private LocalidadLogic logic = new LocalidadLogic();
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		LogicResponse<Localidad> ress = new LogicResponse<Localidad>();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		int pid = -1;
		try {
			if(request.getParameter("provinceId") != null) pid = Integer.parseInt(request.getParameter("provinceId"));
		} catch(NumberFormatException e) {
			ress.status = false;
		}
		Dictionary parameters = Dictionary.fromArray("provinceId", pid);
		try {
			boolean b = parameterModel.validate(parameters);
			ress.status = b;
		} catch (SchemaValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ress.die(false, e.getMessage());
			response.getWriter().append(ress.toFinalJSON());
			return;
		}
		if(ress.status) {
			Provincia pp = new Provincia();
			pp.setId(pid);
			ress = logic.filterByProvince(pp);
		}
		// TODO Auto-generated method stub
		response.getWriter().append(gson.toJson(ress));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
