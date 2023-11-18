package logicImpl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dataImpl.SolicitudPrestamoDaoImpl;
import entity.Cliente;
import entity.Cuenta;
import entity.Paginator;
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
	
	public Response<SolicitudPrestamo> PagarCuota(SolicitudPrestamo data) {
		Response<SolicitudPrestamo> res = new Response<SolicitudPrestamo>();
		TransactionResponse<?> tpr;
		
		try {
			tpr = spDao.PagarCuota(data);
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
	
	public Response<SolicitudPrestamo> approve(SolicitudPrestamo data) {
		TransactionResponse<?> res = TransactionResponse.create();
		Response<SolicitudPrestamo> r = new Response<SolicitudPrestamo>();
		try {
			res = spDao.approve(data);
			r.die(res.rowsAffected > 0, (res.rowsAffected > 0) ? 200 : 501,  null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			r.die(false, 500, null);
			e.printStackTrace();
		}
		return r;
	}
	
	public Response<SolicitudPrestamo> reject(SolicitudPrestamo data) {
		TransactionResponse<?> res = TransactionResponse.create();
		Response<SolicitudPrestamo> r = new Response<SolicitudPrestamo>();
		try {
			res = spDao.reject(data);
			r.die(res.rowsAffected > 0, (res.rowsAffected > 0) ? 200 : 501,  null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			r.die(false, 500, null);
			e.printStackTrace();
		}
		return r;
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

	public Response<SolicitudPrestamo> getAll(Paginator paginator) {
		Response<SolicitudPrestamo> res = new Response<SolicitudPrestamo>();
		TransactionResponse<SolicitudPrestamo> tpr = new TransactionResponse<SolicitudPrestamo>();
		try {
			tpr = spDao.getAll(paginator);
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
	public Response<SolicitudPrestamo> getAllForClientByDNI(String DNI) {
		Response<SolicitudPrestamo> res = new Response<SolicitudPrestamo>();
		TransactionResponse<SolicitudPrestamo> tpr = new TransactionResponse<SolicitudPrestamo>();
		try {
			tpr = spDao.getAllForClientByDNI(DNI);
			if(tpr.rowsReturned != null) {
				res.fill(tpr.rowsReturned);
			} else res.die(false, "Hubo un error al intentar realizar la consulta. ");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, " Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}
	
	public Response<SolicitudPrestamo> getAllForClientByDNI(String DNI, Paginator paginator) {
		Response<SolicitudPrestamo> res = new Response<SolicitudPrestamo>();
		TransactionResponse<SolicitudPrestamo> tpr = new TransactionResponse<SolicitudPrestamo>();
		try {
			tpr = spDao.getAllForClientByDNI(DNI, paginator);
			if(tpr.rowsReturned != null) {
				res.fill(tpr.rowsReturned);
			} else res.die(false, "Hubo un error al intentar realizar la consulta. ");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, " Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}
	
	public Response<SolicitudPrestamo> getById(String id) {
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
		if(d.$("cod_Sol")!=null){s.setCodigo(d.$("cod_Sol"));}
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
		if(d.$("montoPedido_Sol")!=null) {s.setMontoPedido(d.$("montoPedido_Sol"));}
		if(d.$("montoAPagar_Sol")!=null) {s.setMontoAPagar(d.$("montoAPagar_Sol"));}
		//double i = (double)d.$("interes_Sol");
		//s.setInteres(i);
		if(d.$("interes_Sol")!=null) {s.setInteres(d.$("interes_Sol"));}
		if(d.$("plazoPago_Sol")!=null) {s.setPlazoPago(d.$("plazoPago_Sol"));}
		if(d.$("cantCuotas_Sol")!=null) {
			Double doo = Double.parseDouble(d.$("cantCuotas_Sol") + "");
			int c = doo.intValue();
			s.setCantCuotas(c);
			}
		if(d.$("montoPorCuota_Sol")!=null) {s.setMontoPorCuota(d.$("montoPorCuota_Sol"));}
		if(d.$("estado_Sol")!=null) {s.setEstado(d.$("estado_Sol"));}
		if(d.$("CBU_Sol") != null) {
			Cuenta cuenta = (new CuentaLogicImpl()).convert(d);
			cuenta.setCBU(d.$("CBU_Sol"));
			s.setCuenta(
					cuenta
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