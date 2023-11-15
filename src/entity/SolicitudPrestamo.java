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
	private float interes;
	private int estado;
	private Cuenta cuenta;
	
	public SolicitudPrestamo() {}
	
	public JsonObject toJsonObject() {
		JsonObject obj = new JsonObject();
		obj.addProperty("codigo", codigo);
		obj.add("cliente", cliente == null ? null : cliente.toJsonObject());
		obj.addProperty("fechaPedido", fechaPedido == null ? null : fechaPedido.toString());
		obj.addProperty("montoPedido", montoPedido);
		obj.addProperty("montoAPagar", montoAPagar);
		obj.addProperty("plazoPago", plazoPago);
		obj.addProperty("cantCuotas", cantCuotas);
		obj.addProperty("montoPorCuota", montoPorCuota);
		obj.addProperty("interes", interes);
		obj.addProperty("estado", estado);
		obj.add("cuenta",cuenta == null ? null : cuenta.toJsonObject() );
		return obj;
	}
	
	public String toJSON(){
		return toJsonObject().toString();
	}
	
	
	@Override
	public Dictionary toDictionary() {
		return Dictionary.fromArray(
				"cod_Sol", codigo,
				"usuario_cl_Sol", cliente == null ? null : cliente.getUsuario(),
				"fechaPedido_Sol",fechaPedido == null ? null : fechaPedido,
				"montoPedido_Sol",montoPedido,
				"montoAPagar_Sol", montoAPagar,
				"plazoPago_Sol", plazoPago,
				"cantCuotas_Sol", cantCuotas,
				"montoPorCuota_Sol", montoPorCuota,
				"interes_Sol", interes,
				"estado_Sol", estado,
				"CBU_Sol", cuenta == null ? null : cuenta.getCBU()
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

	public float getInteres() {
		return interes;
	}

	public void setInteres(float interes) {
		this.interes = interes;
	}

	public int isEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}	
	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
}