package logicImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataImpl.DetalleCuotaPrestamoDaoImpl;
import entity.Cliente;
import entity.Cuota;
import entity.Prestamo;
import entity.SolicitudPrestamo;
import logic.IDetalleCuotaPrestamoLogic;
import max.Dictionary;
import max.IRecordLogic;
import max.Response;
import max.TransactionResponse;
import oops.SchemaValidationException;

public class DetalleCuotaPrestamoLogicImpl implements IRecordLogic<Cuota,Integer>, IDetalleCuotaPrestamoLogic {

	private static DetalleCuotaPrestamoDaoImpl daoCtPrest = new DetalleCuotaPrestamoDaoImpl();
	
	public DetalleCuotaPrestamoLogicImpl() {}
	
	
	/* (non-Javadoc)
	 * @see logicImpl.IDetalleCuotaPrestamoLogic#validate(entity.DetalleCuotaPrestamo, boolean)
	 */
	@Override
	public Response<Cuota> validate(Cuota obj, boolean validar) {
		
		Response<Cuota> lg = new Response<>();
		
		try {
				lg.status = validar
						? DetalleCuotaPrestamoDaoImpl._model.validate(obj.toDictionary())
						: DetalleCuotaPrestamoDaoImpl._schema.validate(obj.toDictionary());
		}catch(SchemaValidationException e) {
			e.printStackTrace();
			lg.die(false,e.getMessage());
		}
		
		return lg;		
	}

	/* (non-Javadoc)
	 * @see logicImpl.IDetalleCuotaPrestamoLogic#insert(entity.DetalleCuotaPrestamo)
	 */
	@Override
	public Response<Cuota> insert(Cuota obj) {
		
		Response<Cuota> lg = new Response<>();
		
		try {
				TransactionResponse<?> t = daoCtPrest.insert(obj);
				if(t.rowsAffected > 0) {
					lg.die(true, 201, "El registro ha sido ingresado con éxito");
				}else {
					lg.die(false, 500, "Error al intentar ingresar el registro");
				}
		}catch(SQLException e) {
			e.printStackTrace();
			lg.die(false, 500, "Error al intentar ingresar el registro");
		}		
		
		return lg;		
	}

	/* (non-Javadoc)
	 * @see logicImpl.IDetalleCuotaPrestamoLogic#modify(entity.DetalleCuotaPrestamo)
	 */
	@Override
	public Response<Cuota> modify(Cuota obj) {

		Response<Cuota> lg = new Response<>();
		
		try {
				TransactionResponse<?> t = daoCtPrest.modify(obj);
				if(t.rowsAffected > 0) {
					lg.die(true, 200, "El registro ha sido modificado con éxito");
				}else {
					lg.die(false, 500, "Error al intentar modificar el registro");
				}				
		}catch(SQLException e) {
			e.printStackTrace();
			lg.die(false, 500, "Error al intentar modificar el registro");
		}		
		
		return lg;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IDetalleCuotaPrestamoLogic#delete(entity.DetalleCuotaPrestamo)
	 */
	@Override
	public Response<Cuota> delete(Cuota obj) {

		Response<Cuota> lg = new Response<>();
		
		try {
				TransactionResponse<?> t = daoCtPrest.delete(obj);
				if(t.rowsAffected > 0) {
					lg.die(true, 200, "El registro ha sido borrado con éxito");
				}else {
					lg.die(false, 500, "Error al intentar borrar el registro");
				}
		}catch(SQLException e) {
			e.printStackTrace();
			lg.die(false, 500, "Error al intentar borrar el registro");
		}
		
		return lg;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IDetalleCuotaPrestamoLogic#getAll()
	 */
	@Override
	public Response<Cuota> getAll() {

		Response<Cuota> lg = new Response<>();
		
		try {
				TransactionResponse<Cuota> t = daoCtPrest.getAll();
				if(t.nonEmptyResult()) {
					lg.fill(t.rowsReturned);
				}else {
					lg.die(false, "Error al intentar obtener todos los registros");
				}
		}catch(SQLException e) {
			e.printStackTrace();
			lg.die(false, "Error al intentar obtener todos los registros");
		}		
		
		return lg;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IDetalleCuotaPrestamoLogic#getById(java.lang.Integer)
	 */
	@Override
	public Response<Cuota> getById(Integer id) {

		Response<Cuota> lg = new Response<>();
		
		try {
				TransactionResponse<Cuota> t = daoCtPrest.getById(id);
				if(t.nonEmptyResult()) {
					lg.fill(t.rowsReturned);
				}else {
					lg.die(false, "Error al intentar obtener registro por id");
				}
		}catch(SQLException e) {
			e.printStackTrace();
			lg.die(false, "Error al intentar obtener registro por id");
		}		
		
		return lg;
	}
	
	/* (non-Javadoc)
	 * @see logicImpl.IDetalleCuotaPrestamoLogic#filterByUserName(entity.Cliente)
	 */
	@Override
public Response<Cuota> filterByUserName(Cliente c){
		
		Response<Cuota> lg = new Response<>();
		
		try {
				TransactionResponse<Cuota> t = daoCtPrest.filterByUserName(c);
				if(t.nonEmptyResult()) {
					lg.fill(t.rowsReturned);
					lg.die(true, "Los registros por nombre de usuario se han obtenido exitosamente");
				}else {
					lg.die(false, "Error al intentar obtener los registros por nombre de usuario");
				}
		}catch(SQLException e) {
			e.printStackTrace();
			lg.die(false, "Error al intentar obtener los registros por nombre de usuario");
		}
		
		return lg;
	}
	
public Response<Cuota> getByRequest(SolicitudPrestamo sp) {
		Response<Cuota> lg = new Response<>();
		try {
			TransactionResponse<Cuota> t = daoCtPrest.getByRequest(sp);
			lg.fill(t.rowsReturned);
		} catch(SQLException e) {
			e.printStackTrace();
			lg.die(false, "Error al intentar obtener los registros por nombre de usuario");
		}
		
		return lg;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IDetalleCuotaPrestamoLogic#exists(java.lang.Integer)
	 */
	@Override
	public Response<Cuota> exists(Integer id) {
		
		Response<Cuota> lg = new Response<>();
		boolean estado = false;
		
		try {
				estado = daoCtPrest.exists(id);
				if(estado) {
					lg.die(true, "El registro ya existe");
				}else {
					lg.die(false, "El registro buscado no existe");
				}
		}catch(SQLException e) {
			e.printStackTrace();
			lg.die(false, "El registro buscado no existe");
		}		
		
		return lg;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IDetalleCuotaPrestamoLogic#convert(max.data.Dictionary)
	 */
	@Override
	public Cuota convert(Dictionary row) {
		
		Cuota obj = new Cuota();
				
		if(row.$("id_DTPT") != null) obj.setId_DTPT(row.$("id_DTPT"));
		
		if(row.$("usuario_cl_DTPT") != null) obj.setUsuario_cl_DTPT(new Cliente() {{
			this.setUsuario(row.$("usuario_cl_DTPT"));
		}});				
		
		if(row.$("fechaPago_DTPT") != null) obj.setFechaPago_DTPT(row.$("fechaPago_DTPT"));				
		
		if(row.$("numCuotaPagada_DTPT") != null) obj.setNumCuotaPagada_DTPT(row.$("numCuotaPagada_DTPT"));
				
		if(row.$("cod_Sol_DTPT") != null)obj.setCod_Sol_DTPT(new Prestamo(){{
			this.setSolicitud(new SolicitudPrestamo() {{
				this.setCodigo(row.$("cod_Sol_DTPT"));
			}});
		}});	 
		
		return obj;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IDetalleCuotaPrestamoLogic#convert(java.util.List)
	 */
	@Override
	public List<Cuota> convert(List<Dictionary> rows) {
		
		List<Cuota> ls = new ArrayList<>();
		
		for(Dictionary d : rows) {
			ls.add(convert(d));
		}		
		
		return ls;
	}


	public Response<Cuota> getAll(Cliente cliente) {
		Response<Cuota> res = new Response<Cuota>();
		TransactionResponse<Cuota> tpr = new TransactionResponse<Cuota>();
		try {
			tpr = daoCtPrest.getAll(cliente);
			if(tpr.nonEmptyResult()) {
				res.fill(tpr.rowsReturned);
			} else res.die(false, "Hubo un error al intentar realizar la consulta. ");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, " Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}
}

