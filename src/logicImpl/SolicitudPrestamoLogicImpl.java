package logicImpl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dataImpl.SolicitudPrestamoDaoImpl;
import entity.Cliente;
import entity.Cuenta;
import entity.SolicitudPrestamo;
import logic.ISolicitudPrestamoLogic;
import max.Dictionary;
import max.IRecordLogic;
import max.Response;
import max.TransactionResponse;
import oops.SchemaValidationException;


public class SolicitudPrestamoLogicImpl implements IRecordLogic<SolicitudPrestamo,String>, ISolicitudPrestamoLogic{

	public SolicitudPrestamoLogicImpl() {}
	
	private static SolicitudPrestamoDaoImpl spDao = new SolicitudPrestamoDaoImpl();
	
	/* (non-Javadoc)
	 * @see logicImpl.ISolicitudPrestamoLogic#validate(entity.SolicitudPrestamo, boolean)
	 */
	@Override
	public Response<SolicitudPrestamo> validate(SolicitudPrestamo data, boolean validateConstraints) {
		Response<SolicitudPrestamo> res = new Response<SolicitudPrestamo>();
		try {
			res.status = validateConstraints
					? SolicitudPrestamoDaoImpl._model.validate(data.toDictionary()) 
					: SolicitudPrestamoDaoImpl._schema.validate(data.toDictionary());
		} catch (SchemaValidationException e) {
			e.printStackTrace();
			res.die(false, e.getMessage());
		}
		return res;
	}

	
	
	
	/* (non-Javadoc)
	 * @see logicImpl.ISolicitudPrestamoLogic#convertWildcard(max.data.TransactionResponse)
	 */
	@Override
	public Response<SolicitudPrestamo> convertWildcard(TransactionResponse<?> data) {
		Response<SolicitudPrestamo> x = new Response<SolicitudPrestamo>();
		x.status = data.status;
		x.errorMessage = data.dbError == null ? null : data.dbError.getMessage();
		x.exception = data.error;
		return x;
	}
	
	
	
	/* (non-Javadoc)
	 * @see logicImpl.ISolicitudPrestamoLogic#insert(entity.SolicitudPrestamo)
	 */
	@Override
	public Response<SolicitudPrestamo> insert(SolicitudPrestamo data) {
		/*Response<SolicitudPrestamo> res = new Response<SolicitudPrestamo>();
		TransactionResponse<?>spr;
		try {
			spr = spDao.insert(arg0);
			if(spr.rowsAffected>0) {
				res.die(true, 201,"El registro se insertó con éxito. ");
			} else res.die(false, 500,"Hubo un error al intentar insertar el registro");
		}catch (SQLException e) {
			res.die(false,500,"Hubo un error al intentar insertar el registro.");
			e.printStackTrace();
		}
		try {
			res = convertWildcard(spDao.insert(arg0));
			res.objectReturned = arg0;
			res.message = res.status ? "El registro se insertó correctamente. " : "No se insertó ningún registro. ";
			res.http = res.status ? 201 : 500;
		} catch (SQLException e) {
			res.die(false, 500, "Hubo un error al intentar realizar la transacción. ");
			e.printStackTrace();
		}
		return res;*/
		Response<SolicitudPrestamo> res = new Response<SolicitudPrestamo>();
		TransactionResponse<?> tpr;
		
		try {
			tpr = spDao.insert(data);
			if(tpr.rowsAffected > 0) {
				res.die(true, 201, "El registro se inserto con exito. ");
			} else res.die(false, 500, "Hubo un error al intentar insertar el registro. ");
		} catch (SQLException e) {
			res.die(false, 500, " Hubo un error al intentar insertar el registro. ");
			e.printStackTrace();
		}
		return res;
	}

	
	/* (non-Javadoc)
	 * @see logicImpl.ISolicitudPrestamoLogic#modify(entity.SolicitudPrestamo)
	 */
	@Override
	public Response<SolicitudPrestamo> modify(SolicitudPrestamo data) {
		Response<SolicitudPrestamo> res = new Response<SolicitudPrestamo>();
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

	/* (non-Javadoc)
	 * @see logicImpl.ISolicitudPrestamoLogic#delete(entity.SolicitudPrestamo)
	 */
	@Override
	public Response<SolicitudPrestamo> delete(SolicitudPrestamo data) {
		Response<SolicitudPrestamo> res = new Response<SolicitudPrestamo>();
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

	/* (non-Javadoc)
	 * @see logicImpl.ISolicitudPrestamoLogic#getAll()
	 */
	@Override
	public Response<SolicitudPrestamo> getAll() {
		Response<SolicitudPrestamo> res = new Response<SolicitudPrestamo>();
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

	/* (non-Javadoc)
	 * @see logicImpl.ISolicitudPrestamoLogic#getById(java.lang.String)
	 */
	@Override
	public Response<SolicitudPrestamo> getById(String id) {
		Response<SolicitudPrestamo> res = new Response<SolicitudPrestamo>();
		TransactionResponse<SolicitudPrestamo> tpr = new TransactionResponse<SolicitudPrestamo>();
		try {
			tpr = spDao.getById(id);
			if(tpr.rowsReturned != null) {
				res.fill(tpr.rowsReturned);
			} else res.die(false, "Hubo un error al intentar realizar la consulta. ");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, " Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}
	
	public Response<SolicitudPrestamo> getById(Integer id) {
		Response<SolicitudPrestamo> res = new Response<SolicitudPrestamo>();
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

	/* (non-Javadoc)
	 * @see logicImpl.ISolicitudPrestamoLogic#exists(java.lang.String)
	 */
	@Override
	public Response<SolicitudPrestamo> exists(String id) {
		Response<SolicitudPrestamo> res = new Response<SolicitudPrestamo>();
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

	/* (non-Javadoc)
	 * @see logicImpl.ISolicitudPrestamoLogic#convert(max.data.Dictionary)
	 */
	@Override
	public SolicitudPrestamo convert(Dictionary d) {
		SolicitudPrestamo s = new SolicitudPrestamo();
		s.setCodigo(d.$("cod_Sol"));
		if(d.$("usuario_cl_Sol") != null) {
			Cliente c = (new ClienteLogicImpl()).convert(d);
			c.setUsuario(d.$("usuario_cl_Sol"));
			s.setCliente(c);
				
		}
		
		if(d.$("fechaPedido_Sol") != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date parsedDate = new java.util.Date();
			try {
				parsedDate = dateFormat.parse(d.$("fechaPedido_Sol") + "");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        java.sql.Date edate = new java.sql.Date(parsedDate.getTime());
	        s.setFechaPedido(edate);
			
		}
		s.setMontoPedido(d.$("montoPedido_Sol"));
		s.setMontoAPagar(d.$("montoAPagar_Sol"));
		s.setInteres(d.$("interes_Sol"));
		s.setPlazoPago(d.$("plazoPago_Sol"));
		s.setCantCuotas(d.$("cantCuotas_Sol"));
		s.setMontoPorCuota(d.$("montoPorCuota_Sol"));
		s.setEstado(d.$("estado_Sol"));
		if(d.$("CBU_Sol") != null) {
			s.setCuenta(
					(new CuentaLogicImpl()).convert(d)
					);
		}
		return s;
	}
	

	/* (non-Javadoc)
	 * @see logicImpl.ISolicitudPrestamoLogic#convert(java.util.List)
	 */
	@Override
	public List<SolicitudPrestamo> convert(List<Dictionary> rows) {
		List<SolicitudPrestamo> list = new ArrayList<SolicitudPrestamo>();
		for(Dictionary data : rows) {
			list.add(convert(data));
		}
		return list;
	}

}