package entity;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import max.data.Dictionary;
import max.data.IEntity;

public class DetalleCuota implements IEntity {
	
	@Expose(serialize = true)
	private int id;
	@Expose(serialize = true)
	private SolicitudPrestamo cod_Solicitud;
	@Expose(serialize = true)
	private Cliente cliente;
	@Expose(serialize = true)
	private java.sql.Date fechaPago;
	@Expose(serialize = true)
	private int numCuotaPagada;
	
	public DetalleCuota() {}
	
	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	@Override
	public Dictionary toDictionary() {
		return Dictionary.fromArray(
				"id_DTPT", id,
				"cod_Sol_DTPT", cod_Solicitud.getCodigo(),
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
		return cod_Solicitud;
	}

	public void setCod_Solicitud(SolicitudPrestamo cod_Solicitud) {
		this.cod_Solicitud = cod_Solicitud;
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
