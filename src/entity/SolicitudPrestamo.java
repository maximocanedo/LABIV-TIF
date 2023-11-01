package entity;

import com.google.gson.Gson;

import max.data.Dictionary;
import max.data.IEntity;

public class SolicitudPrestamo implements IEntity{

	private String cod_Sol;
	private Cliente usuario_cl_Sol;
	private java.sql.Date fechaPedido_Sol;
	private double montoPedido_Sol;
	private double montoAPagar_Sol;
	private int plazoPago_Sol;
	private int cantCuotas_Sol;
	private double montoPorCuota_Sol;
	private double interes_Sol;
	private boolean estado_Sol;
	
	
	public SolicitudPrestamo() {}
	
	public String toJSON(){
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	
	@Override
	public Dictionary toDictionary() {
		return Dictionary.fromArray(
				"cod_Sol", cod_Sol,
				"usuario_cl_Sol", usuario_cl_Sol.getUsuario(),
				"fechaPedido_Sol",fechaPedido_Sol,
				"montoPedido_Sol",montoPedido_Sol,
				"montoAPagar_Sol", montoAPagar_Sol,
				"plazoPago_Sol", plazoPago_Sol,
				"cantCuotas_Sol", cantCuotas_Sol,
				"montoPorCuota_Sol", montoPorCuota_Sol,
				"interes_Sol", interes_Sol,
				"estado_Sol", estado_Sol
				);
	}
	@Override
	public Dictionary toIdentifiableDictionary() {
		return Dictionary.fromArray(
				"codigo_solicitud",cod_Sol
				);
	}

	public String getCod_Sol() {
		return cod_Sol;
	}

	public void setCod_Sol(String cod_Sol) {
		this.cod_Sol = cod_Sol;
	}

	public Cliente getUsuario_cl_Sol() {
		return usuario_cl_Sol;
	}

	public void setUsuario_cl_Sol(Cliente usuario_cl_Sol) {
		this.usuario_cl_Sol = usuario_cl_Sol;
	}

	public java.sql.Date getFechaPedido_Sol() {
		return fechaPedido_Sol;
	}

	public void setFechaPedido_Sol(java.sql.Date fechaPedido_Sol) {
		this.fechaPedido_Sol = fechaPedido_Sol;
	}

	public double getMontoPedido_Sol() {
		return montoPedido_Sol;
	}

	public void setMontoPedido_Sol(double montoPedido_Sol) {
		this.montoPedido_Sol = montoPedido_Sol;
	}

	public double getMontoAPagar_Sol() {
		return montoAPagar_Sol;
	}

	public void setMontoAPagar_Sol(double montoAPagar_Sol) {
		this.montoAPagar_Sol = montoAPagar_Sol;
	}

	public int getPlazoPago_Sol() {
		return plazoPago_Sol;
	}

	public void setPlazoPago_Sol(int plazoPago_Sol) {
		this.plazoPago_Sol = plazoPago_Sol;
	}

	public int getCantCuotas_Sol() {
		return cantCuotas_Sol;
	}

	public void setCantCuotas_Sol(int cantCuotas_Sol) {
		this.cantCuotas_Sol = cantCuotas_Sol;
	}

	public double getMontoPorCuota_Sol() {
		return montoPorCuota_Sol;
	}

	public void setMontoPorCuota_Sol(double montoPorCuota_Sol) {
		this.montoPorCuota_Sol = montoPorCuota_Sol;
	}

	public double getInteres_Sol() {
		return interes_Sol;
	}

	public void setInteres_Sol(double interes_Sol) {
		this.interes_Sol = interes_Sol;
	}

	public boolean isEstado_Sol() {
		return estado_Sol;
	}

	public void setEstado_Sol(boolean estado_Sol) {
		this.estado_Sol = estado_Sol;
	}

	
	
	
	

	
}
