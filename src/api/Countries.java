package api;

import entity.Pais;
import logicImpl.PaisLogicImpl;
import max.Response;
import servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/v2/countries")
public class Countries extends BaseServlet {
    public Countries() { super(); }
    protected final PaisLogicImpl logic = new PaisLogicImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        Response<Pais> res = logic.getAll();
        write(response, res.toFinalJSON());
    }

    @Override
    protected String[] getAllowedMethods() {
        return new String[] { "GET", "OPTIONS" };
    }
}
