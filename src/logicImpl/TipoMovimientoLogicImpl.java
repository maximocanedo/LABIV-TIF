package logicImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataImpl.TipoMovimientoDaoImpl;
import entity.TipoMovimiento;
import logic.ITipoMovimientoLogic;
import max.Dictionary;
import max.IRecordLogic;
import max.Response;
import max.TransactionResponse;
import max.oops.SchemaValidationException;

public class TipoMovimientoLogicImpl implements IRecordLogic<TipoMovimiento,String>, ITipoMovimientoLogic {
	
	private static TipoMovimientoDaoImpl daoTPM = new TipoMovimientoDaoImpl();
	
	public TipoMovimientoLogicImpl() {}

	/* (non-Javadoc)
	 * @see logicImpl.ITipoMovimientoLogic#validate(entity.TipoMovimiento, boolean)
	 */
	@Override
	public Response<TipoMovimiento> validate(TipoMovimiento obj, boolean validar) {
		Response<TipoMovimiento> ltpm = new Response<>();
		
		try {
				ltpm.status = validar
						? TipoMovimientoDaoImpl._model.validate(obj.toDictionary())
						: TipoMovimientoDaoImpl._schema.validate(obj.toDictionary());
		}catch(SchemaValidationException e) {
			e.printStackTrace();
			ltpm.die(false,e.getMessage());
		}
		
		return ltpm;
	}

	/* (non-Javadoc)
	 * @see logicImpl.ITipoMovimientoLogic#insert(entity.TipoMovimiento)
	 */
	@Override
	public Response<TipoMovimiento> insert(TipoMovimiento obj) {
		
		Response<TipoMovimiento> ltpm = new Response<>();
				
		try {
				TransactionResponse<?> t = daoTPM.insert(obj);
				if(t.rowsAffected > 0) {
					ltpm.die(true, 201, "El registró se ingresó con éxito");
				}else {
					ltpm.die(false, 500, "Error al intentar ingresar el registro");
				}			
		}catch(SQLException e) {
			e.printStackTrace();
			ltpm.die(false, 500, "Error al intentar ingresar el registro");
		}
		
		
		return ltpm;
	}

	/* (non-Javadoc)
	 * @see logicImpl.ITipoMovimientoLogic#modify(entity.TipoMovimiento)
	 */
	@Override
	public Response<TipoMovimiento> modify(TipoMovimiento obj) {
		
		Response<TipoMovimiento> ltpm = new Response<>();
		
		try {
			TransactionResponse<?> t = daoTPM.modify(obj);
			if(t.rowsAffected > 0) {
				ltpm.die(true, 200, "El registró se modificó con éxito");
			}else {
				ltpm.die(false, 500, "Error al intentar modificar el registro");
			}
		}catch(SQLException e) {
			e.printStackTrace();
			ltpm.die(false, 500, "Error al intentar modificar el registro");
		}
				
		return ltpm;
	}

	/* (non-Javadoc)
	 * @see logicImpl.ITipoMovimientoLogic#delete(entity.TipoMovimiento)
	 */
	@Override
	public Response<TipoMovimiento> delete(TipoMovimiento obj) {
		
		Response<TipoMovimiento> ltpm = new Response<>();
		
		try {
				TransactionResponse<?> t = daoTPM.delete(obj);
				if(t.rowsAffected > 0) {
					ltpm.die(true, 200, "El registro se borró con éxito");
				}else {
					ltpm.die(false, 500, "Error al intentar borrar el registro");
				}			
		}catch(SQLException e) {
			e.printStackTrace();
			ltpm.die(false, 500, "Error al intentar borrar el registro");
		}
		
		return ltpm;
	}

	/* (non-Javadoc)
	 * @see logicImpl.ITipoMovimientoLogic#getAll()
	 */
	@Override
	public Response<TipoMovimiento> getAll() {
		Response<TipoMovimiento> ltpm = new Response<>();
		
		try {
				TransactionResponse<TipoMovimiento> t = daoTPM.getAll();
				if(t.nonEmptyResult()) {
					ltpm.fill(t.rowsReturned);					
				}else {
					ltpm.die(false, "Error al intentar obtener la lista de todos los registros");
				}
		}catch(SQLException e) {
			e.printStackTrace();
			ltpm.die(false, "Error al intentar obtener la lista de todos los registros");
		}
				
		return ltpm;
	}

	/* (non-Javadoc)
	 * @see logicImpl.ITipoMovimientoLogic#getById(java.lang.String)
	 */
	@Override
	public Response<TipoMovimiento> getById(String cod_TPMV) {
		
		Response<TipoMovimiento> ltpm = new Response<>();
		
		try {
				TransactionResponse<TipoMovimiento> t = daoTPM.getById(cod_TPMV);
				if(t.nonEmptyResult()) {
					ltpm.fill(t.rowsReturned);
				}else {
					ltpm.die(false, "Error al intentar obtener el registro por su clave primaria");
				}
		}catch(SQLException e) {
			e.printStackTrace();
			ltpm.die(false, "Error al intentar obtener el registro por su clave primaria");
		}
		
		return ltpm;
	}

	/* (non-Javadoc)
	 * @see logicImpl.ITipoMovimientoLogic#exists(java.lang.String)
	 */
	@Override
	public Response<TipoMovimiento> exists(String cod_TPMV) {
		
		Response<TipoMovimiento> ltpm = new Response<>();
		boolean estado = false;
		
		try {
				estado = daoTPM.exists(cod_TPMV);
				if(estado) {
					ltpm.die(true, "El registro ya existe");
				}else {
					ltpm.die(false, "El registro buscado no existe");
				}
		}catch(SQLException e) {
			e.printStackTrace();
			ltpm.die(false, "El registro buscado no existe");
		}
		
		return ltpm;
	}

	/* (non-Javadoc)
	 * @see logicImpl.ITipoMovimientoLogic#convert(max.data.Dictionary)
	 */
	@Override
	public TipoMovimiento convert(Dictionary row) {
		
		TipoMovimiento obj = new TipoMovimiento();
		
		if(row.$("cod_TPMV") != null) {
			obj.setCod_TPMV(row.$("cod_TPMV"));			
		}
		
		if(row.$("descripcion_TPMV") != null) {
			obj.setDescripcion_TPMV(row.$("descripcion_TPMV"));
		}
		
		return obj;
	}

	/* (non-Javadoc)
	 * @see logicImpl.ITipoMovimientoLogic#convert(java.util.List)
	 */
	@Override
	public List<TipoMovimiento> convert(List<Dictionary> rows) {
		
		List<TipoMovimiento> ls = new ArrayList<>();
		
		for(Dictionary d : rows) {
			ls.add(convert(d));
		}		
		
		return ls;
	}

}
