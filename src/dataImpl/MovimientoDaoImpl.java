package dataImpl;

import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

import data.IMovimientoDao;
import entity.Cliente;
import entity.Cuenta;
import entity.Movimiento;
import entity.Paginator;
import logicImpl.MovimientoLogicImpl;
import max.Connector;
import max.Dictionary;
import max.IModel;
import max.IRecord;
import max.MySQLSchemaModel;
import max.Schema;
import max.SchemaProperty;
import max.TransactionResponse;
import oops.SchemaValidationException;

public class MovimientoDaoImpl implements IRecord<Movimiento,Integer>, IMovimientoDao {
	
	public static void main(String[] args) {
		MovimientoDaoImpl l = new MovimientoDaoImpl();
		
		try {
			l.getInformeMovimientos();
			//String tipo1 = cantidad.rowsReturned.get(0).$("Cantidad");
			//System.out.println("CANTIDAD ===" + tipo1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
		
		
	
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
	
	public final static IModel _model = new MySQLSchemaModel("movimientos","tif",_schema) {{
		compile();
	}};

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
		return getAll(Paginator.DEFAULT);
	}

	/* (non-Javadoc)
	 * @see dataImpl.IMovimientoDao#getById(java.lang.Integer)
	 */
	@Override
	public TransactionResponse<Movimiento> getById(Integer id) throws SQLException {
		
		TransactionResponse<Dictionary> td = dbCon.fetch("SELECT * FROM movimientos__select WHERE id_Mv=@idMv",
				Dictionary.fromArray("idMv",id));
		TransactionResponse<Movimiento> t = TransactionResponse.create();
		
		if(td.nonEmptyResult()) {
			t.rowsReturned = lgm.convert(td.rowsReturned);
		}		
		
		return t;
	}
	
	public TransactionResponse<Dictionary> getInformeMovimientos() throws SQLException{
		TransactionResponse<Dictionary> rows = dbCon.fetch(
				"SELECT cod_TPMV_Mv, COUNT(*) AS Cantidad FROM movimientos GROUP BY cod_TPMV_Mv"  
				);
		if(rows.nonEmptyResult()) {
		Dictionary row = new Dictionary();
		row.put("tipo1", rows.rowsReturned.get(0));
		if(rows.rowsReturned.size() > 1) { row.put("tipo2", rows.rowsReturned.get(1));}
		if(rows.rowsReturned.size() > 2) { row.put("tipo3", rows.rowsReturned.get(2));}
		if(rows.rowsReturned.size() > 3) { row.put("tipo4", rows.rowsReturned.get(3));}
		
		
		for (int i = 1; i <= rows.rowsReturned.size(); i++) {	
		//int cantidad = (int)row.get("Cantidad").getOrDefault("Cantidad", 0);            
           	
		//System.out.println("TIPO = "+ i +", CANTIDAD =  "+ (int)cantidad);
			return rows;
			}
		}
		return rows;
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


	@Override
	public TransactionResponse<Movimiento> getAll(Paginator paginator) throws SQLException {
		return select(
			"CALL movimientos__getAll(@page, @size, NULL)",
			new Dictionary().paginate(paginator)
		);
	}


	@Override
	public TransactionResponse<Movimiento> getAll(Cliente cliente, Paginator paginator) throws SQLException {
		return select(
				"CALL movimientos__getAllFromAccount(@dni, @page, @size, NULL)",
				Dictionary.fromArray(
					"dni", cliente.getDNI()
				).paginate(paginator)
			);
	}


	@Override
	public TransactionResponse<Movimiento> getAll(Cuenta cuenta, Paginator paginator) throws SQLException {
		return select(
				"CALL movimientos__getAllFromAccount(@cuenta, @page, @size, NULL)",
				Dictionary.fromArray(
					"cuenta", cuenta.getNumero()
				).paginate(paginator)
			);
	}

}
