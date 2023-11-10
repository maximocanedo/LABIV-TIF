package entity;

import java.sql.Date;

import com.google.gson.JsonObject;

import max.Dictionary;
import max.IEntity;

public class Movimiento implements IEntity{
	private int id;
	private Cuenta cuenta;
	private Concepto concepto;
	private double saldoAnterior;
	private double importe;
	private double saldoPosterior;
	private Date fechaMovimiento;
	private TipoMovimiento tipo;

	public JsonObject toJsonObject() {
		JsonObject obj = new JsonObject();
		obj.addProperty("id", id);
		obj.add("cuenta", cuenta.toJsonObject());
		obj.add("concepto", concepto.toJsonObject());
		obj.addProperty("saldoAnterior", saldoAnterior);
		obj.addProperty("importe", importe);
		obj.addProperty("saldoPosterior", saldoPosterior);
		obj.addProperty("fechaMovimiento", fechaMovimiento.toString());
		obj.add("tipo", tipo.toJsonObject());
		return obj;
	}
	
	public Movimiento(int id_Mv, Cuenta num_cuenta_CxC_Mv, Concepto cod_Con_Mv, double saldo_anterior_Mv,
			double importe_Mv, double saldo_posterior_Mv, Date fechaMov_Mv, TipoMovimiento cod_TPMV_Mv) {
		this.id = id_Mv;
		this.cuenta = num_cuenta_CxC_Mv;
		this.concepto = cod_Con_Mv;
		this.saldoAnterior = saldo_anterior_Mv;
		this.importe = importe_Mv;
		this.saldoPosterior = saldo_posterior_Mv;
		this.fechaMovimiento = fechaMov_Mv;
		this.tipo = cod_TPMV_Mv;
	}
	
	public Movimiento() {}

	public int getId_Mv() {
		return id;
	}

	public void setId_Mv(int id_Mv) {
		this.id = id_Mv;
	}

	public Cuenta getNum_cuenta_CxC_Mv() {
		return cuenta;
	}

	public void setNum_cuenta_CxC_Mv(Cuenta num_cuenta_CxC_Mv) {
		this.cuenta = num_cuenta_CxC_Mv;
	}

	public Concepto getCod_Con_Mv() {
		return concepto;
	}

	public void setCod_Con_Mv(Concepto cod_Con_Mv) {
		this.concepto = cod_Con_Mv;
	}

	public double getSaldo_anterior_Mv() {
		return saldoAnterior;
	}

	public void setSaldo_anterior_Mv(double saldo_anterior_Mv) {
		this.saldoAnterior = saldo_anterior_Mv;
	}

	public double getImporte_Mv() {
		return importe;
	}

	public void setImporte_Mv(double importe_Mv) {
		this.importe = importe_Mv;
	}

	public double getSaldo_posterior_Mv() {
		return saldoPosterior;
	}

	public void setSaldo_posterior_Mv(double saldo_posterior_Mv) {
		this.saldoPosterior = saldo_posterior_Mv;
	}

	public java.sql.Date getFechaMov_Mv() {
		return fechaMovimiento;
	}

	public void setFechaMov_Mv(java.sql.Date fechaMov_Mv) {
		this.fechaMovimiento = fechaMov_Mv;
	}

	public TipoMovimiento getCod_TPMV_Mv() {
		return tipo;
	}

	public void setCod_TPMV_Mv(TipoMovimiento cod_TPMV_Mv) {
		this.tipo = cod_TPMV_Mv;
	}

	@Override
	public Dictionary toDictionary() {
		return Dictionary.fromArray(
				"id_Mv",id,
				"num_cuenta_CxC_Mv",cuenta.getNumero(),
				"cod_Con_Mv",concepto.getCodigo(),
				"saldo_anterior_Mv",saldoAnterior,
				"importe_Mv",importe,
				"saldo_posterior_Mv",saldoPosterior,
				"fechaMov_Mv",fechaMovimiento,
				"cod_TPMV_Mv",tipo.getCod_TPMV()				
				);
	}

	@Override
	public Dictionary toIdentifiableDictionary() {
		return Dictionary.fromArray("id_Mv",id);
	}

	public String toJSON() {
		return toJsonObject().toString();
	}
}
