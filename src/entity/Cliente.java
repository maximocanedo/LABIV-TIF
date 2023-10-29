package entity;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import logic.PasswordUtils;
import max.data.Dictionary;
import max.data.IEntity;

public class Cliente implements IEntity {
	@Expose(serialize = true)
	private String usuario;
	
	@Expose(serialize = true)
	private String DNI;
	
	@Expose(serialize = true)
	private String CUIL;
	
	@Expose(serialize = true)
	private String nombre;
	
	@Expose(serialize = true)
	private String apellido;
	
	@Expose(serialize = true)
	private String sexo;
	
	@Expose(serialize = true)
	private Pais nacionalidad;
	
	@Expose(serialize = true)
	private java.sql.Date fechaNacimiento;
	
	@Expose(serialize = true)
	private String direccion;
	
	@Expose(serialize = true)
	private Localidad localidad;
	
	@Expose(serialize = true)
	private Provincia provincia;
	
	@Expose(serialize = true)
	private String correo;
	
	@Expose(serialize = false)
	private byte[] hash;
	
	@Expose(serialize = false)
	private byte[] salt;
	
	@Expose(serialize = true)
	private boolean estado;
	
	// Se serializa porque el cliente no elige la clave, 
	// Por lo que el sistema por medio de esta propiedad le muestra la clave asignada.
	@Expose(serialize = true)
	private String contraseña;
	
	
	public Cliente() {}
	
	@Override
	public String toJSON() {
		Gson gson = new Gson();
		return gson.toJson(this);
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
			"estado", estado
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
