package entity;

import com.google.gson.annotations.Expose;

import max.data.Dictionary;
import max.data.IEntity;

public class Cuenta implements IEntity{
	
	public Cuenta() {}
	
	@Expose(serialize = true)
	private String Num_Cuenta_CxC;
	@Expose(serialize = true)
	private String CBU_CxC;
	@Expose(serialize = true)
	private java.sql.Date FechaCreacion_CxC;
	@Expose(serialize = true)
	private double saldoCuenta_CxC;
	@Expose(serialize = true)
	private TipoCuenta Cod_TPCT_CxC;
	@Expose(serialize = true)
	private String Dni_Cl_CxC;
	@Expose(serialize = true)
	private String Activo_CxC;
	
	@Override
	public Dictionary toDictionary() {
		return Dictionary.fromArray(
				"Num_Cuenta_CxC", Num_Cuenta_CxC,
				"CBU_CxC", CBU_CxC,
				"FechaCreacion_CxC", FechaCreacion_CxC,
				"Cod_TPCT_CxC", Cod_TPCT_CxC.getCod_TPCT(),
				"Dni_Cl_CxC", Dni_Cl_CxC,
				"Activo_CxC", Activo_CxC
			);
	}

	@Override
	public Dictionary toIdentifiableDictionary() {
		return Dictionary.fromArray(
				"Num_Cuenta_CxC", Num_Cuenta_CxC
			);
	}

}
