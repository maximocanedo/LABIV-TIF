package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Administrador;
import entity.Localidad;
import entity.Pais;
import entity.Provincia;
import max.data.Dictionary;
import max.data.IRecordLogic;
import max.data.LogicResponse;

public class AdministradorLogic implements IRecordLogic<Administrador, String> {

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

	@Override
	public LogicResponse<Administrador> delete(Administrador arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<Administrador> exists(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<Administrador> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<Administrador> getById(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<Administrador> insert(Administrador arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<Administrador> modify(Administrador arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<Administrador> validate(Administrador arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
