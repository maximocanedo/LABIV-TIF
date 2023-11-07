package dataImpl;

import java.sql.SQLException;
import java.sql.Types;

import data.IAdministradorDao;
import entity.Administrador;
import logicImpl.AdministradorLogicImpl;
import max.Connector;
import max.Dictionary;
import max.IModel;
import max.IRecord;
import max.MySQLSchemaModel;
import max.Schema;
import max.SchemaProperty;
import max.TransactionResponse;
import max.oops.SchemaValidationException;

public class AdministradorDaoImpl implements IRecord<Administrador, String>, IAdministradorDao {
	
	private Connector db = new Connector(_model.getDatabaseName());
	private AdministradorLogicImpl logic = new AdministradorLogicImpl();
	
	// Campos
	public static class Fields {
		public static SchemaProperty usuario = new SchemaProperty("usuario_admin") {{
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
		public static SchemaProperty dni = new SchemaProperty("dni_admin") {{
			unique = true;
			required = true;
			type = Types.VARCHAR;
			matches = "^[0-9]+$";
			maxlength = 12;
			trim = true;
			modifiable = false;
		}};
		public static SchemaProperty cuil = new SchemaProperty("cuil_admin") {{
			unique = true;
			required = true;
			type = Types.VARCHAR;
			matches = "^[0-9]+$";
			maxlength = 16;
			trim = true;
			modifiable = false;
		}};
		public static SchemaProperty nombre = new SchemaProperty("nombre_admin") {{
			required = true;
			type = Types.VARCHAR;
			maxlength = 48;
			minlength = 1;
			trim = true;
		}};
		public static SchemaProperty apellido = new SchemaProperty("apellido_admin") {{
			required = true;
			type = Types.VARCHAR;
			maxlength = 48;
			minlength = 1;
			trim = true;
		}};
		public static SchemaProperty sexo = new SchemaProperty("sexo_admin") {{
			required = true;
			type = Types.VARCHAR;
			maxlength = 3;
			minlength = 1;
			matches = "^[MF]$";
			trim = true;
		}};
		public static SchemaProperty nacionalidad = new SchemaProperty("nacionalidad_admin") {{
			required = true;
			type = Types.VARCHAR;
			maxlength = 2;
			ref = PaisDaoImpl._model.ref("code");
			minlength = 2;
		}};
		public static SchemaProperty fechaNacimiento = new SchemaProperty("fechaNacimiento_admin") {{
			required = true;
			type = Types.DATE;
		}};
		public static SchemaProperty direccion = new SchemaProperty("direccion_admin") {{
			required = true;
			type = Types.VARCHAR;
			maxlength = 128;
			minlength = 1;
		}};
		public static SchemaProperty localidad = new SchemaProperty("localidad_admin") {{
			required = true;
			type = Types.INTEGER;
			min = 0;
			ref = LocalidadDaoImpl._model.ref("id_loc");
		}};
		public static SchemaProperty provincia = new SchemaProperty("provincia_admin") {{
			required = true;
			type = Types.INTEGER;
			min = 0;
			ref = ProvinciaDaoImpl._model.ref("id_provincia");
		}};
		public static SchemaProperty correo = new SchemaProperty("correo_admin") {{
			required = true;
			type = Types.VARCHAR;
			matches = "^(.+)@(\\S+)$";
			minlength = 5;
			maxlength = 255;
		}};
		public static SchemaProperty hash = new SchemaProperty("hash_admin") {{
			required = true;
			select = false;
			type = Types.VARBINARY;
			maxlength = 64;
		}};
		public static SchemaProperty salt = new SchemaProperty("salt_admin") {{
			required = true;
			select = false;
			type = Types.VARBINARY;
			maxlength = 24;
		}};
		public static SchemaProperty estado = new SchemaProperty("estado_admin") {{
			required = true;
			type = Types.BIT;
			defaultValue = true;
		}};
		public static SchemaProperty contraseña = new SchemaProperty("password_admin") {{
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
			Fields.usuario,
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
				Fields.contraseña,
				Fields.estado
			);

	public static final IModel _model = new MySQLSchemaModel("administradores", "tif", _schema) {{
		compile(true);
	}};
	
	
	public String printTDB() {
		return _model.getDatabaseName() + "." + _model.getTableName();
	}
	

	@Override
	public TransactionResponse<?> delete(Administrador a) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			// Bug #41. Descomentar cuando se haya resuelto.
			res = _model.modify(Dictionary.fromArray("estado_admin", false), a.toIdentifiableDictionary());
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
		Administrador a = new Administrador();
		a.setUsuario(arg0);
		return _model.exists(a.toIdentifiableDictionary());
	}
	public boolean exists(Dictionary d) throws SQLException {
		return _model.exists(d);
	}

	@Override
	public TransactionResponse<Administrador> getAll() throws SQLException {
		return select("SELECT * FROM " + printTDB());
	}

	@Override
	public TransactionResponse<Administrador> getById(String arg0) throws SQLException {
		return select("SELECT * FROM " + printTDB() + " WHERE usuario_admin = @user", Dictionary.fromArray("user", arg0));
	}

	@Override
	public TransactionResponse<?> insert(Administrador arg0) throws SQLException {
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
	public TransactionResponse<?> modify(Administrador arg0) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		IModel model = new MySQLSchemaModel("administradores", "tif", _editable);
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
	
	public TransactionResponse<Administrador> getFullById(String arg0) throws SQLException {
		TransactionResponse<Administrador> res = new TransactionResponse<Administrador>();
		TransactionResponse<Dictionary> rd = db.fetch("SELECT * FROM " + printTDB() + " WHERE usuario_admin = @user", Dictionary.fromArray( "user", arg0 ));
		if(rd.nonEmptyResult()) res.rowsReturned = logic.convert(rd.rowsReturned, true);
		return res;
	}

	private TransactionResponse<Administrador> select(String arg0) throws SQLException {
		TransactionResponse<Administrador> res = new TransactionResponse<Administrador>();
		TransactionResponse<Dictionary> rd = db.fetch(arg0);
		if(rd.nonEmptyResult()) {
			res.rowsReturned = logic.convert(rd.rowsReturned);
		}
		return res;
	}

	private TransactionResponse<Administrador> select(String arg0, Dictionary arg1) throws SQLException {
		TransactionResponse<Administrador> res = new TransactionResponse<Administrador>();
		TransactionResponse<Dictionary> rd = db.fetch(arg0, arg1);
		if(rd.nonEmptyResult()) {
			res.rowsReturned = logic.convert(rd.rowsReturned);
		}
		return res;
	}

}
