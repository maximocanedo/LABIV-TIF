package data;

import java.sql.SQLException;
import java.sql.Types;
import entity.TelefonosXCliente;
import logic.TelefonoXClienteLogic;
import max.*;
import oops.*;

public class TelefonoXClienteDao implements IRecord<TelefonosXCliente, String>{

	public TelefonoXClienteDao() {}
	private TelefonoXClienteLogic tpLogic= new TelefonoXClienteLogic();
	

	public static final Schema tablaTP = new Schema(
			//2 llaves porque al ser clase anonima la tengo que encerrar 
			//El otro par es para llenar las variables de la clase
			new SchemaProperty("Dni_Cl_TxC") {{
				required= true;
				primary= true;
				type= Types.VARCHAR;
				maxlength= 60;
			}},
			new SchemaProperty("Tel_TxC") {{
				required= true;
				type= Types.VARCHAR;
				maxlength= 30;
			}},
			new SchemaProperty("Activo_TxC") {{
				required= true;
				type= Types.BIT;
				maxlength= 1;
			}}
	);
	public static final IModel _model= new MySQLSchemaModel("TelefonosXCliente","tif",tablaTP);
	private Connector db = new Connector(_model.getDatabaseName());
	
	public String printTDB() {
		return _model.getDatabaseName() + "." + _model.getTableName();
	}
	
	@Override
	public TransactionResponse<?> insert(TelefonosXCliente data) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = _model.create(data.toDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public TransactionResponse<?> delete(TelefonosXCliente data) throws SQLException {
		TransactionResponse<?> res = null;
		try {
			res = _model.delete(data.toIdentifiableDictionary());
		} catch (SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public TransactionResponse<?> modify(TelefonosXCliente data) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = _model.modify(data.toDictionary(), data.toIdentifiableDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public TransactionResponse<TelefonosXCliente> getAll() throws SQLException {
		TransactionResponse<Dictionary> rows= db.fetch(
				"select * from "+ printTDB()
		);
		TransactionResponse<TelefonosXCliente> rowsTP= new TransactionResponse<TelefonosXCliente>();
		if(rows.nonEmptyResult()) {
			rowsTP.rowsReturned= tpLogic.convert(rows.rowsReturned);
		}
		return rowsTP;
	}

	@Override
	public TransactionResponse<TelefonosXCliente> getById(String DNI_Usuario) throws SQLException {
		TransactionResponse<Dictionary> rows= db.fetch(
				"select * from "+ printTDB() + "where DNI_Usuario = @DNI_Usuario",
				Dictionary.fromArray("DNI_Usuario",DNI_Usuario)
		);
		TransactionResponse<TelefonosXCliente> rowsTP= new TransactionResponse<TelefonosXCliente>();
		if(rows.nonEmptyResult()) {
			rowsTP.rowsReturned= tpLogic.convert(rows.rowsReturned);
		}
		return rowsTP;
	}

	@Override
	public boolean exists(String DNI_Usuario) throws SQLException {
		
		return _model.exists(Dictionary.fromArray("DNI_Usuario",DNI_Usuario));
	}
	
}
