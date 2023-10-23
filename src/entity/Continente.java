package entity;

import com.google.gson.Gson;

import max.data.Dictionary;
import max.data.IEntity;

public class Continente implements IEntity {
	private String codigo;
	private String nombre;

	public Continente() {}
	@Override
	public String toJSON() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	public Dictionary toIdentifiableDictionary() {
		return Dictionary.fromArray("code", codigo);
	}
	public Dictionary toDictionary() {
		return Dictionary.fromArray("code", codigo, "name", nombre);
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
