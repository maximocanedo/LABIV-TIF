package dataImpl;

import java.sql.SQLException;
import java.sql.Types;

import data.IMovimientoDao;
import entity.Cuenta;
import entity.Movimiento;
import logicImpl.MovimientoLogicImpl;
import max.Dictionary;
import max.IRecord;
import max.TransactionResponse;
import max.data.*;
import max.net.Connector;
import max.oops.SchemaValidationException;
import max.schema.*;

public class MovimientoDaoImpl implements IRecord<Movimiento,Integer>, IMovimientoDao {
	
	public final static Schema _schema = new Schema(
			new SchemaProperty("id_Mv") {{
				required=true;
				primary=true;
				type=Types.INTEGER;
				autoIncrement=true;				
			}},
			new SchemaProperty("num_cuenta_CxC_Mv") {{
				required=true;
				type=Types.VARCHAR;
				maxlength=10;
				minlength=1;
				ref=CuentaDaoImpl._model.ref("Num_Cuenta_CxC");
			}},
			new SchemaProperty("cod_Con_Mv") {{
				required=true;
				type=Types.VARCHAR;
				maxlength=4;
				minlength=1;
				ref=ConceptoDaoImpl._model.ref("cod_Con");
			}},
			new SchemaProperty("saldo_anterior_Mv") {{
				required=true;
				type=Types.DECIMAL;
				maxlength=10;
			}},
			new SchemaProperty("importe_Mv") {{
				required=true;
				type=Types.DECIMAL;
				maxlength=10;
			}},
			new SchemaProperty("saldo_posterior_Mv") {{
				required=true;
				type=Types.DECIMAL;
				maxlength=10;
			}},
			new SchemaProperty("fechaMov_Mv") {{
				required=true;
				type=Types.DATE;
			}},
			new SchemaProperty("cod_TPMV_Mv") {{
				required=true;
				type=Types.VARCHAR;
				maxlength=4;
				minlength=1;
				ref=TipoMovimientoDaoImpl._model.ref("cod_TPMV");
			}}			
			);
	
	public final static IModel _model = new MySQLSchemaModel("movimientos","tif",_schema);
	
	private Connector dbCon = new Connector(_model.getDatabaseName());
	private MovimientoLogicImpl lgm = new MovimientoLogicImpl();
	
	/* (non-Javadoc)
	 * @see dataImpl.IMovimientoDao#printTDB()
	 */
	@Override
	public String printTDB() {
		return _model.getDatabaseName() + "." + _model.getTableName();
	}
			

	/* (non-Javadoc)
	 * @see dataImpl.IMovimientoDao#insert(entity.Movimiento)
	 */
	@Override
	public TransactionResponse<?> insert(Movimiento obj) throws SQLException {
		
		TransactionResponse<?> t = TransactionResponse.create();
		
		try {
				t=_model.create(obj.toDictionary());			
		}catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		
		return t;
	}

	/* (non-Javadoc)
	 * @see dataImpl.IMovimientoDao#delete(entity.Movimiento)
	 */
	@Override
	public TransactionResponse<?> delete(Movimiento obj) throws SQLException {
		
		TransactionResponse<?> t = TransactionResponse.create();
		
		try {
				t = _model.delete(obj.toIdentifiableDictionary());			
		}catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		
		return t;
	}

	/* (non-Javadoc)
	 * @see dataImpl.IMovimientoDao#modify(entity.Movimiento)
	 */
	@Override
	public TransactionResponse<?> modify(Movimiento obj) throws SQLException {
		
		TransactionResponse<?> t = TransactionResponse.create();
		
		try {
				t=_model.modify(obj.toDictionary(),obj.toIdentifiableDictionary());
		}catch(SchemaValidationException e) {
			e.printStackTrace();
		}		
		
		return t;
	}

	/* (non-Javadoc)
	 * @see dataImpl.IMovimientoDao#getAll()
	 */
	@Override
	public TransactionResponse<Movimiento> getAll() throws SQLException {
		
		TransactionResponse<Dictionary> td = dbCon.fetch("SELECT * FROM " + printTDB());
		TransactionResponse<Movimiento> t = TransactionResponse.create();
		
		if(td.nonEmptyResult()) {
			t.rowsReturned = lgm.convert(td.rowsReturned);
		}		
		
		return t;
	}

	/* (non-Javadoc)
	 * @see dataImpl.IMovimientoDao#getById(java.lang.Integer)
	 */
	@Override
	public TransactionResponse<Movimiento> getById(Integer id) throws SQLException {
		
		TransactionResponse<Dictionary> td = dbCon.fetch("SELECT * FROM " + printTDB() + " WHERE id_Mv=@idMv",
				Dictionary.fromArray("idMv",id));
		TransactionResponse<Movimiento> t = TransactionResponse.create();
		
		if(td.nonEmptyResult()) {
			t.rowsReturned = lgm.convert(td.rowsReturned);
		}		
		
		return t;
	}
	
	/* (non-Javadoc)
	 * @see dataImpl.IMovimientoDao#filterByAccountNumber(entity.Cuenta)
	 */
	@Override
	public TransactionResponse<Movimiento> filterByAccountNumber(Cuenta c) throws SQLException{
		
		return select("SELECT * FROM " + printTDB() + " WHERE num_cuenta_CxC_Mv = @numC",Dictionary.fromArray("numC",c.getNum_Cuenta_CxC()));
			
	}

	/* (non-Javadoc)
	 * @see dataImpl.IMovimientoDao#exists(java.lang.Integer)
	 */
	@Override
	public boolean exists(Integer id) throws SQLException {
		return _model.exists(Dictionary.fromArray("id_Mv",id));
	}
	
	private TransactionResponse<Movimiento> select(String arg0, Dictionary arg1) throws SQLException{
		
		TransactionResponse<Dictionary> td = dbCon.fetch(arg0,arg1);
		TransactionResponse<Movimiento> t = TransactionResponse.create();
		
		if(td.nonEmptyResult()) {
			t.rowsReturned = lgm.convert(td.rowsReturned);
		}
		
		return t;
	}

}
