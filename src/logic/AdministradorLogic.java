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
	
	/**
	 * Clase de datos.
	 */
	private static AdministradorDao data = new AdministradorDao();
	
	/**
	 * Obtener la estructura de datos de la entidad.
	 * @return
	 */
	public Schema getSchema() {
		return AdministradorDao._schema;
	}
	
	/**
	 * Obtener la estructura inicial de datos de la entidad. La que se usa a la hora de crear una cuenta.
	 * @return
	 */
	public Schema getInitialSchema() {
		return AdministradorDao._initial;
	}
	
	/**
	 * Convierte un Dictionary a un objeto Administrador, omitiendo los campos HASH y SALT.
	 */
	@Override
	public Administrador convert(Dictionary d) {
		return convertFull(d, false);
	}
	
	/**
	 * Convierte un Dictionary a un objeto Administrador, con la opción de omitir los campos HASH y SALT.
	 * @param d El objeto Dictionary a convertir.
	 * @param pass Especifica si se deben incluir los campos HASH y SALT.
	 * @return Objeto Administrador convertido.
	 */
	private Administrador convertFull(Dictionary d, boolean pass) {
		boolean privateData = pass;
		Administrador a = new Administrador();
		a.setUsuario(d.$("usuario_admin"));
		a.setDNI(d.$("dni_admin"));
		a.setCUIL(d.$("cuil_admin"));
		a.setNombre(d.$("nombre_admin"));
		a.setApellido(d.$("apellido_admin"));
		a.setSexo(d.$("sexo_admin"));
		if(d.$("nacionalidad_admin") != null) {
			a.setNacionalidad(new Pais() {{ setCodigo(d.$("nacionalidad_admin")); }});
		}
		if(d.$("fechaNacimiento_admin") != null) {
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
			
		}
		a.setDireccion(d.$("direccion_admin"));
		if(d.$("localidad_admin") != null) {
			Number loc = (d.$("localidad_admin"));
			int locc = -1;
			try {
				Double loc2 = Double.parseDouble(loc + "");
				locc = (int) Math.round(loc2);
			} catch(NumberFormatException e) {
				e.printStackTrace();
			}
			int locId = locc;
			a.setLocalidad(new Localidad() {{ setId(locId); }});
		}
		if(d.$("provincia_admin") != null) {
			Number prov = d.$("provincia_admin");
			int provv = -1;
			try {
				Double prov2 = Double.parseDouble(prov + "");
				provv = (int) Math.round(prov2);
			} catch(NumberFormatException e) {
				e.printStackTrace();
			}
			int provId = provv;
			a.setProvincia(new Provincia() {{ setId(provId); }});
		}
		
		if(privateData) {
			a.setHash(d.$("hash_admin"));
			a.setSalt(d.$("salt_admin"));
		}
		a.setCorreo(d.$("correo_admin"));
		if(d.$("estado_admin") != null) {
			a.setEstado(d.$("estado_admin"));
		}
		return a;
	}

	/**
	 * Convierte una lista de Dictionary en una lista de objetos Administrador, con la opción de omitir los campos HASH y SALT.
	 * @param arg0 Lista de Dictionary que se desea convertir.
	 * @param includePrivateData Especifica si se deben incluir los campos HASH y SALT.
	 * @return Lista de objetos Administrador.
	 */
	public List<Administrador> convert(List<Dictionary> arg0, boolean includePrivateData) {
		List<Administrador> l = new ArrayList<Administrador>();
		for(Dictionary d : arg0) {
			l.add(convertFull(d, includePrivateData));
		}
		return l;
	}
	
	/**
	 * Convierte una lista de Dictionary en una lista de objetos Administrador, omitiendo los campos HASH y SALT.
	 * @param arg0 Lista de Dictionary que se desea convertir.
	 * @return Lista de objetos Administrador.
	 */
	@Override
	public List<Administrador> convert(List<Dictionary> arg0) {
		return convert(arg0, false);
	}
	
	/**
	 * Convierte un objeto TransactionResponse a un LogicResponse.
	 * @param data Objeto a convertir.
	 * @return Objeto convertido.
	 */
	public LogicResponse<Administrador> convert(TransactionResponse<Administrador> data) {
		LogicResponse<Administrador> x = new LogicResponse<Administrador>();
		x.status = data.status;
		x.errorMessage = data.dbError == null ? null : data.dbError.getMessage();
		x.listReturned = data.rowsReturned;
		x.exception = data.error;
		return x;
	}
	
	/**
	 * Convierte un objeto TransactionResponse con wildcard a un LogicResponse parametrizado.
	 * @param data Objeto a convertir.
	 * @return Objeto convertido.
	 */
	public LogicResponse<Administrador> convertWildcard(TransactionResponse<?> data) {
		LogicResponse<Administrador> x = new LogicResponse<Administrador>();
		x.status = data.status;
		x.errorMessage = data.dbError == null ? null : data.dbError.getMessage();
		x.exception = data.error;
		return x;
	}

	/**
	 * Deshabilita una cuenta de administrador.
	 * @param arg0 Objeto Administrador con los datos del usuario a deshabilitar.
	 * @returns Resultado de la operación.
	 */
	@Override
	public LogicResponse<Administrador> delete(Administrador arg0) {
		TransactionResponse<?> res;
		LogicResponse<Administrador> result = new LogicResponse<Administrador>();
		try {
			res = data.delete(arg0);
			result = convertWildcard(res);
			result.message = result.status ? "El registro se eliminó correctamente. " : "No se eliminó el registro. ";
			result.http = result.status ? 200 : 500;
		} catch (SQLException e) {
			result.die(false, 500, "Hubo un error al intentar realizar la transacción. ");
			e.printStackTrace();
		}
		return result;		
	}
	
	/**
	 * Examina si existe un usuario en la base de datos a partir de su nombre de usuario.
	 * @param arg0 Nombre de usuario.
	 */
	@Override
	public LogicResponse<Administrador> exists(String arg0) {
		LogicResponse<Administrador> res = new LogicResponse<Administrador>();
		boolean o = false;
		try {
			o = data.exists(arg0);
			res.die(o, o ? 200 : 404, "");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, 500, "");
		}
		return res;
	}
	
	/**
	 * Comprueba si un usuario está activo en la base de datos a partir de su nombre de usuario.
	 * @param arg0 Nombre de usuario.
	 * @return Resultado de la operación.
	 */
	public LogicResponse<Administrador> isActive(String arg0) {
		LogicResponse<Administrador> result = new LogicResponse<Administrador>();
		try {
			boolean res = data.exists(Dictionary.fromArray(
						AdministradorDao.Fields.usuario.name, arg0,
						AdministradorDao.Fields.estado.name, true
					));
			result.status = res;
			result.http = res ? 200 : 404;
			result.message = res ? "El usuario está activo. " : "El usuario fue deshabilitado o no existe. ";
		} catch(SQLException e) {
			result.die(false, 500, "Hubo un error al intentar realizar la consulta. ");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Examina si existe un usuario en la base de datos a partir de su número de documento.
	 * @param dni Número de documento a buscar.
	 * @return Resultado de la operación.
	 */
	public LogicResponse<Administrador> DNIExists(String dni) {
		LogicResponse<Administrador> res = new LogicResponse<Administrador>();
		boolean o = false;
		try {
			o = data.exists(Dictionary.fromArray(AdministradorDao.Fields.dni.name, dni));
			res.die(o, o ? 200 : 404, "");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, 500, "");
		}
		return res;
	}
	
	/**
	 * Examina si existe un usuario en la base de datos a partir de su número de CUIL.
	 * @param cuil Número de CUIL a buscar.
	 * @return Resultado de la operación. 
	 */
	public LogicResponse<Administrador> CUILExists(String cuil) {
		LogicResponse<Administrador> res = new LogicResponse<Administrador>();
		boolean o = false;
		try {
			o = data.exists(Dictionary.fromArray(AdministradorDao.Fields.cuil.name, cuil));
			res.die(o, o ? 200 : 404, "");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, 500, "");
		}
		return res;
	}

	/**
	 * Devuelve todos los registros de la base de datos.
	 */
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

	/**
	 * Devuelve un registro cuyo nombre de usuario coincida con el dado.
	 * @param arg0 Nombre de usuario.
	 */
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

	/**
	 * Inserta un nuevo registro en la base de datos.
	 * @param arg0 Objeto Administrador con los datos a insertar.
	 */
	@Override
	public LogicResponse<Administrador> insert(Administrador arg0) {
		LogicResponse<Administrador> result = new LogicResponse<Administrador>();
		try {
			result = convertWildcard(data.insert(arg0));
			result.message = result.status ? "El registro se insertó correctamente. " : "No se insertó ningún registro. ";
			result.http = result.status ? 201 : 500;
		} catch (SQLException e) {
			result.die(false, 500, "Hubo un error al intentar realizar la transacción. ");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Valida los datos recibidos en base a la estructura inicial.
	 * Se usa para validar la información antes de crear una cuenta de administrador.
	 * @param d Datos a validar.
	 * @return Resultado de la validación.
	 * @throws SchemaValidationException Si uno de los campos no cumple alguna de las validaciones requeridas.
	 */
	public boolean validateInitialSchema(Dictionary d) throws SchemaValidationException {
		IModel _eModel = new MySQLSchemaModel("administradores", "tif", AdministradorDao._initial);
		return _eModel.validate(d);
	}
	
	/**
	 * Crea una cuenta de administrador.
	 * @param d Datos del nuevo usuario.
	 * @return Resultado de la operación.
	 */
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
        Administrador obj = convertFull(finalData, true);
        res = insert(obj);
        // Devolvemos el resultado de la operación.
        return res;		
	}

	/**
	 * Actualiza la contraseña de un administrador.
	 * @param admin Objeto Administrador con su nombre de usuario establecido.
	 * @param params La nueva contraseña.
	 * @return Resultado de la operación.
	 */
	public LogicResponse<Administrador> updatePassword(Administrador admin, Dictionary params) {
		LogicResponse<Administrador> result = new LogicResponse<Administrador>();
		Schema updatePasswordSchema = new Schema(AdministradorDao.Fields.contraseña);
		try {
			boolean validationStatus = updatePasswordSchema.validate(params);
			if(validationStatus) {
				byte[] salt = PasswordUtils.createSalt();
				byte[] hash = PasswordUtils.createHash(params.$(AdministradorDao.Fields.contraseña.name), salt);
				try {
					result = convertWildcard(data.updatePassword(admin.getUsuario(), hash, salt));
					result.http = result.status ? 200 : 500;
					result.errorMessage = result.status ? "La contraseña se actualizó correctamente. " : "No se actualizó la contraseña. ";
				} catch (SQLException e) {
					result.die(false, 500, "Hubo un problema al intentar actualizar la contraseña. ");
					e.printStackTrace();
				}
				
			}
		} catch (SchemaValidationException e) {
			result.die(false, 400, e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Valida una contraseña en texto plano con la contraseña encriptada de una cuenta de administrador existente.
	 * @param admin Objeto Administrador con su nombre de usuario, hash y salt establecidos.
	 * @param pass Contraseña en texto plano a validar.
	 * @return Resultado de la operación. 
	 */
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
	
	/**
	 * Intenta iniciar sesión, y devuelve un token JWT.
	 * @param user Usuario
	 * @param pass Contraseña, en texto plano
	 * @return
	 */
	public LogicResponse<Administrador> login(String user, String pass) {
		LogicResponse<Administrador> res = new LogicResponse<Administrador>();
		// Validar que el usuario exista:
		LogicResponse<Administrador> userExists =  exists(user);
		if(userExists.status) {
			try {
				TransactionResponse<Administrador> res2 = data.getFullById(user);
				if(res2.nonEmptyResult()) {
					Administrador adm = res2.rowsReturned.get(0);
					if(adm.isEstado()) {
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
					} else {
						res.die(false, 403, "User was disabled and cannot log in. ");
					}
					
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
	
	/**
	 * [TODO Mover de sitio] Inicia sesión a partir de parámetros del Servlet.
	 * @param servlet_parameters Parámetros recibidos del servlet.
	 * @return Resultado de la operación.
	 */
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
	
	/**
	 * Método main usado para pruebas.
	 * @param args
	 */
	public static void test(String[] args) {
		AdministradorLogic logic = new AdministradorLogic();
		
		Dictionary exampleUser = Dictionary.fromArray(
				"usuario_admin", "root",
				"dni_admin", "10000001",
				"cuil_admin", "10100000010",
				"nombre_admin", "Administrador",
				"apellido_admin", "del Sistema",
				"sexo_admin", "M",
				"nacionalidad_admin", "AR",
				"fechaNacimiento_admin", "2000-01-01",
				"direccion_admin", " ~ No address ~ ",
				"localidad_admin", 78007,
				"provincia_admin", 78,
				"correo_admin", "root@support.bank.org",
				"estado_admin", true,
				"password_admin", "D2g%CF**#3$9!62bNL&F$T$7724"
			); //*/
		//AdministradorLogic logic = new AdministradorLogic();
		logic.createAccount(exampleUser);
		//LogicResponse<Administrador> resInicioSesion = logic.login("roote3035", "mip.ass_9036");
	}
	
	/**
	 * Modifica datos en un objeto Administrador.
	 */
	@Override
	public LogicResponse<Administrador> modify(Administrador arg0) {
		LogicResponse<Administrador> result = new LogicResponse<Administrador>();
		try {
			result = convertWildcard(data.modify(arg0));
			result.message = result.status ? "El registro se modificó correctamente. " : "No se modificó ningún registro. ";
			result.http = result.status ? 200 : 500;
		} catch (SQLException e) {
			result.die(false, 500, "Hubo un error al intentar realizar la transacción. ");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Modifica datos en un objeto Administrador.
	 * Recibe los parámetros directo del servlet, y los valida antes de enviarlo a la sobrecarga de único parámetro de esta función.
	 * @param arg0 Parámetros enviados por el servlet en cuestión.
	 * @param user Usuario a ser modificado
	 * @return Resultado de la operación.
	 */
	public LogicResponse<Administrador> modify(Dictionary arg0, Administrador user) {
		LogicResponse<Administrador> res = new LogicResponse<Administrador>();
		Administrador obj = new Administrador();
		try {
			MySQLSchemaModel model = new MySQLSchemaModel("administradores", "tif", AdministradorDao._editable);
			Dictionary v = model.prepareForEditing(arg0);
			v.put(AdministradorDao.Fields.usuario.name, user.getUsuario());
			
			obj = convert(v);
			return modify(obj);
		} catch (SchemaValidationException e) {
			res.die(false, 400, e.getMessage());
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * Valida un objeto Administrador.
	 * @param arg0 Objeto Administrador a validar.
	 * @param validateConstraints Especifica si se deben validar las claves foráneas o únicas, como el usuario o el DNI.
	 */
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
