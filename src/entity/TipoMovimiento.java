package entity;

import com.google.gson.JsonObject;

import max.Dictionary;
import max.IEntity;

public class TipoMovimiento implements IEntity {
	
	private String codigo;
	private String descripcion;
	
	public JsonObject toJsonObject() {
		JsonObject obj = new JsonObject();
		obj.addProperty("codigo", codigo);
		obj.addProperty("descripcion", descripcion);
		return obj;
	}
	
	public TipoMovimiento() {}
	
	public TipoMovimiento(String cod_TPMV, String descripcion_TPMV) {
		this.codigo=cod_TPMV;
		this.descripcion=descripcion_TPMV;
	}
	
	public String getCod_TPMV() {
		return codigo;
	}

	public void setCod_TPMV(String cod_TPMV) {
		this.codigo = cod_TPMV;
	}

	public String getDescripcion_TPMV() {
		return descripcion;
	}

	public void setDescripcion_TPMV(String descripcion_TPMV) {
		this.descripcion = descripcion_TPMV;
	}

	@Override
	public Dictionary toDictionary() {
		return Dictionary.fromArray(
				"cod_TPMV",codigo,
				"descripcion_TPMV",descripcion				
				);
	}

	@Override
	public Dictionary toIdentifiableDictionary() {
		return Dictionary.fromArray("cod_TPMV",codigo);
	}
	
	public String toJSON() {
		return toJsonObject().toString();
	}

}
