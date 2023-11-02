package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.SolicitudPrestamoDao;
import entity.Cliente;
import entity.SolicitudPrestamo;
import max.data.Dictionary;
import max.data.IRecordLogic;
import max.data.LogicResponse;
import max.data.TransactionResponse;
import max.oops.SchemaValidationException;


public class SolicitudPrestamoLogic implements IRecordLogic<SolicitudPrestamo,String>{

	public SolicitudPrestamoLogic() {}
	
	private static SolicitudPrestamoDao spDao = new SolicitudPrestamoDao();
	
	@Override
	public LogicResponse<SolicitudPrestamo> validate(SolicitudPrestamo data, boolean validateConstraints) {
		LogicResponse<SolicitudPrestamo> res = new LogicResponse<SolicitudPrestamo>();
		try {
			res.status = validateConstraints
					? SolicitudPrestamoDao._model.validate(data.toDictionary()) 
					: SolicitudPrestamoDao._schema.validate(data.toDictionary());
		} catch (SchemaValidationException e) {
			e.printStackTrace();
			res.die(false, e.getMessage());
		}
		return res;
	}

	
	
	
	/**
	 * Convierte un objeto TransactionResponse con wildcard a un LogicResponse parametrizado.
	 * @param data Objeto a convertir.
	 * @return Objeto convertido.
	 */
	public LogicResponse<SolicitudPrestamo> convertWildcard(TransactionResponse<?> data) {
		LogicResponse<SolicitudPrestamo> x = new LogicResponse<SolicitudPrestamo>();
		x.status = data.status;
		x.errorMessage = data.dbError == null ? null : data.dbError.getMessage();
		x.exception = data.error;
		return x;
	}
	
	
	
	@Override
	public LogicResponse<SolicitudPrestamo> insert(SolicitudPrestamo arg0) {
		LogicResponse<SolicitudPrestamo> res = new LogicResponse<SolicitudPrestamo>();
		/*TransactionResponse<?>spr;
		try {
			spr = spDao.insert(arg0);
			if(spr.rowsAffected>0) {
				res.die(true, 201,"El registro se insertó con éxito. ");
			} else res.die(false, 500,"Hubo un error al intentar insertar el registro");
		}catch (SQLException e) {
			res.die(false,500,"Hubo un error al intentar insertar el registro.");
			e.printStackTrace();
		}*/
		try {
			res = convertWildcard(spDao.insert(arg0));
			res.objectReturned = arg0;
			res.message = res.status ? "El registro se insertó correctamente. " : "No se insertó ningún registro. ";
			res.http = res.status ? 201 : 500;
		} catch (SQLException e) {
			res.die(false, 500, "Hubo un error al intentar realizar la transacción. ");
			e.printStackTrace();
		}
		return res;
	}

	
	@Override
	public LogicResponse<SolicitudPrestamo> modify(SolicitudPrestamo data) {
		LogicResponse<SolicitudPrestamo> res = new LogicResponse<SolicitudPrestamo>();
		TransactionResponse<?> tpr;
		try {
			tpr = spDao.modify(data);
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
	public LogicResponse<SolicitudPrestamo> delete(SolicitudPrestamo data) {
		LogicResponse<SolicitudPrestamo> res = new LogicResponse<SolicitudPrestamo>();
		TransactionResponse<?> spr;
		try {
			spr = spDao.delete(data);
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
	public LogicResponse<SolicitudPrestamo> getAll() {
		LogicResponse<SolicitudPrestamo> res = new LogicResponse<SolicitudPrestamo>();
		TransactionResponse<SolicitudPrestamo> tpr = new TransactionResponse<SolicitudPrestamo>();
		try {
			tpr = spDao.getAll();
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
	public LogicResponse<SolicitudPrestamo> getById(String id) {
		LogicResponse<SolicitudPrestamo> res = new LogicResponse<SolicitudPrestamo>();
		TransactionResponse<SolicitudPrestamo> tpr = new TransactionResponse<SolicitudPrestamo>();
		try {
			tpr = spDao.getById(id);
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
	public LogicResponse<SolicitudPrestamo> exists(String id) {
		LogicResponse<SolicitudPrestamo> res = new LogicResponse<SolicitudPrestamo>();
		boolean existe = false;
		try {
			existe = spDao.exists(id);
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
	public SolicitudPrestamo convert(Dictionary d) {
		SolicitudPrestamo s = new SolicitudPrestamo();
		s.setCodigo(d.$("cod_Sol"));
		if(d.$("usuario_cl_Sol") != null) {
				s.setCliente(new Cliente() {{setUsuario(d.$("usuario_cl_Sol"));}});
		}
		s.setFechaPedido(d.$("fechaPedido_Sol"));
		s.setMontoPedido(d.$("montoPedido_Sol"));
		s.setMontoAPagar(d.$("montoAPagar_Sol"));
		s.setInteres(d.$("interes_Sol"));
		s.setPlazoPago(d.$("plazoPago_Sol"));
		s.setCantCuotas(d.$("cantCuotas_Sol"));
		s.setMontoPorCuota(d.$("montoPorCuota_Sol"));
		s.setEstado(d.$("estado_Sol"));
		
		return s;
	}
	

	@Override
	public List<SolicitudPrestamo> convert(List<Dictionary> rows) {
		List<SolicitudPrestamo> list = new ArrayList<SolicitudPrestamo>();
		for(Dictionary data : rows) {
			list.add(convert(data));
		}
		return list;
	}

}

