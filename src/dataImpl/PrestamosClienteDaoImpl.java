package dataImpl;

import java.sql.SQLException;
import java.sql.Types;

import data.IPrestamoClienteDao;
import entity.Prestamo;
import logicImpl.PrestamoClienteLogicImpl;
import max.Connector;
import max.Dictionary;
import max.IModel;
import max.IRecord;
import max.MySQLSchemaModel;
import max.Schema;
import max.SchemaProperty;
import max.TransactionResponse;
import oops.SchemaValidationException;
	


public class PrestamosClienteDaoImpl implements IRecord<Prestamo, Integer>, IPrestamoClienteDao {

	
	public PrestamosClienteDaoImpl() {}
	
	private Connector db = new Connector(_model.getDatabaseName());
	private PrestamoClienteLogicImpl logic = new PrestamoClienteLogicImpl();
	
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
			ref = ClienteDaoImpl._model.ref("usuario");
		}};
		public static SchemaProperty codigoPrestamo = new SchemaProperty("cod_Sol_PxC") {{
			required = true;
			type = Types.VARCHAR;//era char, se cambio a varchar
			maxlength = 6;
			ref = SolicitudPrestamoDaoImpl._model.ref("cod_Sol");
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
	
	/* (non-Javadoc)
	 * @see dataImpl.IPrestamoClienteDao#printTDB()
	 */
	@Override
	public String printTDB() {
		return _model.getDatabaseName() + "." + _model.getTableName();
	}
	
	/* (non-Javadoc)
	 * @see dataImpl.IPrestamoClienteDao#insert(entity.PrestamosCliente)
	 */
	@Override
	public TransactionResponse<?> insert(Prestamo data) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = _model.create(data.toDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see dataImpl.IPrestamoClienteDao#delete(entity.PrestamosCliente)
	 */
	@Override
	public TransactionResponse<?> delete(Prestamo data) throws SQLException {
		TransactionResponse<?> res = null;
		try {
			res = _model.delete(data.toIdentifiableDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see dataImpl.IPrestamoClienteDao#modify(entity.PrestamosCliente)
	 */
	@Override
	public TransactionResponse<?> modify(Prestamo data) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = _model.modify(data.toDictionary(),data.toIdentifiableDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see dataImpl.IPrestamoClienteDao#getAll()
	 */
	@Override
	public TransactionResponse<Prestamo> getAll() throws SQLException {
		return select("SELECT * FROM prestamos__select");
	}
	
	private TransactionResponse<Prestamo> select(String arg0) throws SQLException {
		TransactionResponse<Prestamo> res = new TransactionResponse<Prestamo>();
		TransactionResponse<Dictionary> rd = db.fetch(arg0);
		if(rd.nonEmptyResult()) {
			res.rowsReturned = logic.convert(rd.rowsReturned);
		}
		return res;
	}	

	private TransactionResponse<Prestamo> select(String arg0, Dictionary arg1) throws SQLException {
		TransactionResponse<Prestamo> res = new TransactionResponse<Prestamo>();
		TransactionResponse<Dictionary> rd = db.fetch(arg0, arg1);
		if(rd.nonEmptyResult()) {
			res.rowsReturned = logic.convert(rd.rowsReturned);
		}
		return res;
	}	
	
	/* (non-Javadoc)
	 * @see dataImpl.IPrestamoClienteDao#getById(java.lang.Integer)
	 */
	@Override
	public TransactionResponse<Prestamo> getById(Integer id) throws SQLException {
		return select("SELECT * FROM prestamos__select WHERE id_PxC = @id", Dictionary.fromArray("id",id));
	}
	/* (non-Javadoc)
	 * @see dataImpl.IPrestamoClienteDao#getById(java.lang.String)
	 */
	@Override
	public TransactionResponse<Prestamo> getById(String usuario_cl_PxC) throws SQLException {
		return select("SELECT * FROM prestamos__select WHERE usuario_cl_PxC = @usuario_cl_PxC", Dictionary.fromArray("usuario_cl_PxC",usuario_cl_PxC));
	}

	/* (non-Javadoc)
	 * @see dataImpl.IPrestamoClienteDao#exists(java.lang.Integer)
	 */
	@Override
	public boolean exists(Integer id) throws SQLException {	
		return _model.exists(Dictionary.fromArray("id", id));
	}

}