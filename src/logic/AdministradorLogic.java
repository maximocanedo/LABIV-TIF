package logic;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import data.AdministradorDao;
import entity.Administrador;
import entity.Localidad;
import entity.Pais;
import entity.Provincia;
import max.data.Dictionary;
import max.data.IRecordLogic;
import max.data.LogicResponse;
import max.data.TransactionResponse;
import max.oops.SchemaValidationException;
import max.schema.IModel;
import max.schema.MySQLSchemaModel;
import max.schema.Schema;

public class AdministradorLogic implements IRecordLogic<Administrador, String> {
	
	// Propiedades estáticas
	private static AdministradorDao data = new AdministradorDao();
	
	public Schema getSchema() {
		return AdministradorDao._schema;
	}
	public Schema getInitialSchema() {
		return AdministradorDao._initial;
	}
	
	@Override
	public Administrador convert(Dictionary d) {
		return convertR(d, false);
	}
	private Administrador convertR(Dictionary d, boolean pass) {
		boolean privateData = pass;
		Administrador a = new Administrador();
		a.setUsuario(d.$("usuario_admin"));
		a.setDNI(d.$("dni_admin"));
		a.setCUIL(d.$("cuil_admin"));
		a.setNombre(d.$("nombre_admin"));
		a.setApellido(d.$("apellido_admin"));
		a.setSexo(d.$("sexo_admin"));
		a.setNacionalidad(new Pais() {{ setCodigo(d.$("nacionalidad_admin")); }});
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate = new java.util.Date();
		try {
			parsedDate = dateFormat.parse(d.$("fechaNacimiento_admin") + "");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        java.sql.Date edate = new java.sql.Date(parsedDate.getTime());
		a.setFechaNacimiento(edate);
		a.setDireccion(d.$("direccion_admin"));
		Number loc = (d.$("localidad_admin"))
			 , prov = d.$("provincia_admin");
		int locc = -1, provv = -1;
		try {
			Double loc2 = Double.parseDouble(loc + "");
			Double prov2 = Double.parseDouble(prov + "");
			locc = (int) Math.round(loc2);
			provv = (int) Math.round(prov2);
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		int locId = locc;
		int provId = provv;
		
		a.setLocalidad(new Localidad() {{ setId(locId); }});
		a.setProvincia(new Provincia() {{ setId(provId); }});
		if(privateData) a.setHash(d.$("hash_admin"));
		if(privateData) a.setSalt(d.$("salt_admin"));
		a.setCorreo(d.$("correo_admin"));
		a.setEstado(d.$("estado_admin"));
		return a;
	}

	
	public List<Administrador> convert(List<Dictionary> arg0, boolean includePrivateData) {
		List<Administrador> l = new ArrayList<Administrador>();
		for(Dictionary d : arg0) {
			l.add(convertR(d, includePrivateData));
		}
		return l;
	}
	@Override
	public List<Administrador> convert(List<Dictionary> arg0) {
		return convert(arg0, false);
	}
	public LogicResponse<Administrador> convert(TransactionResponse<Administrador> data) {
		LogicResponse<Administrador> x = new LogicResponse<Administrador>();
		x.status = data.status;
		x.errorMessage = data.dbError == null ? null : data.dbError.getMessage();
		x.listReturned = data.rowsReturned;
		x.exception = data.error;
		return x;
	}
	
	public LogicResponse<Administrador> convertO(TransactionResponse<?> data) {
		LogicResponse<Administrador> x = new LogicResponse<Administrador>();
		x.status = data.status;
		x.errorMessage = data.dbError == null ? null : data.dbError.getMessage();
		x.exception = data.error;
		return x;
	}

	@Override
	public LogicResponse<Administrador> delete(Administrador arg0) throws SQLException {
		TransactionResponse<?> res = data.delete(arg0);
		return convertO(res);		
	}
	public LogicResponse<Administrador> deleteOne(Administrador arg0) {
		LogicResponse<Administrador> res = new LogicResponse<Administrador>();
		try {
			res = delete(arg0);
			res.message = res.status ? "El registro se eliminó correctamente. " : "No se eliminó el registro. ";
			res.http = res.status ? 201 : 500;
		} catch (SQLException e) {
			res.die(false, 500, "Hubo un problema al intentar eliminar el registro. ");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public LogicResponse<Administrador> exists(String arg0) {
		LogicResponse<Administrador> res = new LogicResponse<Administrador>();
		boolean o = false;
		try {
			o = data.exists(arg0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		res.die(o, "");
		return res;
	}

	@Override
	public LogicResponse<Administrador> getAll() {
		LogicResponse<Administrador> res = new LogicResponse<Administrador>();
		try {
			res = convert(data.getAll());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, "");
		}
		return res;
		
	}

	@Override
	public LogicResponse<Administrador> getById(String arg0) {
		LogicResponse<Administrador> res = new LogicResponse<Administrador>();
		try {
			res = convert(data.getById(arg0));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, "");
		}
		return res;
	}

	@Override
	public LogicResponse<Administrador> insert(Administrador arg0) throws SQLException {
		return convertO(data.insert(arg0));
	}
	public boolean validateInitialSchema(Dictionary d) throws SchemaValidationException {
		IModel _eModel = new MySQLSchemaModel("administradores", "tif", AdministradorDao._initial);
		return _eModel.validate(d);
	}
	
	
	public LogicResponse<Administrador> createAccount(Dictionary d) {
		LogicResponse<Administrador> res = new LogicResponse<Administrador>();
		// Validar datos
		boolean initialSchemaValidated = false;
		try {
			initialSchemaValidated = validateInitialSchema(d);
			if(!initialSchemaValidated) {
				res.die(false, "Unknown error during validation. ");
				return res;
			}
		} catch(SchemaValidationException e) {
			e.printStackTrace();
			res.die(false, e.getMessage());
			return res;
		}
		// Accedemos a la clave provista.
		String plainPassword = d.$("password_admin");
		// La encriptamos
        byte[] salt = PasswordUtils.createSalt();
        byte[] hash = PasswordUtils.createHash(plainPassword, salt);
        // Creamos el dictionary final que se subirá a la base de datos.
        Dictionary finalData = d;
        finalData.put("hash_admin", hash);
        finalData.put("salt_admin", salt);
        // Convertimos el Dictionary en objeto entidad para trabajar con métodos DAO.
        Administrador obj = convertR(finalData, true);
        try {
        	// Insertamos el objeto en la base de datos.
			res = insert(obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Devolvemos el resultado de la operación.
        return res;		
	}
	
	public LogicResponse<Administrador> validatePassword(Administrador admin, String pass) {
		LogicResponse<Administrador> response = new LogicResponse<Administrador>();
		byte[] originalHash = admin.getHash();
		byte[] originalSalt = admin.getSalt();
		byte[] newHash = PasswordUtils.createHash(pass, originalSalt);
		if(Arrays.equals(originalHash, newHash)) {
			response.status = true;
		} else {
			response.http = 401;
			response.status = false;
		}
		return response;
	}
	
	public LogicResponse<Administrador> login(String user, String pass) {
		LogicResponse<Administrador> res = new LogicResponse<Administrador>();
		// Validar que el usuario exista:
		LogicResponse<Administrador> userExists =  exists(user);
		if(userExists.status) {
			try {
				TransactionResponse<Administrador> res2 = data.getFullById(user);
				if(res2.nonEmptyResult()) {
					Administrador adm = res2.rowsReturned.get(0);
					LogicResponse<Administrador> resI = validatePassword(adm, pass);
					if(resI.status) {
						// Inicio de sesión válido.
						String token = AuthManager.generateJWT(adm.getUsuario(), AuthManager.ADMIN);
						LogicResponse<Administrador> resT = new LogicResponse<Administrador>();
						resT.die(true, "");
						resT.eField = token;
						return resT;
					}
					return resI;
					
				}
			} catch (SQLException e) {
				res.status = false;
				res.http = 500; // INTERNAL SERVER ERROR
				e.printStackTrace();
			}
		} else {
			res.http = 401; // UNAUTHORIZED
		}
		return res;
	}
	
	public LogicResponse<Administrador> login(Dictionary servlet_parameters) {
		LogicResponse<Administrador> response = new LogicResponse<Administrador>();
		Schema schema = new Schema(AdministradorDao.Fields.usuario, AdministradorDao.Fields.contraseña);
		try {
			boolean validationResult = schema.validate(servlet_parameters);
			if(validationResult) {
				return login(
						servlet_parameters.$(AdministradorDao.Fields.usuario.name),
						servlet_parameters.$(AdministradorDao.Fields.contraseña.name)
					);
			}
		} catch (SchemaValidationException e) {
			response.http = 400;
			response.status = false;
			response.message = e.getMessage();
			e.printStackTrace();
		}
		return response;
	}
	
	public static void main(String[] args) {
		AdministradorLogic logic = new AdministradorLogic();
		
		Administrador admin = new Administrador();
		admin.setUsuario("roote3035");
		logic.deleteOne(admin);
		/*Dictionary exampleUser = Dictionary.fromArray(
				"usuario_admin", "roote3035",
				"dni_admin", "47006272",
				"cuil_admin", "20240555",
				"nombre_admin", "Héctor",
				"apellido_admin", "Hernández",
				"sexo_admin", "M",
				"nacionalidad_admin", "AR",
				"fechaNacimiento_admin", "1980-07-05",
				"direccion_admin", "Av. Portugal 1320",
				"localidad_admin", 78007,
				"provincia_admin", 78,
				"correo_admin", "3015@gmail.com",
				"estado_admin", true,
				"password_admin", "mip.ass_9036"
			); */
		//AdministradorLogic logic = new AdministradorLogic();
		//logic.createAccount(exampleUser);
		//LogicResponse<Administrador> resInicioSesion = logic.login("roote3035", "mip.ass_9036");
	}
	
	

	@Override
	public LogicResponse<Administrador> modify(Administrador arg0) throws SQLException {
		return convertO(data.modify(arg0));
	}

	@Override
	public LogicResponse<Administrador> validate(Administrador arg0, boolean validateConstraints) {
		LogicResponse<Administrador> res = new LogicResponse<Administrador>();
		try {
			res.status = validateConstraints 
					? AdministradorDao._model.validate(arg0.toDictionary()) 
					: AdministradorDao._schema.validate(arg0.toDictionary());
		} catch (SchemaValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, e.getMessage());
		}
		return res;
	}

}
