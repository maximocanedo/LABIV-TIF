package entity;

import com.google.gson.JsonObject;

import max.Dictionary;
import max.IEntity;

public class Cuenta implements IEntity{
	
	public Cuenta() {}
	
	private String numero;
	private String CBU;
	private java.sql.Date fechaCreacion;
	private double saldo;
	private TipoCuenta tipo;
	private Cliente cliente;
	private boolean estado;
	
	public JsonObject toJsonObject() {
		JsonObject obj = new JsonObject();
		obj.addProperty("numero", numero);
		obj.addProperty("CBU", CBU);
		obj.addProperty("fechaCreacion", fechaCreacion.toString());
		obj.addProperty("saldo", saldo);
		obj.add("tipo", tipo.toJsonObject());
		obj.add("cliente", cliente.toJsonObject());
		obj.addProperty("estado", estado);
		return obj;
	}
	
	public String toJSON() {
		return toJsonObject().toString();
	}
	@Override
	public Dictionary toDictionary() {
		return Dictionary.fromArray(
				"Num_Cuenta_CxC", numero,
				"CBU_CxC", CBU,
				"FechaCreacion_CxC", fechaCreacion,
				"Cod_TPCT_CxC", tipo.getCod_TPCT(),
				"Dni_Cl_CxC", cliente.getDNI(),
				"saldoCuenta_CxC",saldo,
				"Activo_CxC", estado
			);
	}



	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCBU() {
		return CBU;
	}

	public void setCBU(String cBU) {
		CBU = cBU;
	}

	public java.sql.Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(java.sql.Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public TipoCuenta getTipo() {
		return tipo;
	}

	public void setTipo(TipoCuenta tipo) {
		this.tipo = tipo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public Dictionary toIdentifiableDictionary() {
		return Dictionary.fromArray(
				"Num_Cuenta_CxC", numero
			);
	}

}
