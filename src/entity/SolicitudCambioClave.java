package entity;

import com.google.gson.JsonObject;

import max.Dictionary;
import max.IEntity;

/**
 * Clase que representa una solicitud de cambio de contraseña.
 * @author delac
 */
public class SolicitudCambioClave implements IEntity {
	
	private Integer id;
	private Cliente issuer; // Acá incluir DNI de la cuenta a recuperar
	private boolean status;
	private java.sql.Timestamp issuedOn;
	private java.sql.Timestamp closedOn;
	private String message;
	private Cliente data; // Acá incluir, si se aprueba, la contraseña nueva y el usuario.

	public JsonObject toJsonObject() {
		JsonObject obj = new JsonObject();
		obj.addProperty("id", id);
		obj.add("issuer", issuer.toJsonObject());
		obj.addProperty("status", status);
		obj.addProperty("issuedOn", issuedOn.toString());
		obj.addProperty("closedOn", closedOn.toString());
		obj.addProperty("message", message);
		obj.add("data", data.toJsonObject());
		return obj;
	}
	
	public SolicitudCambioClave() { }

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Cliente getIssuer() {
		return issuer;
	}

	public void setIssuer(Cliente issuer) {
		this.issuer = issuer;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public java.sql.Timestamp getIssuedOn() {
		return issuedOn;
	}

	public void setIssuedOn(java.sql.Timestamp issuedOn) {
		this.issuedOn = issuedOn;
	}

	public java.sql.Timestamp getClosedOn() {
		return closedOn;
	}

	public void setClosedOn(java.sql.Timestamp closedOn) {
		this.closedOn = closedOn;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Cliente getData() {
		return data;
	}

	public void setData(Cliente data) {
		this.data = data;
	}

	
	public String toJSON() {
		return toJsonObject().toString();
	}
	
	@Override
	public Dictionary toDictionary() {
		return Dictionary.fromArray(
			"id_rc01", id,
			"client_rc01", issuer != null ? issuer.getDNI() : null,
			"status_rc01", status,
			"issuedOn_rc01", issuedOn,
			"closedOn_rc01", closedOn,
			"message_rc01", message,
			"nuevaClave_rc01", data != null ? data.getContrasena() : null
		);
	}

	@Override
	public Dictionary toIdentifiableDictionary() {
		return Dictionary.fromArray(
			"id_rc01", id
		);
	}
	
	
	
	

}
