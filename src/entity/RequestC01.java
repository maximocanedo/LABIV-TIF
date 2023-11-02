package entity;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import max.data.Dictionary;
import max.data.IEntity;

/**
 * Clase que representa una solicitud de cambio de contraseña.
 * @author delac
 */
public class RequestC01 implements IEntity {
	
	// Datos del cliente:
	@Expose(serialize = true)
	private Integer id;
	@Expose(serialize = true)
	private Cliente issuer; // Acá incluir DNI de la cuenta a recuperar
	@Expose(serialize = true)
	private boolean status;
	@Expose(serialize = true)
	private java.sql.Timestamp issuedOn;
	@Expose(serialize = true)
	private java.sql.Timestamp closedOn;
	@Expose(serialize = true)
	private String message;
	@Expose(serialize = true)
	private Cliente data; // Acá incluir, si se aprueba, la contraseña nueva y el usuario.

	public RequestC01() { }

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
		return new Gson().toJson(this);
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
			"nuevaClave_rc01", data != null ? data.getContraseña() : null
		);
	}

	@Override
	public Dictionary toIdentifiableDictionary() {
		return Dictionary.fromArray(
			"id_rc01", id
		);
	}
	
	
	
	

}
