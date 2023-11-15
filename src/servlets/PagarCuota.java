package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Administrador;
import entity.Cliente;
import entity.Paginator;
import logicImpl.AuthManager;
import logicImpl.SolicitudPrestamoLogicImpl;
import logicImpl.AuthManager.TokenData;
import max.Dictionary;
import max.Response;

/**
 * Servlet implementation class PagarCuota
 */
@WebServlet("/api/client/pagarcuota")
public class PagarCuota extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PagarCuota() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private SolicitudPrestamoLogicImpl logic = new SolicitudPrestamoLogicImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		TokenData td = AuthManager.readToken(request);
		if(td != null) {

			Cliente cliente = AuthManager.getActualClient(request, response);
			if(cliente!=null) {
				Dictionary parameters = getParameters(request);
				if(parameters == null) {
					die(response, false, 400, "Bad request");
					return;
				}
				
				entity.SolicitudPrestamo pagarCuota = logic.convert(parameters);
				pagarCuota.setCliente(cliente);
				Response<entity.SolicitudPrestamo> res = logic.PagarCuota(pagarCuota);
				response.setStatus(res.http);
				write(response, res.toFinalJSON());
				return;
			} else {
				response.setStatus(403);
				return;
				
			}
		} else {
			response.setStatus(401);
		}
		return;
	}


	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "POST" };
	}
}