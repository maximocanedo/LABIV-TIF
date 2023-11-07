package logic;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.CuentaDao;
import entity.Cliente;
import entity.Cuenta;
import entity.TipoCuenta;
import max.data.Dictionary;
import max.data.IRecordLogic;
import max.data.Response;
import max.data.TransactionResponse;
import max.oops.SchemaValidationException;

public class CuentaLogic implements IRecordLogic<Cuenta,String>{

	public CuentaLogic() {}
	
	private static CuentaDao clDao= new CuentaDao();
	
	@Override
	public Response<Cuenta> validate(Cuenta data, boolean validatePKDuplicates) {
		Response<Cuenta> res = new Response<Cuenta>();
		try {
			res.status = validatePKDuplicates 
					? CuentaDao._model.validate(data.toDictionary()) 
					: CuentaDao.tablaCL.validate(data.toDictionary());
		} catch (SchemaValidationException e) {
			e.printStackTrace();
			res.die(false, e.getMessage());
		}
		return res;
	}

	
	
	public Response<Cuenta> verifyLimitAccount(Cuenta data){
		Response<Cuenta> res = new Response<Cuenta>();
		//TransactionResponse<?> tpr;
		String user = data.getDni_Cl_CxC().getUsuario();
		int count;
		try {
			count = clDao.countUserAccounts(user);
			if(count < 3) {
				res.die(true,201, "Cuenta creada exitosamente. ");
			} else res.die(false, 403, "Limite de cuentas alcanzado. ");
		} catch (SQLException e) {
			res.die(false, 500 , "Hubo un error al intentar acceder a los registros. ");
			e.printStackTrace();
		}
			
		return res;
	}
	
	@Override
	public Response<Cuenta> insert(Cuenta data) {
		Response<Cuenta> res = new Response<Cuenta>();
		TransactionResponse<?> tpr;
		try {
			tpr = clDao.insert(data);
			if(tpr.rowsAffected > 0) {
				res.die(true, 201, "El registro se insertó con éxito. ");
			} else res.die(false, 500, "Hubo un error al intentar insertar el registro. ");
		} catch (SQLException e) {
			res.die(false, 500, " Hubo un error al intentar insertar el registro. ");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Response<Cuenta> modify(Cuenta data) {
		Response<Cuenta> res = new Response<Cuenta>();
		TransactionResponse<?> tpr;
		try {
			tpr = clDao.modify(data);
			if(tpr.rowsAffected > 0) {
				res.die(true, 200, "El registro se modificó con éxito. ");
			} else res.die(false, 500, "Hubo un error al intentar modificar el registro. ");
		} catch (SQLException e) {
			res.die(false, 500, " Hubo un error al intentar modificar el registro. ");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Response<Cuenta> delete(Cuenta data) {
		Response<Cuenta> res = new Response<Cuenta>();
		TransactionResponse<?> tpr;
		try {
			tpr = clDao.delete(data);
			if(tpr.rowsAffected > 0) {
				res.die(true, 200, "El registro se eliminó con éxito. ");
			} else res.die(false, 500, "Hubo un error al intentar eliminar el registro. ");
		} catch (SQLException e) {
			res.die(false, 500, " Hubo un error al intentar eliminar el registro. ");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Response<Cuenta> getAll() {
		Response<Cuenta> res = new Response<Cuenta>();
		TransactionResponse<Cuenta> tpr = new TransactionResponse<Cuenta>();
		try {
			tpr = clDao.getAll();
			if(tpr.nonEmptyResult()) {
				res.fill(tpr.rowsReturned);
			} else res.die(false, "Hubo un error al intentar realizar la consulta. ");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, " Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}
	
	public Response<Cuenta> getAllFor(Cliente obj) {
		Response<Cuenta> res = new Response<Cuenta>();
		TransactionResponse<Cuenta> tpr = new TransactionResponse<Cuenta>();
		try {
			tpr = clDao.getAllFor(obj);
			if(tpr.nonEmptyResult()) {
				res.fill(tpr.rowsReturned);
			} else res.die(false, "Hubo un error al intentar realizar la consulta. ");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, " Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}

	@Override
	public Response<Cuenta> getById(String id) {
		Response<Cuenta> res = new Response<Cuenta>();
		TransactionResponse<Cuenta> tpr = new TransactionResponse<Cuenta>();
		try {
			tpr = clDao.getById(id);
			if(tpr.nonEmptyResult()) {
				res.fill(tpr.rowsReturned);
			} else res.die(false, "Hubo un error al intentar realizar la consulta. ");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, " Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}

	@Override
	public Response<Cuenta> exists(String id) {
		Response<Cuenta> res = new Response<Cuenta>();
		boolean existe = false;
		try {
			existe = clDao.exists(id);
			if(existe) {
				res.die(true, "El registro existe. ");
			} else res.die(false, "No existe un registro con esas características. ");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, " Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}

	@Override
	public Cuenta convert(Dictionary row) {
		Cuenta cuenta = new Cuenta();
		if(row.$("Num_Cuenta_CxC") != null) cuenta.setNum_Cuenta_CxC(row.$("Num_Cuenta_CxC"));
		if(row.$("CBU_CxC") != null) cuenta.setCBU_CxC(row.$("CBU_CxC"));
		if(row.$("FechaCreacion_CxC") != null) cuenta.setFechaCreacion_CxC(row.$("FechaCreacion_CxC"));
		if(row.$("Cod_TPCT_CxC") != null) { cuenta.setCod_TPCT_CxC(new TipoCuenta(){{
			this.setCod_TPCT(row.$("Cod_TPCT_CxC"));
		}});}else System.out.println("es nulo el Cod_TPCT_CxC");
		if(row.$("Dni_Cl_CxC") != null) cuenta.setDni_Cl_CxC(new Cliente() {{
			this.setDNI(row.$("Dni_Cl_CxC"));}});
		if(row.$("Activo_CxC") != null) cuenta.setActivo_CxC(row.$("Activo_CxC"));
		if(row.$("saldoCuenta_CxC") != null) {
			BigDecimal saldo = row.$("saldoCuenta_CxC");
			cuenta.setSaldoCuenta_CxC(saldo.doubleValue());			
		}
		return cuenta;
	}

	@Override
	public List<Cuenta> convert(List<Dictionary> rows) {
		List<Cuenta> list = new ArrayList<Cuenta>();
		for(Dictionary data : rows) {
			list.add(convert(data));
		}
		return list;
	}

}
