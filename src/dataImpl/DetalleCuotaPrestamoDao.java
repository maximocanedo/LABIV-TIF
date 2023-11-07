package dataImpl;

import java.sql.SQLException;
import java.sql.Types;

import entity.Cliente;
import entity.DetalleCuotaPrestamo;
import logicImpl.DetalleCuotaPrestamoLogic;
import max.data.Dictionary;
import max.data.IRecord;
import max.data.TransactionResponse;
import max.net.Connector;
import max.oops.SchemaValidationException;
import max.schema.IModel;
import max.schema.MySQLSchemaModel;
import max.schema.Schema;
import max.schema.SchemaProperty;

public class DetalleCuotaPrestamoDao implements IRecord<DetalleCuotaPrestamo,Integer>{
	
	public final static Schema _schema = new Schema(
			new SchemaProperty("id_DTPT"){{
				required=true;
				primary=true;
				type=Types.INTEGER;	
				autoIncrement=true;
			}},
			new SchemaProperty("usuario_cl_DTPT"){{
				required=true;
				type=Types.VARCHAR;
				maxlength=20;
				minlength=1;
				ref=ClienteDao._model.ref("usuario");
			}},
			new SchemaProperty("fechaPago_DTPT"){{
				required=true;
				type=Types.DATE;
			}},
			new SchemaProperty("numCuotaPagada_DTPT"){{
				required=true;
				type=Types.INTEGER;
				min=0;
			}},
			new SchemaProperty("cod_Sol_DTPT"){{
				required=true;
				type=Types.VARCHAR;
				maxlength=6;
				minlength=1;
				ref=PrestamosClienteDao._model.ref("cod_Sol_PxC");
			}}			
			);	
	
	
	public final static IModel _model = new MySQLSchemaModel("detalle_cuotasprestamo","tif",_schema) {{
		compile();
	}};
	
	private Connector dbCon = new Connector(_model.getDatabaseName());
	private DetalleCuotaPrestamoLogic lgcp = new DetalleCuotaPrestamoLogic();
	
	public String printTDB() {
		return _model.getDatabaseName() + "." + _model.getTableName();
	}
	
	
	@Override
	public TransactionResponse<?> insert(DetalleCuotaPrestamo obj) throws SQLException {
		
		TransactionResponse<?> tr = TransactionResponse.create();
		
		try {
				tr= _model.create(obj.toDictionary());			
		}catch(SchemaValidationException e) {
			e.printStackTrace();			
		}
		
		return tr;		
	}

	@Override
	public TransactionResponse<?> delete(DetalleCuotaPrestamo obj) throws SQLException {
		
		TransactionResponse<?> tr = TransactionResponse.create();
		
		try {
				tr=_model.delete(obj.toIdentifiableDictionary());			
		}catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		
		return tr;
	}

	@Override
	public TransactionResponse<?> modify(DetalleCuotaPrestamo obj) throws SQLException {
		
		TransactionResponse<?> tr = TransactionResponse.create();
		
		try {
				tr=_model.modify(obj.toDictionary(),obj.toIdentifiableDictionary());
		}catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		
		return tr;
	}

	@Override
	public TransactionResponse<DetalleCuotaPrestamo> getAll() throws SQLException {
		
		TransactionResponse<Dictionary> tr =dbCon.fetch("SELECT * FROM " + printTDB());
		TransactionResponse<DetalleCuotaPrestamo> t = TransactionResponse.create();
		
		if(tr.nonEmptyResult()) {
			t.rowsReturned = lgcp.convert(tr.rowsReturned);			
		}
		
		return t;
	}

	@Override
	public TransactionResponse<DetalleCuotaPrestamo> getById(Integer id) throws SQLException {
		
		TransactionResponse<Dictionary> td = dbCon.fetch(
				"SELECT * FROM " + printTDB() + " WHERE id_DTPT=@id",
				Dictionary.fromArray("id",id));
		
		TransactionResponse<DetalleCuotaPrestamo> t = TransactionResponse.create();
		
		if(td.nonEmptyResult()) {
			t.rowsReturned=lgcp.convert(td.rowsReturned);			
		}
		
		return t;
	}

	@Override
	public boolean exists(Integer id) throws SQLException {
		return _model.exists(Dictionary.fromArray("id_DTPT",id));
	}
	
	public TransactionResponse<DetalleCuotaPrestamo> filterByUserName(Cliente c) throws SQLException{
		
		return select("SELECT * FROM " + printTDB() + " WHERE usuario_cl_DTPT = @usuario",Dictionary.fromArray("usuario",c.getUsuario()));
			
	}

	private TransactionResponse<DetalleCuotaPrestamo> select(String arg0, Dictionary arg1) throws SQLException{
		
		TransactionResponse<Dictionary> td = dbCon.fetch(arg0,arg1);
		TransactionResponse<DetalleCuotaPrestamo> t = TransactionResponse.create();
		
		if(td.nonEmptyResult()) {
			t.rowsReturned = lgcp.convert(td.rowsReturned);
		}
		
		return t;
	}

}
