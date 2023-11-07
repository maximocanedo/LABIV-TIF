package entity;


import com.google.gson.JsonObject;

import max.Dictionary;
import max.IEntity;

public class TipoCuenta implements IEntity{
	
	public TipoCuenta() {}
	
	private String codigo;
	private String descripcion;
	
	public JsonObject toJsonObject() {
		JsonObject obj = new JsonObject();
		obj.addProperty("codigo", codigo);
		obj.addProperty("descripcion", descripcion);
		return obj;
	}
	
	public String toJSON() {
		return toJsonObject().toString();
	}
	public String getCod_TPCT() {
		return codigo;
	}
	public void setCod_TPCT(String cod_TPCT) {
		codigo = cod_TPCT;
	}
	public String getDescripcion_TPCT() {
		return descripcion;
	}
	public void setDescripcion_TPCT(String dEscripcion_TPCT) {
		descripcion = dEscripcion_TPCT;
	}
	@Override
	public Dictionary toDictionary() {
		return Dictionary.fromArray(
				"Cod_TPCT", codigo,
				"Descripcion_TPCT", descripcion
			);
	}
	@Override
	public Dictionary toIdentifiableDictionary() {
		return Dictionary.fromArray(
				"Cod_TPCT", codigo
				);
	}

}
