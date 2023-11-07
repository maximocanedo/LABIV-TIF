package logicImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataImpl.LocalidadDao;
import entity.*;
import max.data.*;
import max.oops.SchemaValidationException;

public class LocalidadLogic implements IRecordLogic<Localidad, Integer> {
	
	private static LocalidadDao data = new LocalidadDao();
	
	public LocalidadLogic() { }

	/**
	 * Convierte un Dictionary a un objeto Localidad.
	 */
	@Override
	public Localidad convert(Dictionary data) {
		Localidad p = new Localidad();
		if(data.$("id_loc") != null) {
			p.setId(data.$("id_loc"));
		} if(data.$("nombre_loc") != null) {
			p.setNombre(data.$("nombre_loc"));
		} if(data.$("provincia_loc") != null) {
			p.setProvincia(new Provincia() {{
				setId(data.$("provincia_loc"));
			}});
		}
		return p;
	}
	
	/**
	 * Convierte una lista de Dictionary a una lista de localidades.
	 */
	@Override
	public List<Localidad> convert(List<Dictionary> list) {
		List<Localidad> arrP = new ArrayList<Localidad>();
		for(Dictionary data : list) {
			arrP.add(convert(data));
		}
		return arrP;
	}

	/**
	 * Elimina un registro.
	 */
	@Override
	public Response<Localidad> delete(Localidad arg0) {
		Response<Localidad> res = new Response<Localidad>();
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
	public Response<Localidad> exists(Integer arg0) {
		Response<Localidad> res = new Response<Localidad>();
		boolean tn = false;
		try {
			tn = data.exists(arg0);
			if(tn) {
				res.die(true, 200, "El registro existe. ");
			} else res.die(false, 404, "No existe un registro con esas características. ");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, 500, "Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}

	/**
	 * Obtiene todos los registros de la base de datos.
	 */
	@Override
	public Response<Localidad> getAll() {
		Response<Localidad> res = new Response<Localidad>();
		TransactionResponse<Localidad> tn = new TransactionResponse<Localidad>();
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
	 * Obtiene todas las localidades de una provincia.
	 * @param p Objeto Provincia con el ID de la provincia a filtrar.
	 * @return Resultado de la operación.
	 */
	public Response<Localidad> filterByProvince(Provincia p) {
		Response<Localidad> res = new Response<Localidad>();
		TransactionResponse<Localidad> tn = new TransactionResponse<Localidad>();
		try {
			tn = data.filterByProvince(p);
			if(tn.nonEmptyResult()) {
				res.fill(tn.rowsReturned);
				res.http = 200;
			} else res.die(false, 500, "Hubo un error al intentar realizar la consulta. ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, 500, "Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}
	
	/**
	 * Busca una localidad por su ID.
	 */
	@Override
	public Response<Localidad> getById(Integer arg0) {
		Response<Localidad> res = new Response<Localidad>();
		TransactionResponse<Localidad> tn = new TransactionResponse<Localidad>();
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
	public Response<Localidad> insert(Localidad arg0) {
		Response<Localidad> res = new Response<Localidad>();
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
	public Response<Localidad> modify(Localidad arg0) {
		Response<Localidad> res = new Response<Localidad>();
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
	 * Valida un objeto Localidad.
	 * Si "validateConstraints" es falso, se valida usando el método Schema.validate().
	 * Si "validateConstraints" es verdadero, se valida usando el método IModel.validate(), que incluye validaciones en la base de datos.
	 */
	@Override
	public Response<Localidad> validate(Localidad arg0, boolean validateConstraints) {
		Response<Localidad> res = new Response<Localidad>();
		try {
			res.status = validateConstraints 
					? LocalidadDao._model.validate(arg0.toDictionary()) 
					: LocalidadDao._schema.validate(arg0.toDictionary());
		} catch (SchemaValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, e.getMessage());
		}
		return res;
	}
	
	

}
