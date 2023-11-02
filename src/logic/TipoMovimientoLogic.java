package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.TipoMovimientoDao;
import entity.TipoMovimiento;
import max.data.Dictionary;
import max.data.IRecordLogic;
import max.data.LogicResponse;
import max.data.TransactionResponse;
import max.oops.SchemaValidationException;

public class TipoMovimientoLogic implements IRecordLogic<TipoMovimiento,String> {
	
	private static TipoMovimientoDao daoTPM = new TipoMovimientoDao();
	
	public TipoMovimientoLogic() {}

	@Override
	public LogicResponse<TipoMovimiento> validate(TipoMovimiento obj, boolean validar) {
		LogicResponse<TipoMovimiento> ltpm = new LogicResponse<>();
		
		try {
				ltpm.status = validar
						? TipoMovimientoDao._model.validate(obj.toDictionary())
						: TipoMovimientoDao._schema.validate(obj.toDictionary());
		}catch(SchemaValidationException e) {
			e.printStackTrace();
			ltpm.die(false,e.getMessage());
		}
		
		return ltpm;
	}

	@Override
	public LogicResponse<TipoMovimiento> insert(TipoMovimiento obj) {
		
		LogicResponse<TipoMovimiento> ltpm = new LogicResponse<>();
				
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

	@Override
	public LogicResponse<TipoMovimiento> modify(TipoMovimiento obj) {
		
		LogicResponse<TipoMovimiento> ltpm = new LogicResponse<>();
		
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

	@Override
	public LogicResponse<TipoMovimiento> delete(TipoMovimiento obj) {
		
		LogicResponse<TipoMovimiento> ltpm = new LogicResponse<>();
		
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

	@Override
	public LogicResponse<TipoMovimiento> getAll() {
		LogicResponse<TipoMovimiento> ltpm = new LogicResponse<>();
		
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

	@Override
	public LogicResponse<TipoMovimiento> getById(String cod_TPMV) {
		
		LogicResponse<TipoMovimiento> ltpm = new LogicResponse<>();
		
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

	@Override
	public LogicResponse<TipoMovimiento> exists(String cod_TPMV) {
		
		LogicResponse<TipoMovimiento> ltpm = new LogicResponse<>();
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

	@Override
	public List<TipoMovimiento> convert(List<Dictionary> rows) {
		
		List<TipoMovimiento> ls = new ArrayList<>();
		
		for(Dictionary d : rows) {
			ls.add(convert(d));
		}		
		
		return ls;
	}

}
