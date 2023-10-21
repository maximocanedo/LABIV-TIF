package entity;

import java.sql.Types;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import max.data.Dictionary;
import max.data.IEntity;
import max.schema.*;

public class Localidad implements IEntity {
	
	private int id;
	private String nombre;
	private Provincia provincia;
	
	@Expose(serialize = false)
	public final Schema _schema = new Schema(
		new SchemaProperty("id_loc") {{
			required = true;
			type = Types.INTEGER;
			primary = true;
		}},
		new SchemaProperty("nombre_loc") {{
			required = true;
			type = Types.VARCHAR;
			maxlength = 75;
		}},
		new SchemaProperty("provincia_loc") {{
			required = true;
			type = Types.INTEGER;
			ref = Provincia._model.ref("id_provincia");
		}}
	);
	@Expose(serialize = false)
	public final IModel _model = new MySQLSchemaModel("localidades", "tif", _schema) {{
		compile();
	}};
	
	public Localidad() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toJSON() {
		Gson gson = new Gson();
		return gson.toJson(this);
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
