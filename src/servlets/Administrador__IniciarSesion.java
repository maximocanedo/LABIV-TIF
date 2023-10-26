package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import entity.Administrador;
import logic.AdministradorLogic;
import logic.AuthManager;
import max.data.Dictionary;
import max.data.LogicResponse;

/**
 * Servlet implementation class Administrador__IniciarSesion
 */
@WebServlet("/api/admin/login")
public class Administrador__IniciarSesion extends HttpServlet {
private AdministradorLogic AL = new AdministradorLogic();
	
	protected String getBody(HttpServletRequest req) throws IOException {
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
	protected Dictionary getParameters(HttpServletRequest req) throws IOException {
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
	
	protected void status(HttpServletResponse res, int code) {
		res.setStatus(code);
	}
	protected void sendMessage(HttpServletResponse res, boolean status, String content) throws IOException {
		LogicResponse<Object> r = new LogicResponse<Object>();
		r.status = status;
		r.message = content;
		res.getWriter().append(r.toFinalJSON());
	}
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Administrador__IniciarSesion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Cannot GET ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dictionary parameters = getParameters(request);
		LogicResponse<Administrador> finalRes = AL.login(parameters);
		if(finalRes.status) {
			String tk = finalRes.eField;
			if(tk != null) {
				AuthManager.sendToken(response, tk);
			}
		}
		status(response, finalRes.http);
        response.getWriter().append(finalRes.toFinalJSON());
	}

}
