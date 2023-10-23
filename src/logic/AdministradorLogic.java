package logic;

import java.sql.SQLException;
import java.util.ArrayList;
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

public class AdministradorLogic implements IRecordLogic<Administrador, String> {
	
	// Propiedades est�ticas
	private static AdministradorDao data = new AdministradorDao();
	
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
		a.setFechaNacimiento(d.$("fechaNacimiento_admin"));
		a.setDireccion(d.$("direccion_admin"));
		a.setLocalidad(new Localidad() {{ setId(d.$("localidad_admin")); }});
		a.setProvincia(new Provincia() {{ setId(d.$("provincia_admin")); }});
		if(privateData) a.setHash(d.$("hash_admin"));
		if(privateData) a.setSalt(d.$("salt_admin"));
		a.setCorreo(d.$("correo_admin"));
		a.setEstado(d.$("estado_admin"));
		return a;
	}

	@Override
	public List<Administrador> convert(List<Dictionary> arg0) {
		List<Administrador> l = new ArrayList<Administrador>();
		for(Dictionary d : arg0) {
			l.add(convert(d));
		}
		return l;
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
			res = convert(data.getAll());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, "");
		}
		return res;
	}

	@Override
	public LogicResponse<Administrador> insert(Administrador arg0) throws SQLException {
		System.out.println(arg0.toJSON());
		return convertO(data.insert(arg0));
	}
	public boolean validateInitialSchema(Dictionary d) throws SchemaValidationException {
			return AdministradorDao._initial.validate(d);
	}
	
	
	public LogicResponse<Administrador> createAccount(Dictionary d) {
		LogicResponse<Administrador> res = new LogicResponse<Administrador>();
		// Validar datos
		boolean initialSchemaValidated = false;
		try {
			initialSchemaValidated = validateInitialSchema(d);
			if(!initialSchemaValidated) {
				res.die(false, "");
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
        // Creamos el dictionary final que se subir� a la base de datos.
        Dictionary finalData = d;
        finalData.put("hash_admin", hash);
        finalData.put("salt_admin", salt);
        // Convertimos el Dictionary en objeto entidad para trabajar con m�todos DAO.
        Administrador obj = convertR(finalData, true);
        try {
        	// Insertamos el objeto en la base de datos.
			res = insert(obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Devolvemos el resultado de la operaci�n.
        return res;		
	}
	
	public static void main(String[] args) {
		Dictionary exampleUser = Dictionary.fromArray(
				"usuario_admin", "hec1932_e",
				"dni_admin", "34533344",
				"cuil_admin", "27345333445",
				"nombre_admin", "H�ctor",
				"apellido_admin", "Da Silva",
				"sexo_admin", "M",
				"nacionalidad_admin", "US",
				"fechaNacimiento_admin", new java.sql.Date(123),
				"direccion_admin", "Av. Portugal 1320",
				"localidad_admin", 78007,
				"provincia_admin", 78,
				"correo_admin", "hector58@gmail.com",
				"estado_admin", true,
				"password_admin", "Hec34533344"
			);
		AdministradorLogic logic = new AdministradorLogic();
		logic.createAccount(exampleUser);
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
