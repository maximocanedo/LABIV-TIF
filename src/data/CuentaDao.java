package data;

import java.sql.SQLException;
import java.sql.Types;
import entity.Cuenta;
import logic.CuentaLogic;
import max.data.Dictionary;
import max.data.IRecord;
import max.data.TransactionResponse;
import max.net.Connector;
import max.oops.SchemaValidationException;
import max.schema.IModel;
import max.schema.MySQLSchemaModel;
import max.schema.Schema;
import max.schema.SchemaProperty;

public class CuentaDao implements IRecord<Cuenta, String>{

	public CuentaDao(){}
	private CuentaLogic clLogic= new CuentaLogic();
	
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
			new SchemaProperty("SaldoCuenta_CxC") {{
				required= true;
				type= Types.DECIMAL;
				maxlength= 10;
			}},
			new SchemaProperty("Cod_TPCT_CxC") {{
				required= true;
				type= Types.CHAR;
				maxlength= 4;
				ref = TipoCuentaDao._model.ref("Cod_TPCT");
			}},
			new SchemaProperty("Dni_Cl_CxC") {{
				required= true;
				type= Types.VARCHAR;
				maxlength= 60;
				ref = ClienteDao._model.ref("dni");
				modifiable = false;
			}},
			new SchemaProperty("Activo_CxC") {{
				required= true;
				type= Types.BIT;
				
			}}

	);
	public static final IModel _model= new MySQLSchemaModel("Cuentas","tif",tablaCL);
	private Connector db = new Connector(_model.getDatabaseName());
	
	public String printTDB() {
		return _model.getDatabaseName() + "." + _model.getTableName();
	}
	
	@Override
	public TransactionResponse<?> insert(Cuenta data) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = _model.create(data.toDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
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

	@Override
	public TransactionResponse<?> modify(Cuenta data) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = _model.modify(data.toDictionary(), data.toIdentifiableDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public TransactionResponse<Cuenta> getAll() throws SQLException {
		TransactionResponse<Dictionary> rows= db.fetch(
				"select * from "+ printTDB()
		);
		TransactionResponse<Cuenta> rowsTP= new TransactionResponse<Cuenta>();
		if(rows.nonEmptyResult()) {
			rowsTP.rowsReturned= clLogic.convert(rows.rowsReturned);
		}
		return rowsTP;
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

}