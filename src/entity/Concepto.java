package entity;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import max.data.Dictionary;
import max.data.IEntity;

public class Concepto implements IEntity {
	
	@Expose(serialize = true)
	private String cod_Con;
	@Expose(serialize = true)
	private String descripcion_Con;
	
	public Concepto() {}
	
	public Concepto(String cod_Con, String descripcion_Con) {
		this.cod_Con=cod_Con;
		this.descripcion_Con=descripcion_Con;
	}
	
	public String getCod_Con() {
		return cod_Con;
	}

	public void setCod_Con(String cod_Con) {
		this.cod_Con = cod_Con;
	}

	public String getDescripcion_Con() {
		return descripcion_Con;
	}

	public void setDescripcion_Con(String descripcion_Con) {
		this.descripcion_Con = descripcion_Con;
	}

	@Override
	public Dictionary toDictionary() {
		return Dictionary.fromArray(
				"cod_Con",cod_Con,
				"descripcion_Con",descripcion_Con);
	}

	@Override
	public Dictionary toIdentifiableDictionary() {
		return Dictionary.fromArray("cod_Con",cod_Con);
	}
	
	public String toJSON() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

}
