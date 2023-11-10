package entity;

import com.google.gson.JsonObject;

import max.Dictionary;
import max.IEntity;

public class Telefono implements IEntity {
	
private String DNI_Usuario;
private String Telefono;
private Boolean Activo;

public Telefono() {}

public Telefono(String usuario, String telefono) {
	super();
	DNI_Usuario = usuario;
	Telefono = telefono;
}

public JsonObject toJsonObject() {
	JsonObject obj = new JsonObject();
	obj.addProperty("Dni_Cl_TxC", DNI_Usuario);
	obj.addProperty("Tel_TxC", Telefono);
	return obj;
}

public Boolean getActivo() {
	return Activo;
}

public void setActivo(Boolean activo) {
	Activo = activo;
}

public String toJSON() {
	return toJsonObject().toString();
}

public String getDNI_Usuario() {
	return DNI_Usuario;
}
public void setDNI_Usuario(String usuario) {
	DNI_Usuario = usuario;
}
public String getTelefono() {
	return DNI_Usuario;
}
public void setTelefono(String numeroTelefono) {
	DNI_Usuario = numeroTelefono;
}
@Override
public Dictionary toDictionary() {
	return Dictionary.fromArray(
			"Dni_Cl_TxC", DNI_Usuario,
			"Tel_TxC", Telefono,
			"Activo_TxC",Activo
	);
}
@Override
public Dictionary toIdentifiableDictionary() {
	return Dictionary.fromArray(
		"Dni_Cl_TxC", DNI_Usuario
	);
}
}
