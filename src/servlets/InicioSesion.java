package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Cliente;
import logicImpl.ClienteLogicImpl;

/**
 * Servlet implementation class InicioSesion
 */
@WebServlet("/InicioSesion")
public class InicioSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InicioSesion() {
        super();
        // TODO Auto-generated constructor stub
    }

    ClienteLogicImpl logic = new  ClienteLogicImpl();
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("btnInicioSesion") != null) {
	        String rol = request.getParameter("user-type").toString();
	        if (rol.equals("cliente")) {
	            String usuario = request.getParameter("inputUsuario");
	            String clave = request.getParameter("inputClave");

	            Cliente cliente = logic.loginCliente(usuario, clave);

	            if (cliente != null && cliente.isEstado()) {
	            	 request.setAttribute("usuario", cliente.getUsuario());
	            	 request.setAttribute("nombre", cliente.getNombre());
		             RequestDispatcher dispatcher = request.getRequestDispatcher("/clientes/Index.jsp");
		             dispatcher.forward(request, response);
	            } else {
	                request.setAttribute("error", "Error al iniciar sesion, el usuario no existe o fue dado de baja");
	                RequestDispatcher dispatcher = request.getRequestDispatcher("/clientes/inicioSesion.jsp");
	                dispatcher.forward(request, response);
	            }
	        } else {
	        }
	    }

	}

}
