package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.ConceptoDao;
import entity.Concepto;
import max.data.*;
import max.oops.SchemaValidationException;

public class ConceptoLogic implements IRecordLogic<Concepto,String> {
	
	private static ConceptoDao daoCon = new ConceptoDao();
	
	public ConceptoLogic() {}

	@Override
	public LogicResponse<Concepto> validate(Concepto obj, boolean validar) {
		
		LogicResponse<Concepto> lc = new LogicResponse<>();
		
		try {
				lc.status = validar
						? ConceptoDao._model.validate(obj.toDictionary())
						: ConceptoDao._schema.validate(obj.toDictionary());			
		}catch(SchemaValidationException e) {
			e.printStackTrace();
			lc.die(false,e.getMessage());
		}
		
		return lc;
	}

	@Override
	public LogicResponse<Concepto> insert(Concepto obj) {
		
		LogicResponse<Concepto> lc = new LogicResponse<>();
		
		try {
				TransactionResponse<?> t = daoCon.insert(obj);
				if(t.rowsAffected > 0) {
					lc.die(true, 201,"El registro ha sido ingresado con éxito.");
				}else {
					lc.die(false, 500, "Error al intentar ingresar el registro.");
				}
		}catch(SQLException e) {
			e.printStackTrace();
			lc.die(false, 500, "Error al intentar ingresar el registro");			
		}
		
		
		return lc;
	}

	@Override
	public LogicResponse<Concepto> modify(Concepto obj) {
		
		LogicResponse<Concepto> lc = new LogicResponse<>();
		
		try {
				TransactionResponse<?> t = daoCon.modify(obj);
				if(t.rowsAffected > 0) {
					lc.die(true, 200, "El registro ha sido modificado con éxito");
				}else {
					lc.die(false, 500, "Error al intentar modificar el registro");
				}
			
		}catch(SQLException e) {
			e.printStackTrace();
			lc.die(false, 500, "Error al intentar modificar el registro.");
		}
		
		return lc;
	}

	@Override
	public LogicResponse<Concepto> delete(Concepto obj) {
		
		LogicResponse<Concepto> lc = new LogicResponse<>();
		
		try {
				TransactionResponse<?> t = daoCon.delete(obj);
				if(t.rowsAffected > 0) {
					lc.die(true, 200, "El registro ha sido eliminado con éxito");
				}else {
					lc.die(false, 500, "Error al intentar borrar el registro.");
				}
			
		}catch(SQLException e) {
			e.printStackTrace();
			lc.die(false, 500, "Error al intentar borrar el registro.");
		}
		
		return lc;
	}

	@Override
	public LogicResponse<Concepto> getAll() {
		
		LogicResponse<Concepto> lc = new LogicResponse<>();
		
		try {
				TransactionResponse<Concepto> tr = daoCon.getAll();
				if(tr.nonEmptyResult()) {
					lc.fill(tr.rowsReturned);
				}else {
					lc.die(false, "Error al intentar obtener todos los registros" );
				}
		}catch(SQLException e) {
			e.printStackTrace();
			lc.die(false, "Error al intentar obtener todos los registros");
		}		
		
		return lc;
	}

	@Override
	public LogicResponse<Concepto> getById(String cod_Con) {
		
		LogicResponse<Concepto> lc = new LogicResponse<>();
		
		try {
				TransactionResponse<Concepto> tr = daoCon.getById(cod_Con);
				if(tr.nonEmptyResult()) {
					lc.fill(tr.rowsReturned);
				}else {
					lc.die(false, "Error al intentar obtener el registro buscado");
				}		
		}catch(SQLException e) {
			e.printStackTrace();
			lc.die(false, "Error al intentar obtener el registro buscado");
		}		
		
		return lc;
	}

	@Override
	public LogicResponse<Concepto> exists(String cod_Con) {
		
		LogicResponse<Concepto> lc = new LogicResponse<>();
		boolean estado = false;
		
		try {
				estado = daoCon.exists(cod_Con);
				if(estado) {
					lc.die(true, "El registro ya existe");
				}else {
					lc.die(false, "El registro buscado no existe");
				}			
		}catch(SQLException e) {
			e.printStackTrace();
			lc.die(false, "El registro buscado no existe");			
		}		
		
		return lc;
	}

	@Override
	public Concepto convert(Dictionary row) {
		
		Concepto obj = new Concepto();
		
		if(row.$("cod_Con") != null) {
			obj.setCod_Con(row.$("cod_Con"));
		}
		
		if(row.$("descripcion_Con") != null) {
			obj.setDescripcion_Con(row.$("descripcion_Con"));
		}
		
		return obj;
	}

	@Override
	public List<Concepto> convert(List<Dictionary> rows) {
		
		List<Concepto> ls = new ArrayList<>();
		
		for(Dictionary d : rows) {
			ls.add(convert(d));
		}		
		
		return ls;
	}

}
