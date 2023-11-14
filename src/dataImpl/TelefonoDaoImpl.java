package dataImpl;

import java.sql.SQLException;
import java.sql.Types;

import data.ITelefonoDao;
import entity.Cliente;
import entity.Cuenta;
import entity.Telefono;
import logicImpl.TelefonoLogicImpl;
import max.Connector;
import max.Dictionary;
import max.IModel;
import max.IRecord;
import max.MySQLSchemaModel;
import max.Schema;
import max.SchemaProperty;
import max.TransactionResponse;
import oops.SchemaValidationException;

public class TelefonoDaoImpl implements IRecord<Telefono, String>, ITelefonoDao{
	public TelefonoDaoImpl() {}
	private TelefonoLogicImpl tpLogic= new TelefonoLogicImpl();
	

	public static final Schema tablaTP = new Schema(
			//2 llaves porque al ser clase anonima la tengo que encerrar 
			//El otro par es para llenar las variables de la clase
			new SchemaProperty("Dni") {{
				required= true;
				primary= true;
				type= Types.VARCHAR;
				maxlength= 60;
			}},
			new SchemaProperty("Tel") {{
				required= true;
				//primary= true; da error, lo hare desde el workbench
				type= Types.VARCHAR;
				maxlength= 30;
			}},
			new SchemaProperty("Activo") {{
				required= true;
				type= Types.BIT;
				defaultValue = true;
			}}
	);
	public static final IModel _model= new MySQLSchemaModel("telefonos","tif",tablaTP) {{
		compile();
	}};
	private Connector db = new Connector(_model.getDatabaseName());
	
	public String printTDB() {
		return _model.getDatabaseName() + "." + _model.getTableName();
	}
	
	@Override
	public TransactionResponse<?> insert(Telefono data) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = _model.create(data.toDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public TransactionResponse<?> delete(Telefono data) throws SQLException {
		TransactionResponse<?> res = null;
		try {
			res = _model.delete(data.toIdentifiableDictionary());
		} catch (SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public TransactionResponse<?> modify(Telefono data) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = _model.modify(data.toDictionary(), data.toIdentifiableDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public TransactionResponse<Telefono> getAll() throws SQLException {
		TransactionResponse<Dictionary> rows= db.fetch(
				"select * from "+ printTDB()
		);
		TransactionResponse<Telefono> rowsTP= new TransactionResponse<Telefono>();
		if(rows.nonEmptyResult()) {
			rowsTP.rowsReturned= tpLogic.convert(rows.rowsReturned);
		}
		return rowsTP;
	}

	@Override
	public TransactionResponse<Telefono> getById(String DNI_Usuario) throws SQLException {
		TransactionResponse<Dictionary> rows= db.fetch(
				"select * from "+ printTDB() + "where Dni = @DNI_Usuario",
				Dictionary.fromArray("DNI_Usuario",DNI_Usuario)
		);
		TransactionResponse<Telefono> rowsTP= new TransactionResponse<Telefono>();
		if(rows.nonEmptyResult()) {
			rowsTP.rowsReturned= tpLogic.convert(rows.rowsReturned);
		}
		return rowsTP;
	}
 
	@Override
	public boolean exists(String DNI_Usuario) throws SQLException {
		
		return _model.exists(Dictionary.fromArray("Dni",DNI_Usuario));
	}

	public TransactionResponse<Telefono> getAll(Cliente obj) throws SQLException {
		String dniCL= obj.getDNI();
		TransactionResponse<Dictionary> rows= db.fetch(
				"select * from telefonos where Dni = @dniCL",
				Dictionary.fromArray("Dni",dniCL)
		);
		TransactionResponse<Telefono> rowsTP= new TransactionResponse<Telefono>();
		if(rows.nonEmptyResult()) {
			rowsTP.rowsReturned= tpLogic.convert(rows.rowsReturned);
		}
		return rowsTP;
	}
}
