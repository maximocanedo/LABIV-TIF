package dataImpl;

import java.sql.SQLException;
import java.sql.Types;

import data.IDetalleCuotaDao;
import entity.DetalleCuota;
import logicImpl.DetalleCuotaLogicImpl;
import max.Connector;
import max.Dictionary;
import max.IModel;
import max.IRecord;
import max.MySQLSchemaModel;
import max.Schema;
import max.SchemaProperty;
import max.TransactionResponse;
import oops.SchemaValidationException;

public class DetalleCuotaDaoImpl implements IRecord<DetalleCuota, Integer>, IDetalleCuotaDao {

	/*public static void main(String[] args) {
			//DetalleCuotaDao test = new DetalleCuotaDao();
			//test._model.compile();
		
		DetalleCuotaLogic logic = new DetalleCuotaLogic();
		DetalleCuota dc = new DetalleCuota();
		Cliente cl = new Cliente();
		cl.setUsuario("Maria_12144165");
		SolicitudPrestamo sp = new SolicitudPrestamo();
		sp.setCodigo("SL0001");
		
		
		dc.setCliente(cl);
		dc.setCod_Solicitud(sp);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsedDate = new java.util.Date();
		try {
			parsedDate = dateFormat.parse("2023-11-03");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date edate = new java.sql.Date(parsedDate.getTime());
		dc.setFechaPago(edate);
		dc.setNumCuotaPagada(1);
		
		logic.insert(dc);
	}*/
	
	public DetalleCuotaDaoImpl() {}
	
	private Connector db = new Connector(_model.getDatabaseName());
	private DetalleCuotaLogicImpl logic = new DetalleCuotaLogicImpl();
	
	private static class Fields{
		public static SchemaProperty id = new SchemaProperty("id_DTPT") {{
			primary = true;
			type = Types.INTEGER;
			autoIncrement = true;
		}};
		public static SchemaProperty solicitud = new SchemaProperty("cod_Sol_DTPT") {{
			required = true;
			type = Types.VARCHAR;
			maxlength = 6;
			ref = PrestamosClienteDaoImpl._model.ref("cod_Sol_PxC");
		}};
		public static SchemaProperty cliente = new SchemaProperty("usuario_cl_DTPT") {{
			required = true;
			type = Types.VARCHAR;
			matches = "^[a-zA-Z0-9_]{4,20}$";
			maxlength = 20;
			minlength = 4;
			trim = true;
			ref = PrestamosClienteDaoImpl._model.ref("usuario_cl_PxC");
		}};
		public static SchemaProperty fechaPago = new SchemaProperty("fechaPago_DTPT") {{
			required = true;
			type = Types.DATE;
		}};
		public static SchemaProperty numCuota = new SchemaProperty("numCuotaPagada_DTPT") {{
			required = true;
			type = Types.INTEGER;
			min=0;
		}};
	}
	
	public static final Schema _schema = new Schema(
			Fields.id,
			Fields.cliente,
			Fields.solicitud,
			Fields.fechaPago,
			Fields.numCuota
			);
	
	public static final IModel _model = new MySQLSchemaModel("detalleCuotas","tif",_schema) {{
		compile(true);
	}};
	
	public String printTDB() {
		return _model.getDatabaseName() + "." + _model.getTableName();
	}
	
	@Override
	public TransactionResponse<?> insert(DetalleCuota data) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = _model.create(data.toDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}
	

	@Override
	public TransactionResponse<?> delete(DetalleCuota data) throws SQLException {
		TransactionResponse<?> res = null;
		try {
			res = _model.delete(data.toIdentifiableDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public TransactionResponse<?> modify(DetalleCuota data) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = _model.modify(data.toDictionary(),data.toIdentifiableDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	public String selectTemplate() {
		return "SELECT * " + 
				"	FROM tif.detallecuotas" + 
				"	INNER JOIN clientes__safeselect" + 
				"		ON clientes__safeselect.usuario = detallecuotas.usuario_cl_DTPT" + 
				"	INNER JOIN solicitudprestamos" + 
				"		ON solicitudprestamos.cod_Sol = detallecuotas.cod_Sol_DTPT";
	}
	
	@Override
	public TransactionResponse<DetalleCuota> getAll() throws SQLException {
		return select(selectTemplate());
	}

	private TransactionResponse<DetalleCuota> select(String arg0) throws SQLException {
		TransactionResponse<DetalleCuota> res = new TransactionResponse<DetalleCuota>();
		TransactionResponse<Dictionary> rd = db.fetch(arg0);
		if(rd.nonEmptyResult()) {
			res.rowsReturned = logic.convert(rd.rowsReturned);
		}
		return res;
	}	

	private TransactionResponse<DetalleCuota> select(String arg0, Dictionary arg1) throws SQLException {
		TransactionResponse<DetalleCuota> res = new TransactionResponse<DetalleCuota>();
		TransactionResponse<Dictionary> rd = db.fetch(arg0, arg1);
		if(rd.nonEmptyResult()) {
			res.rowsReturned = logic.convert(rd.rowsReturned);
		}
		return res;
	}	
	
	
	@Override
	public TransactionResponse<DetalleCuota> getById(Integer id) throws SQLException {
		return select(selectTemplate() + " WHERE id_PxC = @id", Dictionary.fromArray("id",id));
	}

	@Override
	public boolean exists(Integer id) throws SQLException {
		return _model.exists(Dictionary.fromArray("id", id));
	}

}
