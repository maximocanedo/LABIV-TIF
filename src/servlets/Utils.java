package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import max.data.Dictionary;
import max.data.LogicResponse;

public class Utils {
	
 /*
  * Devuelve el último valor de la ruta dada.
  * Útil para rutas dinámicas.
  *
  */
	public static String getPathParameter(HttpServletRequest request) {
    	String requestURI = request.getRequestURI();
        String[] segments = requestURI.split("/");
        String param = segments[segments.length - 1];
    	return param;
    }
	
/*
 * Devuelve el cuerpo de una petición.
 *
 */
	public static String getBody(HttpServletRequest req) throws IOException {
		// Obtener el cuerpo de la solicitud como texto
		BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream(), "UTF-8"));
	    StringBuilder body = new StringBuilder();
	    String line;

        while ((line = reader.readLine()) != null) {
            body.append(line);
        }

        // Ahora, 'body' contiene el contenido del cuerpo de la solicitud
        String requestBody = body.toString();
        return requestBody;
	}

 /*
  * Escribe (Preferentemente un JSON) en el cuerpo de la respuesta de un servlet.
  *
  */
	public static void write(HttpServletResponse res, String text) throws IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		res.getWriter().append(text);
	}

 /*
  * Obtiene todos los parámetros que se enviaron por el cuerpo de la petición, en formato Dictionary.
  */
	public static Dictionary getParameters(HttpServletRequest req) throws IOException {
		Dictionary parameters = new Dictionary();
		String body = getBody(req);
        Gson gson = new Gson();

        Type type = new TypeToken<Dictionary>(){}.getType();
        try {
        	parameters = gson.fromJson(body, type);
        } catch (JsonSyntaxException e) {
			return null;
		}
		return parameters;
	}
	
	public static void status(HttpServletResponse res, int code) {
		res.setStatus(code);
	}

 /* 
  * Envía un mensaje al usuario. Recibe un estado, y un mensaje. */
	public static void sendMessage(HttpServletResponse res, boolean status, String content) throws IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		LogicResponse<Object> r = new LogicResponse<Object>();
		r.status = status;
		r.message = content;
		res.getWriter().append(r.toFinalJSON());
	}
}
