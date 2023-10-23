package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.ContinenteDao;
import entity.*;
import max.data.*;
import max.oops.SchemaValidationException;

public class ContinenteLogic implements IRecordLogic<Continente, String> {
	
	private static ContinenteDao data = new ContinenteDao();
	
	public ContinenteLogic() {
		// TODO Auto-generated constructor stub 
		
	}

	@Override
	public Continente convert(Dictionary data) {
		Continente p = new Continente();
		if(data.$("code") != null) {
			p.setCodigo(data.$("code"));
		} if(data.$("name") != null) {
			p.setNombre(data.$("name"));
		}
		return p;
	}

	@Override
	public List<Continente> convert(List<Dictionary> list) {
		List<Continente> arrP = new ArrayList<Continente>();
		for(Dictionary data : list) {
			arrP.add(convert(data));
		}
		return arrP;
	}

	@Override
	public LogicResponse<Continente> delete(Continente arg0) throws SQLException {
		LogicResponse<Continente> res = new LogicResponse<Continente>();
		TransactionResponse<?> tn = data.delete(arg0);
		if(tn.rowsAffected > 0) {
			res.die(true, "El registro se eliminó con éxito. ");
		} else res.die(false, "Hubo un error al intentar eliminar el registro. ");
		return res;
		
	}

	@Override
	public LogicResponse<Continente> exists(String arg0) {
		LogicResponse<Continente> res = new LogicResponse<Continente>();
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
	public LogicResponse<Continente> getAll() {
		LogicResponse<Continente> res = new LogicResponse<Continente>();
		TransactionResponse<Continente> tn = new TransactionResponse<Continente>();
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
	public LogicResponse<Continente> getById(String arg0) {
		LogicResponse<Continente> res = new LogicResponse<Continente>();
		TransactionResponse<Continente> tn = new TransactionResponse<Continente>();
		try {
			tn = data.getById(arg0);
			if(tn.nonEmptyResult()) {
				res.fill(tn.rowsReturned);
			} else res.die(false, "Hubo un error al intentar realizar la consulta. ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, "Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}

	@Override
	public LogicResponse<Continente> insert(Continente arg0) throws SQLException {
		LogicResponse<Continente> res = new LogicResponse<Continente>();
		TransactionResponse<?> tn = data.insert(arg0);
		if(tn.rowsAffected > 0) {
			res.die(true, "El registro se insertó con éxito. ");
		} else res.die(false, "Hubo un error al intentar insertar el registro. ");
		return res;
	}

	@Override
	public LogicResponse<Continente> modify(Continente arg0) throws SQLException {
		LogicResponse<Continente> res = new LogicResponse<Continente>();
		TransactionResponse<?> tn = data.modify(arg0);
		if(tn.rowsAffected > 0) {
			res.die(true, "El registro se modificó con éxito. ");
		} else res.die(false, "Hubo un error al intentar modificar el registro. ");
		return res;
	}

	/**
	 * Valida un objeto Continente.
	 * Si "validateConstraints" es falso, se valida usando el método Schema.validate().
	 * Si "validateConstraints" es verdadero, se valida usando el método IModel.validate(), que incluye validaciones en la base de datos.
	 */
	@Override
	public LogicResponse<Continente> validate(Continente arg0, boolean validateConstraints) {
		LogicResponse<Continente> res = new LogicResponse<Continente>();
		try {
			res.status = validateConstraints 
					? ContinenteDao._model.validate(arg0.toDictionary()) 
					: Continente._schema.validate(arg0.toDictionary());
		} catch (SchemaValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, e.getMessage());
		}
		return res;
	}
	
	

}
