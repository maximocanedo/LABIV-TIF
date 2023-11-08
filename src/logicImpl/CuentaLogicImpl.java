package logicImpl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import dataImpl.AdministradorDaoImpl;
import dataImpl.CuentaDaoImpl;
import entity.Cliente;
import entity.Cuenta;
import entity.TipoCuenta;
import logic.ICuentaLogic;
import max.Dictionary;
import max.IModel;
import max.IRecordLogic;
import max.MySQLSchemaModel;
import max.Response;
import max.TransactionResponse;
import oops.SchemaValidationException;

public class CuentaLogicImpl implements IRecordLogic<Cuenta,String>, ICuentaLogic {

	public CuentaLogicImpl() {}
	
	private static CuentaDaoImpl clDao= new CuentaDaoImpl();
	
	
	public boolean validateInitialSchema(Dictionary d) throws SchemaValidationException {
		IModel _eModel = new MySQLSchemaModel("Cuentas", "tif", CuentaDaoImpl.tablaCL);
		return _eModel.validate(d);
	}
	
	public String generateCBU() {
        String caracteresPermitidos = "0123456789";
        StringBuilder cbu = new StringBuilder();

        Random rand = new Random();
    
        String cbuFinal = new String();
        boolean exist = true;
        while(exist==true) {
        	for (int i = 0; i < 22; i++) {
                int randomIndex = rand.nextInt(caracteresPermitidos.length());
                char caracter = caracteresPermitidos.charAt(randomIndex);
                cbu.append(caracter);
                }
        	cbuFinal = cbu.toString();
            exist = clDao.existsCBU(cbuFinal);
        }
        
        return cbuFinal;
    }
	
	
	public Response<Cuenta> createAccount (Dictionary d){
		Response<Cuenta> res = new Response<Cuenta>();
		
		boolean initialSchemaValidated = false;
		try {
			initialSchemaValidated = validateInitialSchema(d);
			if(!initialSchemaValidated) {
				res.die(false, "Unknown error during validation. ");
				return res;
			}
		} catch (SchemaValidationException e ) {
			e.printStackTrace();
			res.die(false,  e.getMessage());
			return res;
		}
		Cuenta nuevaCuenta = convert(d);
		if(verifyLimitAccount(nuevaCuenta) == false) {
			Date fecha = new Date();
			d.put("CBU_CxC", generateCBU());
			d.put("FechaCreacion_CxC", fecha);
			d.put("SaldoCuenta_CxC", nuevaCuenta.getSaldo() );
			d.put("Cod_TPCT_CxC", nuevaCuenta.getTipo().getCod_TPCT());
			d.put("Dni_Cl_CxC", nuevaCuenta.getCliente().getDNI());
			
			nuevaCuenta = convert(d);
			res = insert(nuevaCuenta);
		}
		else {
			res.die(false, 403 , "Limite de cuentas alcanzado. ");
		}
		return res;
	}
	
	
	
	
	@Override
	public Response<Cuenta> validate(Cuenta data, boolean validatePKDuplicates) {
		Response<Cuenta> res = new Response<Cuenta>();
		try {
			res.status = validatePKDuplicates 
					? CuentaDaoImpl._model.validate(data.toDictionary()) 
					: CuentaDaoImpl.tablaCL.validate(data.toDictionary());
		} catch (SchemaValidationException e) {
			e.printStackTrace();
			res.die(false, e.getMessage());
		}
		return res;
	}

	
	
	public boolean verifyLimitAccount(Cuenta data){
		
		String user = data.getCliente().getUsuario();
		int count;
		try {
			count = clDao.countUserAccounts(user);
			if(count < 3) {
				return false;
			} else return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
			
	
	}
	
	@Override
	public Response<Cuenta> insert(Cuenta data) {
		Response<Cuenta> res = new Response<Cuenta>();
		TransactionResponse<?> tpr;
		
		try {
			tpr = clDao.insert(data);
			if(tpr.rowsAffected > 0) {
				res.die(true, 201, "El registro se insert� con �xito. ");
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
				res.die(true, 200, "El registro se modific� con �xito. ");
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
				res.die(true, 200, "El registro se elimin� con �xito. ");
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
			} else res.die(false, "No existe un registro con esas caracter�sticas. ");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, " Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}

	@Override
	public Cuenta convert(Dictionary row) {
		Cuenta cuenta = new Cuenta();
		if(row.$("Num_Cuenta_CxC") != null) cuenta.setNumero(row.$("Num_Cuenta_CxC"));
		if(row.$("CBU_CxC") != null) cuenta.setCBU(row.$("CBU_CxC"));
		if(row.$("FechaCreacion_CxC") != null) cuenta.setFechaCreacion(row.$("FechaCreacion_CxC"));
		if(row.$("Cod_TPCT_CxC") != null) { cuenta.setTipo(new TipoCuenta(){{
			this.setCod_TPCT(row.$("Cod_TPCT_CxC"));
		}});} //else System.out.println("es nulo el Cod_TPCT_CxC");
		if(row.$("Dni_Cl_CxC") != null) cuenta.setCliente(new Cliente() {{
			this.setDNI(row.$("Dni_Cl_CxC"));}});
		if(row.$("Activo_CxC") != null) cuenta.setEstado(row.$("Activo_CxC"));
		if(row.$("saldoCuenta_CxC") != null) {
			BigDecimal saldo = row.$("saldoCuenta_CxC");
			cuenta.setSaldo(saldo.doubleValue());			
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
