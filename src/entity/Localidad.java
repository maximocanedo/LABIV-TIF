package entity;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import max.data.Dictionary;
import max.data.IEntity;

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
