package logicImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataImpl.LocalidadDaoImpl;
import entity.*;
import logic.ILocalidadLogic;
import max.Dictionary;
import max.IRecordLogic;
import max.Response;
import max.TransactionResponse;
import max.data.*;
import max.oops.SchemaValidationException;

public class LocalidadLogicImpl implements IRecordLogic<Localidad, Integer>, ILocalidadLogic {
	
	private static LocalidadDaoImpl data = new LocalidadDaoImpl();
	
	public LocalidadLogicImpl() { }

	/* (non-Javadoc)
	 * @see logicImpl.ILocalidadLogic#convert(max.data.Dictionary)
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
	
	/* (non-Javadoc)
	 * @see logicImpl.ILocalidadLogic#convert(java.util.List)
	 */
	@Override
	public List<Localidad> convert(List<Dictionary> list) {
		List<Localidad> arrP = new ArrayList<Localidad>();
		for(Dictionary data : list) {
			arrP.add(convert(data));
		}
		return arrP;
	}

	/* (non-Javadoc)
	 * @see logicImpl.ILocalidadLogic#delete(entity.Localidad)
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

	/* (non-Javadoc)
	 * @see logicImpl.ILocalidadLogic#exists(java.lang.Integer)
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

	/* (non-Javadoc)
	 * @see logicImpl.ILocalidadLogic#getAll()
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
	
	/* (non-Javadoc)
	 * @see logicImpl.ILocalidadLogic#filterByProvince(entity.Provincia)
	 */
	@Override
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
	
	/* (non-Javadoc)
	 * @see logicImpl.ILocalidadLogic#getById(java.lang.Integer)
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

	/* (non-Javadoc)
	 * @see logicImpl.ILocalidadLogic#insert(entity.Localidad)
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

	/* (non-Javadoc)
	 * @see logicImpl.ILocalidadLogic#modify(entity.Localidad)
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

	/* (non-Javadoc)
	 * @see logicImpl.ILocalidadLogic#validate(entity.Localidad, boolean)
	 */
	@Override
	public Response<Localidad> validate(Localidad arg0, boolean validateConstraints) {
		Response<Localidad> res = new Response<Localidad>();
		try {
			res.status = validateConstraints 
					? LocalidadDaoImpl._model.validate(arg0.toDictionary()) 
					: LocalidadDaoImpl._schema.validate(arg0.toDictionary());
		} catch (SchemaValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, e.getMessage());
		}
		return res;
	}
	
	

}
