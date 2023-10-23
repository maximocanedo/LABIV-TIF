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

	private static AdministradorDao data = new AdministradorDao();
	@Override
	public Administrador convert(Dictionary d) {
		boolean privateData = false;
		Administrador a = new Administrador();
		a.setUsuario(d.$("usuario_admin"));
		a.setDNI(d.$("dni_admin"));
		a.setCUIL(d.$("cuil_admin"));
		a.setNombre(d.$("nombre_admin"));
		a.setApellido(d.$("apellido_admin"));
		a.setSexo(d.$("sexo_admin"));
		a.setNacionalidad(new Pais() {{ setCodigo(d.$("nacionalidad_admin")); }});
		a.setFechaNacimiento(d.$("fechaNacimiento_admin"));
		a.setDireccion(d.$("direcccion_admin"));
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
		x.errorMessage = data.dbError.getMessage();
		x.listReturned = data.rowsReturned;
		x.exception = data.error;
		return x;
	}
	
	public LogicResponse<Administrador> convertO(TransactionResponse<?> data) {
		LogicResponse<Administrador> x = new LogicResponse<Administrador>();
		x.status = data.status;
		x.errorMessage = data.dbError.getMessage();
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

	// PENDIENTE
	@Override
	public LogicResponse<Administrador> insert(Administrador arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
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
					: Administrador._schema.validate(arg0.toDictionary());
		} catch (SchemaValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, e.getMessage());
		}
		return res;
	}

}
