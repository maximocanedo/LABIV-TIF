package dataImpl;

import java.sql.SQLException;
import java.sql.Types;

import data.ITipoMovimientoDao;
import entity.TipoMovimiento;
import logicImpl.TipoMovimientoLogic;
import max.data.*;
import max.data.IRecord;
import max.data.TransactionResponse;
import max.net.Connector;
import max.oops.SchemaValidationException;
import max.schema.*;

public class TipoMovimientoDaoImpl implements IRecord<TipoMovimiento,String>, ITipoMovimientoDao {
	
	public final static Schema _schema = new Schema(
			new SchemaProperty("cod_TPMV"){{
				required=true;
				primary=true;
				type=Types.VARCHAR;
				maxlength=4;
				minlength=1;				
			}},
			new SchemaProperty("descripcion_TPMV"){{
				required=true;
				type=Types.VARCHAR;
				maxlength=100;
				minlength=1;
			}}			
		);
	
	public final static IModel _model = new MySQLSchemaModel("tipo_movimiento","tif",_schema) {{
		compile();
	}};
	
	private Connector dbCon = new Connector(_model.getDatabaseName());
	private TipoMovimientoLogic tmlg = new TipoMovimientoLogic();
	
	/* (non-Javadoc)
	 * @see dataImpl.ITipoMovimientoDao#printTDB()
	 */
	@Override
	public String printTDB() {
		return _model.getDatabaseName() + "." + _model.getTableName();
	}

	/* (non-Javadoc)
	 * @see dataImpl.ITipoMovimientoDao#insert(entity.TipoMovimiento)
	 */
	@Override
	public TransactionResponse<?> insert(TipoMovimiento obj) throws SQLException {
		
		TransactionResponse<?> tr = TransactionResponse.create();
		
		try {
				tr= _model.create(obj.toDictionary());			
		}catch(SchemaValidationException e) {
			e.printStackTrace();			
		}
		
		return tr;
	}

	/* (non-Javadoc)
	 * @see dataImpl.ITipoMovimientoDao#delete(entity.TipoMovimiento)
	 */
	@Override
	public TransactionResponse<?> delete(TipoMovimiento obj) throws SQLException {
		
		TransactionResponse<?> tr = TransactionResponse.create();
		
		try {
				tr=_model.delete(obj.toIdentifiableDictionary());			
		}catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		
		return tr;
	}

	/* (non-Javadoc)
	 * @see dataImpl.ITipoMovimientoDao#modify(entity.TipoMovimiento)
	 */
	@Override
	public TransactionResponse<?> modify(TipoMovimiento obj) throws SQLException {
		
		TransactionResponse<?> tr = TransactionResponse.create();
		
		try {
				tr=_model.modify(obj.toDictionary(),obj.toIdentifiableDictionary());
		}catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		
		return tr;
	}
	
	

	/* (non-Javadoc)
	 * @see dataImpl.ITipoMovimientoDao#getAll()
	 */
	@Override
	public TransactionResponse<TipoMovimiento> getAll() throws SQLException {
		
		TransactionResponse<Dictionary> tr =dbCon.fetch("SELECT * FROM " + printTDB());
		TransactionResponse<TipoMovimiento> t = TransactionResponse.create();
		
		if(tr.nonEmptyResult()) {
			t.rowsReturned = tmlg.convert(tr.rowsReturned);			
		}
		
		return t;
	}

	/* (non-Javadoc)
	 * @see dataImpl.ITipoMovimientoDao#getById(java.lang.String)
	 */
	@Override
	public TransactionResponse<TipoMovimiento> getById(String cod_TPMV) throws SQLException {
		TransactionResponse<Dictionary> td = dbCon.fetch(
				"SELECT * FROM " + printTDB() + " WHERE cod_TPMV=@codTPMV",
				Dictionary.fromArray("codTPMV",cod_TPMV));
		
		TransactionResponse<TipoMovimiento> t = TransactionResponse.create();
		
		if(td.nonEmptyResult()) {
			t.rowsReturned=tmlg.convert(td.rowsReturned);			
		}
		
		return t;
	}

	/* (non-Javadoc)
	 * @see dataImpl.ITipoMovimientoDao#exists(java.lang.String)
	 */
	@Override
	public boolean exists(String codTPMV) throws SQLException {
		return _model.exists(Dictionary.fromArray("cod_TPMV",codTPMV));
	}

}
