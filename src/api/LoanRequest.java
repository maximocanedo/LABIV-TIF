package api;

import entity.Administrador;
import entity.SolicitudPrestamo;
import logicImpl.AuthManager;
import logicImpl.SolicitudPrestamoLogicImpl;
import max.Dictionary;
import max.Response;
import servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/v2/loans/requests/*")
public class LoanRequest extends BaseServlet {
    public LoanRequest() { super(); }
    private final SolicitudPrestamoLogicImpl SPLI = new SolicitudPrestamoLogicImpl();
    protected SolicitudPrestamo getSpecifiedLoanRequest(HttpServletRequest req, HttpServletResponse res) {
        String id = getPathParameter(req, 0);
        if(id == null) {
            res.setStatus(400);
            return null;
        }
        Response<SolicitudPrestamo> data = SPLI.getById(id);
        if(data == null || data.listReturned == null) {
            res.setStatus(404);
            return null;
        }
        return data.listReturned.get(0);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SolicitudPrestamo data = getSpecifiedLoanRequest(req, resp);
        if(data == null) return;
        write(resp, data.toJSON());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Administrador admin = AuthManager.getActualAdmin(request, response);
        Dictionary parametersAdmin = getParameters(request);
        if (admin == null) {
            response.setStatus(403);
            return;
        }
        if(parametersAdmin == null) {
            response.setStatus(400);
            return;
        }
        SolicitudPrestamo data = getSpecifiedLoanRequest(request, response);
        if(data == null) return;
        parametersAdmin.put("cod_Sol", data.getCodigo());
        entity.SolicitudPrestamo prestamo = SPLI.convert(parametersAdmin);
        Response<entity.SolicitudPrestamo> res = SPLI.approve(prestamo);
        response.setStatus(res.http);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Administrador admin = AuthManager.getActualAdmin(request, response);
        Dictionary parametersAdmin = getParameters(request);
        if (admin == null) {
            response.setStatus(403);
            return;
        }
        if(parametersAdmin == null) {
            response.setStatus(400);
            return;
        }
        SolicitudPrestamo data = getSpecifiedLoanRequest(request, response);
        if(data == null) return;
        parametersAdmin.put("cod_Sol", data.getCodigo());
        entity.SolicitudPrestamo prestamo = SPLI.convert(parametersAdmin);
        Response<entity.SolicitudPrestamo> res = SPLI.reject(prestamo);
        response.setStatus(res.http);
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) {
        resp.setStatus(200);
        getSpecifiedLoanRequest(req, resp);
    }

    @Override
    protected String[] getAllowedMethods() {
        return new String[] { GET, POST, DELETE, HEAD };
    }
}
