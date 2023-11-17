package api;

import entity.Localidad;
import entity.Provincia;
import logic.ILocalidadLogic;
import logicImpl.LocalidadLogicImpl;
import max.Response;
import servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/v2/provinces/*/localties")
public class Localties extends BaseServlet {
    public Localties() { super(); }
    private ILocalidadLogic logic = new LocalidadLogicImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id;
        try {
            String provId = getPathParameter(request, 1);
            id = Integer.parseInt(provId);
        } catch(NumberFormatException e) {
            response.setStatus(400);
            return;
        }
        Provincia p = new Provincia();
        p.setId(id);
        Response<Localidad> res = logic.filterByProvince(p);
        response.setStatus(res.http);
        write(response, res.toFinalJSON());
    }

    @Override
    protected String[] getAllowedMethods() {
        return new String[] { "GET", "OPTIONS" };
    }
}
