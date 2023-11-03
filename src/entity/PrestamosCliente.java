package entity;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import max.data.Dictionary;
import max.data.IEntity;

public class PrestamosCliente implements IEntity {
	@Expose(serialize = true)
	private int id;
	@Expose(serialize = true)
	private Cliente cliente;
	@Expose(serialize = true)
	private SolicitudPrestamo solicitud;
	@Expose(serialize = true)
	private java.sql.Date fechaOtorgado;
	@Expose(serialize = true)
	private double montoAPagar;
	@Expose(serialize = true)
	private int plazoPago;
	@Expose(serialize = true)
	private int cantCuotas;
	@Expose(serialize = true)
	private double montoPorCuota;
	@Expose(serialize = true)
	private int cuotasPagadas;
	@Expose(serialize = true)
	private int cuotasRestantes;
	
	
	public PrestamosCliente() {}
	
	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
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