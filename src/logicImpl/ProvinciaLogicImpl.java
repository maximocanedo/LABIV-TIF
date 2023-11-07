package logicImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataImpl.ProvinciaDaoImpl;
import entity.Provincia;
import logic.IProvinciaLogic;
import max.Dictionary;
import max.IRecordLogic;
import max.Response;
import max.TransactionResponse;
import oops.SchemaValidationException;

public class ProvinciaLogicImpl implements IRecordLogic<Provincia, Integer>, IProvinciaLogic {
	
	private static ProvinciaDaoImpl data = new ProvinciaDaoImpl();
	
	public ProvinciaLogicImpl() { }

	/* (non-Javadoc)
	 * @see logicImpl.IProvinciaLogic#convert(max.data.Dictionary)
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

	/* (non-Javadoc)
	 * @see logicImpl.IProvinciaLogic#convert(java.util.List)
	 */
	@Override
	public List<Provincia> convert(List<Dictionary> list) {
		List<Provincia> arrP = new ArrayList<Provincia>();
		for(Dictionary data : list) {
			arrP.add(convert(data));
		}
		return arrP;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IProvinciaLogic#delete(entity.Provincia)
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

	/* (non-Javadoc)
	 * @see logicImpl.IProvinciaLogic#exists(java.lang.Integer)
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

	/* (non-Javadoc)
	 * @see logicImpl.IProvinciaLogic#getAll()
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

	/* (non-Javadoc)
	 * @see logicImpl.IProvinciaLogic#getById(java.lang.Integer)
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

	/* (non-Javadoc)
	 * @see logicImpl.IProvinciaLogic#insert(entity.Provincia)
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
	
	/* (non-Javadoc)
	 * @see logicImpl.IProvinciaLogic#modify(entity.Provincia)
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

	/* (non-Javadoc)
	 * @see logicImpl.IProvinciaLogic#validate(entity.Provincia, boolean)
	 */
	@Override
	public Response<Provincia> validate(Provincia arg0, boolean validateConstraints) {
		Response<Provincia> res = new Response<Provincia>();
		try {
			res.status = validateConstraints 
					? ProvinciaDaoImpl._model.validate(arg0.toDictionary()) 
					: ProvinciaDaoImpl._schema.validate(arg0.toDictionary());
		} catch (SchemaValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, e.getMessage());
		}
		return res;
	}

}
