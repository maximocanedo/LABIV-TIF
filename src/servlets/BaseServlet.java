package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import max.data.Dictionary;
import max.data.LogicResponse;

public abstract class BaseServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected abstract String[] getAllowedMethods();

	public BaseServlet() {}
	
	protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Implementación por defecto de PATCH
    }
	
	public String getPathParameter(HttpServletRequest request, int index) {
    	String requestURI = request.getRequestURI();
        String[] segments = requestURI.split("/");
        String param = segments[segments.length - 1 - (index >= 0 ? index : 0)];
    	return param;
    }
	
	public String getPathParameter(HttpServletRequest request) {
		return getPathParameter(request, 0);
	}
	
	public String getBody(HttpServletRequest req) throws IOException {
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
	
	
	/**
	 * Obtiene todos los parámetros que se enviaron por el cuerpo de la petición, en formato Dictionary.
	 * @param req
	 * @return
	 * @throws IOException
	 */
	public Dictionary getParameters(HttpServletRequest req) throws IOException {
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
	
	public void die(HttpServletResponse res, boolean status, int code, String content) throws IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		LogicResponse<Object> r = new LogicResponse<Object>();
		r.status = status;
		r.http = code;
		r.message = content;
		res.setStatus(code);
		res.getWriter().append(r.toFinalJSON());
	}
	
	/**
	 * Sobreescribe el método service para que sea compatible con los métodos HTTP PATCH.
	 */
	@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        
        resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
        
        boolean availableHandler = false;
        for(String allowedMethod : getAllowedMethods()) {
        	if(method.equals(allowedMethod)) availableHandler = true;
        }
        if(availableHandler && method.equals("PATCH")) {
        	this.doPatch(req, resp);
        	return;
        }
        if(availableHandler) {
        	super.service(req, resp);
        	return;
        }
        resp.setStatus(405);
        write(resp, "Cannot " + method + " " + req.getRequestURL());
        return;
    }
	public void write(HttpServletResponse res, String text) throws IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		res.getWriter().append(text);
	}

}
