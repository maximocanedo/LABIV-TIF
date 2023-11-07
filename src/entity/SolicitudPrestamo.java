package entity;


import com.google.gson.JsonObject;

import max.Dictionary;
import max.IEntity;

public class SolicitudPrestamo implements IEntity{

	private String codigo;
	private Cliente cliente;
	private java.sql.Date fechaPedido;
	private double montoPedido;
	private double montoAPagar;
	private int plazoPago;
	private int cantCuotas;
	private double montoPorCuota;
	private double interes;
	private boolean estado;
	
	public SolicitudPrestamo() {}
	
	public JsonObject toJsonObject() {
		JsonObject obj = new JsonObject();
		obj.addProperty("codigo", codigo);
		obj.add("cliente", cliente.toJsonObject());
		obj.addProperty("fechaPedido", fechaPedido.toString());
		obj.addProperty("montoPedido", montoPedido);
		obj.addProperty("montoAPagar", montoAPagar);
		obj.addProperty("plazoPago", plazoPago);
		obj.addProperty("cantCuotas", cantCuotas);
		obj.addProperty("montoPorCuota", montoPorCuota);
		obj.addProperty("interes", interes);
		obj.addProperty("estado", estado);
		return obj;
	}
	
	public String toJSON(){
		return toJsonObject().toString();
	}
	
	
	@Override
	public Dictionary toDictionary() {
		return Dictionary.fromArray(
				"cod_Sol", codigo,
				"usuario_cl_Sol", cliente.getUsuario(),
				"fechaPedido_Sol",fechaPedido,
				"montoPedido_Sol",montoPedido,
				"montoAPagar_Sol", montoAPagar,
				"plazoPago_Sol", plazoPago,
				"cantCuotas_Sol", cantCuotas,
				"montoPorCuota_Sol", montoPorCuota,
				"interes_Sol", interes,
				"estado_Sol", estado
				);
	}
	@Override
	public Dictionary toIdentifiableDictionary() {
		return Dictionary.fromArray(
				"cod_Sol",codigo
				);
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public java.sql.Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(java.sql.Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public double getMontoPedido() {
		return montoPedido;
	}

	public void setMontoPedido(double montoPedido) {
		this.montoPedido = montoPedido;
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

	public double getInteres() {
		return interes;
	}

	public void setInteres(double interes) {
		this.interes = interes;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}	
}