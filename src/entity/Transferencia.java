package entity;

import java.sql.Date;

import com.google.gson.JsonObject;

import max.Dictionary;
import max.IEntity;

public class Transferencia implements IEntity{
	private String CBUOrigen;
	private String CBUDestino;
	private double montoTransf;
	private String tipoCon;

	public JsonObject toJsonObject() {
		JsonObject obj = new JsonObject();
		obj.addProperty("CBUOrigen", CBUOrigen);
		obj.addProperty("CBUDestino", CBUDestino);
		obj.addProperty("montoTransf", montoTransf);
		obj.addProperty("tipoCon", tipoCon);
		return obj;
	}

	@Override
	public Dictionary toDictionary() {
		return Dictionary.fromArray(
				"CBUOrigen",CBUOrigen,
				"CBUDestino",CBUDestino,
				"montoTransf",montoTransf,
				"tipoCon",tipoCon						
				);
	}

	@Override
	public Dictionary toIdentifiableDictionary() {
		return Dictionary.fromArray("tipoCon",tipoCon);
	}

	public String getCBUOrigen() {
		return CBUOrigen;
	}

	public void setCBUOrigen(String cBUOrigen) {
		CBUOrigen = cBUOrigen;
	}

	public String getCBUDestino() {
		return CBUDestino;
	}

	public void setCBUDestino(String cBUDestino) {
		CBUDestino = cBUDestino;
	}

	public double getMontoTransf() {
		return montoTransf;
	}

	public void setMontoTransf(double montoTransf) {
		this.montoTransf = montoTransf;
	}

	public String getTipoCon() {
		return tipoCon;
	}

	public void setTipoCon(String tipoCon) {
		this.tipoCon = tipoCon;
	}

	public String toJSON() {
		return toJsonObject().toString();
	}
}
