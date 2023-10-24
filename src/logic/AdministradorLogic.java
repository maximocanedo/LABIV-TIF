package logic;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		Object loc = (d.$("localidad_admin"))
			 , prov = d.get("provincia_admin");
		int locc = -1, provv = -1;
		try {
			locc = Integer.parseUnsignedInt(loc + "");
			provv = Integer.parseUnsignedInt(prov + "");
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
	
	public static void main(String[] args) {
		Dictionary exampleUser = Dictionary.fromArray(
				"usuario_admin", "hec191e3rwee2_e5",
				"dni_admin", "34533344513e2",
				"cuil_admin", "27345333414e55432",
				"nombre_admin", "Héctor",
				"apellido_admin", "Da Silva",
				"sexo_admin", "M",
				"nacionalidad_admin", "US",
				"fechaNacimiento_admin", new java.sql.Date(123),
				"direccion_admin", "Av. Portugal 1320",
				"localidad_admin", 191,
				"provincia_admin", 728,
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
