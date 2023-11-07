package logicImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataImpl.PaisDaoImpl;
import entity.Pais;
import logic.IPaisLogic;
import max.Dictionary;
import max.IRecordLogic;
import max.Response;
import max.TransactionResponse;
import oops.SchemaValidationException;

public class PaisLogicImpl implements IRecordLogic<Pais, String>, IPaisLogic {
	
	private static PaisDaoImpl data = new PaisDaoImpl();

	public PaisLogicImpl() { }

	/* (non-Javadoc)
	 * @see logicImpl.IPaisLogic#convert(max.data.Dictionary)
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

	/* (non-Javadoc)
	 * @see logicImpl.IPaisLogic#convert(java.util.List)
	 */
	@Override
	public List<Pais> convert(List<Dictionary> list) {
		List<Pais> arrP = new ArrayList<Pais>();
		for(Dictionary data : list) {
			arrP.add(convert(data));
		}
		return arrP;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IPaisLogic#delete(entity.Pais)
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

	/* (non-Javadoc)
	 * @see logicImpl.IPaisLogic#exists(java.lang.String)
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

	/* (non-Javadoc)
	 * @see logicImpl.IPaisLogic#getAll()
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

	/* (non-Javadoc)
	 * @see logicImpl.IPaisLogic#getById(java.lang.String)
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

	/* (non-Javadoc)
	 * @see logicImpl.IPaisLogic#insert(entity.Pais)
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

	/* (non-Javadoc)
	 * @see logicImpl.IPaisLogic#modify(entity.Pais)
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

	/* (non-Javadoc)
	 * @see logicImpl.IPaisLogic#validate(entity.Pais, boolean)
	 */
	@Override
	public Response<Pais> validate(Pais arg0, boolean validateConstraints) {
		Response<Pais> res = new Response<Pais>();
		try {
			res.status = validateConstraints 
					? PaisDaoImpl._model.validate(arg0.toDictionary()) 
					: PaisDaoImpl._schema.validate(arg0.toDictionary());
		} catch (SchemaValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, e.getMessage());
		}
		return res;
	}
	
	

}
