package logicImpl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import java.sql.Date;

import dataImpl.ClienteDaoImpl;
import dataImpl.CuentaDaoImpl;
import entity.Cliente;
import entity.Cuenta;
import entity.Movimiento;
import entity.Paginator;
import entity.TipoCuenta;
import entity.Transferencia;
import logic.ICuentaLogic;
import max.Dictionary;
import max.IRecordLogic;
import max.Response;
import max.TransactionResponse;
import oops.SchemaValidationException;

public class CuentaLogicImpl implements IRecordLogic<Cuenta,String>, ICuentaLogic {
	/*
	public static void main(String[] args) {

	CuentaLogicImpl logic = new CuentaLogicImpl();
	Cuenta dc = new Cuenta();
	Cliente cl = new Cliente();
	cl.setDNI("15777888");
	TipoCuenta tc = new TipoCuenta();
	tc.setCod_TPCT("TC02");

	//CREAR NUEVA CUENTA
	//dc.setTipo(tc);
	//dc.setCliente(cl);
	
	//logic.createAccount(dc);
	
	//MODIFICAR CUENTA
	dc.setNumero("1234671115");
	dc.setTipo(tc);
	dc.setCliente(cl);
	dc.setSaldo(15000);
	dc.setEstado(true);
	logic.modify(dc);
	}
	*/
	
	public CuentaLogicImpl() {}
	private static CuentaDaoImpl clDao= new CuentaDaoImpl();
	
	/*
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
	 */
	
	public Response<Cuenta> createAccount (Cuenta d){
		Response<Cuenta> res = new Response<Cuenta>();
		
		Cuenta nuevaCuenta = d;
		if(verifyLimitAccount(nuevaCuenta) == false) {
			Date fecha = new Date(System.currentTimeMillis());
			nuevaCuenta.setFechaCreacion(fecha);
			
			res = insert(nuevaCuenta);
		}
		else {
			res.die(false, 403 , "Limite de cuentas alcanzado. ");
			System.out.println("Limite de cuentas alcanzado. ");
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
		
		String dni = data.getCliente().getDNI();
		int count;
		try {
			count = clDao.countUserAccounts(dni);
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
		try {
			int count = clDao.countUserAccounts(data.getCliente().getDNI());
			if(count == 3) {
				res.die(false, 403, "Alcanzaste el límite de cuentas. ");
				return res;
			}
			TransactionResponse<?> tpr;
			System.out.println(data.toJSON());
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
				res.die(true, 200, "El registro se modificï¿½ con ï¿½xito. ");
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
				res.die(true, 200, "El registro se eliminï¿½ con ï¿½xito. ");
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
			res.fill(tpr.rowsReturned);
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
			res.fill(tpr.rowsReturned);
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
			res.fill(tpr.rowsReturned);
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, " Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}
	
	public Response<Cuenta> getByDNI(String dni) {
		Response<Cuenta> res = new Response<Cuenta>();
		TransactionResponse<Cuenta> tpr = new TransactionResponse<Cuenta>();
		try {
			tpr = clDao.getByDNI(dni);
			res.fill(tpr.rowsReturned);
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, " Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}
	
	/**
	 * Examina si existe una cuenta en la base de datos a partir del numero de cuenta otorgado.
	 * @param numero Nï¿½mero de cuenta a buscar.
	 * @return Resultado de la operaciï¿½n.
	 */

	@Override
	public Response<Cuenta> exists(String numero) {
		Response<Cuenta> res = new Response<Cuenta>();
		boolean existe = false;
		try {
			existe = clDao.exists(numero);
			if(existe) {
				res.die(true, "El registro existe. ");
			} else res.die(false, "No existe un registro con esas caracterï¿½sticas. ");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, " Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}
	
	
	/**
	 * Examina si existe una cuenta en la base de datos a partir del CBU de cuenta otorgado.
	 * @param CBU Nï¿½mero de CBU a buscar.
	 * @return Resultado de la operaciï¿½n.
	 */
	public Response<Cuenta> CBUExists(String CBU) {
		Response<Cuenta> res = new Response<Cuenta>();
		boolean o = false;
		try {
			o = clDao.existsCBU(CBU);
			res.die(o, o ? 200 : 404, "");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, 500, "");
		}
		return res;
	}
	/**
	 * Examina si existe una cuenta en la base de datos a partir del DNI otorgado.
	 * @param DNI Nï¿½mero de DNI a buscar.
	 * @return Resultado de la operaciï¿½n.
	 */
	public Response<Cuenta> DNIExists(String DNI) {
		Response<Cuenta> res = new Response<Cuenta>();
		boolean o = false;
		try {
			o = clDao.existsDNI(DNI);
			res.die(o, o ? 200 : 404, "");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, 500, "");
		}
		return res;
	}
	
	
	
	
	@Override
	public Cuenta convert(Dictionary row) {
		Cuenta cuenta = new Cuenta();
		if(row.$("Num_Cuenta_CxC") != null) cuenta.setNumero(row.$("Num_Cuenta_CxC"));
		if(row.$("CBU_CxC") != null) cuenta.setCBU(row.$("CBU_CxC"));
		if(row.$("FechaCreacion_CxC") != null) cuenta.setFechaCreacion(row.$("FechaCreacion_CxC"));
		if(row.$("Cod_TPCT_CxC") != null) { 
			TipoCuenta tc = (new TipoCuentaLogicImpl()).convert(row);
			tc.setCod_TPCT(row.$("Cod_TPCT_CxC"));
			cuenta.setTipo(tc);
		}
		if(row.$("Dni_Cl_CxC") != null) {
			Cliente cliente = (new ClienteLogicImpl().convert(row));
			cliente.setDNI(row.$("Dni_Cl_CxC"));
			cuenta.setCliente(cliente);
		}
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


	@Override
	public Response<Cuenta> getAll(Paginator paginator) {
		Response<Cuenta> res = new Response<Cuenta>();
		TransactionResponse<Cuenta> tpr = new TransactionResponse<Cuenta>();
		try {
			tpr = clDao.getAll(paginator);
			if(tpr.nonEmptyResult()) {
				res.fill(tpr.rowsReturned);
			} else res.die(false, "Hubo un error al intentar realizar la consulta. ");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, " Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}




	public entity.Transferencia convertTransfer(Dictionary row) {
		entity.Transferencia transferencia = new Transferencia();
		if(row.$("CBUOrigen") != null)transferencia.setCBUOrigen(row.$("CBUOrigen"));
		if(row.$("CBUDestino") != null)transferencia.setCBUDestino(row.$("CBUDestino"));
		if(row.$("montoTransf") != null)transferencia.setMontoTransf(row.$("montoTransf"));
		if(row.$("tipoConcep") != null)transferencia.setTipoCon(row.$("tipoConcep"));
				
		return transferencia;
	}




	public Response<entity.Transferencia> insertTransfer(entity.Transferencia data) {
		Response<entity.Transferencia> res = new Response<entity.Transferencia>();
		try {
			TransactionResponse<?> tpr;
			System.out.println(data.toJSON());
			tpr = clDao.insertTransfer(data);
			if(tpr.rowsAffected > 0) {
				res.die(true, 201, "La transferencia se insertó con éxito. ");
			} else res.die(false, 500, "Hubo un error al intentar transferir. ");
		} catch (SQLException e) {
			res.die(false, 500, " Hubo un error al intentar transferir. ");
			e.printStackTrace();
		}
		return res;
	}

}
