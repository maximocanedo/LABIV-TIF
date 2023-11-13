package entity;

import com.google.gson.JsonObject;

import max.Dictionary;
import max.IEntity;

public class Telefono implements IEntity {
	
private String DNI;
private String Tel;
private Boolean Activo;

public Telefono() {}

public Telefono(String usuario, String telefono) {
	super();
	DNI = usuario;
	Tel = telefono;
}

public JsonObject toJsonObject() {
	JsonObject obj = new JsonObject();
	obj.addProperty("Dni", DNI);
	obj.addProperty("Tel", Tel);
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
	return DNI;
}
public void setDNI_Usuario(String usuario) {
	DNI = usuario;
}
public String getTelefono() {
	return DNI;
}
public void setTelefono(String numeroTelefono) {
	DNI = numeroTelefono;
}
@Override
public Dictionary toDictionary() {
	return Dictionary.fromArray(
			"Dni", DNI,
			"Tel", Tel,
			"Activo",Activo
	);
}
@Override
public Dictionary toIdentifiableDictionary() {
	return Dictionary.fromArray(
		"Dni", DNI
	);
}
}
