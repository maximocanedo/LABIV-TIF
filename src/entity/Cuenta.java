package entity;

import com.google.gson.JsonObject;
import max.data.Dictionary;
import max.data.IEntity;

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

	public String getNum_Cuenta_CxC() {
		return numero;
	}

	public void setNum_Cuenta_CxC(String num_Cuenta_CxC) {
		numero = num_Cuenta_CxC;
	}

	public String getCBU_CxC() {
		return CBU;
	}

	public void setCBU_CxC(String cBU_CxC) {
		CBU = cBU_CxC;
	}

	public java.sql.Date getFechaCreacion_CxC() {
		return fechaCreacion;
	}

	public void setFechaCreacion_CxC(java.sql.Date fechaCreacion_CxC) {
		fechaCreacion = fechaCreacion_CxC;
	}

	public double getSaldoCuenta_CxC() {
		return saldo;
	}

	public void setSaldoCuenta_CxC(double saldoCuenta_CxC) {
		this.saldo = saldoCuenta_CxC;
	}

	public TipoCuenta getCod_TPCT_CxC() {
		return tipo;
	}

	public void setCod_TPCT_CxC(TipoCuenta cod_TPCT_CxC) {
		tipo = cod_TPCT_CxC;
	}

	public Cliente getDni_Cl_CxC() {
		return cliente;
	}

	public void setDni_Cl_CxC(Cliente dni_Cl_CxC) {
		cliente = dni_Cl_CxC;
	}

	public boolean getActivo_CxC() {
		return estado;
	}

	public void setActivo_CxC(boolean activo_CxC) {
		estado = activo_CxC;
	}

	@Override
	public Dictionary toIdentifiableDictionary() {
		return Dictionary.fromArray(
				"Num_Cuenta_CxC", numero
			);
	}

}
