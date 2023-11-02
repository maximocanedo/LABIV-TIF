package max.testing;

import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import data.PrestamosClienteDao;
import data.SolicitudPrestamoDao;
import entity.Cliente;
import entity.SolicitudPrestamo;
import logic.SolicitudPrestamoLogic;


public class Main {
	public static void main(String[] args) {
			//crearTablaSolicitud();
			//testSolicitud();
			crearTablaPrestamos();
    }
	
/*
    public static void testDictionary() {
        try {
            // Prueba de creación de un Dictionary a partir de un array
            Dictionary dict = Dictionary.fromArray(
                "nombre", "%e",
                "apellido", "Pérez"
            );

            // Prueba de exists
            assert dict.exists("nombre") == true;
            assert dict.exists("edad") == false;

            // Prueba de getParameters
            String query = "SELECT * FROM Personas WHERE Nombre LIKE @nombre";
            Object[] params = dict.getParameters(query);
            assert params.length == 2;
            assert params[0].equals("%e");
            assert params[1].equals("Pérez");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testConnector() {
        Connector x = new Connector(Connector.DB.bdPersonas);
        try {
            // Prueba de fetch
            TransactionResponse<Dictionary> t = x.fetch(
                "SELECT * FROM Personas WHERE Nombre LIKE @nombre",
                Dictionary.fromArray(
                    "nombre", "%e"
                )
            );

            List<Dictionary> ppl = t.rowsReturned;
            for (Dictionary p : ppl) {
                String name = (String) p.get("Nombre");
                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/
		
	/*public static void testSolicitud() {
		
		SolicitudPrestamoLogic logic = new SolicitudPrestamoLogic();
		SolicitudPrestamo sp = new SolicitudPrestamo();
		Cliente cl = new Cliente();
		
		sp.setCodigo("SL0001");
		cl.setUsuario("Maria_12144165");
		sp.setCliente(cl);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate = new java.util.Date();
        try {
        	parsedDate = dateFormat.parse("2023-10-30");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        java.sql.Date edate = new java.sql.Date(parsedDate.getTime());
		sp.setFechaPedido(edate);
		
		sp.setMontoPedido(5600000);
		sp.setMontoAPagar(7500000);
		sp.setPlazoPago(3);
		sp.setCantCuotas(12);
		sp.setMontoPorCuota(8000000);
		sp.setInteres(8.6);
		sp.setEstado(true);
		
		logic.insert(sp);
		
	}
	
	public static void crearTablaSolicitud()
	{
		SolicitudPrestamoDao a = new SolicitudPrestamoDao();
		a._model.compile();	
	}*/
	
	public static void crearTablaPrestamos() {
		PrestamosClienteDao a = new PrestamosClienteDao();
		a._model.compile();
	}
}
