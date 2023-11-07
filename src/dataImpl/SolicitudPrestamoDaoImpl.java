package dataImpl;

import java.sql.SQLException;
import java.sql.Types;

import data.ISolicitudPrestamoDao;
import entity.SolicitudPrestamo;
import logicImpl.SolicitudPrestamoLogic;
import max.data.Dictionary;
import max.data.IRecord;
import max.data.TransactionResponse;
import max.net.Connector;
import max.oops.SchemaValidationException;
import max.schema.IModel;
import max.schema.MySQLSchemaModel;
import max.schema.Schema;
import max.schema.SchemaProperty;

public class SolicitudPrestamoDaoImpl implements IRecord<SolicitudPrestamo, String>, ISolicitudPrestamoDao {

	public SolicitudPrestamoDaoImpl() {}
	
	private Connector db = new Connector(_model.getDatabaseName());
	private SolicitudPrestamoLogic logic = new SolicitudPrestamoLogic();
	
	//Campos
	public static class Fields{
		public static SchemaProperty codigo = new SchemaProperty("cod_Sol") {{
			primary = true;
			type = Types.VARCHAR;//Era CHAR, luego de crear la tabla se cambio a VARCHAR
			maxlength = 6;
		}};
		public static SchemaProperty usuario = new SchemaProperty("usuario_cl_Sol") {{
			required = true;
			type = Types.VARCHAR;
			matches = "^[a-zA-Z0-9_]{4,20}$";
			maxlength = 20;
			minlength = 4;
			trim = true;			
			ref = ClienteDaoImpl._model.ref("usuario");
		}};
		public static SchemaProperty fecha = new SchemaProperty("fechaPedido_Sol") {{
			required = true;
			type = Types.DATE;			
		}};
		public static SchemaProperty montoPedido = new SchemaProperty("montoPedido_Sol") {{
			required = true;
			type = Types.DOUBLE; //decimal 8.2			
		}};
		public static SchemaProperty montoAPagar = new SchemaProperty("montoAPagar_Sol") {{
			required = true;
			type = Types.DOUBLE; //decimal 8.2			
		}};
		public static SchemaProperty plazo = new SchemaProperty("plazoPago_Sol") {{
			required = true;
			type = Types.INTEGER;
			min = 0;			
		}};
		public static SchemaProperty cuotas = new SchemaProperty("cantCuotas_Sol") {{
			required = true;
			type = Types.INTEGER;
			min = 0;			
		}};
		public static SchemaProperty montoCuota = new SchemaProperty("montoPorCuota_Sol") {{
			required = true;
			type = Types.DOUBLE;			
		}};
		public static SchemaProperty interes = new SchemaProperty("interes_Sol") {{
			required = true;
			type = Types.DOUBLE; 			
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
	
	/* (non-Javadoc)
	 * @see dataImpl.ISolicitudPrestamoDao#printTDB()
	 */
	@Override
	public String printTDB() {
		return _model.getDatabaseName() + "." + _model.getTableName();
	}
			
			
	
	/* (non-Javadoc)
	 * @see dataImpl.ISolicitudPrestamoDao#insert(entity.SolicitudPrestamo)
	 */
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

	/* (non-Javadoc)
	 * @see dataImpl.ISolicitudPrestamoDao#delete(entity.SolicitudPrestamo)
	 */
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

	/* (non-Javadoc)
	 * @see dataImpl.ISolicitudPrestamoDao#modify(entity.SolicitudPrestamo)
	 */
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

	/* (non-Javadoc)
	 * @see dataImpl.ISolicitudPrestamoDao#getAll()
	 */
	@Override
	public TransactionResponse<SolicitudPrestamo> getAll() throws SQLException {
		return select("SELECT * FROM " + printTDB());
	}

	/* (non-Javadoc)
	 * @see dataImpl.ISolicitudPrestamoDao#getById(java.lang.String)
	 */
	@Override
	public TransactionResponse<SolicitudPrestamo> getById(String arg0) throws SQLException {
		return select("SELECT * FROM " + printTDB() + " WHERE cod_Sol = @codigo", Dictionary.fromArray("codigo",arg0));
	}

	/* (non-Javadoc)
	 * @see dataImpl.ISolicitudPrestamoDao#exists(java.lang.String)
	 */
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