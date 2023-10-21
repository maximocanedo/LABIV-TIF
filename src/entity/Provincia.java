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
	public Schema _schema = new Schema(
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
	public IModel _model = new MySQLSchemaModel("provincias", "tif", _schema);

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
	
	@Override
	public String toJSON() {
		Gson obj = new Gson();
	}

}
