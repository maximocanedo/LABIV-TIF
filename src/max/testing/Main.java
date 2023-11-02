package max.testing;

import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import max.data.Dictionary;
import max.data.IEntity;
import max.data.TransactionResponse;
import max.net.Connector;
import max.oops.SchemaValidationException;
import max.schema.IModel;
import max.schema.MySQLSchemaModel;
import max.schema.Schema;
import max.schema.SchemaProperty;

public class Main {
	public static void main(String[] args) {
		
    }

    public static void testDictionary() {
        try {
            // Prueba de creaci�n de un Dictionary a partir de un array
            Dictionary dict = Dictionary.fromArray(
                "nombre", "%e",
                "apellido", "P�rez"
            );

            // Prueba de exists
            assert dict.exists("nombre") == true;
            assert dict.exists("edad") == false;

            // Prueba de getParameters
            String query = "SELECT * FROM Personas WHERE Nombre LIKE @nombre";
            Object[] params = dict.getParameters(query);
            assert params.length == 2;
            assert params[0].equals("%e");
            assert params[1].equals("P�rez");
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

}
