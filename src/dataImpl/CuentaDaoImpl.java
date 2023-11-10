package dataImpl;

import java.sql.SQLException;
import java.sql.Types;


import data.ICuentaDao;
import entity.Cliente;
import entity.Cuenta;
import entity.Paginator;
import logicImpl.CuentaLogicImpl;
import max.Connector;
import max.Dictionary;
import max.IModel;
import max.IRecord;
import max.MySQLSchemaModel;
import max.Schema;
import max.SchemaProperty;
import max.TransactionResponse;
import oops.SchemaValidationException;

public class CuentaDaoImpl implements IRecord<Cuenta, String>, ICuentaDao {
	
	public CuentaDaoImpl(){}
	private CuentaLogicImpl clLogic= new CuentaLogicImpl();
	
	public static final Schema tablaCL = new Schema(
			new SchemaProperty("Num_Cuenta_CxC") {{
				required= true;
				primary= true;
				type= Types.VARCHAR;
				maxlength= 10;
				modifiable = false;
			}},
			new SchemaProperty("CBU_CxC") {{
				required= true;
				type= Types.VARCHAR;
				maxlength= 22;
				modifiable = false;
			}},
			new SchemaProperty("FechaCreacion_CxC") {{
				required= true;
				type= Types.DATE;
				modifiable = false;
			}},
			new SchemaProperty("saldoCuenta_CxC") {{
				required= true;
				type= Types.DECIMAL;
				maxlength= 10;
			}},
			new SchemaProperty("Cod_TPCT_CxC") {{
				required= true;
				type= Types.VARCHAR;
				maxlength= 4;
				ref = TipoCuentaDaoImpl._model.ref("Cod_TPCT");
			}},
			new SchemaProperty("Dni_Cl_CxC") {{
				required= true;
				type= Types.VARCHAR;
				maxlength= 60;
				ref = ClienteDaoImpl._model.ref("dni");
				modifiable = false;
			}},
			new SchemaProperty("Activo_CxC") {{
				required= true;
				type= Types.BIT;
				
			}}

	);
	public static final IModel _model= new MySQLSchemaModel("Cuentas","tif",tablaCL) {{
		compile();
	}};
	private Connector db = new Connector(_model.getDatabaseName());
	
	public String printTDB() {
		return _model.getDatabaseName() + "." + _model.getTableName();
	}
	
	public int countUserAccounts(String dni) throws SQLException {
		TransactionResponse<Dictionary> res = new Connector().fetch(
			"SELECT COUNT(*) as counted FROM " + printTDB() + " WHERE Dni_Cl_CxC = @Dni",
			Dictionary.fromArray("Dni", dni)
		);
		if(res.nonEmptyResult()) {
			Dictionary row = res.rowsReturned.get(0);
			long counted = row.$("counted");
			return Integer.parseInt(""  + counted);
		}
		return -1;
	}
	
	/*@Override
	public TransactionResponse<?> insert(Cuenta data) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = _model.create(data.toDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}*/
	
	@Override
	public TransactionResponse<?> insert(Cuenta data) throws SQLException {
		TransactionResponse<Dictionary> rows= db.fetch(
				"CALL SP_AGREGARNUEVACUENTABANCARIA (@dni , @fechaCreacion , @tipoCuenta )",
				Dictionary.fromArray("dni",data.getCliente().getDNI(),
									 "fechaCreacion" , data.getFechaCreacion(),
									 "tipoCuenta" , data.getTipo().getCod_TPCT()
									 )
		);
		TransactionResponse<Cuenta> rowsTP= new TransactionResponse<Cuenta>();
		if(rows.nonEmptyResult()) {
			return rowsTP;
		}
		return rowsTP;
	}

	@Override
	public TransactionResponse<?> delete(Cuenta data) throws SQLException {
		TransactionResponse<?> res = null;
		try {
			res = _model.delete(data.toIdentifiableDictionary());
		} catch (SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	/*@Override
	public TransactionResponse<?> modify(Cuenta data) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = _model.modify(data.toDictionary(), data.toIdentifiableDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}*/
	@Override
	public TransactionResponse<?> modify(Cuenta data) throws SQLException {
		TransactionResponse<Dictionary> rows= db.fetch(
				"CALL SP_ModificarCuentaBancaria (@NumCuenta , @DNI ,@TipoCuenta ,@Saldo,@Estado)",
				Dictionary.fromArray("NumCuenta" , data.getNumero(),
									 "DNI",data.getCliente().getDNI(),
									 "TipoCuenta" , data.getTipo().getCod_TPCT(),
									 "Saldo" , data.getSaldo(),
									 "Estado", data.getEstado()
									 )
		);
		TransactionResponse<Cuenta> rowsTP= new TransactionResponse<Cuenta>();
		if(rows.nonEmptyResult()) {
			return rowsTP;
		}
		return rowsTP;
	}

	@Override
	public TransactionResponse<Cuenta> getAll(Paginator paginator) throws SQLException {
		TransactionResponse<Dictionary> rows= db.fetch(
			"CALL cuentas__getAll(@page, @size, NULL)",
			new Dictionary().paginate(paginator)
		);
		TransactionResponse<Cuenta> rowsTP= new TransactionResponse<Cuenta>();
		if(rows.nonEmptyResult()) {
			rowsTP.rowsReturned= clLogic.convert(rows.rowsReturned);
		}
		return rowsTP;
	}
	@Override
	public TransactionResponse<Cuenta> getAll() throws SQLException {
		return getAll(Paginator.DEFAULT);
	}

	@Override
	public TransactionResponse<Cuenta> getById(String Num_Cuenta_CxC) throws SQLException {
		TransactionResponse<Dictionary> rows= db.fetch(
				"select * from "+ printTDB() + "where Num_Cuenta_CxC = @Num_Cuenta_CxC",
				Dictionary.fromArray("Num_Cuenta_CxC",Num_Cuenta_CxC)
		);
		TransactionResponse<Cuenta> rowsTP= new TransactionResponse<Cuenta>();
		if(rows.nonEmptyResult()) {
			rowsTP.rowsReturned= clLogic.convert(rows.rowsReturned);
		}
		return rowsTP;
	}

	@Override
	public boolean exists(String Num_Cuenta_CxC) throws SQLException {
		
		return _model.exists(Dictionary.fromArray("Num_Cuenta_CxC",Num_Cuenta_CxC));
	}

	
	public boolean existsCBU(String CBU){
		return _model.exists(Dictionary.fromArray("CBU_CxC", CBU));
	}
	
	
	public TransactionResponse<Cuenta> getAllFor(Cliente obj) throws SQLException {
		TransactionResponse<Dictionary> res = db.fetch(
			"SELECT * FROM " + printTDB() + " WHERE Dni_Cl_CxC = @dni ",
			Dictionary.fromArray("dni", obj.getDNI())
		);
		TransactionResponse<Cuenta> fres = new TransactionResponse<Cuenta>();
		fres.status = res.status;
		if(res.nonEmptyResult()) {
			fres.rowsReturned = clLogic.convert(res.rowsReturned);
		}
		return fres;
		
	}

}