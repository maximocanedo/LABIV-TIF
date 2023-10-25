package data;

import java.sql.SQLException;
import java.sql.Types;

import entity.Administrador;
import logic.AdministradorLogic;
import max.data.Dictionary;
import max.data.IRecord;
import max.data.TransactionResponse;
import max.net.Connector;
import max.oops.SchemaValidationException;
import max.schema.IModel;
import max.schema.MySQLSchemaModel;
import max.schema.Schema;
import max.schema.SchemaProperty;

public class AdministradorDao implements IRecord<Administrador, String> {
	
	private Connector db = new Connector(_model.getDatabaseName());
	private AdministradorLogic logic = new AdministradorLogic();
	
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
			
		}};
		public static SchemaProperty dni = new SchemaProperty("dni_admin") {{
			unique = true;
			required = true;
			type = Types.VARCHAR;
			matches = "^[0-9]+$";
			maxlength = 12;
			trim = true;
		}};
		public static SchemaProperty cuil = new SchemaProperty("cuil_admin") {{
			unique = true;
			required = true;
			type = Types.VARCHAR;
			matches = "^[0-9]+$";
			maxlength = 16;
			trim = true;
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
			trim = true;
		}};
		public static SchemaProperty nacionalidad = new SchemaProperty("nacionalidad_admin") {{
			required = true;
			type = Types.VARCHAR;
			maxlength = 2;
			ref = PaisDao._model.ref("code");
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
			ref = LocalidadDao._model.ref("id_loc");
		}};
		public static SchemaProperty provincia = new SchemaProperty("provincia_admin") {{
			required = true;
			type = Types.INTEGER;
			min = 0;
			ref = ProvinciaDao._model.ref("id_provincia");
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
			res = _model.modify(Dictionary.fromArray("estado_admin"), a.toIdentifiableDictionary());
		} catch (SchemaValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public boolean exists(String arg0) throws SQLException {
		Administrador a = new Administrador();
		a.setUsuario(arg0);
		return _model.exists(a.toIdentifiableDictionary());
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

	@Override
	public TransactionResponse<?> modify(Administrador arg0) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = _model.modify(arg0.toDictionary(), arg0.toIdentifiableDictionary());
		} catch (SchemaValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public TransactionResponse<Administrador> getFullById(String arg0) throws SQLException {
		TransactionResponse<Administrador> res = new TransactionResponse<Administrador>();
		TransactionResponse<Dictionary> rd = db.fetch("SELECT * FROM " + printTDB() + " WHERE usuario_admin = @user", Dictionary.fromArray( "user", arg0 ));
		if(rd.nonEmptyResult()) res.rowsReturned = logic.convert(rd.rowsReturned, true);
		return res;
	}

	@Override
	public TransactionResponse<Administrador> select(String arg0) throws SQLException {
		TransactionResponse<Administrador> res = new TransactionResponse<Administrador>();
		TransactionResponse<Dictionary> rd = db.fetch(arg0);
		if(rd.nonEmptyResult()) {
			res.rowsReturned = logic.convert(rd.rowsReturned);
		}
		return res;
	}

	@Override
	public TransactionResponse<Administrador> select(String arg0, Dictionary arg1) throws SQLException {
		TransactionResponse<Administrador> res = new TransactionResponse<Administrador>();
		TransactionResponse<Dictionary> rd = db.fetch(arg0, arg1);
		if(rd.nonEmptyResult()) {
			res.rowsReturned = logic.convert(rd.rowsReturned);
		}
		return res;
	}

	@Override
	public TransactionResponse<Administrador> select(String arg0, Object[] arg1) throws SQLException {
		TransactionResponse<Administrador> res = new TransactionResponse<Administrador>();
		TransactionResponse<Dictionary> rd = db.fetch(arg0, arg1);
		if(rd.nonEmptyResult()) {
			res.rowsReturned = logic.convert(rd.rowsReturned);
		}
		return res;
	}

}
