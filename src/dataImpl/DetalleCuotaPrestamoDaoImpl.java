package dataImpl;

import java.sql.SQLException;
import java.sql.Types;

import data.IDetalleCuotaPrestamoDao;
import entity.Cliente;
import entity.Cuota;
import entity.SolicitudPrestamo;
import logicImpl.DetalleCuotaPrestamoLogicImpl;
import max.Connector;
import max.Dictionary;
import max.IModel;
import max.IRecord;
import max.MySQLSchemaModel;
import max.Schema;
import max.SchemaProperty;
import max.TransactionResponse;
import oops.SchemaValidationException;

public class DetalleCuotaPrestamoDaoImpl implements IRecord<Cuota,Integer>, IDetalleCuotaPrestamoDao {
	
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
				ref=ClienteDaoImpl._model.ref("usuario");
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
				ref=PrestamosClienteDaoImpl._model.ref("cod_Sol_PxC");
			}}			
			);	
	
	
	public final static IModel _model = new MySQLSchemaModel("detalle_cuotasprestamo","tif",_schema) {{
		compile();
	}};
	
	private Connector dbCon = new Connector(_model.getDatabaseName());
	private DetalleCuotaPrestamoLogicImpl lgcp = new DetalleCuotaPrestamoLogicImpl();
	
	public String printTDB() {
		return _model.getDatabaseName() + "." + _model.getTableName();
	}
	
	
	@Override
	public TransactionResponse<?> insert(Cuota obj) throws SQLException {
		
		TransactionResponse<?> tr = TransactionResponse.create();
		
		try {
				tr= _model.create(obj.toDictionary());			
		}catch(SchemaValidationException e) {
			e.printStackTrace();			
		}
		
		return tr;		
	}

	@Override
	public TransactionResponse<?> delete(Cuota obj) throws SQLException {
		
		TransactionResponse<?> tr = TransactionResponse.create();
		
		try {
				tr=_model.delete(obj.toIdentifiableDictionary());			
		}catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		
		return tr;
	}

	@Override
	public TransactionResponse<?> modify(Cuota obj) throws SQLException {
		
		TransactionResponse<?> tr = TransactionResponse.create();
		
		try {
				tr=_model.modify(obj.toDictionary(),obj.toIdentifiableDictionary());
		}catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		
		return tr;
	}

	@Override
	public TransactionResponse<Cuota> getAll() throws SQLException {
		
		TransactionResponse<Dictionary> tr =dbCon.fetch("SELECT * FROM " + printTDB());
		TransactionResponse<Cuota> t = TransactionResponse.create();
		
		if(tr.nonEmptyResult()) {
			t.rowsReturned = lgcp.convert(tr.rowsReturned);			
		}
		
		return t;
	}
	
	public TransactionResponse<Cuota> getAll(Cliente obj) throws SQLException {
		
		TransactionResponse<Dictionary> res = dbCon.fetch(
				"SELECT * FROM cuotas__select WHERE usuario_cl_DTPT = @user ",
				Dictionary.fromArray("user", obj.getUsuario())
			);
			TransactionResponse<Cuota> fres = new TransactionResponse<Cuota>();
			fres.status = res.status;
			if(res.nonEmptyResult()) {
				fres.rowsReturned = lgcp.convert(res.rowsReturned);
			}
			return fres;
	}
	
public TransactionResponse<Cuota> getByRequest(SolicitudPrestamo obj) throws SQLException {
		
		TransactionResponse<Dictionary> res = dbCon.fetch(
				"SELECT * FROM tif.detallecuotas WHERE cod_Sol_DTPT = @sp ",
				Dictionary.fromArray("sp", obj.getCodigo())
			);
			TransactionResponse<Cuota> fres = new TransactionResponse<Cuota>();
			fres.status = res.status;
			if(res.nonEmptyResult()) {
				fres.rowsReturned = lgcp.convert(res.rowsReturned);
			}
			return fres;
	}

	@Override
	public TransactionResponse<Cuota> getById(Integer id) throws SQLException {
		
		TransactionResponse<Dictionary> td = dbCon.fetch(
				"SELECT * FROM " + printTDB() + " WHERE id_DTPT=@id",
				Dictionary.fromArray("id",id));
		
		TransactionResponse<Cuota> t = TransactionResponse.create();
		
		if(td.nonEmptyResult()) {
			t.rowsReturned=lgcp.convert(td.rowsReturned);			
		}
		
		return t;
	}

	@Override
	public boolean exists(Integer id) throws SQLException {
		return _model.exists(Dictionary.fromArray("id_DTPT",id));
	}
	
	public TransactionResponse<Cuota> filterByUserName(Cliente c) throws SQLException{
		
		return select("SELECT * FROM " + printTDB() + " WHERE usuario_cl_DTPT = @usuario",Dictionary.fromArray("usuario",c.getUsuario()));
			
	}

	private TransactionResponse<Cuota> select(String arg0, Dictionary arg1) throws SQLException{
		
		TransactionResponse<Dictionary> td = dbCon.fetch(arg0,arg1);
		TransactionResponse<Cuota> t = TransactionResponse.create();
		
		if(td.nonEmptyResult()) {
			t.rowsReturned = lgcp.convert(td.rowsReturned);
		}
		
		return t;
	}



}
