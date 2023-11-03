package data;

import java.sql.SQLException;
import java.sql.Types;


import entity.PrestamosCliente;
import logic.PrestamosClienteLogic;
import max.data.Dictionary;
import max.data.IRecord;
import max.data.TransactionResponse;
import max.net.Connector;
import max.oops.SchemaValidationException;
import max.schema.IModel;
import max.schema.MySQLSchemaModel;
import max.schema.Schema;
import max.schema.SchemaProperty;
	


public class PrestamosClienteDao implements IRecord<PrestamosCliente, Integer> {

	
	public PrestamosClienteDao() {}
	
	private Connector db = new Connector(_model.getDatabaseName());
	private PrestamosClienteLogic logic = new PrestamosClienteLogic();
	
	private static class Fields{
		public static SchemaProperty id = new SchemaProperty("id_PxC") {{
			primary = true;
			type = Types.INTEGER;
			autoIncrement = true;
		}};
		public static SchemaProperty usuario = new SchemaProperty("usuario_cl_PxC") {{
			required = true;
			type = Types.VARCHAR;
			matches = "^[a-zA-Z0-9_]{4,20}$";
			maxlength = 20;
			minlength = 4;
			trim = true;
			ref = ClienteDao._model.ref("usuario");
		}};
		public static SchemaProperty codigoPrestamo = new SchemaProperty("cod_Sol_PxC") {{
			required = true;
			type = Types.VARCHAR;//era char, se cambio a varchar
			maxlength = 6;
			ref = SolicitudPrestamoDao._model.ref("cod_Sol");
		}};
		public static SchemaProperty fechaOtorgado = new SchemaProperty("fechaOtorgado_PxC") {{
			required = true;
			type = Types.DATE;
		}};
		public static SchemaProperty montoAPagar = new SchemaProperty("montoAPagar_PxC") {{
			required = true;
			type = Types.DOUBLE;
		}};
		public static SchemaProperty plazoPago = new SchemaProperty("plazoPago_PxC") {{
			required = true;
			type = Types.INTEGER;
			min=0;
		}};
		public static SchemaProperty cantCuotas = new SchemaProperty("cantCuotas_PxC") {{
			required = true;
			type = Types.INTEGER;
			min=0;
		}};
		public static SchemaProperty montoPorCuota = new SchemaProperty("montoPorCuota_PxC") {{
			required = true;
			type = Types.DOUBLE;
		}};
		public static SchemaProperty cuotasPagadas = new SchemaProperty("cuotasPagadas_PxC") {{
			required = true;
			type = Types.INTEGER;
			min=0;
		}};
		public static SchemaProperty cuotasRestantes = new SchemaProperty("cuotasRestantes_PxC") {{
			required = true;
			type = Types.INTEGER;
			min=0;	
		}};
	}
	
	public static final Schema _schema = new Schema(
			Fields.id,
			Fields.usuario,
			Fields.codigoPrestamo,
			Fields.fechaOtorgado,
			Fields.montoAPagar,
			Fields.plazoPago,
			Fields.cantCuotas,
			Fields.montoPorCuota,
			Fields.cuotasPagadas,
			Fields.cuotasRestantes
			);
	public static final Schema _editable = new Schema(
			Fields.cuotasPagadas,
			Fields.cuotasRestantes
			);
	
	public static final IModel _model = new MySQLSchemaModel("prestamosClientes","tif",_schema) {{
		compile(true);
	}};
	
	public String printTDB() {
		return _model.getDatabaseName() + "." + _model.getTableName();
	}
	
	@Override
	public TransactionResponse<?> insert(PrestamosCliente data) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = _model.create(data.toDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public TransactionResponse<?> delete(PrestamosCliente data) throws SQLException {
		TransactionResponse<?> res = null;
		try {
			res = _model.delete(data.toIdentifiableDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public TransactionResponse<?> modify(PrestamosCliente data) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = _model.modify(data.toDictionary(),data.toIdentifiableDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public TransactionResponse<PrestamosCliente> getAll() throws SQLException {
		return select("SELECT * FROM " + printTDB());
	}
	
	private TransactionResponse<PrestamosCliente> select(String arg0) throws SQLException {
		TransactionResponse<PrestamosCliente> res = new TransactionResponse<PrestamosCliente>();
		TransactionResponse<Dictionary> rd = db.fetch(arg0);
		if(rd.nonEmptyResult()) {
			res.rowsReturned = logic.convert(rd.rowsReturned);
		}
		return res;
	}	

	private TransactionResponse<PrestamosCliente> select(String arg0, Dictionary arg1) throws SQLException {
		TransactionResponse<PrestamosCliente> res = new TransactionResponse<PrestamosCliente>();
		TransactionResponse<Dictionary> rd = db.fetch(arg0, arg1);
		if(rd.nonEmptyResult()) {
			res.rowsReturned = logic.convert(rd.rowsReturned);
		}
		return res;
	}	
	
	@Override
	public TransactionResponse<PrestamosCliente> getById(Integer id) throws SQLException {
		return select("SELECT * FROM " + printTDB() + " WHERE id_PxC = @id", Dictionary.fromArray("id",id));
	}

	@Override
	public boolean exists(Integer id) throws SQLException {	
		return _model.exists(Dictionary.fromArray("id", id));
	}

}