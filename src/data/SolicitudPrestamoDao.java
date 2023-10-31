package data;

import java.sql.SQLException;
import java.sql.Types;

import entity.SolicitudPrestamo;
import logic.SolicitudPrestamoLogic;
import max.data.Dictionary;
import max.data.IRecord;
import max.data.TransactionResponse;
import max.net.Connector;
import max.oops.SchemaValidationException;
import max.schema.IModel;
import max.schema.MySQLSchemaModel;
import max.schema.Schema;
import max.schema.SchemaProperty;

public class SolicitudPrestamoDao implements IRecord<SolicitudPrestamo, String> {

	public SolicitudPrestamoDao() {}
	
	private Connector db = new Connector(_model.getDatabaseName());
	private SolicitudPrestamoLogic logic = new SolicitudPrestamoLogic();
	
	// Campos
	public static class Fields{
		public static SchemaProperty codigo = new SchemaProperty("cod_Sol") {{
			primary = true;
			unique = true;
			type = Types.VARCHAR;//Era CHAR, luego de crear la tabla se cambio a VARCHAR
			maxlength = 6;
			modifiable = false;
		}};
		public static SchemaProperty usuario = new SchemaProperty("usuario_cl_Sol") {{
			required = true;
			unique = true;
			type = Types.VARCHAR;
			matches = "^[a-zA-Z0-9_]{4,20}$";
			maxlength = 20;
			minlength = 4;
			trim = true;
			modifiable = false;
			ref = ClienteDao._model.ref("usuario");
		}};
		public static SchemaProperty fecha = new SchemaProperty("fechaPedido_Sol") {{
			required = true;
			type = Types.DATE;
			modifiable = false;
		}};
		public static SchemaProperty montoPedido = new SchemaProperty("montoPedido_Sol") {{
			required = true;
			type = Types.DOUBLE; //decimal 8.2
			modifiable = false;
		}};
		public static SchemaProperty montoAPagar = new SchemaProperty("montoAPagar_Sol") {{
			required = true;
			type = Types.DOUBLE; //decimal 8.2
			modifiable = false;
		}};
		public static SchemaProperty plazo = new SchemaProperty("plazoPago_Sol") {{
			required = true;
			type = Types.INTEGER;
			min = 0;
			modifiable = false;
		}};
		public static SchemaProperty cuotas = new SchemaProperty("cantCuotas_Sol") {{
			required = true;
			type = Types.INTEGER;
			min = 0;
			modifiable = false;
		}};
		public static SchemaProperty montoCuota = new SchemaProperty("montoPorCuota_Sol") {{
			required = true;
			type = Types.DOUBLE;
			modifiable = false;
		}};
		public static SchemaProperty interes = new SchemaProperty("interes_Sol") {{
			required = true;
			type = Types.FLOAT; 
			modifiable = false;
		}};
		public static SchemaProperty estado = new SchemaProperty("estado_Sol") {{
			required = true;
			type = Types.BIT;
			defaultValue= true;
		}};
				
	};
	
	public static final Schema _schema = new Schema(
			Fields.codigo,
			Fields.usuario,
			Fields.fecha,
			Fields.montoPedido,
			Fields.montoAPagar,
			Fields.plazo,
			Fields.cuotas,
			Fields.montoCuota,
			Fields.interes,
			Fields.estado
			);
	public static final Schema _editable = new Schema(
			Fields.estado
			);
	
	public static final IModel _model = new MySQLSchemaModel("solicitudPrestamos","tif",_schema) {{
		compile(true);
	}};
	
	public String printTDB() {
		return _model.getDatabaseName() + "." + _model.getTableName();
	}
			
			
	
	@Override
	public TransactionResponse<?> insert(SolicitudPrestamo data) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = _model.create(data.toDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public TransactionResponse<?> delete(SolicitudPrestamo data) throws SQLException {
		TransactionResponse<?> res = null;
		try {
			res = _model.delete(data.toIdentifiableDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public TransactionResponse<?> modify(SolicitudPrestamo data) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = _model.modify(data.toDictionary(),data.toIdentifiableDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public TransactionResponse<SolicitudPrestamo> getAll() throws SQLException {
		return select("SELECT * FROM " + printTDB());
	}

	@Override
	public TransactionResponse<SolicitudPrestamo> getById(String arg0) throws SQLException {
		return select("SELECT * FROM " + printTDB() + " WHERE cod_Sol = @codigo", Dictionary.fromArray("codigo",arg0));
	}

	@Override
	public boolean exists(String codigo) throws SQLException {
		
		return _model.exists(Dictionary.fromArray("codigo_solicitud", codigo));	
	}

	
	
	
	private TransactionResponse<SolicitudPrestamo> select(String arg0) throws SQLException {
		TransactionResponse<SolicitudPrestamo> res = new TransactionResponse<SolicitudPrestamo>();
		TransactionResponse<Dictionary> rd = db.fetch(arg0);
		if(rd.nonEmptyResult()) {
			res.rowsReturned = logic.convert(rd.rowsReturned);
		}
		return res;
	}
	private TransactionResponse<SolicitudPrestamo> select(String arg0, Dictionary arg1) throws SQLException {
		TransactionResponse<SolicitudPrestamo> res = new TransactionResponse<SolicitudPrestamo>();
		TransactionResponse<Dictionary> rd = db.fetch(arg0, arg1);
		if(rd.nonEmptyResult()) {
			res.rowsReturned = logic.convert(rd.rowsReturned);
		}
		return res;
	}
}
