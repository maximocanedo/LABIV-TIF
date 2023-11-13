package servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logicImpl.AuthManager;
import logicImpl.AuthManager.TokenData;
import logicImpl.*;
import max.*;
import entity.*;

/**
 * Servlet implementation class Cuenta__Singular
 */
@WebServlet("/api/accounts/*")
public class Cuenta__Singular extends BaseServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see BaseServlet#BaseServlet()
     */
    public Cuenta__Singular() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Verificar si es cliente o admin */
		String num = getPathParameter(request);
		Response<Cuenta> cnt = (new CuentaLogicImpl()).getById(num);
		TokenData td = AuthManager.readToken(request);
		if(td != null) {
			switch(td.role) {
			case AuthManager.ADMIN:
				write(response, cnt.toFinalJSON());
				response.setStatus(cnt.http);
				return;
			case AuthManager.CLIENT:
				Cliente cliente = AuthManager.getActualClient(request, response);
				if(cliente == null) return;
				if(cnt.nonEmptyResult()) {
					Cuenta cn = cnt.listReturned.get(0);
					if(cn.getCliente().getDNI().equals(cliente.getDNI())) {
						response.setStatus(cnt.http);
						write(response, cnt.toFinalJSON());
						return;
					} else {
						response.setStatus(403);
						return;
					}
				} else response.setStatus(404);
				break;
			default:
				response.setStatus(403);
				break;
			}
		} else {
			response.setStatus(401);
		}
		return;
	}


	@Override
	protected String[] getAllowedMethods() {
		return new String[] {"GET", "DELETE", "OPTIONS" };
	}

}
