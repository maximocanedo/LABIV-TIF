package api;

import entity.Cliente;
import entity.DetalleCuotaPrestamo;
import entity.PrestamosCliente;
import entity.SolicitudPrestamo;
import logicImpl.AuthManager;
import logicImpl.DetalleCuotaPrestamoLogicImpl;
import logicImpl.PrestamoClienteLogicImpl;
import logicImpl.SolicitudPrestamoLogicImpl;
import max.Dictionary;
import max.Response;
import servlets.BaseServlet;
import servlets.Prestamo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/v2/loans/id/*/installments")
public class LoanInstallments extends BaseServlet {
    public LoanInstallments() { super(); }
    private final PrestamoClienteLogicImpl PCLI = new PrestamoClienteLogicImpl();
    private SolicitudPrestamoLogicImpl logic = new SolicitudPrestamoLogicImpl();
    private DetalleCuotaPrestamoLogicImpl cuota = new DetalleCuotaPrestamoLogicImpl();
    protected PrestamosCliente getSpecifiedLoan(HttpServletRequest req, HttpServletResponse res) {
        String strid = getPathParameter(req, 1);
        if(strid == null) {
            res.setStatus(400);
            return null;
        }
        int id;
        try {
            id = Integer.parseInt(strid);
        } catch(NumberFormatException e) {
            res.setStatus(400);
            return null;
        }
        Response<PrestamosCliente> data = PCLI.getById(id);
        if(data == null || data.listReturned == null) {
            res.setStatus(404);
            return null;
        }
        return data.listReturned.get(0);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthManager.TokenData td = AuthManager.readToken(request);
        if(td == null) {
            response.setStatus(401);
            return;
        }
        Cliente cliente = AuthManager.getActualClient(request, response);
        if(cliente != null) {
            PrestamosCliente loan = getSpecifiedLoan(request, response);
            String codigo = loan.getSolicitud().getCodigo();
            SolicitudPrestamo sp = new SolicitudPrestamo();
            sp.setCodigo(codigo);
            Response<DetalleCuotaPrestamo> res = cuota.getByRequest(sp);
            response.setStatus(res.http);
            write(response, res.toFinalJSON());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthManager.TokenData td = AuthManager.readToken(request);
        if(td != null) {
            Cliente cliente = AuthManager.getActualClient(request, response);
            if(cliente!=null) {
                Dictionary parameters = getParameters(request);
                if(parameters == null) {
                    die(response, false, 400, "Bad request");
                    return;
                }
                PrestamosCliente data = getSpecifiedLoan(request, response);
                if(data == null) return;
                SolicitudPrestamo pagarCuota = logic.convert(parameters);
                pagarCuota.setCodigo(data.getSolicitud().getCodigo());
                pagarCuota.setCliente(cliente);
                Response<SolicitudPrestamo> res = logic.PagarCuota(pagarCuota);
                response.setStatus(res.http);
                write(response, res.toFinalJSON());
            } else {
                response.setStatus(403);
            }
        } else {
            response.setStatus(401);
        }
    }

    @Override
    public String[] getAllowedMethods() {
        return new String[] { GET, POST };
    }
}
