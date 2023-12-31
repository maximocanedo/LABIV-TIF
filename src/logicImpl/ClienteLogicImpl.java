package logicImpl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import dataImpl.ClienteDaoImpl;
import entity.Cliente;
import entity.Localidad;
import entity.Paginator;
import entity.Pais;
import entity.Provincia;
import entity.filter.ClienteFilter;
import logic.IClienteLogic;
import max.Dictionary;
import max.IModel;
import max.IRecordLogic;
import max.MySQLSchemaModel;
import max.Response;
import max.Schema;
import max.TransactionResponse;
import oops.SchemaValidationException;

public class ClienteLogicImpl implements IRecordLogic<Cliente, String>, IClienteLogic {

	public ClienteLogicImpl() { }
	
	
	
	/**
	 * Clase de datos.
	 */
	private static ClienteDaoImpl data = new ClienteDaoImpl();
	
	/**
	 * Obtener la estructura de datos de la entidad.
	 * @return
	 */
	public Schema getSchema() {
		return ClienteDaoImpl._schema;
	}
	
	/**
	 * Obtener la estructura inicial de datos de la entidad. La que se usa a la hora de crear una cuenta.
	 * @return
	 */
	public Schema getInitialSchema() {
		return ClienteDaoImpl._initial;
	}
	
	/**
	 * Convierte un Dictionary a un objeto Cliente, omitiendo los campos HASH y SALT.
	 */
	@Override
	public Cliente convert(Dictionary d) {
		return convertFull(d, false);
	}
	
	/**
	 * Convierte un Dictionary a un objeto Cliente, con la opci�n de omitir los campos HASH y SALT.
	 * @param d El objeto Dictionary a convertir.
	 * @param pass Especifica si se deben incluir los campos HASH y SALT.
	 * @return Objeto Cliente convertido.
	 */
	private Cliente convertFull(Dictionary d, boolean pass) {
		boolean privateData = pass;
		Cliente a = new Cliente();
		a.setUsuario(d.$("usuario"));
		a.setDNI(d.$("dni"));
		a.setCUIL(d.$("cuil"));
		a.setNombre(d.$("nombre"));
		a.setApellido(d.$("apellido"));
		a.setSexo(d.$("sexo"));
		if(d.$("nacionalidad") != null) {
			Pais nacionalidad = new Pais();
			PaisLogicImpl pli = new PaisLogicImpl();
			nacionalidad = pli.convert(d);
			nacionalidad.setCodigo(d.$("nacionalidad"));
			a.setNacionalidad(nacionalidad);
			
		}
		if(d.$("fechaNacimiento") != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date parsedDate = new java.util.Date();
			try {
				parsedDate = dateFormat.parse(d.$("fechaNacimiento") + "");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        java.sql.Date edate = new java.sql.Date(parsedDate.getTime());
			a.setFechaNacimiento(edate);
			
		}
		a.setDireccion(d.$("direccion"));
		if(d.$("localidad") != null) {
			Localidad locty = new Localidad();
			LocalidadLogicImpl lli = new LocalidadLogicImpl();
			locty = lli.convert(d);
			locty.setId((int) Math.round(Double.parseDouble(d.$("localidad") + "")));
			a.setLocalidad(locty);
		}
		if(d.$("provincia") != null) {
			Provincia provincia = new Provincia();
			ProvinciaLogicImpl pli = new ProvinciaLogicImpl();
			provincia = pli.convert(d);
			provincia.setId((int) Math.round(Double.parseDouble(d.$("provincia") + "")));
			a.setProvincia(provincia);
		}
		
		if(privateData) {
			a.setHash(d.$("hash"));
			a.setSalt(d.$("salt"));
		}
		a.setCorreo(d.$("correo"));
		if(d.$("estado") != null) {
			a.setEstado(d.$("estado"));
		}
		return a;
	}

	/**
	 * Convierte una lista de Dictionary en una lista de objetos Cliente, con la opci�n de omitir los campos HASH y SALT.
	 * @param arg0 Lista de Dictionary que se desea convertir.
	 * @param includePrivateData Especifica si se deben incluir los campos HASH y SALT.
	 * @return Lista de objetos Cliente.
	 */
	public List<Cliente> convert(List<Dictionary> arg0, boolean includePrivateData) {
		List<Cliente> l = new ArrayList<Cliente>();
		for(Dictionary d : arg0) {
			l.add(convertFull(d, includePrivateData));
		}
		return l;
	}
	
	/**
	 * Convierte una lista de Dictionary en una lista de objetos Cliente, omitiendo los campos HASH y SALT.
	 * @param arg0 Lista de Dictionary que se desea convertir.
	 * @return Lista de objetos Cliente.
	 */
	@Override
	public List<Cliente> convert(List<Dictionary> arg0) {
		return convert(arg0, false);
	}
	
	/**
	 * Convierte un objeto TransactionResponse a un LogicResponse.
	 * @param data Objeto a convertir.
	 * @return Objeto convertido.
	 */
	public Response<Cliente> convert(TransactionResponse<Cliente> data) {
		Response<Cliente> x = new Response<Cliente>();
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
	public Response<Cliente> convertWildcard(TransactionResponse<?> data) {
		Response<Cliente> x = new Response<Cliente>();
		x.status = data.status;
		x.errorMessage = data.dbError == null ? null : data.dbError.getMessage();
		x.exception = data.error;
		return x;
	}

	
	/**
	 * Deshabilita una cuenta de cliente (S�LO Cliente)
	 * @param arg0 Objeto Cliente con los datos del usuario a deshabilitar.
	 * @returns Resultado de la operaci�n.
	 */
	@Override
	public Response<Cliente> delete(Cliente arg0) {
		TransactionResponse<?> res;
		Response<Cliente> result = new Response<Cliente>();
		try {
			res = data.delete(arg0);
			result = convertWildcard(res);
			result.message = result.status ? "El cliente fue deshabilitado correctamente. " : "No se deshabilit� la cuenta. ";
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
	public Response<Cliente> exists(String arg0) {
		Response<Cliente> res = new Response<Cliente>();
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
	 * Comprueba si un usuario est� activo en la base de datos a partir de su nombre de usuario.
	 * @param arg0 Nombre de usuario.
	 * @return Resultado de la operaci�n.
	 */
	public Response<Cliente> isActive(String arg0) {
		Response<Cliente> result = new Response<Cliente>();
		try {
			boolean res = data.exists(Dictionary.fromArray(
						ClienteDaoImpl.Fields.usuario.name, arg0,
						ClienteDaoImpl.Fields.estado.name, true
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
	public Response<Cliente> DNIExists(String dni) {
		Response<Cliente> res = new Response<Cliente>();
		boolean o = false;
		try {
			o = data.exists(Dictionary.fromArray(ClienteDaoImpl.Fields.dni.name, dni));
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
	public Response<Cliente> CUILExists(String cuil) {
		Response<Cliente> res = new Response<Cliente>();
		boolean o = false;
		try {
			o = data.exists(Dictionary.fromArray(ClienteDaoImpl.Fields.cuil.name, cuil));
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
	public Response<Cliente> getAll() {
		Response<Cliente> res = new Response<Cliente>();
		try {
			res = convert(data.getAll());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, "");
		}
		return res;
		
	}
	public Response<Cliente> search(ClienteFilter filter) {
		Response<Cliente> res = new Response<Cliente>();
		try {
			res = convert(data.search(filter));
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, "");
		}
		return res;
		
	}
	public Response<Cliente> search(ClienteFilter filter, Paginator paginator) {
		Response<Cliente> res = new Response<Cliente>();
		try {
			res = convert(data.search(filter, paginator));
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, "");
		}
		return res;
		
	}
	public Response<Cliente> getAll(Paginator paginator) {
		Response<Cliente> res = new Response<Cliente>();
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
	public Response<Cliente> getById(String arg0) {
		Response<Cliente> res = new Response<Cliente>();
		try {
			res = convert(data.getById(arg0));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, "");
		}
		return res;
	}
	
	public Response<Cliente> getByDNI(String dni) {
		Response<Cliente> res = new Response<Cliente>();
		try {
			res = convert(data.getByDNI(dni));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, "");
		}
		return res;
	}

	public Response<Cliente> getByCUIL(String cuil) {
		Response<Cliente> res = new Response<>();
		try {
			res = convert(data.getByCUIL(cuil));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, "");
		}
		return res;
	}
	
	public String getTwoRandomChars(String input) {
	    if (input == null || input.length() < 2) {
	        return "xx";
	    }

	    Random rand = new Random();
	    int index1 = rand.nextInt(input.length());
	    int index2;

	    do {
	        index2 = rand.nextInt(input.length());
	    } while (index1 == index2);

	    char caracter1 = input.charAt(index1);
	    char caracter2 = input.charAt(index2);

	    return String.valueOf(caracter1) + String.valueOf(caracter2);
	}
	
	public String normalizeString(String input) {
	    if (input == null) {
	        return null; // Retorna null si la cadena de entrada es nula.
	    }

	    // Remueve espacios en blanco y tildes
	    String sinEspaciosTildes = input
	    		  .replaceAll("\\s", "")
	    		  .replaceAll("[����]", "a")
	              .replaceAll("[����]", "e")
	              .replaceAll("[����]", "i")
	              .replaceAll("[����]", "o")
	              .replaceAll("[����]", "u")
	              .replaceAll("[�]", "n")
	              .replaceAll("[�]", "N");
	    
	    return sinEspaciosTildes;
	}
	
	/**
	 * Generar un nombre de usuario aleatorio
	 * @param cliente
	 * @return
	 */
	public String generatePassword(Cliente cliente) {
		Random random = new Random();
		String nombre = normalizeString(cliente.getNombre());
	    String apellido = normalizeString(cliente.getApellido());
	    String dni = cliente.getDNI();
	    String cuil = cliente.getCUIL();
		int randomNum = random.nextInt(900) + 100;
		int nameLen = nombre.length();
		int surnLen = apellido.length();
		int substrName = random.nextInt(nameLen - 2) + 2;
		int substrSurn = random.nextInt(surnLen - 2) + 2;
		
		boolean fs = substrName % 2 == 0;


		String partOfName = nombre.substring(0, substrName);
		String partOfSurn = apellido.substring(0, substrSurn);
		
	    String dniAleatorio = getTwoRandomChars(dni);
	    String cuilAleatorio = getTwoRandomChars(cuil);
	    
	    
	    String password = 
	    		(fs ? partOfName : partOfSurn) 
	    		+ (fs ? "#" : "$")
	    		+ dniAleatorio 
	    		+ (fs ? "%" : "#") 
	    		+ randomNum
	    		+ (fs ? "$" : "+")
	    		+ cuilAleatorio
	    		+ (fs ? "*" : "%")
	    		+ (!fs ? partOfName : partOfSurn);

	    return password;
	}
	
	public String generateUsername(Cliente cliente) {
		return normalizeString(cliente.getNombre()) + "_" + cliente.getDNI();
	}

	/**
	 * Inserta un nuevo registro en la base de datos.
	 * @param arg0 Objeto Cliente con los datos a insertar.
	 */
	@Override
	public Response<Cliente> insert(Cliente arg0) {
		Response<Cliente> result = new Response<Cliente>();
		try {
			result = convertWildcard(data.insert(arg0));
			result.objectReturned = arg0;
			result.message = result.status ? "El registro se insert� correctamente. " : "No se insert� ning�n registro. ";
			result.http = result.status ? 201 : 500;
		} catch (SQLException e) {
			result.die(false, 500, "Hubo un error al intentar realizar la transacci�n. ");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Valida los datos recibidos en base a la estructura inicial.
	 * Se usa para validar la informaci�n antes de crear una cuenta de Cliente.
	 * @param d Datos a validar.
	 * @return Resultado de la validaci�n.
	 * @throws SchemaValidationException Si uno de los campos no cumple alguna de las validaciones requeridas.
	 */
	public boolean validateInitialSchema(Dictionary d) throws SchemaValidationException {
		IModel _eModel = new MySQLSchemaModel("Clientes", "tif", ClienteDaoImpl._initial);
		return _eModel.validate(d);
	}
	
	/**
	 * Crea una cuenta de Cliente.
	 * @param d Datos del nuevo usuario.
	 * @return Resultado de la operaci�n.
	 */
	public Response<Cliente> createAccount(Dictionary d) {
		Response<Cliente> res = new Response<Cliente>();
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
		d.remove("usuario");
		d.remove("password");
        Cliente obj = convertFull(d, false);
		// Generamos una clave
		String plainPassword = generatePassword(obj);
		// La encriptamos
        byte[] salt = PasswordUtils.createSalt();
        byte[] hash = PasswordUtils.createHash(plainPassword, salt);
        // Creamos el dictionary final que se subir� a la base de datos.
        Dictionary finalData = d;
        finalData.put("hash", hash);
        finalData.put("salt", salt);
        // Convertimos el Dictionary en objeto entidad para trabajar con m�todos DAO.
        String user = generateUsername(obj);
		obj.setUsuario(user);
		obj.setHash(hash);
		obj.setSalt(salt);
		obj.setEstado(true);
        res = insert(obj);
        // Devolvemos el resultado de la operaci�n.
        Cliente temp = new Cliente();
        temp.setUsuario(user);
        temp.setContrasena(plainPassword);
        res.objectReturned = temp;
        
        return res;		
	}

	/**
	 * Actualiza la contrase�a de un Cliente.
	 * @param admin Objeto Cliente con su nombre de usuario establecido.
	 * @param params La nueva contrase�a.
	 * @return Resultado de la operaci�n.
	 */
	public Response<Cliente> updatePassword(Cliente admin, Dictionary params) {
		Response<Cliente> result = new Response<Cliente>();
		Schema updatePasswordSchema = new Schema(ClienteDaoImpl.Fields.contrasena);
		try {
			boolean validationStatus = updatePasswordSchema.validate(params);
			if(validationStatus) {
				byte[] salt = PasswordUtils.createSalt();
				byte[] hash = PasswordUtils.createHash(params.$(ClienteDaoImpl.Fields.contrasena.name), salt);
				try {
					result = convertWildcard(data.updatePassword(admin.getUsuario(), hash, salt));
					result.http = result.status ? 200 : 500;
					result.errorMessage = result.status ? "La contrase�a se actualiz� correctamente. " : "No se actualiz� la contrase�a. ";
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
	 * Valida una contrase�a en texto plano con la contrase�a encriptada de una cuenta de Cliente existente.
	 * @param cliente Objeto Cliente con su nombre de usuario, hash y salt establecidos.
	 * @param pass Contrase�a en texto plano a validar.
	 * @return Resultado de la operaci�n. 
	 */
	public Response<Cliente> validatePassword(Cliente cliente, String pass) {
		Response<Cliente> response = new Response<Cliente>();
		byte[] originalHash = cliente.getHash();
		byte[] originalSalt = cliente.getSalt();
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
	 * @return
	 */
	public Response<Cliente> login(String user, String pass) {
		Response<Cliente> res = new Response<Cliente>();
		// Validar que el usuario exista:
		Response<Cliente> userExists =  exists(user);
		if(userExists.status) {
			try {
				TransactionResponse<Cliente> res2 = data.getFullById(user);
				if(res2.nonEmptyResult()) {
					Cliente adm = res2.rowsReturned.get(0);
					if(adm.isEstado()) {
						Response<Cliente> resI = validatePassword(adm, pass);
						if(resI.status) {
							// Inicio de sesi�n v�lido.
							String token = AuthManager.generateJWT(adm.getUsuario(), AuthManager.CLIENT);
							Response<Cliente> resT = new Response<Cliente>();
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
	public Response<Cliente> login(Dictionary servlet_parameters) {
		Response<Cliente> response = new Response<Cliente>();
		Schema schema = new Schema(ClienteDaoImpl.Fields.usuario, ClienteDaoImpl.Fields.contrasena);
		try {
			boolean validationResult = schema.validate(servlet_parameters);
			if(validationResult) {
				return login(
						servlet_parameters.$(ClienteDaoImpl.Fields.usuario.name),
						servlet_parameters.$(ClienteDaoImpl.Fields.contrasena.name)
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
	 * Modifica datos en un objeto Cliente.
	 */
	@Override
	public Response<Cliente> modify(Cliente arg0) {
		Response<Cliente> result = new Response<Cliente>();
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
	 * Modifica datos en un objeto Cliente.
	 * Recibe los par�metros directo del servlet, y los valida antes de enviarlo a la sobrecarga de �nico par�metro de esta funci�n.
	 * @param arg0 Par�metros enviados por el servlet en cuesti�n.
	 * @param user Usuario a ser modificado
	 * @return Resultado de la operaci�n.
	 */
	public Response<Cliente> modify(Dictionary arg0, Cliente user) {
		Response<Cliente> res = new Response<Cliente>();
		Cliente obj = new Cliente();
		try {
			MySQLSchemaModel model = new MySQLSchemaModel("Clientes", "tif", ClienteDaoImpl._editable);
			Dictionary v = model.prepareForEditing(arg0);
			
			v.put(ClienteDaoImpl.Fields.usuario.name, user.getUsuario());
			//System.out.println("LOGIC 682: " + v.toString());
			obj = convert(v);
			//System.out.println("LOGIC 683: " + obj.toJSON());
			return modify(obj);
		} catch (SchemaValidationException e) {
			res.die(false, 400, e.getMessage());
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * Valida un objeto Cliente.
	 * @param arg0 Objeto Cliente a validar.
	 * @param validateConstraints Especifica si se deben validar las claves for�neas o �nicas, como el usuario o el DNI.
	 */
	@Override
	public Response<Cliente> validate(Cliente arg0, boolean validateConstraints) {
		Response<Cliente> res = new Response<Cliente>();
		try {
			res.status = validateConstraints 
					? ClienteDaoImpl._model.validate(arg0.toDictionary()) 
					: ClienteDaoImpl._schema.validate(arg0.toDictionary());
		} catch (SchemaValidationException e) {
			e.printStackTrace();
			res.die(false, e.getMessage());
		}
		return res;
	}

    public Response<Cliente> enable(Cliente client) {
		TransactionResponse<?> res;
		Response<Cliente> result = new Response<>();
		try {
			res = data.enable(client);
			result = convertWildcard(res);
			result.message = result.status ? "El cliente fue habilitado correctamente. " : "No se habilit� la cuenta. ";
			result.http = result.status ? 200 : 500;
		} catch (SQLException e) {
			result.die(false, 500, "Hubo un error al intentar realizar la transacci�n. ");
			e.printStackTrace();
		}
		return result;
    }
}
