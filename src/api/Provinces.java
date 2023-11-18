package api;

import entity.Provincia;
import logicImpl.ProvinciaLogicImpl;
import max.Response;
import servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/v2/provinces")
public class Provinces extends BaseServlet {
    public Provinces() { super(); }
    private final ProvinciaLogicImpl logic = new ProvinciaLogicImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Response<Provincia> res = logic.getAll();
        String finalJSON = res.toFinalJSON();
        write(resp, finalJSON);
    }
    @Override
    protected String[] getAllowedMethods() {
        return new String[] { GET, OPTIONS };
    }
}
