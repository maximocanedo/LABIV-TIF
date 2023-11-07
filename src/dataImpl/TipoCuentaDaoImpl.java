package dataImpl;

import java.sql.SQLException;
import java.sql.Types;

import data.ITipoCuentaDao;
import entity.TipoCuenta;
import logicImpl.TipoCuentaLogicImpl;
import max.Connector;
import max.Dictionary;
import max.IModel;
import max.IRecord;
import max.MySQLSchemaModel;
import max.Schema;
import max.SchemaProperty;
import max.TransactionResponse;
import oops.SchemaValidationException;

public class TipoCuentaDaoImpl implements IRecord<TipoCuenta, String>, ITipoCuentaDao{

	public TipoCuentaDaoImpl(){}
	private TipoCuentaLogicImpl tpLogic= new TipoCuentaLogicImpl();
	
	public static final Schema tablaTP = new Schema(
			//2 llaves porque al ser clase anonima la tengo que encerrar 
			//El otro par es para llenar las variables de la clase
			new SchemaProperty("Cod_TPCT") {{
				required= true;
				primary= true;
				type= Types.VARCHAR;
				maxlength= 4;
			}},
			new SchemaProperty("Descripcion_TPCT") {{
				required= true;
				type= Types.VARCHAR;
				maxlength= 100;
			}}
	);
	public static final IModel _model= new MySQLSchemaModel("TipoCuenta","tif",tablaTP);
	private Connector db = new Connector(_model.getDatabaseName());
	
	/* (non-Javadoc)
	 * @see dataImpl.ITipoCuentaDao#printTDB()
	 */
	@Override
	public String printTDB() {
		return _model.getDatabaseName() + "." + _model.getTableName();
	}
	
	/* (non-Javadoc)
	 * @see dataImpl.ITipoCuentaDao#insert(entity.TipoCuenta)
	 */
	@Override
	public TransactionResponse<?> insert(TipoCuenta data) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = _model.create(data.toDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see dataImpl.ITipoCuentaDao#delete(entity.TipoCuenta)
	 */
	@Override
	public TransactionResponse<?> delete(TipoCuenta data) throws SQLException {
		TransactionResponse<?> res = null;
		try {
			res = _model.delete(data.toIdentifiableDictionary());
		} catch (SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see dataImpl.ITipoCuentaDao#modify(entity.TipoCuenta)
	 */
	@Override
	public TransactionResponse<?> modify(TipoCuenta data) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = _model.modify(data.toDictionary(), data.toIdentifiableDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see dataImpl.ITipoCuentaDao#getAll()
	 */
	@Override
	public TransactionResponse<TipoCuenta> getAll() throws SQLException {
		TransactionResponse<Dictionary> rows= db.fetch(
				"select * from "+ printTDB()
		);
		TransactionResponse<TipoCuenta> rowsTP= new TransactionResponse<TipoCuenta>();
		if(rows.nonEmptyResult()) {
			rowsTP.rowsReturned= tpLogic.convert(rows.rowsReturned);
		}
		return rowsTP;
	}

	/* (non-Javadoc)
	 * @see dataImpl.ITipoCuentaDao#getById(java.lang.String)
	 */
	@Override
	public TransactionResponse<TipoCuenta> getById(String Cod_TPCT) throws SQLException {
		TransactionResponse<Dictionary> rows= db.fetch(
				"select * from "+ printTDB() + "where Cod_TPCT = @Cod_TPCT",
				Dictionary.fromArray("Cod_TPCT",Cod_TPCT)
		);
		TransactionResponse<TipoCuenta> rowsTP= new TransactionResponse<TipoCuenta>();
		if(rows.nonEmptyResult()) {
			rowsTP.rowsReturned= tpLogic.convert(rows.rowsReturned);
		}
		return rowsTP;
	}

	/* (non-Javadoc)
	 * @see dataImpl.ITipoCuentaDao#exists(java.lang.String)
	 */
	@Override
	public boolean exists(String Cod_TPCT) throws SQLException {
		
		return _model.exists(Dictionary.fromArray("Cod_TPCT",Cod_TPCT));
	}

}
