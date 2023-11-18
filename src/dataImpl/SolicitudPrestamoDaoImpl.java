package dataImpl;

import java.sql.SQLException;
import java.sql.Types;

import data.ISolicitudPrestamoDao;
import entity.Paginator;
import entity.SolicitudPrestamo;
import logicImpl.SolicitudPrestamoLogicImpl;
import max.Connector;
import max.Dictionary;
import max.IModel;
import max.IRecord;
import max.MySQLSchemaModel;
import max.Schema;
import max.SchemaProperty;
import max.TransactionResponse;
import oops.SchemaValidationException;

public class SolicitudPrestamoDaoImpl implements IRecord<SolicitudPrestamo, String>, ISolicitudPrestamoDao {

	public SolicitudPrestamoDaoImpl() {}
	
	private Connector db = new Connector(_model.getDatabaseName());
	private SolicitudPrestamoLogicImpl logic = new SolicitudPrestamoLogicImpl();
	
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
			type = Types.INTEGER;
			
		}};
		public static SchemaProperty CBU_Sol = new SchemaProperty("CBU_Sol") {{
			required = true;
			type = Types.VARCHAR;
			maxlength = 22;
			trim = true;
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
			Fields.estado,
			Fields.CBU_Sol
			);
	
	public static final Schema _editable = new Schema(
			Fields.estado
			);
	
	public static final IModel _model = new MySQLSchemaModel("solicitudprestamos","tif",_schema) {{
		compile(true);
	}};
	

	@Override
	public String printTDB() {
		return _model.getDatabaseName() + "." + _model.getTableName();
	}
			
			
	

	@Override
	public TransactionResponse<?> insert(SolicitudPrestamo data) throws SQLException {
		TransactionResponse<Dictionary> rows= db.fetch(
				"CALL SP_INGRESARPEDIDOPRESTAMO (@usuario, @monto, @cantCuotas, @CBU)",
				Dictionary.fromArray("usuario",data.getCliente().getUsuario(),
									 "monto" , data.getMontoPedido(),
									 "cantCuotas", data.getCantCuotas(),
									 "CBU", data.getCuenta().getCBU()
									 )
		);
		TransactionResponse<?> affectedRows= TransactionResponse.create();
		if(rows.nonEmptyResult()) {
			String result = rows.rowsReturned.get(0).$("RESULT");
			if(result.equals("Pedido de préstamo generado correctamente.")) {
				affectedRows.status = true;
				affectedRows.rowsAffected = 1;
			}
		}
		return affectedRows;
	}
	
	public TransactionResponse<?> PagarCuota(SolicitudPrestamo data) throws SQLException {
		TransactionResponse<Dictionary> rows= db.fetch(
				"CALL SP_PAGARCUOTAPRESTAMO (@codSol, @CBU)",
				Dictionary.fromArray("codSol",data.getCodigo(),
									 "CBU",data.getCuenta().getCBU()
									 )
		);
		TransactionResponse<?> affectedRows= TransactionResponse.create();
		if(rows.nonEmptyResult()) {
			String result = rows.rowsReturned.get(0).$("resultado");
			if(result.equals("el pago se realizo con exito")) {
				affectedRows.status = true;
				affectedRows.rowsAffected = 1;
			}
		}
		return affectedRows;	
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
	
	public TransactionResponse<?> approve(SolicitudPrestamo data) throws SQLException {
		return db.transact("UPDATE tif.solicitudprestamos SET estado_Sol = 1 WHERE cod_Sol = @solicitud", Dictionary.fromArray(
			"solicitud", data.getCodigo()
		));
	}

	public TransactionResponse<?> reject(SolicitudPrestamo data) throws SQLException {
		return db.transact("UPDATE tif.solicitudprestamos SET estado_Sol = -1 WHERE cod_Sol = @solicitud", Dictionary.fromArray(
			"solicitud", data.getCodigo()
		));
	}

	@Override
	public TransactionResponse<SolicitudPrestamo> getAll() throws SQLException {
		return select("SELECT * FROM solicitudesDePrestamos__select");
	}
	
	public TransactionResponse<SolicitudPrestamo> getAll(Paginator paginator) throws SQLException {
		return select("CALL solicitudesDePrestamos__getAll(@page, @size, NULL)", new Dictionary().paginate(paginator));
	}

	public TransactionResponse<SolicitudPrestamo> getAllForClientByDNI(String DNI) throws SQLException {
		return select("SELECT * FROM solicitudesDePrestamos__select WHERE usuario_cl_Sol = @usuario_cl_Sol", Dictionary.fromArray("usuario_cl_Sol",DNI));
	}
	
	public TransactionResponse<SolicitudPrestamo> getAllForClientByDNI(String DNI, Paginator paginator) throws SQLException {
		return select("CALL solicitudesDePrestamos__getAllFromClient(@usuario, @page, @size, NULL)", Dictionary.fromArray("usuario", DNI).paginate(paginator));
	}
	
	public TransactionResponse<SolicitudPrestamo> getById(String id) throws SQLException {
		return select("SELECT * FROM solicitudesDePrestamos__select WHERE cod_Sol = @codigo", Dictionary.fromArray("codigo",id));
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