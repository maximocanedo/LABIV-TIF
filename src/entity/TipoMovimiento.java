package entity;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import max.data.Dictionary;
import max.data.IEntity;

public class TipoMovimiento implements IEntity {
	
	@Expose(serialize = true)
	private String cod_TPMV;
	@Expose(serialize = true)
	private String descripcion_TPMV;
	
	public TipoMovimiento() {}
	
	public TipoMovimiento(String cod_TPMV, String descripcion_TPMV) {
		this.cod_TPMV=cod_TPMV;
		this.descripcion_TPMV=descripcion_TPMV;
	}
	
	public String getCod_TPMV() {
		return cod_TPMV;
	}

	public void setCod_TPMV(String cod_TPMV) {
		this.cod_TPMV = cod_TPMV;
	}

	public String getDescripcion_TPMV() {
		return descripcion_TPMV;
	}

	public void setDescripcion_TPMV(String descripcion_TPMV) {
		this.descripcion_TPMV = descripcion_TPMV;
	}

	@Override
	public Dictionary toDictionary() {
		return Dictionary.fromArray(
				"cod_TPMV",cod_TPMV,
				"descripcion_TPMV",descripcion_TPMV				
				);
	}

	@Override
	public Dictionary toIdentifiableDictionary() {
		return Dictionary.fromArray("cod_TPMV",cod_TPMV);
	}
	
	public String toJSON() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

}
