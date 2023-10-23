package entity;

import java.sql.Types;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import logic.PasswordUtils;
import max.data.*;
import max.schema.*;

public class Administrador implements IEntity {

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
	
	
	public Administrador() {}
	
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

	@Override
	public Dictionary toDictionary() {
		return Dictionary.fromArray(
			"usuario_admin", usuario,
			"dni_admin", DNI,
			"cuil_admin", CUIL,
			"nombre_admin", nombre,
			"apellido_admin", apellido,
			"sexo_admin", sexo,
			"nacionalidad_admin", nacionalidad.getCodigo(),
			"fechaNacimiento_admin", fechaNacimiento,
			"direccion_admin", direccion,
			"localidad_admin", localidad.getId(),
			"provincia_admin", provincia.getId(),
			"correo_admin", correo,
			"estado_admin", estado
		);
	}
	
	public Dictionary toFullDictionary() {
		return Dictionary.fromArray(
			"usuario_admin", usuario,
			"dni_admin", DNI,
			"cuil_admin", CUIL,
			"nombre_admin", nombre,
			"apellido_admin", apellido,
			"sexo_admin", sexo,
			"nacionalidad_admin", nacionalidad.getCodigo(),
			"fechaNacimiento_admin", fechaNacimiento,
			"direccion_admin", direccion,
			"localidad_admin", localidad.getId(),
			"provincia_admin", provincia.getId(),
			"hash_admin", hash,
			"salt_admin", salt,
			"correo_admin", correo,
			"estado_admin", estado
		);
	}
	
	public Dictionary toSecret() {
		return Dictionary.fromArray(
				"usuario_admin", usuario,
				"nombre_admin", nombre,
				"apellido_admin", apellido,
				"hash_admin", hash,
				"salt_admin", salt
			);
	}
	public Dictionary toSecret2() {
		return Dictionary.fromArray(
				"usuario_admin", usuario,
				"nombre_admin", nombre,
				"apellido_admin", apellido,
				"hash_admin", PasswordUtils.bytesToHex(hash),
				"salt_admin", PasswordUtils.bytesToHex(salt)
			);
	}

	@Override
	public Dictionary toIdentifiableDictionary() {
		return Dictionary.fromArray(
				"usuario_admin", usuario
			);
	}
	
	

}
