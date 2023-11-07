package entity;

import com.google.gson.JsonObject;

import max.Dictionary;
import max.IEntity;

public class Concepto implements IEntity {
	
	private String codigo;
	private String descripcion;
	
	public Concepto() {}
	
	public Concepto(String cod_Con, String descripcion_Con) {
		this.codigo=cod_Con;
		this.descripcion=descripcion_Con;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String cod_Con) {
		this.codigo = cod_Con;
	}

	public String getDescripcion_Con() {
		return descripcion;
	}

	public void setDescripcion_Con(String descripcion_Con) {
		this.descripcion = descripcion_Con;
	}

	@Override
	public Dictionary toDictionary() {
		return Dictionary.fromArray(
			"cod_Con",codigo,
			"descripcion_Con",descripcion
		);
	}

	@Override
	public Dictionary toIdentifiableDictionary() {
		return Dictionary.fromArray("cod_Con",codigo);
	}
	
	public JsonObject toJsonObject() {
		JsonObject obj = new JsonObject();
		obj.addProperty("codigo", codigo);
		obj.addProperty("descripcion", descripcion);
		return obj;
	}
	
	public String toJSON() {
		return toJsonObject().toString();
	}

}
