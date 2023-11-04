package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.DetalleCuotaPrestamoDao;
import entity.Cliente;
import entity.DetalleCuotaPrestamo;
import entity.PrestamosCliente;
import entity.SolicitudPrestamo;
import max.data.Dictionary;
import max.data.IRecordLogic;
import max.data.Response;
import max.data.TransactionResponse;
import max.oops.SchemaValidationException;

public class DetalleCuotaPrestamoLogic implements IRecordLogic<DetalleCuotaPrestamo,Integer> {

	private static DetalleCuotaPrestamoDao daoCtPrest = new DetalleCuotaPrestamoDao();
	
	public DetalleCuotaPrestamoLogic() {}
	
	
	@Override
	public Response<DetalleCuotaPrestamo> validate(DetalleCuotaPrestamo obj, boolean validar) {
		
		Response<DetalleCuotaPrestamo> lg = new Response<>();
		
		try {
				lg.status = validar
						? DetalleCuotaPrestamoDao._model.validate(obj.toDictionary())
						: DetalleCuotaPrestamoDao._schema.validate(obj.toDictionary());
		}catch(SchemaValidationException e) {
			e.printStackTrace();
			lg.die(false,e.getMessage());
		}
		
		return lg;		
	}

	@Override
	public Response<DetalleCuotaPrestamo> insert(DetalleCuotaPrestamo obj) {
		
		Response<DetalleCuotaPrestamo> lg = new Response<>();
		
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

	@Override
	public Response<DetalleCuotaPrestamo> modify(DetalleCuotaPrestamo obj) {

		Response<DetalleCuotaPrestamo> lg = new Response<>();
		
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

	@Override
	public Response<DetalleCuotaPrestamo> delete(DetalleCuotaPrestamo obj) {

		Response<DetalleCuotaPrestamo> lg = new Response<>();
		
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

	@Override
	public Response<DetalleCuotaPrestamo> getAll() {

		Response<DetalleCuotaPrestamo> lg = new Response<>();
		
		try {
				TransactionResponse<DetalleCuotaPrestamo> t = daoCtPrest.getAll();
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

	@Override
	public Response<DetalleCuotaPrestamo> getById(Integer id) {

		Response<DetalleCuotaPrestamo> lg = new Response<>();
		
		try {
				TransactionResponse<DetalleCuotaPrestamo> t = daoCtPrest.getById(id);
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
	
	public Response<DetalleCuotaPrestamo> filterByUserName(Cliente c){
		
		Response<DetalleCuotaPrestamo> lg = new Response<>();
		
		try {
				TransactionResponse<DetalleCuotaPrestamo> t = daoCtPrest.filterByUserName(c);
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

	@Override
	public Response<DetalleCuotaPrestamo> exists(Integer id) {
		
		Response<DetalleCuotaPrestamo> lg = new Response<>();
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

	@Override
	public DetalleCuotaPrestamo convert(Dictionary row) {
		
		DetalleCuotaPrestamo obj = new DetalleCuotaPrestamo();
				
		if(row.$("id_DTPT") != null) obj.setId_DTPT(row.$("id_DTPT"));
		
		if(row.$("usuario_cl_DTPT") != null) obj.setUsuario_cl_DTPT(new Cliente() {{
			this.setUsuario(row.$("usuario_cl_DTPT"));
		}});				
		
		if(row.$("fechaPago_DTPT") != null) obj.setFechaPago_DTPT(row.$("fechaPago_DTPT"));				
		
		if(row.$("numCuotaPagada_DTPT") != null) obj.setNumCuotaPagada_DTPT(row.$("numCuotaPagada_DTPT"));
				
		if(row.$("cod_Sol_DTPT") != null)obj.setCod_Sol_DTPT(new PrestamosCliente(){{
			this.setSolicitud(new SolicitudPrestamo() {{
				this.setCodigo(row.$("cod_Sol_DTPT"));
			}});
		}});	 
		
		return obj;
	}

	@Override
	public List<DetalleCuotaPrestamo> convert(List<Dictionary> rows) {
		
		List<DetalleCuotaPrestamo> ls = new ArrayList<>();
		
		for(Dictionary d : rows) {
			ls.add(convert(d));
		}		
		
		return ls;
	}

}