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

	public String getNum_Cuenta_CxC() {
		return Num_Cuenta_CxC;
	}

	public void setNum_Cuenta_CxC(String num_Cuenta_CxC) {
		Num_Cuenta_CxC = num_Cuenta_CxC;
	}

	public String getCBU_CxC() {
		return CBU_CxC;
	}

	public void setCBU_CxC(String cBU_CxC) {
		CBU_CxC = cBU_CxC;
	}

	public java.sql.Date getFechaCreacion_CxC() {
		return FechaCreacion_CxC;
	}

	public void setFechaCreacion_CxC(java.sql.Date fechaCreacion_CxC) {
		FechaCreacion_CxC = fechaCreacion_CxC;
	}

	public double getSaldoCuenta_CxC() {
		return saldoCuenta_CxC;
	}

	public void setSaldoCuenta_CxC(double saldoCuenta_CxC) {
		this.saldoCuenta_CxC = saldoCuenta_CxC;
	}

	public TipoCuenta getCod_TPCT_CxC() {
		return Cod_TPCT_CxC;
	}

	public void setCod_TPCT_CxC(TipoCuenta cod_TPCT_CxC) {
		Cod_TPCT_CxC = cod_TPCT_CxC;
	}

	public String getDni_Cl_CxC() {
		return Dni_Cl_CxC;
	}

	public void setDni_Cl_CxC(String dni_Cl_CxC) {
		Dni_Cl_CxC = dni_Cl_CxC;
	}

	public String getActivo_CxC() {
		return Activo_CxC;
	}

	public void setActivo_CxC(String activo_CxC) {
		Activo_CxC = activo_CxC;
	}

	@Override
	public Dictionary toIdentifiableDictionary() {
		return Dictionary.fromArray(
				"Num_Cuenta_CxC", Num_Cuenta_CxC
			);
	}

}
