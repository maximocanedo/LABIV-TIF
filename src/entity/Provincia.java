package entity;

import java.sql.Types;
import com.google.gson.*;
import com.google.gson.annotations.Expose;
import max.data.*;
import max.schema.*;


public class Provincia implements IEntity  {
	
	// Propiedades
	private int id;
	private String nombre;
	
	/**
	 * Estructura de datos de la entidad. Sirve para hacer validaciones.
	 */
	@Expose(serialize = false)
	public static Schema _schema = new Schema(
		new SchemaProperty("id_provincia") {{
			required = true;
			type = Types.INTEGER;
			primary = true;
		}},
		new SchemaProperty("nombre_provincia") {{
			required = true;
			type = Types.VARCHAR;
			maxlength = 75;
		}}
	);
	

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
