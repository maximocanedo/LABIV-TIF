package servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.TipoCuenta;
import logicImpl.TipoCuentaLogicImpl;
import max.Response;

/**
 * Servlet implementation class TiposCuenta
 */
@WebServlet("/api/accountTypes")
public class TiposCuenta extends BaseServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see BaseServlet#BaseServlet()
     */
    public TiposCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Response<TipoCuenta> res = (new TipoCuentaLogicImpl()).getAll();
		response.setStatus(res.http);
		write(response, res.toFinalJSON());
	}

	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "GET", "OPTIONS" };
	}

}
