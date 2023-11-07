package logicImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataImpl.ContinenteDao;
import entity.*;
import logic.IContinenteLogic;
import max.data.*;
import max.oops.SchemaValidationException;

public class ContinenteLogic implements IRecordLogic<Continente, String>, IContinenteLogic {
	
	private static ContinenteDao data = new ContinenteDao();
	
	public ContinenteLogic() {}

	/**
	 * Convierte un objeto Dictionary a un objeto de tipo Continente.
	 */
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

	/**
	 * Convierte una lista de Dictionary a una lista de Continente.
	 */
	@Override
	public List<Continente> convert(List<Dictionary> list) {
		List<Continente> arrP = new ArrayList<Continente>();
		for(Dictionary data : list) {
			arrP.add(convert(data));
		}
		return arrP;
	}

	/**
	 * Elimina un registro.
	 */
	@Override
	public Response<Continente> delete(Continente arg0){
		Response<Continente> res = new Response<Continente>();
		TransactionResponse<?> tn;
		try {
			tn = data.delete(arg0);
			if(tn.rowsAffected > 0) {
				res.die(true, 200, "El registro se eliminó con éxito. ");
			} else res.die(false, 500, "Hubo un error al intentar eliminar el registro. ");
		} catch (SQLException e) {
			res.die(false, 500, "Hubo un error al intentar eliminar el registro. ");
			e.printStackTrace();
		}
		return res;
		
	}

	/**
	 * Determina si un registro existe.
	 */
	@Override
	public Response<Continente> exists(String arg0) {
		Response<Continente> res = new Response<Continente>();
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

	/**
	 * Obtiene todos los registros de la base de datos.
	 */
	@Override
	public Response<Continente> getAll() {
		Response<Continente> res = new Response<Continente>();
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

	/**
	 * Busca un registro por su ID.
	 */
	@Override
	public Response<Continente> getById(String arg0) {
		Response<Continente> res = new Response<Continente>();
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

	/**
	 * Inserta un registro en la base de datos.
	 */
	@Override
	public Response<Continente> insert(Continente arg0){
		Response<Continente> res = new Response<Continente>();
		TransactionResponse<?> tn;
		try {
			tn = data.insert(arg0);
			if(tn.rowsAffected > 0) {
				res.die(true, 201, "El registro se insertó con éxito. ");
			} else res.die(false, 500, "Hubo un error al intentar insertar el registro. ");
		} catch (SQLException e) {
			res.die(false, 500, "Hubo un error al intentar insertar el registro. ");
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Modifica un registro en la base de datos.
	 */
	@Override
	public Response<Continente> modify(Continente arg0) {
		Response<Continente> res = new Response<Continente>();
		TransactionResponse<?> tn;
		try {
			tn = data.modify(arg0);
			if(tn.rowsAffected > 0) {
				res.die(true, 200, "El registro se modificó con éxito. ");
			} else res.die(false, 500, "Hubo un error al intentar modificar el registro. ");
		} catch (SQLException e) {
			res.die(false, 500, "Hubo un error al intentar modificar el registro. ");
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Valida un objeto Continente.
	 * Si "validateConstraints" es falso, se valida usando el método Schema.validate().
	 * Si "validateConstraints" es verdadero, se valida usando el método IModel.validate(), que incluye validaciones en la base de datos.
	 */
	@Override
	public Response<Continente> validate(Continente arg0, boolean validateConstraints) {
		Response<Continente> res = new Response<Continente>();
		try {
			res.status = validateConstraints 
					? ContinenteDao._model.validate(arg0.toDictionary()) 
					: ContinenteDao._schema.validate(arg0.toDictionary());
		} catch (SchemaValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, e.getMessage());
		}
		return res;
	}
	
	

}
