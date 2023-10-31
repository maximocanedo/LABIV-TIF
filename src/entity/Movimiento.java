package entity;

import java.sql.Date;

import com.google.gson.Gson;

import max.data.Dictionary;
import max.data.IEntity;

public class Movimiento implements IEntity{
	
	private int id_Mv;
	private Cuenta num_cuenta_CxC_Mv;
	private Concepto cod_Con_Mv;
	private double saldo_anterior_Mv;
	private double importe_Mv;
	private double saldo_posterior_Mv;
	private Date fechaMov_Mv;
	private TipoMovimiento cod_TPMV_Mv;
	
	
	public Movimiento(int id_Mv, Cuenta num_cuenta_CxC_Mv, Concepto cod_Con_Mv, double saldo_anterior_Mv,
			double importe_Mv, double saldo_posterior_Mv, Date fechaMov_Mv, TipoMovimiento cod_TPMV_Mv) {
		this.id_Mv = id_Mv;
		this.num_cuenta_CxC_Mv = num_cuenta_CxC_Mv;
		this.cod_Con_Mv = cod_Con_Mv;
		this.saldo_anterior_Mv = saldo_anterior_Mv;
		this.importe_Mv = importe_Mv;
		this.saldo_posterior_Mv = saldo_posterior_Mv;
		this.fechaMov_Mv = fechaMov_Mv;
		this.cod_TPMV_Mv = cod_TPMV_Mv;
	}
	
	public Movimiento() {}

	public int getId_Mv() {
		return id_Mv;
	}

	public void setId_Mv(int id_Mv) {
		this.id_Mv = id_Mv;
	}

	public Cuenta getNum_cuenta_CxC_Mv() {
		return num_cuenta_CxC_Mv;
	}

	public void setNum_cuenta_CxC_Mv(Cuenta num_cuenta_CxC_Mv) {
		this.num_cuenta_CxC_Mv = num_cuenta_CxC_Mv;
	}

	public Concepto getCod_Con_Mv() {
		return cod_Con_Mv;
	}

	public void setCod_Con_Mv(Concepto cod_Con_Mv) {
		this.cod_Con_Mv = cod_Con_Mv;
	}

	public double getSaldo_anterior_Mv() {
		return saldo_anterior_Mv;
	}

	public void setSaldo_anterior_Mv(double saldo_anterior_Mv) {
		this.saldo_anterior_Mv = saldo_anterior_Mv;
	}

	public double getImporte_Mv() {
		return importe_Mv;
	}

	public void setImporte_Mv(double importe_Mv) {
		this.importe_Mv = importe_Mv;
	}

	public double getSaldo_posterior_Mv() {
		return saldo_posterior_Mv;
	}

	public void setSaldo_posterior_Mv(double saldo_posterior_Mv) {
		this.saldo_posterior_Mv = saldo_posterior_Mv;
	}

	public java.sql.Date getFechaMov_Mv() {
		return fechaMov_Mv;
	}

	public void setFechaMov_Mv(java.sql.Date fechaMov_Mv) {
		this.fechaMov_Mv = fechaMov_Mv;
	}

	public TipoMovimiento getCod_TPMV_Mv() {
		return cod_TPMV_Mv;
	}

	public void setCod_TPMV_Mv(TipoMovimiento cod_TPMV_Mv) {
		this.cod_TPMV_Mv = cod_TPMV_Mv;
	}

	@Override
	public Dictionary toDictionary() {
		return Dictionary.fromArray(
				"id_Mv",id_Mv,
				"num_cuenta_CxC_Mv",num_cuenta_CxC_Mv.getNum_Cuenta_CxC(),
				"cod_Con_Mv",cod_Con_Mv.getCod_Con(),
				"saldo_anterior_Mv",saldo_anterior_Mv,
				"importe_Mv",importe_Mv,
				"saldo_posterior_Mv",saldo_posterior_Mv,
				"fechaMov_Mv",fechaMov_Mv,
				"cod_TPMV_Mv",cod_TPMV_Mv.getCod_TPMV()				
				);
	}

	@Override
	public Dictionary toIdentifiableDictionary() {
		return Dictionary.fromArray("id_Mv",id_Mv);
	}

	public String toJSON() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	
	

}
