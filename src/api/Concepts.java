package api;

import entity.Concepto;
import logicImpl.ConceptoLogicImpl;
import max.Response;
import servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/v2/concepts")
public class Concepts extends BaseServlet {
    public Concepts() { super(); }
    private final ConceptoLogicImpl CLI = new ConceptoLogicImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Response<Concepto> res = CLI.getAll();
        write(resp, res.toFinalJSON());
    }

    @Override
    protected String[] getAllowedMethods() {
        return new String[] { GET, OPTIONS };
    }
}
