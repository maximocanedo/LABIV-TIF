package entity;

import java.sql.Types;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import max.data.IEntity;
import max.schema.*;

public class Continente implements IEntity {
	private String codigo;
	private String nombre;

	@Expose(serialize = false)
	public static final Schema _schema = new Schema(
		new SchemaProperty("code") {{
			primary = true;
			required = true;
			type = Types.VARCHAR;
			maxlength = 2;
		}},
		new SchemaProperty("name") {{
			primary = true;
			required = true;
			type = Types.VARCHAR;
			maxlength = 255;
		}}
	);
	@Expose(serialize = false)
	public static final IModel _model = new MySQLSchemaModel("continents", "tif", _schema);
	public Continente() {}
	@Override
	public String toJSON() {
		Gson gson = new Gson();
		return gson.toJson(this);
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