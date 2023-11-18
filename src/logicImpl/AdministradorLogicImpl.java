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
	private static final AdministradorDaoImpl data = new AdministradorDaoImpl();
	
	/**
	 * Obtener la estructura de datos de la entidad.
	 * @return Schema
	 */
	public Schema getSchema() {
		return AdministradorDaoImpl._schema;
	}
	
	/**
	 * Obtener la estructura inicial de datos de la entidad. La que se usa a la hora de crear una cuenta.
	 * @return Estructura inicial de datos.
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
	 * @param privateData Especifica si se deben incluir los campos HASH y SALT.
	 * @return Objeto Administrador convertido.
	 */
	private Administrador convertFull(Dictionary d, boolean privateData) {
		Administrador a = new Administrador();
		a.setUsuario(d.$("usuario_admin"));
		a.setDNI(d.$("dni_admin"));
		a.setCUIL(d.$("cuil_admin"));
		a.setNombre(d.$("nombre_admin"));
		a.setApellido(d.$("apellido_admin"));
		a.setSexo(d.$("sexo_admin"));
		if(d.$("nacionalidad_admin") != null) {
			PaisLogicImpl pli = new PaisLogicImpl();
			Pais nacionalidad = pli.convert(d);
			nacionalidad.setCodigo(d.$("nacionalidad_admin"));
			a.setNacionalidad(nacionalidad);
			
		}
		if(d.$("fechaNacimiento_admin") != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date parsedDate = new java.util.Date();
			try {
				parsedDate = dateFormat.parse(d.$("fechaNacimiento_admin") + "");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				//noinspection CallToPrintStackTrace
				e.printStackTrace();
			}
	        java.sql.Date edate = new java.sql.Date(parsedDate.getTime());
			a.setFechaNacimiento(edate);
			
		}
		a.setDireccion(d.$("direccion_admin"));
		if(d.$("localidad_admin") != null) {
			LocalidadLogicImpl lli = new LocalidadLogicImpl();
			Localidad localty = lli.convert(d);
			localty.setId((int) Math.round(Double.parseDouble(d.$("localidad_admin") + "")));
			a.setLocalidad(localty);
		}
		if(d.$("provincia_admin") != null) {
			ProvinciaLogicImpl pli = new ProvinciaLogicImpl();
			Provincia provincia = pli.convert(d);
			provincia.setId((int) Math.round(Double.parseDouble(d.$("provincia_admin") + "")));
			a.setProvincia(provincia);
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
	 * Convierte una lista de Dictionary en una lista de objetos Administrador, con la opci�n de omitir los campos HASH y SALT.
	 * @param arg0 Lista de Dictionary que se desea convertir.
	 * @param includePrivateData Especifica si se deben incluir los campos HASH y SALT.
	 * @return Lista de objetos Administrador.
	 */
	public List<Administrador> convert(List<Dictionary> arg0, boolean includePrivateData) {
		List<Administrador> l = new ArrayList<>();
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
		Response<Administrador> x = new Response<>();
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
		Response<Administrador> x = new Response<>();
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
	 * @param admin Objeto Administrador con los datos del usuario a deshabilitar.
	 * @return Resultado de la operación.
	 */
	@Override
	public Response<Administrador> delete(Administrador admin) {
		TransactionResponse<?> res;
		Response<Administrador> result = new Response<>();
		if(isRoot(admin)) {
			result.die(false, 406, "Root user cannot be disabled or deleted. ");
			return result;
		}
		try {
			res = data.delete(admin);
			result = convertWildcard(res);
			result.message = result.status ? "El registro se elimin� correctamente. " : "No se elimin� el registro. ";
			result.http = result.status ? 200 : 500;
		} catch (SQLException e) {
			result.die(false, 500, "Hubo un error al intentar realizar la transacci�n. ");
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
		Response<Administrador> res = new Response<>();
		try {
			boolean o = data.exists(arg0);
			res.die(o, o ? 200 : 404, "");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, 500, "");
		}
		return res;
	}
	
	/**
	 * Comprueba si un usuario est� activo en la base de datos a partir de su nombre de usuario.
	 * @param arg0 Nombre de usuario.
	 * @return Resultado de la operaci�n.
	 */
	public Response<Administrador> isActive(String arg0) {
		Response<Administrador> result = new Response<>();
		try {
			boolean res = data.exists(Dictionary.fromArray(
						AdministradorDaoImpl.Fields.usuario.name, arg0,
						AdministradorDaoImpl.Fields.estado.name, true
					));
			result.status = res;
			result.http = res ? 200 : 404;
			result.message = res ? "El usuario est� activo. " : "El usuario fue deshabilitado o no existe. ";
		} catch(SQLException e) {
			result.die(false, 500, "Hubo un error al intentar realizar la consulta. ");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Examina si existe un usuario en la base de datos a partir de su n�mero de documento.
	 * @param dni N�mero de documento a buscar.
	 * @return Resultado de la operaci�n.
	 */
	public Response<Administrador> DNIExists(String dni) {
		Response<Administrador> res = new Response<>();
		try {
			boolean o = data.exists(Dictionary.fromArray(AdministradorDaoImpl.Fields.dni.name, dni));
			res.die(o, o ? 200 : 404, "");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, 500, "");
		}
		return res;
	}
	
	/**
	 * Examina si existe un usuario en la base de datos a partir de su n�mero de CUIL.
	 * @param cuil N�mero de CUIL a buscar.
	 * @return Resultado de la operaci�n. 
	 */
	public Response<Administrador> CUILExists(String cuil) {
		Response<Administrador> res = new Response<>();
		try {
			boolean o = data.exists(Dictionary.fromArray(AdministradorDaoImpl.Fields.cuil.name, cuil));
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
		Response<Administrador> res = new Response<>();
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
		Response<Administrador> res = new Response<>();
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
		Response<Administrador> res = new Response<>();
		try {
			res = convert(data.getById(arg0));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, "");
		}
		return res;
	}

	public Response<Administrador> getByDNI(String DNI) {
		Response<Administrador> res = new Response<>();
		try {
			res = convert(data.getByDNI(DNI));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, "");
		}
		return res;
	}

	public Response<Administrador> getByCUIL(String CUIL) {
		Response<Administrador> res = new Response<>();
		try {
			res = convert(data.getByCUIL(CUIL));
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
		Response<Administrador> result = new Response<>();
		try {
			result = convertWildcard(data.insert(arg0));
			result.message = result.status ? "El registro se insert� correctamente. " : "No se insert� ning�n registro. ";
			result.http = result.status ? 201 : 500;
		} catch (SQLException e) {
			result.die(false, 500, "Hubo un error al intentar realizar la transacci�n. ");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Valida los datos recibidos con base en la estructura inicial.
	 * Se usa para validar la informaci�n antes de crear una cuenta de administrador.
	 * @param d Datos a validar.
	 * @return Resultado de la validaci�n.
	 * @throws SchemaValidationException Si uno de los campos no cumple alguna de las validaciones requeridas.
	 */
	public boolean validateInitialSchema(Dictionary d) throws SchemaValidationException {
		IModel _eModel = new MySQLSchemaModel("administradores", "tif", AdministradorDaoImpl._initial);
		return _eModel.validate(d);
	}
	
	/**
	 * Crea una cuenta de administrador.
	 * @param data Datos del nuevo usuario.
	 * @return Resultado de la operaci�n.
	 */
	public Response<Administrador> createAccount(Dictionary data) {
		Response<Administrador> res = new Response<>();
		// Validar datos
		try {
			boolean initialSchemaValidated = validateInitialSchema(data);
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
		String plainPassword = data.$("password_admin");
		// La encriptamos
        byte[] salt = PasswordUtils.createSalt();
        byte[] hash = PasswordUtils.createHash(plainPassword, salt);
        // Creamos el dictionary final que se subir� a la base de datos.
        data.put("hash_admin", hash);
        data.put("salt_admin", salt);
        // Convertimos el Dictionary en objeto entidad para trabajar con m�todos DAO.
        Administrador obj = convertFull(data, true);
        res = insert(obj);
        // Devolvemos el resultado de la operaci�n.
        return res;		
	}

	/**
	 * Actualiza la contrase�a de un administrador.
	 * @param admin Objeto Administrador con su nombre de usuario establecido.
	 * @param params La nueva contrase�a.
	 * @return Resultado de la operaci�n.
	 */
	public Response<Administrador> updatePassword(Administrador admin, Dictionary params) {
		Response<Administrador> result = new Response<>();
		Schema updatePasswordSchema = new Schema(AdministradorDaoImpl.Fields.contrasena);
		try {
			boolean validationStatus = updatePasswordSchema.validate(params);
			if(validationStatus) {
				byte[] salt = PasswordUtils.createSalt();
				byte[] hash = PasswordUtils.createHash(params.$(AdministradorDaoImpl.Fields.contrasena.name), salt);
				try {
					result = convertWildcard(data.updatePassword(admin.getUsuario(), hash, salt));
					result.http = result.status ? 200 : 500;
					result.errorMessage = result.status ? "La contraseña se actualiz� correctamente. " : "No se actualiz� la contrase�a. ";
				} catch (SQLException e) {
					result.die(false, 500, "Hubo un problema al intentar actualizar la contrase�a. ");
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
	 * Valida una contrase�a en texto plano con la contrase�a encriptada de una cuenta de administrador existente.
	 * @param admin Objeto Administrador con su nombre de usuario, hash y salt establecidos.
	 * @param pass Contrase�a en texto plano a validar.
	 * @return Resultado de la operaci�n. 
	 */
	public Response<Administrador> validatePassword(Administrador admin, String pass) {
		Response<Administrador> response = new Response<>();
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
	 * Intenta iniciar sesi�n, y devuelve un token JWT.
	 * @param user Usuario
	 * @param pass Contrase�a, en texto plano
	 * @return Resultado de la operación.
	 */
	public Response<Administrador> login(String user, String pass) {
		Response<Administrador> res = new Response<>();
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
							// Inicio de sesi�n v�lido.
							String token = AuthManager.generateJWT(adm.getUsuario(), AuthManager.ADMIN);
							Response<Administrador> resT = new Response<>();
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
	 * [TODO Mover de sitio] Inicia sesi�n a partir de par�metros del Servlet.
	 * @param servlet_parameters Par�metros recibidos del servlet.
	 * @return Resultado de la operaci�n.
	 */
	public Response<Administrador> login(Dictionary servlet_parameters) {
		Response<Administrador> response = new Response<>();
		Schema schema = new Schema(AdministradorDaoImpl.Fields.usuario, AdministradorDaoImpl.Fields.contrasena);
		try {
			boolean validationResult = schema.validate(servlet_parameters);
			if(validationResult) {
				return login(
						servlet_parameters.$(AdministradorDaoImpl.Fields.usuario.name),
						servlet_parameters.$(AdministradorDaoImpl.Fields.contrasena.name)
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
	 */
	@SuppressWarnings("unused")
	public static void test() {
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
		Response<Administrador> result = new Response<>();
		try {
			result = convertWildcard(data.modify(arg0));
			result.message = result.status ? "El registro se modific� correctamente. " : "No se modific� ning�n registro. ";
			result.http = result.status ? 200 : 500;
		} catch (SQLException e) {
			result.die(false, 500, "Hubo un error al intentar realizar la transacci�n. ");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Modifica datos en un objeto Administrador.
	 * Recibe los par�metros directo del servlet, y los valida antes de enviarlo a la sobrecarga de �nico par�metro de esta funci�n.
	 * @param arg0 Par�metros enviados por el servlet en cuesti�n.
	 * @param user Usuario a ser modificado
	 * @return Resultado de la operaci�n.
	 */
	public Response<Administrador> modify(Dictionary arg0, Administrador user) {
		Response<Administrador> res = new Response<>();
		try {
			MySQLSchemaModel model = new MySQLSchemaModel("administradores", "tif", AdministradorDaoImpl._editable);
			Dictionary v = model.prepareForEditing(arg0);
			v.put(AdministradorDaoImpl.Fields.usuario.name, user.getUsuario());

			Administrador obj = convert(v);
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
	 * @param validateConstraints Especifica si se deben validar las claves for�neas o �nicas, como el usuario o el DNI.
	 */
	@Override
	public Response<Administrador> validate(Administrador arg0, boolean validateConstraints) {
		Response<Administrador> res = new Response<>();
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
