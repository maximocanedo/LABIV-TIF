package max.testing;

import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import data.SolicitudPrestamoDao;
import entity.Cliente;
import entity.SolicitudPrestamo;
import logic.SolicitudPrestamoLogic;


public class Main {
	public static void main(String[] args) {
			//crearTabla();
		test();
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
		
	public static void test() {
		
		SolicitudPrestamoLogic logic = new SolicitudPrestamoLogic();
		SolicitudPrestamo sp = new SolicitudPrestamo();
		Cliente cl = new Cliente();
		
		sp.setCod_Sol("SL0001");
		cl.setUsuario("Maria_12144165");
		sp.setUsuario_cl_Sol(cl);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate = new java.util.Date();
        try {
        	parsedDate = dateFormat.parse("2023-10-30");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        java.sql.Date edate = new java.sql.Date(parsedDate.getTime());
		sp.setFechaPedido_Sol(edate);
		
		sp.setMontoPedido_Sol(5600000);
		sp.setMontoAPagar_Sol(7500000);
		sp.setPlazoPago_Sol(3);
		sp.setCantCuotas_Sol(12);
		sp.setMontoPorCuota_Sol(8000000);
		sp.setInteres_Sol(8.6);
		sp.setEstado_Sol(true);
		
		logic.insert(sp);
		
	}
	
	public static void crearTabla()
	{
		SolicitudPrestamoDao a = new SolicitudPrestamoDao();
		a._model.compile();
		
		
	}
}
