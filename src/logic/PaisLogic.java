package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataImpl.PaisDao;
import entity.Pais;
import max.data.Dictionary;
import max.data.IRecordLogic;
import max.data.Response;
import max.data.TransactionResponse;
import max.oops.SchemaValidationException;

public class PaisLogic implements IRecordLogic<Pais, String> {
	
	private static PaisDao data = new PaisDao();

	public PaisLogic() { }

	/**
	 * Convierte un objeto Dictionary a un objeto País.
	 */
	@Override
	public Pais convert(Dictionary data) {
		Pais p = new Pais();
		if(data.$("code") != null) {
			p.setCodigo(data.$("code"));
		} if(data.$("name") != null) {
			p.setNombre(data.$("name"));
		}
		return p;
	}

	/**
	 * Convierte una lista de Dictionary a una lista País.
	 */
	@Override
	public List<Pais> convert(List<Dictionary> list) {
		List<Pais> arrP = new ArrayList<Pais>();
		for(Dictionary data : list) {
			arrP.add(convert(data));
		}
		return arrP;
	}

	/**
	 * Elimina un registro.
	 */
	@Override
	public Response<Pais> delete(Pais arg0) {
		Response<Pais> res = new Response<Pais>();
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
	public Response<Pais> exists(String arg0) {
		Response<Pais> res = new Response<Pais>();
		boolean tn = false;
		try {
			tn = data.exists(arg0);
			if(tn) {
				res.die(true, "El registro existe. ");
			} else res.die(false, "No existe un registro con esas características. ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, "Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}

	/**
	 * Obtiene todos los registros de la base de datos.
	 */
	@Override
	public Response<Pais> getAll() {
		Response<Pais> res = new Response<Pais>();
		TransactionResponse<Pais> tn = new TransactionResponse<Pais>();
		try {
			tn = data.getAll();
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
	 * Busca un registro por su ID.
	 */
	@Override
	public Response<Pais> getById(String arg0) {
		Response<Pais> res = new Response<Pais>();
		TransactionResponse<Pais> tn = new TransactionResponse<Pais>();
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
	 * Inserta un registro.
	 */
	@Override
	public Response<Pais> insert(Pais arg0) {
		Response<Pais> res = new Response<Pais>();
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
	public Response<Pais> modify(Pais arg0) {
		Response<Pais> res = new Response<Pais>();
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
	 * Valida un objeto Pais.
	 * Si "validateConstraints" es falso, se valida usando el método Schema.validate().
	 * Si "validateConstraints" es verdadero, se valida usando el método IModel.validate(), que incluye validaciones en la base de datos.
	 */
	@Override
	public Response<Pais> validate(Pais arg0, boolean validateConstraints) {
		Response<Pais> res = new Response<Pais>();
		try {
			res.status = validateConstraints 
					? PaisDao._model.validate(arg0.toDictionary()) 
					: PaisDao._schema.validate(arg0.toDictionary());
		} catch (SchemaValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, e.getMessage());
		}
		return res;
	}
	
	

}
