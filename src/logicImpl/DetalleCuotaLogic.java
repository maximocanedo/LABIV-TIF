package logicImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataImpl.DetalleCuotaDao;
import entity.Cliente;
import entity.DetalleCuota;
import entity.SolicitudPrestamo;
import max.data.Dictionary;
import max.data.IRecordLogic;
import max.data.Response;
import max.data.TransactionResponse;
import max.oops.SchemaValidationException;

public class DetalleCuotaLogic implements IRecordLogic<DetalleCuota, Integer>{

	
	public DetalleCuotaLogic() {}
	
	private static DetalleCuotaDao dcDao = new DetalleCuotaDao();
	
	@Override
	public Response<DetalleCuota> validate(DetalleCuota data, boolean validatePKDuplicates) {
		Response<DetalleCuota> res = new Response<DetalleCuota>();
		try {
			res.status = validatePKDuplicates
					? DetalleCuotaDao._model.validate(data.toDictionary()) 
					: DetalleCuotaDao._schema.validate(data.toDictionary());
		} catch (SchemaValidationException e) {
			e.printStackTrace();
			res.die(false, e.getMessage());
		}
		return res;
	}

	public Response<DetalleCuota> convertWildcard(TransactionResponse<?> data) {
		Response<DetalleCuota> x = new Response<DetalleCuota>();
		x.status = data.status;
		x.errorMessage = data.dbError == null ? null : data.dbError.getMessage();
		x.exception = data.error;
		return x;
	}
	
	@Override
	public Response<DetalleCuota> insert(DetalleCuota data) {
		Response<DetalleCuota> res = new Response<DetalleCuota>();
		try {
			res = convertWildcard(dcDao.insert(data));
			res.objectReturned = data;
			res.message = res.status ? "El registro se insertó correctamente. " : "No se insertó ningún registro. ";
			res.http = res.status ? 201 : 500;
		} catch (SQLException e) {
			res.die(false, 500, "Hubo un error al intentar realizar la transacción. ");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Response<DetalleCuota> modify(DetalleCuota data) {
		Response<DetalleCuota> res = new Response<DetalleCuota>();
		TransactionResponse<?> tpr;
		try {
			tpr = dcDao.modify(data);
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
	public Response<DetalleCuota> delete(DetalleCuota data) {
		Response<DetalleCuota> res = new Response<DetalleCuota>();
		TransactionResponse<?> spr;
		try {
			spr = dcDao.delete(data);
			if(spr.rowsAffected > 0) {
				res.die(true, 200, "El registro se eliminó con éxito. ");
			} else res.die(false, 500, "Hubo un error al intentar eliminar el registro. ");
		} catch (SQLException e) {
			res.die(false, 500, " Hubo un error al intentar eliminar el registro. ");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Response<DetalleCuota> getAll() {
		Response<DetalleCuota> res = new Response<DetalleCuota>();
		TransactionResponse<DetalleCuota> tpr = new TransactionResponse<DetalleCuota>();
		try {
			tpr = dcDao.getAll();
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
	public Response<DetalleCuota> getById(Integer id) {
		Response<DetalleCuota> res = new Response<DetalleCuota>();
		TransactionResponse<DetalleCuota> tpr = new TransactionResponse<DetalleCuota>();
		try {
			tpr = dcDao.getById(id);
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
	public Response<DetalleCuota> exists(Integer id) {
		Response<DetalleCuota> res = new Response<DetalleCuota>();
		boolean existe = false;
		try {
			existe = dcDao.exists(id);
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
	public DetalleCuota convert(Dictionary d) {
		DetalleCuota c = new DetalleCuota();
		if(d.$("id_DTPT") != null) c.setId(d.$("id_DTPT"));
		if(d.$("cod_Sol_DTPT") != null) { 
			c.setCod_Solicitud(new SolicitudPrestamo() {{setCodigo(d.$("cod_Sol_DTPT"));}});
		}
		if(d.$("usuario_cl_DTPT") != null) {
			c.setCliente(new Cliente() {{setUsuario(d.$("usuario_cl_DTPT"));}});
		}
		if(d.$("fechaPago_DTPT") != null) c.setFechaPago(d.$("fechaPago_DTPT"));
		if(d.$("numCuotaPagada_DTPT") != null) c.setNumCuotaPagada(d.$("numCuotaPagada_DTPT"));
		
		return c;
	}

	@Override
	public List<DetalleCuota> convert(List<Dictionary> rows) {
		List<DetalleCuota> list = new ArrayList<DetalleCuota>();
		for(Dictionary data : rows) {
			list.add(convert(data));
		}
		return list;
	}

}
