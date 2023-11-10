package logicImpl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dataImpl.AdministradorDaoImpl;
import entity.Administrador;
import entity.Localidad;
import entity.Paginator;
import entity.Pais;
import entity.Provincia;
import logic.IAdministradorLogic;
import max.Dictionary;
import max.IModel;
import max.IRecordLogic;
import max.MySQLSchemaModel;
import max.Response;
import max.Schema;
import max.TransactionResponse;
import oops.SchemaValidationException;

public class AdministradorLogicImpl implements IRecordLogic<Administrador, String>, IAdministradorLogic {
	
	/**
	 * Clase de datos.
	 */
	private static AdministradorDaoImpl data = new AdministradorDaoImpl();
	
	/**
	 * Obtener la estructura de datos de la entidad.
	 * @return
	 */
	public Schema getSchema() {
		return AdministradorDaoImpl._schema;
	}
	
	/**
	 * Obtener la estructura inicial de datos de la entidad. La que se usa a la hora de crear una cuenta.
	 * @return
	 */
	public Schema getInitialSchema() {
		return AdministradorDaoImpl._initial;
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
	public Response<Administrador> convert(TransactionResponse<Administrador> data) {
		Response<Administrador> x = new Response<Administrador>();
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
	public Response<Administrador> convertWildcard(TransactionResponse<?> data) {
		Response<Administrador> x = new Response<Administrador>();
		x.status = data.status;
		x.errorMessage = data.dbError == null ? null : data.dbError.getMessage();
		x.exception = data.error;
		return x;
	}

	public boolean isRoot(Administrador a) {
		return a.getUsuario().equals("root")
			|| a.getDNI().equals("10000001")
			|| a.getCUIL().equals("10100000010");
	}
	
	/**
	 * Deshabilita una cuenta de administrador.
	 * @param arg0 Objeto Administrador con los datos del usuario a deshabilitar.
	 * @returns Resultado de la operación.
	 */
	@Override
	public Response<Administrador> delete(Administrador arg0) {
		TransactionResponse<?> res;
		Response<Administrador> result = new Response<Administrador>();
		if(isRoot(arg0)) {
			result.die(false, 406, "Root user cannot be disabled or deleted. ");
			return result;
		}
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
	public Response<Administrador> exists(String arg0) {
		Response<Administrador> res = new Response<Administrador>();
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
	public Response<Administrador> isActive(String arg0) {
		Response<Administrador> result = new Response<Administrador>();
		try {
			boolean res = data.exists(Dictionary.fromArray(
						AdministradorDaoImpl.Fields.usuario.name, arg0,
						AdministradorDaoImpl.Fields.estado.name, true
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
	public Response<Administrador> DNIExists(String dni) {
		Response<Administrador> res = new Response<Administrador>();
		boolean o = false;
		try {
			o = data.exists(Dictionary.fromArray(AdministradorDaoImpl.Fields.dni.name, dni));
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
	public Response<Administrador> CUILExists(String cuil) {
		Response<Administrador> res = new Response<Administrador>();
		boolean o = false;
		try {
			o = data.exists(Dictionary.fromArray(AdministradorDaoImpl.Fields.cuil.name, cuil));
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
	public Response<Administrador> getAll() {
		Response<Administrador> res = new Response<Administrador>();
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
	 * Devuelve todos los registros de la base de datos (Paginado).
	 */
	@Override
	public Response<Administrador> getAll(Paginator paginator) {
		Response<Administrador> res = new Response<Administrador>();
		try {
			res = convert(data.getAll(paginator));
		} catch (SQLException e) {
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
	public Response<Administrador> getById(String arg0) {
		Response<Administrador> res = new Response<Administrador>();
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
	public Response<Administrador> insert(Administrador arg0) {
		Response<Administrador> result = new Response<Administrador>();
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
		IModel _eModel = new MySQLSchemaModel("administradores", "tif", AdministradorDaoImpl._initial);
		return _eModel.validate(d);
	}
	
	/**
	 * Crea una cuenta de administrador.
	 * @param d Datos del nuevo usuario.
	 * @return Resultado de la operación.
	 */
	public Response<Administrador> createAccount(Dictionary d) {
		Response<Administrador> res = new Response<Administrador>();
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
	public Response<Administrador> updatePassword(Administrador admin, Dictionary params) {
		Response<Administrador> result = new Response<Administrador>();
		Schema updatePasswordSchema = new Schema(AdministradorDaoImpl.Fields.contraseña);
		try {
			boolean validationStatus = updatePasswordSchema.validate(params);
			if(validationStatus) {
				byte[] salt = PasswordUtils.createSalt();
				byte[] hash = PasswordUtils.createHash(params.$(AdministradorDaoImpl.Fields.contraseña.name), salt);
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
	public Response<Administrador> validatePassword(Administrador admin, String pass) {
		Response<Administrador> response = new Response<Administrador>();
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
	public Response<Administrador> login(String user, String pass) {
		Response<Administrador> res = new Response<Administrador>();
		// Validar que el usuario exista:
		Response<Administrador> userExists =  exists(user);
		if(userExists.status) {
			try {
				TransactionResponse<Administrador> res2 = data.getFullById(user);
				if(res2.nonEmptyResult()) {
					Administrador adm = res2.rowsReturned.get(0);
					if(adm.isEstado()) {
						Response<Administrador> resI = validatePassword(adm, pass);
						if(resI.status) {
							// Inicio de sesión válido.
							String token = AuthManager.generateJWT(adm.getUsuario(), AuthManager.ADMIN);
							Response<Administrador> resT = new Response<Administrador>();
							resT.die(true, null);
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
	public Response<Administrador> login(Dictionary servlet_parameters) {
		Response<Administrador> response = new Response<Administrador>();
		Schema schema = new Schema(AdministradorDaoImpl.Fields.usuario, AdministradorDaoImpl.Fields.contraseña);
		try {
			boolean validationResult = schema.validate(servlet_parameters);
			if(validationResult) {
				return login(
						servlet_parameters.$(AdministradorDaoImpl.Fields.usuario.name),
						servlet_parameters.$(AdministradorDaoImpl.Fields.contraseña.name)
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
		AdministradorLogicImpl logic = new AdministradorLogicImpl();
		
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
	public Response<Administrador> modify(Administrador arg0) {
		Response<Administrador> result = new Response<Administrador>();
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
	public Response<Administrador> modify(Dictionary arg0, Administrador user) {
		Response<Administrador> res = new Response<Administrador>();
		Administrador obj = new Administrador();
		try {
			MySQLSchemaModel model = new MySQLSchemaModel("administradores", "tif", AdministradorDaoImpl._editable);
			Dictionary v = model.prepareForEditing(arg0);
			v.put(AdministradorDaoImpl.Fields.usuario.name, user.getUsuario());
			
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
	public Response<Administrador> validate(Administrador arg0, boolean validateConstraints) {
		Response<Administrador> res = new Response<Administrador>();
		try {
			res.status = validateConstraints 
					? AdministradorDaoImpl._model.validate(arg0.toDictionary()) 
					: AdministradorDaoImpl._schema.validate(arg0.toDictionary());
		} catch (SchemaValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, e.getMessage());
		}
		return res;
	}

}
