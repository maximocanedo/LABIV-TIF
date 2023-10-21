package entity;

import java.sql.Types;
import com.google.gson.*;
import com.google.gson.annotations.Expose;
import max.data.*;
import max.schema.*;


public class Provincia implements IEntity  {
	
	// Propiedades
	private int id_provincia;
	private String nombre_provincia;
	
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
	
	/**
	 * Modelo de datos de la entidad. Sirve para hacer algunas validaciones extra y trabajar con bases de datos.
	 */
	@Expose(serialize = false)
	public static IModel _model = new MySQLSchemaModel("provincias", "tif", _schema) {{
		compile();
	}};

	public Provincia() {
		
	}

	// Getters & Setters
	public int getId() {
		return id_provincia;
	}

	public void setId(int id) {
		this.id_provincia = id;
	}

	public String getNombre() {
		return nombre_provincia;
	}

	public void setNombre(String nombre) {
		this.nombre_provincia = nombre;
	}
	
	public String toString() {
		return "Provincia { Id: " + id_provincia + "; Nombre: " + nombre_provincia + "; }";
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
			"id_provincia", id_provincia,
			"nombre_provincia", nombre_provincia
		);
	}
	
	/**
	 * Devuelve un Dictionary con los datos que pueden identificar al objeto en una base de datos.
	 */
	public Dictionary toIdentifiableDictionary() {
		return Dictionary.fromArray(
			"id_provincia", id_provincia
		);
	}

}
