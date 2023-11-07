package entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;

import max.Dictionary;
import max.IEntity;


public class Provincia implements IEntity  {
	
	// Propiedades
	@Expose(serialize = true)
	private int id;
	@Expose(serialize = true)
	private String nombre;
	

	public Provincia() {
		
	}

	// Getters & Setters
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
	
	public String toString() {
		return "Provincia { Id: " + id + "; Nombre: " + nombre + "; }";
	}
	
	/**
	 * Devuelve un JSON con los datos del objeto.
	 */
	
	public JsonObject toJsonObject() {
		JsonObject obj = new JsonObject();
		obj.addProperty("id", id);
		obj.addProperty("nombre", nombre);
		return obj;
	}
	
	@Override
	public String toJSON() {
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.toJson(this);
	}
	
	/**
	 * Devuelve un Dictionary con los datos del objeto.
	 */
	public Dictionary toDictionary() {
		return Dictionary.fromArray(
			"id_provincia", id,
			"nombre_provincia", nombre
		);
	}
	
	/**
	 * Devuelve un Dictionary con los datos que pueden identificar al objeto en una base de datos.
	 */
	public Dictionary toIdentifiableDictionary() {
		return Dictionary.fromArray(
			"id_provincia", id
		);
	}

}
