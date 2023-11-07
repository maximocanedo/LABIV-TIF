package entity;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;

import max.Dictionary;
import max.IEntity;

public class Localidad implements IEntity {
	@Expose(serialize = true)
	private int id;
	@Expose(serialize = true)
	private String nombre;
	@Expose(serialize = true)
	private Provincia provincia;
	

	public Localidad() {
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return "Localidad { id = " + id + "; nombre = " + nombre + "; provincia = " + provincia.toString() + " }";
	}
	
	public JsonObject toJsonObject() {
		JsonObject obj = new JsonObject();
		obj.addProperty("id", id);
		obj.addProperty("nombre", nombre);
		obj.add("provincia", provincia == null ? null : provincia.toJsonObject());
		return obj;
	}
	
	@Override
	public String toJSON() {
		return toJsonObject().toString();
	}

	public Dictionary toDictionary() {
		return Dictionary.fromArray(
			"id_loc", id,
			"nombre_loc", nombre,
			"provincia", provincia.getId()
		);
	}
	public Dictionary toIdentifiableDictionary() {
		return Dictionary.fromArray(
			"id_loc", id
		);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

}
