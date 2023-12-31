package entity;


import com.google.gson.JsonObject;

import max.Dictionary;
import max.IEntity;

public class Prestamo implements IEntity {

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
	private Cuenta cuenta;
	
	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Prestamo() {}
	
	public JsonObject toJsonObject() {
		JsonObject obj = new JsonObject();
		obj.addProperty("id", id);
		obj.add("cliente", cliente == null ? null : cliente.toJsonObject());
		obj.add("solicitud", solicitud == null ? null : solicitud.toJsonObject());
		obj.addProperty("fechaOtorgado", fechaOtorgado == null ? null : fechaOtorgado.toString());
		obj.addProperty("montoAPagar", montoAPagar);
		obj.addProperty("plazoPago", plazoPago);
		obj.addProperty("cantCuotas", cantCuotas);
		obj.addProperty("montoPorCuota", montoPorCuota);
		obj.addProperty("cuotasPagadas", cuotasPagadas);
		obj.addProperty("cuotasRestantes", cuotasRestantes);
		obj.add("cuenta",cuenta == null ? null : cuenta.toJsonObject());
		return obj;
	}
	
	public String toJSON() {
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
				"cuotasRestantes_PxC",cuotasRestantes,	
				"CBU_PxC",cuenta.getCBU()	
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