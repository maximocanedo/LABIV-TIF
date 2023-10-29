package entity;

import com.google.gson.annotations.Expose;

import max.data.Dictionary;
import max.data.IEntity;

public class TipoCuenta implements IEntity{
	@Expose(serialize = true)
	private String Cod_TPCT;
	@Expose(serialize = true)
	private String Descripcion_TPCT;
	public String getCod_TPCT() {
		return Cod_TPCT;
	}
	public void setCod_TPCT(String cod_TPCT) {
		Cod_TPCT = cod_TPCT;
	}
	public String getDEscripcion_TPCT() {
		return Descripcion_TPCT;
	}
	public void setDEscripcion_TPCT(String dEscripcion_TPCT) {
		Descripcion_TPCT = dEscripcion_TPCT;
	}
	@Override
	public Dictionary toDictionary() {
		return Dictionary.fromArray(
				"Cod_TPCT", Cod_TPCT,
				"Descripcion_TPCT", Descripcion_TPCT
			);
	}
	@Override
	public Dictionary toIdentifiableDictionary() {
		return Dictionary.fromArray(
				"Cod_TPCT", Cod_TPCT
				);
	}

}
