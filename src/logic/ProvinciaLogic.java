package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.ProvinciaDao;
import entity.*;
import max.data.*;
import max.oops.SchemaValidationException;

public class ProvinciaLogic implements IRecordLogic<Provincia, Integer> {
	
	private static ProvinciaDao data = new ProvinciaDao();
	
	public ProvinciaLogic() {
		// TODO Auto-generated constructor stub 
		
	}

	@Override
	public Provincia convert(Dictionary data) {
		Provincia p = new Provincia();
		if(data.$("id_provincia") != null) {
			p.setId(data.$("id_provincia"));
		} if(data.$("nombre_provincia") != null) {
			p.setNombre(data.$("nombre_provincia"));
		}
		return p;
	}

	@Override
	public List<Provincia> convert(List<Dictionary> list) {
		List<Provincia> arrP = new ArrayList<Provincia>();
		for(Dictionary data : list) {
			arrP.add(convert(data));
		}
		return arrP;
	}

	@Override
	public LogicResponse<Provincia> delete(Provincia arg0) throws SQLException {
		LogicResponse<Provincia> res = new LogicResponse<Provincia>();
		TransactionResponse<?> tn = data.delete(arg0);
		if(tn.rowsAffected > 0) {
			res.die(true, "El registro se eliminó con éxito. ");
		} else res.die(false, "Hubo un error al intentar eliminar el registro. ");
		return res;
		
	}

	@Override
	public LogicResponse<Provincia> exists(Integer arg0) {
		LogicResponse<Provincia> res = new LogicResponse<Provincia>();
		boolean tn = false;
		try {
			tn = data.exists(arg0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(tn) {
			res.die(true, "El registro existe. ");
		} else res.die(false, "No existe un registro con esas características. ");
		return res;
	}

	@Override
	public LogicResponse<Provincia> getAll() {
		LogicResponse<Provincia> res = new LogicResponse<Provincia>();
		TransactionResponse<Provincia> tn = new TransactionResponse<Provincia>();
		try {
			tn = data.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(tn.nonEmptyResult()) {
			res.fill(tn.rowsReturned);
		} else res.die(false, "Hubo un error al intentar realizar la consulta. ");
		return res;
	}

	@Override
	public LogicResponse<Provincia> getById(Integer arg0) {
		LogicResponse<Provincia> res = new LogicResponse<Provincia>();
		TransactionResponse<Provincia> tn = new TransactionResponse<Provincia>();
		try {
			tn = data.getById(arg0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(tn.nonEmptyResult()) {
			res.fill(tn.rowsReturned);
		} else res.die(false, "Hubo un error al intentar realizar la consulta. ");
		return res;
	}

	@Override
	public LogicResponse<Provincia> insert(Provincia arg0) throws SQLException {
		LogicResponse<Provincia> res = new LogicResponse<Provincia>();
		TransactionResponse<?> tn = data.insert(arg0);
		if(tn.rowsAffected > 0) {
			res.die(true, "El registro se insertó con éxito. ");
		} else res.die(false, "Hubo un error al intentar insertar el registro. ");
		return res;
	}

	@Override
	public LogicResponse<Provincia> modify(Provincia arg0) throws SQLException {
		LogicResponse<Provincia> res = new LogicResponse<Provincia>();
		TransactionResponse<?> tn = data.modify(arg0);
		if(tn.rowsAffected > 0) {
			res.die(true, "El registro se modificó con éxito. ");
		} else res.die(false, "Hubo un error al intentar modificar el registro. ");
		return res;
	}

	/**
	 * Valida un objeto Provincia.
	 * Si "validateConstraints" es falso, se valida usando el método Schema.validate().
	 * Si "validateConstraints" es verdadero, se valida usando el método IModel.validate(), que incluye validaciones en la base de datos.
	 */
	@Override
	public LogicResponse<Provincia> validate(Provincia arg0, boolean validateConstraints) {
		LogicResponse<Provincia> res = new LogicResponse<Provincia>();
		try {
			res.status = validateConstraints 
					? Provincia._model.validate(arg0.toDictionary()) 
					: Provincia._schema.validate(arg0.toDictionary());
		} catch (SchemaValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, e.getMessage());
		}
		return res;
	}
	
	

}
