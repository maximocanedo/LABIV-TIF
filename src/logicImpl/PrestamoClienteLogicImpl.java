package logicImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataImpl.PrestamosClienteDaoImpl;
import entity.Cliente;
import entity.Cuenta;
import entity.PrestamosCliente;
import entity.SolicitudPrestamo;
import logic.IPrestamoClienteLogic;
import max.Dictionary;
import max.IRecordLogic;
import max.Response;
import max.TransactionResponse;
import oops.SchemaValidationException;

public class PrestamoClienteLogicImpl implements IRecordLogic<PrestamosCliente, Integer>, IPrestamoClienteLogic {

	public PrestamoClienteLogicImpl() {}
	
	private static PrestamosClienteDaoImpl pcDao = new PrestamosClienteDaoImpl();
	
	/* (non-Javadoc)
	 * @see logicImpl.IPrestamoClienteLogic#validate(entity.PrestamosCliente, boolean)
	 */
	@Override
	public Response<PrestamosCliente> validate(PrestamosCliente data, boolean validatePKDuplicates) {
		Response<PrestamosCliente> res = new Response<PrestamosCliente>();
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
	public Response<PrestamosCliente> convertWildcard(TransactionResponse<?> data) {
		Response<PrestamosCliente> x = new Response<PrestamosCliente>();
		x.status = data.status;
		x.errorMessage = data.dbError == null ? null : data.dbError.getMessage();
		x.exception = data.error;
		return x;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IPrestamoClienteLogic#insert(entity.PrestamosCliente)
	 */
	@Override
	public Response<PrestamosCliente> insert(PrestamosCliente data) {
		Response<PrestamosCliente> res = new Response<PrestamosCliente>();
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
	public Response<PrestamosCliente> modify(PrestamosCliente data) {
		Response<PrestamosCliente> res = new Response<PrestamosCliente>();
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
	public Response<PrestamosCliente> delete(PrestamosCliente data) {
		Response<PrestamosCliente> res = new Response<PrestamosCliente>();
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
	public Response<PrestamosCliente> getAll() {
		Response<PrestamosCliente> res = new Response<PrestamosCliente>();
		TransactionResponse<PrestamosCliente> tpr = new TransactionResponse<PrestamosCliente>();
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
	public PrestamosCliente convert(Dictionary d) {
		PrestamosCliente p = new PrestamosCliente();
		
		if(d.$("id_PxC") != null) p.setId(d.$("id_PxC"));
		if(d.$("usuario_cl_PxC") != null) {
				p.setCliente(new Cliente() {{setUsuario(d.$("usuario_cl_PxC"));}});
		}
		if(d.$("fechaOtorgado_PxC") != null) p.setFechaOtorgado(d.$("fechaOtorgado_PxC"));
		if(d.$("montoAPagar_PxC") != null) p.setMontoAPagar(d.$("montoAPagar_PxC"));
		if(d.$("plazoPago_PxC") != null) p.setPlazoPago(d.$("plazoPago_PxC"));
		if(d.$("cantCuotas_PxC") != null) p.setCantCuotas(d.$("cantCuotas_PxC"));
		if(d.$("montoPorCuota_PxC") != null) p.setMontoPorCuota(d.$("montoPorCuota_PxC"));
		if(d.$("cuotasPagadas_PxC") != null) p.setCuotasPagadas(d.$("cuotasPagadas_PxC"));
		if(d.$("cuotasRestantes_PxC") != null) p.setCuotasRestantes(d.$("cuotasRestantes_PxC"));
		if(d.$("CBU_PxC") != null) { 
				p.setCuenta(new Cuenta() {{setCBU(d.$("CBU_PxC"));}});
		}
		if(d.$("cod_Sol_PxC") != null)p.setSolicitud(new SolicitudPrestamo() {{
		this.setCodigo(d.$("cod_Sol_PxC"));
		}});
		return p;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IPrestamoClienteLogic#convert(java.util.List)
	 */
	@Override
	public List<PrestamosCliente> convert(List<Dictionary> rows) {
		List<PrestamosCliente> list = new ArrayList<PrestamosCliente>();
		for(Dictionary data : rows) {
			list.add(convert(data));
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IPrestamoClienteLogic#getById(java.lang.Integer)
	 */
	@Override
	public Response<PrestamosCliente> getById(Integer id) {
		Response<PrestamosCliente> res = new Response<PrestamosCliente>();
		TransactionResponse<PrestamosCliente> tpr = new TransactionResponse<PrestamosCliente>();
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
	public Response<PrestamosCliente> getById(String usuario_cl_PxC) {
		Response<PrestamosCliente> res = new Response<PrestamosCliente>();
		TransactionResponse<PrestamosCliente> tpr = new TransactionResponse<PrestamosCliente>();
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
	public Response<PrestamosCliente> exists(Integer id) {
		
		Response<PrestamosCliente> res = new Response<PrestamosCliente>();
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