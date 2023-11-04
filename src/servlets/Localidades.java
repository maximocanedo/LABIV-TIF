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
import max.data.Response;
import max.oops.SchemaValidationException;
import max.schema.*;
import entity.*;
import logic.*;
/**
 * Servlet implementation class Localidades
 */
@WebServlet("/api/locality/list/*")
public class Localidades extends BaseServlet {
	
	// Serial Version
	private static final long serialVersionUID = 1L;
	// Constante que representa el nombre del campo de ID de Provincia.
    public final String PROVINCE_ID = "provinceId";
    
    // Esquema para los parámetros
	private Schema parameterSchema = new Schema(
	    	new SchemaProperty(PROVINCE_ID) {{
	    		required = true;
	    		type = Types.INTEGER;
	    		min = 0;
	    		ref = data.ProvinciaDao._model.ref("id_provincia");
	    	}}
	    );
	
    // No se compila el modelo porque no se pretenden guardar datos en una base de datos. 
    private IModel parameterModel = new MySQLSchemaModel("localidades", "tif", parameterSchema);
    // Acceso a métodos de lógica.
    private LocalidadLogic logic = new LocalidadLogic();
    // Acceso a métodos de manejo de JSON.
    private Gson gson = new Gson();
    
    // Constructor
    public Localidades() {
        super();
    }
    
    /**
     * Transforma los parámetros de la ruta dinámica en parámetros aceptables por el Servlet.
     * @param request El objeto Request
     * @return Dictionary
     */
    private Dictionary getParams(HttpServletRequest request) {
    	String requestURI = request.getRequestURI();
        String[] segments = requestURI.split("/");
        String provinceId = segments[segments.length - 1];
        int pid = -1;
        try {
        	pid = Integer.parseInt(provinceId);
        } catch(NumberFormatException e) {
        	pid = -1;
        	e.printStackTrace();
        }
    	return Dictionary.fromArray(
    		PROVINCE_ID, pid
    	);
    }

    /**
     * Configura la salida del servlet, como un JSON en UTF-8.
     * @param res Objeto Respuesta.
     */
    protected void configureResponse(HttpServletResponse res) {
    	res.setContentType("application/json");
    	res.setCharacterEncoding("UTF-8");
    }
    
	/**
	 * Acción que se ejecutará al recibir una petición GET.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Creamos el objeto que vamos a enviar como respuesta, y el código HTTP.
		Response<Localidad> res = new Response<Localidad>();
		int statusCode = 200;
		// Configuramos la salida como JSON y en UTF-8
		configureResponse(response);
		// Obtenemos los parámetros.
		Dictionary parameters = getParams(request);
		// Validamos
		try {
			res.status = parameterModel.validate(parameters);
			if(res.status) {
				int provinceId = parameters.$(PROVINCE_ID);
				Provincia prov = new Provincia();
				prov.setId(provinceId);
				res = logic.filterByProvince(prov);
				statusCode = res.status ? 200 : 500;
			}
		} catch(SchemaValidationException e) {
			// Si hay un error de validación:
			e.printStackTrace();
			// La respuesta tendrá código false, y el mensaje de error.
			res.die(false, e.getMessage());
			statusCode = 400;
		}
		// Enviamos la respuesta.
		response.setStatus(statusCode);
		write(response, new Gson().toJson(res));
	}



	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "GET" };
	}

}
