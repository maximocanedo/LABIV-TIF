package data;

import java.sql.SQLException;
import java.sql.Types;

import entity.Cliente;
import logic.ClienteLogic;
import max.data.Dictionary;
import max.data.IRecord;
import max.data.TransactionResponse;
import max.net.Connector;
import max.oops.SchemaValidationException;
import max.schema.IModel;
import max.schema.MySQLSchemaModel;
import max.schema.Schema;
import max.schema.SchemaProperty;

public class ClienteDao implements IRecord<Cliente, String> {

	public ClienteDao() {
		// TODO Auto-generated constructor stub
	}

	private Connector db = new Connector(_model.getDatabaseName());
	private ClienteLogic logic = new ClienteLogic();
	
	// Campos
	public static class Fields {
		public Fields() {}
		public static SchemaProperty usuario = new SchemaProperty("usuario") {{
			primary = true;
			required = true;
			unique = true;
			type = Types.VARCHAR;
			matches = "^[a-zA-Z0-9_]{4,20}$";
			minlength = 4;
			maxlength = 20;
			trim = true;
			modifiable = false;
			
		}};
		public static SchemaProperty dni = new SchemaProperty("dni") {{
			unique = true;
			required = true;
			type = Types.VARCHAR;
			matches = "^[0-9]+$";
			maxlength = 12;
			trim = true;
			modifiable = false;
		}};
		public static SchemaProperty cuil = new SchemaProperty("cuil") {{
			unique = true;
			required = true;
			type = Types.VARCHAR;
			matches = "^[0-9]+$";
			maxlength = 16;
			trim = true;
			modifiable = false;
		}};
		public static SchemaProperty nombre = new SchemaProperty("nombre") {{
			required = true;
			type = Types.VARCHAR;
			maxlength = 48;
			minlength = 1;
			trim = true;
			modifiable = false;
		}};
		public static SchemaProperty apellido = new SchemaProperty("apellido") {{
			required = true;
			type = Types.VARCHAR;
			maxlength = 48;
			minlength = 1;
			trim = true;
			modifiable = false;
		}};
		public static SchemaProperty sexo = new SchemaProperty("sexo") {{
			required = true;
			type = Types.VARCHAR;
			maxlength = 3;
			minlength = 1;
			matches = "^[MF]$";
			trim = true;
			modifiable = false;
		}};
		public static SchemaProperty nacionalidad = new SchemaProperty("nacionalidad") {{
			required = true;
			type = Types.VARCHAR;
			maxlength = 2;
			ref = PaisDao._model.ref("code");
			minlength = 2;
			modifiable = false;
		}};
		public static SchemaProperty fechaNacimiento = new SchemaProperty("fechaNacimiento") {{
			required = true;
			type = Types.DATE;
			modifiable = false;
		}};
		public static SchemaProperty direccion = new SchemaProperty("direccion") {{
			required = true;
			type = Types.VARCHAR;
			maxlength = 128;
			minlength = 1;
		}};
		public static SchemaProperty localidad = new SchemaProperty("localidad") {{
			required = true;
			type = Types.INTEGER;
			min = 0;
			ref = LocalidadDao._model.ref("id_loc");
		}};
		public static SchemaProperty provincia = new SchemaProperty("provincia") {{
			required = true;
			type = Types.INTEGER;
			min = 0;
			ref = ProvinciaDao._model.ref("id_provincia");
		}};
		public static SchemaProperty correo = new SchemaProperty("correo") {{
			required = true;
			type = Types.VARCHAR;
			matches = "^(.+)@(\\S+)$";
			minlength = 5;
			maxlength = 255;
		}};
		public static SchemaProperty hash = new SchemaProperty("hash") {{
			required = true;
			select = false;
			type = Types.VARBINARY;
			maxlength = 64;
		}};
		public static SchemaProperty salt = new SchemaProperty("salt") {{
			required = true;
			select = false;
			type = Types.VARBINARY;
			maxlength = 24;
		}};
		public static SchemaProperty estado = new SchemaProperty("estado") {{
			required = true;
			type = Types.BIT;
			defaultValue = true;
		}};
		public static SchemaProperty contraseña = new SchemaProperty("password") {{
			required = true;
			minlength = 8;
			type = Types.VARCHAR;
		}};
		
	}
	// Estructuras de datos
	public static final Schema _schema = new Schema(
			Fields.usuario,
			Fields.dni,
			Fields.cuil,
			Fields.nombre,
			Fields.apellido,
			Fields.sexo,
			Fields.nacionalidad,
			Fields.fechaNacimiento,
			Fields.direccion,
			Fields.localidad,
			Fields.provincia,
			Fields.correo,
			Fields.hash,
			Fields.salt,
			Fields.estado
		);
	public static final Schema _editable = new Schema(
			Fields.usuario, // Para identificar al cliente. No se recibe como parámetro.
			Fields.nombre,
			Fields.apellido,
			Fields.sexo,
			Fields.nacionalidad,
			Fields.fechaNacimiento,
			Fields.direccion,
			Fields.localidad,
			Fields.provincia,
			Fields.correo
	);
	public static final Schema _initial = new Schema(
			
				Fields.dni,
				Fields.cuil,
				Fields.nombre,
				Fields.apellido,
				Fields.sexo,
				Fields.nacionalidad,
				Fields.fechaNacimiento,
				Fields.direccion,
				Fields.localidad,
				Fields.provincia,
				Fields.correo
			);

	public static final IModel _model = new MySQLSchemaModel("clientes", "tif", _schema) {{
		compile(true);
	}};
	
	
	public String printTDB() {
		return _model.getDatabaseName() + "." + _model.getTableName();
	}
	

	@Override
	public TransactionResponse<?> delete(Cliente a) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			// Bug #41. Descomentar cuando se haya resuelto.
			res = _model.modify(Dictionary.fromArray(Fields.estado.name, false), a.toIdentifiableDictionary());
			/*res = new Connector(_model.getDatabaseName())
					.transact(
						"UPDATE " + printTDB() + " SET " + Fields.estado.name + " = @estado WHERE " + Fields.usuario.name + " = @usuario",
						Dictionary.fromArray(
							"estado", false,
							"usuario", a.getUsuario()
						)
					); */
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (SchemaValidationException e) {
			res.status = false;
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Verifica si existe un registro con ese nombre de usuario.
	 * (!) No diferencia entre activo y no activo. 
	 * Si hay un registro inactivo con el nombre de usuario dado devuelve true.
	 */
	@Override
	public boolean exists(String arg0) throws SQLException {
		Cliente a = new Cliente();
		a.setUsuario(arg0);
		return _model.exists(a.toIdentifiableDictionary());
	}
	public boolean exists(Dictionary d) throws SQLException {
		return _model.exists(d);
	}

	@Override
	public TransactionResponse<Cliente> getAll() throws SQLException {
		return select("SELECT * FROM " + printTDB());
	}

	@Override
	public TransactionResponse<Cliente> getById(String arg0) throws SQLException {
		return select("SELECT * FROM " + printTDB() + " WHERE usuario = @user", Dictionary.fromArray("user", arg0));
	}

	@Override
	public TransactionResponse<?> insert(Cliente arg0) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = _model.create(arg0.toFullDictionary());
		} catch (SchemaValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	// Solucionar bug antes de usar este método.
	@Override
	public TransactionResponse<?> modify(Cliente arg0) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		IModel model = new MySQLSchemaModel("clientes", "tif", _editable);
		try {
			System.out.println(arg0.toString());
			res = model.modify(arg0.toDictionary(), arg0.toIdentifiableDictionary());
		} catch (SchemaValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	public TransactionResponse<?> updatePassword(String username, byte[] hash, byte[] salt) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		res = new Connector(_model.getDatabaseName())
				.transact(
					"UPDATE " + printTDB() + " SET " + Fields.hash.name + " = @hash, " + Fields.salt.name + " = @salt WHERE " + Fields.usuario.name + " = @usuario", 
					Dictionary.fromArray(
						"hash", hash,
						"salt", salt,
						"usuario", username
					)
				);
		return res;
	}
	
	public TransactionResponse<Cliente> getFullById(String arg0) throws SQLException {
		TransactionResponse<Cliente> res = new TransactionResponse<Cliente>();
		TransactionResponse<Dictionary> rd = db.fetch("SELECT * FROM " + printTDB() + " WHERE usuario = @user", Dictionary.fromArray( "user", arg0 ));
		if(rd.nonEmptyResult()) res.rowsReturned = logic.convert(rd.rowsReturned, true);
		return res;
	}

	@Override
	public TransactionResponse<Cliente> select(String arg0) throws SQLException {
		TransactionResponse<Cliente> res = new TransactionResponse<Cliente>();
		TransactionResponse<Dictionary> rd = db.fetch(arg0);
		if(rd.nonEmptyResult()) {
			res.rowsReturned = logic.convert(rd.rowsReturned);
		}
		return res;
	}

	@Override
	public TransactionResponse<Cliente> select(String arg0, Dictionary arg1) throws SQLException {
		TransactionResponse<Cliente> res = new TransactionResponse<Cliente>();
		TransactionResponse<Dictionary> rd = db.fetch(arg0, arg1);
		if(rd.nonEmptyResult()) {
			res.rowsReturned = logic.convert(rd.rowsReturned);
		}
		return res;
	}

	@Override
	public TransactionResponse<Cliente> select(String arg0, Object[] arg1) throws SQLException {
		TransactionResponse<Cliente> res = new TransactionResponse<Cliente>();
		TransactionResponse<Dictionary> rd = db.fetch(arg0, arg1);
		if(rd.nonEmptyResult()) {
			res.rowsReturned = logic.convert(rd.rowsReturned);
		}
		return res;
	}
}

