package logicImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataImpl.ProvinciaDao;
import entity.Provincia;
import max.data.Dictionary;
import max.data.IRecordLogic;
import max.data.Response;
import max.data.TransactionResponse;
import max.oops.SchemaValidationException;

public class ProvinciaLogic implements IRecordLogic<Provincia, Integer> {
	
	private static ProvinciaDao data = new ProvinciaDao();
	
	public ProvinciaLogic() { }

	/**
	 * Convierte un Dictionary en un objeto Provincia.
	 */
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

	/**
	 * Convierte una lista de Dictionary en una lista de provincias.
	 */
	@Override
	public List<Provincia> convert(List<Dictionary> list) {
		List<Provincia> arrP = new ArrayList<Provincia>();
		for(Dictionary data : list) {
			arrP.add(convert(data));
		}
		return arrP;
	}

	/**
	 * Elimina un registro.
	 */
	@Override
	public Response<Provincia> delete(Provincia arg0) {
		Response<Provincia> res = new Response<Provincia>();
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
	 * Determina si existe un registro. 
	 */
	@Override
	public Response<Provincia> exists(Integer arg0) {
		Response<Provincia> res = new Response<Provincia>();
		boolean tn = false;
		try {
			tn = data.exists(arg0);
			if(tn) {
				res.die(true, 200, "El registro existe. ");
			} else res.die(false, 404, "No existe un registro con esas características. ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, 500, "Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}

	/**
	 * Obtiene todos los registros en la base de datos.
	 */
	@Override
	public Response<Provincia> getAll() {
		Response<Provincia> res = new Response<Provincia>();
		TransactionResponse<Provincia> tn = new TransactionResponse<Provincia>();
		try {
			tn = data.getAll();
			if(tn.nonEmptyResult()) {
				res.fill(tn.rowsReturned);
			} else res.die(false, 500, "Hubo un error al intentar realizar la consulta. ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, 500, "Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}

	/**
	 * Busca un registro por su ID.
	 */
	@Override
	public Response<Provincia> getById(Integer arg0) {
		Response<Provincia> res = new Response<Provincia>();
		TransactionResponse<Provincia> tn = new TransactionResponse<Provincia>();
		try {
			tn = data.getById(arg0);
			if(tn.nonEmptyResult()) {
				res.fill(tn.rowsReturned);
			} else res.die(false, 404, "No existe un registro con esas características. ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, 500, "Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}

	/**
	 * Inserta un registro.
	 */
	@Override
	public Response<Provincia> insert(Provincia arg0) {
		Response<Provincia> res = new Response<Provincia>();
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
	 * Modifica un registro.
	 */
	@Override
	public Response<Provincia> modify(Provincia arg0) {
		Response<Provincia> res = new Response<Provincia>();
		TransactionResponse<?> tn;
		try {
			tn = data.modify(arg0);
			if(tn.rowsAffected > 0) {
				res.die(true, "El registro se modificó con éxito. ");
			} else res.die(false, 500, "Hubo un error al intentar modificar el registro. ");
		} catch (SQLException e) {
			res.die(false, 500, "Hubo un error al intentar modificar el registro. ");
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Valida un objeto Provincia.
	 * Si "validateConstraints" es falso, se valida usando el método Schema.validate().
	 * Si "validateConstraints" es verdadero, se valida usando el método IModel.validate(), que incluye validaciones en la base de datos.
	 */
	@Override
	public Response<Provincia> validate(Provincia arg0, boolean validateConstraints) {
		Response<Provincia> res = new Response<Provincia>();
		try {
			res.status = validateConstraints 
					? ProvinciaDao._model.validate(arg0.toDictionary()) 
					: ProvinciaDao._schema.validate(arg0.toDictionary());
		} catch (SchemaValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, e.getMessage());
		}
		return res;
	}

}
