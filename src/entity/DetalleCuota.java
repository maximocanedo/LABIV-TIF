package entity;

import com.google.gson.JsonObject;

import max.Dictionary;
import max.IEntity;

public class DetalleCuota implements IEntity {
	
	private int id;
	private SolicitudPrestamo solicitud;
	private Cliente cliente;
	private java.sql.Date fechaPago;
	private int numCuotaPagada;
	
	public DetalleCuota() {}
	
	@Override
	public JsonObject toJsonObject() {
		JsonObject obj = new JsonObject();
		obj.addProperty("id", id);
		obj.add("solicitud", solicitud.toJsonObject());
		obj.add("cliente", cliente.toJsonObject());
		obj.addProperty("fechaPago", fechaPago.toString());
		obj.addProperty("numCuotaPagada", numCuotaPagada);
		return obj;
	}
	
	public String toJson() {
		return toJsonObject().toString();
	}
	
	@Override
	public Dictionary toDictionary() {
		return Dictionary.fromArray(
				"id_DTPT", id,
				"cod_Sol_DTPT", solicitud.getCodigo(),
				"usuario_cl_DTPT", cliente.getUsuario(),
				"fechaPago_DTPT", fechaPago,
				"numCuotaPagada_DTPT", numCuotaPagada
				);
				
	}

	@Override
	public Dictionary toIdentifiableDictionary() {
		return Dictionary.fromArray(
				"id_DTPT",id
				);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public SolicitudPrestamo getCod_Solicitud() {
		return solicitud;
	}

	public void setCod_Solicitud(SolicitudPrestamo cod_Solicitud) {
		this.solicitud = cod_Solicitud;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public java.sql.Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(java.sql.Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public int getNumCuotaPagada() {
		return numCuotaPagada;
	}

	public void setNumCuotaPagada(int numCuotaPagada) {
		this.numCuotaPagada = numCuotaPagada;
	}

	

	
	
}
