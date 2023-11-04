package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.PrestamosClienteDao;
import entity.Cliente;
import entity.PrestamosCliente;
import entity.SolicitudPrestamo;
import max.data.Dictionary;
import max.data.IRecordLogic;
import max.data.LogicResponse;
import max.data.TransactionResponse;
import max.oops.SchemaValidationException;

public class PrestamosClienteLogic implements IRecordLogic<PrestamosCliente, Integer> {

	public PrestamosClienteLogic() {}
	
	private static PrestamosClienteDao pcDao = new PrestamosClienteDao();
	
	@Override
	public LogicResponse<PrestamosCliente> validate(PrestamosCliente data, boolean validatePKDuplicates) {
		LogicResponse<PrestamosCliente> res = new LogicResponse<PrestamosCliente>();
		try {
			res.status = validatePKDuplicates
					? PrestamosClienteDao._model.validate(data.toDictionary()) 
					: PrestamosClienteDao._schema.validate(data.toDictionary());
		} catch (SchemaValidationException e) {
			e.printStackTrace();
			res.die(false, e.getMessage());
		}
		return res;
	}
	
	public LogicResponse<PrestamosCliente> convertWildcard(TransactionResponse<?> data) {
		LogicResponse<PrestamosCliente> x = new LogicResponse<PrestamosCliente>();
		x.status = data.status;
		x.errorMessage = data.dbError == null ? null : data.dbError.getMessage();
		x.exception = data.error;
		return x;
	}

	@Override
	public LogicResponse<PrestamosCliente> insert(PrestamosCliente data) {
		LogicResponse<PrestamosCliente> res = new LogicResponse<PrestamosCliente>();
		try {
			res = convertWildcard(pcDao.insert(data));
			res.objectReturned = data;
			res.message = res.status ? "El registro se insert� correctamente. " : "No se insert� ning�n registro. ";
			res.http = res.status ? 201 : 500;
		} catch (SQLException e) {
			res.die(false, 500, "Hubo un error al intentar realizar la transacci�n. ");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public LogicResponse<PrestamosCliente> modify(PrestamosCliente data) {
		LogicResponse<PrestamosCliente> res = new LogicResponse<PrestamosCliente>();
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

	@Override
	public LogicResponse<PrestamosCliente> delete(PrestamosCliente data) {
		LogicResponse<PrestamosCliente> res = new LogicResponse<PrestamosCliente>();
		TransactionResponse<?> spr;
		try {
			spr = pcDao.delete(data);
			if(spr.rowsAffected > 0) {
				res.die(true, 200, "El registro se elimin� con �xito. ");
			} else res.die(false, 500, "Hubo un error al intentar eliminar el registro. ");
		} catch (SQLException e) {
			res.die(false, 500, " Hubo un error al intentar eliminar el registro. ");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public LogicResponse<PrestamosCliente> getAll() {
		LogicResponse<PrestamosCliente> res = new LogicResponse<PrestamosCliente>();
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
		if(d.$("cod_Sol_PxC") != null)p.setSolicitud(new SolicitudPrestamo() {{
			this.setCodigo(d.$("cod_Sol_PxC"));
		}});
		return p;
	}

	@Override
	public List<PrestamosCliente> convert(List<Dictionary> rows) {
		List<PrestamosCliente> list = new ArrayList<PrestamosCliente>();
		for(Dictionary data : rows) {
			list.add(convert(data));
		}
		return list;
	}

	@Override
	public LogicResponse<PrestamosCliente> getById(Integer id) {
		LogicResponse<PrestamosCliente> res = new LogicResponse<PrestamosCliente>();
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

	@Override
	public LogicResponse<PrestamosCliente> exists(Integer id) {
		
		LogicResponse<PrestamosCliente> res = new LogicResponse<PrestamosCliente>();
		boolean existe = false;
		try {
			existe = pcDao.exists(id);
			if(existe) {
				res.die(true, "El registro existe. ");
			} else res.die(false, "No existe un registro con esas caracter�sticas. ");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, " Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}

}