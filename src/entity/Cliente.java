package entity;

import com.google.gson.JsonObject;

import logic.PasswordUtils;
import max.data.Dictionary;
import max.data.IEntity;

public class Cliente implements IEntity {
	private String usuario;
	private String DNI;
	private String CUIL;
	private String nombre;
	private String apellido;
	private String sexo;
	private Pais nacionalidad;
	private java.sql.Date fechaNacimiento;
	private String direccion;
	private Localidad localidad;
	private Provincia provincia;
	private String correo;
	private byte[] hash;
	private byte[] salt;
	private boolean estado;
	private boolean correoVerificado = false;
	private String contraseña;
	
	
	public Cliente() {}
	
	public JsonObject toJsonObject() {
		JsonObject obj = new JsonObject();
		obj.addProperty("usuario", usuario);
		obj.addProperty("DNI", DNI);
		obj.addProperty("CUIL", CUIL);
		obj.addProperty("nombre", nombre);
		obj.addProperty("apellido", apellido);
		obj.addProperty("sexo", sexo);
		obj.add("nacionalidad", nacionalidad == null ? null : nacionalidad.toJsonObject());
		obj.addProperty("fechaNacimiento", fechaNacimiento == null ? null : fechaNacimiento.toString());
		obj.addProperty("direccion", direccion);
		obj.add("localidad", localidad == null ? null : localidad.toJsonObject());
		obj.add("provincia", provincia == null ? null : provincia.toJsonObject());
		obj.addProperty("correo", correo);
		obj.addProperty("correoVerificado", correoVerificado);
		obj.addProperty("estado", estado);
		
		return obj;
	}
	
	@Override
	public String toJSON() {
		return toJsonObject().toString();
	}
	
	public String toJson() {
		return toJSON();
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getCUIL() {
		return CUIL;
	}

	public void setCUIL(String cUIL) {
		CUIL = cUIL;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Pais getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(Pais nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public java.sql.Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(java.sql.Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public byte[] getHash() {
		return hash;
	}

	public void setHash(byte[] hash) {
		this.hash = hash;
	}

	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	@Override
	public Dictionary toDictionary() {
		System.out.println("clietne: " + toFullDictionary());
		return Dictionary.fromArray(
			"usuario", usuario,
			"dni", DNI,
			"cuil", CUIL,
			"nombre", nombre,
			"apellido", apellido,
			"sexo", sexo,
			"nacionalidad", nacionalidad != null ? nacionalidad.getCodigo() : null,
			"fechaNacimiento", fechaNacimiento,
			"direccion", direccion,
			"localidad", localidad != null ? localidad.getId() : null,
			"provincia", provincia != null ? provincia.getId() : null,
			"correo", correo,
			"correoVerificado", correoVerificado,
			"estado", estado
		);
	}
	
	public Dictionary toFullDictionary() {
		return Dictionary.fromArray(
			"usuario", usuario,
			"dni", DNI,
			"cuil", CUIL,
			"nombre", nombre,
			"apellido", apellido,
			"sexo", sexo,
			"nacionalidad", nacionalidad != null ? nacionalidad.getCodigo() : null,
			"fechaNacimiento", fechaNacimiento,
			"direccion", direccion,
			"localidad", localidad != null ? localidad.getId() : null,
			"provincia", provincia != null ? provincia.getId() : null,
			"hash", hash,
			"salt", salt,
			"correo", correo,
			"estado", estado,
			"correoVerificado", correoVerificado
		);
	}
	
	
	
	public Dictionary toSecret() {
		return Dictionary.fromArray(
				"usuario", usuario,
				"nombre", nombre,
				"apellido", apellido,
				"hash", hash,
				"salt", salt
			);
	}
	public Dictionary toSecret2() {
		return Dictionary.fromArray(
				"usuario", usuario,
				"nombre", nombre,
				"apellido", apellido,
				"hash", PasswordUtils.bytesToHex(hash),
				"salt", PasswordUtils.bytesToHex(salt)
			);
	}

	@Override
	public Dictionary toIdentifiableDictionary() {
		return Dictionary.fromArray(
				"usuario", usuario
			);
	}
	
	public Dictionary toMinimumDictionary() {
		return Dictionary.fromArray(
				"usuario", usuario,
				"cuil", CUIL,
				"nombre", nombre,
				"apellido", apellido
			);
	}
}
