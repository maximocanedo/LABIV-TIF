package data;

import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;


import entity.Cliente;
import entity.PrestamosCliente;
import entity.SolicitudPrestamo;
import logic.PrestamosClienteLogic;
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



	


public class PrestamosClienteDao implements IRecord<PrestamosCliente, String> {

	
	public static void main(String[] args)  {
		//PrestamosClienteDao a = new PrestamosClienteDao();
		//a._model.compile();
		
		//SolicitudPrestamoDao b = new SolicitudPrestamoDao();
		//b._model.compile();
		
		/*SolicitudPrestamoLogic logic1 = new SolicitudPrestamoLogic();
		SolicitudPrestamo a = new SolicitudPrestamo();
		Cliente c = new Cliente();
		c.setUsuario("Maria_12144165");
		
		a.setCodigo("SL0001");
		a.setCliente(c);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsedDate = new java.util.Date();
		try {
			parsedDate = dateFormat.parse("2023-8-24");
		}
		catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		java.sql.Date edate = new java.sql.Date(parsedDate.getTime());

		a.setFechaPedido(edate);
		a.setMontoPedido(14000.0);
		a.setMontoAPagar(20000.0);
		a.setInteres(5.3);
		a.setMontoPorCuota(1000.0);
		a.setCantCuotas(12);
		a.setPlazoPago(1);
		a.setEstado(true);
		
		logic1.insert(a);*/
		
		/*PrestamosClienteLogic logic = new PrestamosClienteLogic();
		
		PrestamosCliente a = new PrestamosCliente();
		SolicitudPrestamo s = new SolicitudPrestamo();
		Cliente c = new Cliente();
		c.setUsuario("Maria_12144165");
		s.setCodigo("SL0001");
		
		a.setCliente(c);
		a.setSolicitud(s);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsedDate = new java.util.Date();
		try {
			parsedDate = dateFormat.parse("2023-8-24");
		}
		catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		java.sql.Date edate = new java.sql.Date(parsedDate.getTime());
		a.setFechaOtorgado(edate);
		a.setMontoAPagar(200.000);
		a.setPlazoPago(2);
		a.setCantCuotas(24);
		a.setMontoPorCuota(20.000);
		a.setCuotasPagadas(6);
		a.setCuotasRestantes(18);
		
		//logic.insert(a);
		
		//PrestamosCliente del = new PrestamosCliente();
		//del.setId(1);
		//logic.delete(del);
		
		
		PrestamosCliente mod = new PrestamosCliente();
		mod.setId(1);
		mod.setCliente(c);
		mod.setSolicitud(s);
		mod.setCuotasPagadas(7);
		mod.setCuotasRestantes(17);
		logic.modify(mod);*/
		
	}
	
	public PrestamosClienteDao() {}
	
	private Connector db = new Connector(_model.getDatabaseName());
	private PrestamosClienteLogic logic = new PrestamosClienteLogic();
	
	private static class Fields{
		public static SchemaProperty id = new SchemaProperty("id_PxC") {{
			primary = true;
			unique = true;
			type = Types.INTEGER;
			modifiable = false;
			autoIncrement = true;
		}};
		public static SchemaProperty usuario = new SchemaProperty("usuario_cl_PxC") {{
			required = true;
			type = Types.VARCHAR;
			matches = "^[a-zA-Z0-9_]{4,20}$";
			maxlength = 20;
			minlength = 4;
			trim = true;
			modifiable = false;
			ref = ClienteDao._model.ref("usuario");
		}};
		public static SchemaProperty codigoPrestamo = new SchemaProperty("cod_Sol_PxC") {{
			required = true;
			unique= true;
			type = Types.VARCHAR;//era char, se cambio a varchar
			maxlength = 6;
			modifiable = false;
			ref = SolicitudPrestamoDao._model.ref("codigo");
		}};
		public static SchemaProperty fechaOtorgado = new SchemaProperty("fechaOtorgado_PxC") {{
			required = true;
			type = Types.DATE;
			modifiable = false;
		}};
		public static SchemaProperty montoAPagar = new SchemaProperty("montoAPagar_PxC") {{
			required = true;
			type = Types.DOUBLE;
			modifiable = false;
		}};
		public static SchemaProperty plazoPago = new SchemaProperty("plazoPago_PxC") {{
			required = true;
			type = Types.INTEGER;
			min=0;
			modifiable = false;
		}};
		public static SchemaProperty cantCuotas = new SchemaProperty("cantCuotas_PxC") {{
			required = true;
			type = Types.INTEGER;
			min=0;
			modifiable = false;
		}};
		public static SchemaProperty montoPorCuota = new SchemaProperty("montoPorCuota_PxC") {{
			required = true;
			type = Types.DOUBLE;
			modifiable = false;
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

	@Override
	public TransactionResponse<PrestamosCliente> getById(String id) throws SQLException {
		return select("SELECT * FROM " + printTDB() + " WHERE id_PxC = @id", Dictionary.fromArray("id",id));
	}

	@Override
	public boolean exists(String id) throws SQLException {
		return _model.exists(Dictionary.fromArray("id", id));
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

}
