package logicImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataImpl.PrestamosClienteDaoImpl;
import entity.Cliente;
import entity.Cuenta;
import entity.Prestamo;
import entity.SolicitudPrestamo;
import logic.IPrestamoClienteLogic;
import max.Dictionary;
import max.IRecordLogic;
import max.Response;
import max.TransactionResponse;
import oops.SchemaValidationException;

public class PrestamoClienteLogicImpl implements IRecordLogic<Prestamo, Integer>, IPrestamoClienteLogic {

	public PrestamoClienteLogicImpl() {}
	
	private static PrestamosClienteDaoImpl pcDao = new PrestamosClienteDaoImpl();
	
	/* (non-Javadoc)
	 * @see logicImpl.IPrestamoClienteLogic#validate(entity.PrestamosCliente, boolean)
	 */
	@Override
	public Response<Prestamo> validate(Prestamo data, boolean validatePKDuplicates) {
		Response<Prestamo> res = new Response<Prestamo>();
		try {
			res.status = validatePKDuplicates
					? PrestamosClienteDaoImpl._model.validate(data.toDictionary()) 
					: PrestamosClienteDaoImpl._schema.validate(data.toDictionary());
		} catch (SchemaValidationException e) {
			e.printStackTrace();
			res.die(false, e.getMessage());
		}
		return res;
	}
	
	/* (non-Javadoc)
	 * @see logicImpl.IPrestamoClienteLogic#convertWildcard(max.data.TransactionResponse)
	 */
	@Override
	public Response<Prestamo> convertWildcard(TransactionResponse<?> data) {
		Response<Prestamo> x = new Response<Prestamo>();
		x.status = data.status;
		x.errorMessage = data.dbError == null ? null : data.dbError.getMessage();
		x.exception = data.error;
		return x;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IPrestamoClienteLogic#insert(entity.PrestamosCliente)
	 */
	@Override
	public Response<Prestamo> insert(Prestamo data) {
		Response<Prestamo> res = new Response<Prestamo>();
		try {
			res = convertWildcard(pcDao.insert(data));
			res.objectReturned = data;
			res.message = res.status ? "El registro se insertó correctamente. " : "No se insertó ningún registro. ";
			res.http = res.status ? 201 : 500;
		} catch (SQLException e) {
			res.die(false, 500, "Hubo un error al intentar realizar la transacción. ");
			e.printStackTrace();
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IPrestamoClienteLogic#modify(entity.PrestamosCliente)
	 */
	@Override
	public Response<Prestamo> modify(Prestamo data) {
		Response<Prestamo> res = new Response<Prestamo>();
		TransactionResponse<?> tpr;
		try {
			tpr = pcDao.modify(data);
			if(tpr.rowsAffected >0) {
				res.die(true, 200,"El registro se modifico con exito ");
			} else res.die(false, 500,"Hubo un error al intentar modificar el registro ");
		} catch(SQLException e) {
			res.die(false, 500, "Hubo un error al intentar modificar el registro");
			e.printStackTrace();
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IPrestamoClienteLogic#delete(entity.PrestamosCliente)
	 */
	@Override
	public Response<Prestamo> delete(Prestamo data) {
		Response<Prestamo> res = new Response<Prestamo>();
		TransactionResponse<?> spr;
		try {
			spr = pcDao.delete(data);
			if(spr.rowsAffected > 0) {
				res.die(true, 200, "El registro se eliminó con éxito. ");
			} else res.die(false, 500, "Hubo un error al intentar eliminar el registro. ");
		} catch (SQLException e) {
			res.die(false, 500, " Hubo un error al intentar eliminar el registro. ");
			e.printStackTrace();
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IPrestamoClienteLogic#getAll()
	 */
	@Override
	public Response<Prestamo> getAll() {
		Response<Prestamo> res = new Response<Prestamo>();
		TransactionResponse<Prestamo> tpr = new TransactionResponse<Prestamo>();
		try {
			tpr = pcDao.getAll();
			if(tpr.nonEmptyResult()) {
				res.fill(tpr.rowsReturned);
			} else res.die(false, "Hubo un error al intentar realizar la consulta. ");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, " Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IPrestamoClienteLogic#convert(max.data.Dictionary)
	 */
	@Override
	public Prestamo convert(Dictionary d) {
		Prestamo p = new Prestamo();
		
		if(d.$("id_PxC") != null) p.setId(d.$("id_PxC"));
		if(d.$("usuario_cl_PxC") != null) {
			Cliente cliente = (new ClienteLogicImpl()).convert(d);
			cliente.setUsuario(d.$("usuario_cl_PxC"));
			p.setCliente(cliente);
		}
		if(d.$("fechaOtorgado_PxC") != null) p.setFechaOtorgado(d.$("fechaOtorgado_PxC"));
		if(d.$("montoAPagar_PxC") != null) p.setMontoAPagar(d.$("montoAPagar_PxC"));
		if(d.$("plazoPago_PxC") != null) p.setPlazoPago(d.$("plazoPago_PxC"));
		if(d.$("cantCuotas_PxC") != null) p.setCantCuotas(d.$("cantCuotas_PxC"));
		if(d.$("montoPorCuota_PxC") != null) p.setMontoPorCuota(d.$("montoPorCuota_PxC"));
		if(d.$("cuotasPagadas_PxC") != null) p.setCuotasPagadas(d.$("cuotasPagadas_PxC"));
		if(d.$("cuotasRestantes_PxC") != null) p.setCuotasRestantes(d.$("cuotasRestantes_PxC"));
		if(d.$("CBU_PxC") != null) {
			Cuenta cuenta = (new CuentaLogicImpl()).convert(d);
			cuenta.setCBU(d.$("CBU_PxC"));
			p.setCuenta(cuenta);
		}
		if(d.$("cod_Sol_PxC") != null) {
			SolicitudPrestamo sp = (new SolicitudPrestamoLogicImpl()).convert(d);
			sp.setCodigo(d.$("cod_Sol_PxC"));
			p.setSolicitud(sp);
		}
		return p;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IPrestamoClienteLogic#convert(java.util.List)
	 */
	@Override
	public List<Prestamo> convert(List<Dictionary> rows) {
		List<Prestamo> list = new ArrayList<Prestamo>();
		for(Dictionary data : rows) {
			list.add(convert(data));
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IPrestamoClienteLogic#getById(java.lang.Integer)
	 */
	@Override
	public Response<Prestamo> getById(Integer id) {
		Response<Prestamo> res = new Response<Prestamo>();
		TransactionResponse<Prestamo> tpr = new TransactionResponse<Prestamo>();
		try {
			tpr = pcDao.getById(id);
			if(tpr.nonEmptyResult()) {
				res.fill(tpr.rowsReturned);
			} else res.die(false, "Hubo un error al intentar realizar la consulta. ");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, " Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}
	/* (non-Javadoc)
	 * @see logicImpl.IPrestamoClienteLogic#getById(java.lang.String)
	 */
	@Override
	public Response<Prestamo> getById(String usuario_cl_PxC) {
		Response<Prestamo> res = new Response<Prestamo>();
		TransactionResponse<Prestamo> tpr = new TransactionResponse<Prestamo>();
		try {
			tpr = pcDao.getById(usuario_cl_PxC);
			if(tpr.nonEmptyResult()) {
				res.fill(tpr.rowsReturned);
			} else res.die(false, "Hubo un error al intentar realizar la consulta. ");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, " Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IPrestamoClienteLogic#exists(java.lang.Integer)
	 */
	@Override
	public Response<Prestamo> exists(Integer id) {
		
		Response<Prestamo> res = new Response<Prestamo>();
		boolean existe = false;
		try {
			existe = pcDao.exists(id);
			if(existe) {
				res.die(true, "El registro existe. ");
			} else res.die(false, "No existe un registro con esas características. ");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, " Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}

}