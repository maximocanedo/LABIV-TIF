package entity;

import com.google.gson.Gson;

import max.data.Dictionary;
import max.data.IEntity;

/**
 * Clase que representa una solicitud de cambio de contraseña.
 * @author delac
 */
public class RequestC01 implements IEntity {
	
	// Datos del cliente:
	public Integer id;
	private Cliente issuer; // Acá incluir CUIL/DNI/Usuario de la cuenta a recuperar
	private boolean status;
	private java.sql.Date issuedOn;
	private java.sql.Date closedOn;
	private String message;
	private Cliente data; // Acá incluir, si se aprueba, la contraseña nueva y el usuario.

	public RequestC01() { }

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

	public java.sql.Date getIssuedOn() {
		return issuedOn;
	}

	public void setIssuedOn(java.sql.Date issuedOn) {
		this.issuedOn = issuedOn;
	}

	public java.sql.Date getClosedOn() {
		return closedOn;
	}

	public void setClosedOn(java.sql.Date closedOn) {
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
		return new Gson().toJson(this);
	}
	
	@Override
	public Dictionary toDictionary() {
		return Dictionary.fromArray(
			"id", id,
			"issuer", issuer,
			"status", status,
			"issuedOn", issuedOn,
			"closedOn", closedOn,
			"message", message,
			"data", data
		);
	}

	@Override
	public Dictionary toIdentifiableDictionary() {
		return Dictionary.fromArray(
			"id", id
		);
	}
	
	

}
