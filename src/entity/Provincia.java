package entity;

import com.google.gson.Gson;

import max.data.Dictionary;
import max.data.IEntity;


public class Provincia implements IEntity  {
	
	// Propiedades
	private int id;
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
	@Override
	public String toJSON() {
		return new Gson().toJson(this);		
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
