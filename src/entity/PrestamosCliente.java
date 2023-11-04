package entity;


import com.google.gson.JsonObject;

import max.data.Dictionary;
import max.data.IEntity;

public class PrestamosCliente implements IEntity {

	private int id;
	private Cliente cliente;
	private SolicitudPrestamo solicitud;
	private java.sql.Date fechaOtorgado;
	private double montoAPagar;
	private int plazoPago;
	private int cantCuotas;
	private double montoPorCuota;
	private int cuotasPagadas;
	private int cuotasRestantes;
	
	public PrestamosCliente() {}
	
	public JsonObject toJsonObject() {
		JsonObject obj = new JsonObject();
		obj.addProperty("id", id);
		obj.add("cliente", cliente.toJsonObject());
		obj.add("solicitud", solicitud.toJsonObject());
		obj.addProperty("fechaOtorgado", fechaOtorgado.toString());
		obj.addProperty("montoAPagar", montoAPagar);
		obj.addProperty("plazoPago", plazoPago);
		obj.addProperty("cantCuotas", cantCuotas);
		obj.addProperty("montoPorCuota", montoPorCuota);
		obj.addProperty("cuotasPagadas", cuotasPagadas);
		obj.addProperty("cuotasRestantes", cuotasRestantes);
		return obj;
	}
	
	public String toJson() {
		return toJsonObject().toString();
	}
	
		
	@Override
	public Dictionary toDictionary() {
		return Dictionary.fromArray(
				"id_PxC", id,
				"usuario_cl_PxC", cliente.getUsuario(),
				"cod_Sol_PxC", solicitud.getCodigo(),
				"fechaOtorgado_PxC", fechaOtorgado,
				"montoAPagar_PxC", montoAPagar,
				"plazoPago_PxC", plazoPago,
				"cantCuotas_PxC",cantCuotas,
				"montoPorCuota_PxC", montoPorCuota,
				"cuotasPagadas_PxC", cuotasPagadas,
				"cuotasRestantes_PxC",cuotasRestantes				
				);
	}

	@Override
	public Dictionary toIdentifiableDictionary() {
		return Dictionary.fromArray(
				"id_PxC",id
				);			
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public SolicitudPrestamo getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(SolicitudPrestamo solicitud) {
		this.solicitud = solicitud;
	}

	public java.sql.Date getFechaOtorgado() {
		return fechaOtorgado;
	}

	public void setFechaOtorgado(java.sql.Date fechaOtorgado) {
		this.fechaOtorgado = fechaOtorgado;
	}

	public double getMontoAPagar() {
		return montoAPagar;
	}

	public void setMontoAPagar(double montoAPagar) {
		this.montoAPagar = montoAPagar;
	}

	public int getPlazoPago() {
		return plazoPago;
	}

	public void setPlazoPago(int plazoPago) {
		this.plazoPago = plazoPago;
	}

	public int getCantCuotas() {
		return cantCuotas;
	}

	public void setCantCuotas(int cantCuotas) {
		this.cantCuotas = cantCuotas;
	}

	public double getMontoPorCuota() {
		return montoPorCuota;
	}

	public void setMontoPorCuota(double montoPorCuota) {
		this.montoPorCuota = montoPorCuota;
	}

	public int getCuotasPagadas() {
		return cuotasPagadas;
	}

	public void setCuotasPagadas(int cuotasPagadas) {
		this.cuotasPagadas = cuotasPagadas;
	}

	public int getCuotasRestantes() {
		return cuotasRestantes;
	}

	public void setCuotasRestantes(int cuotasRestantes) {
		this.cuotasRestantes = cuotasRestantes;
	}	
}