package logicImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataImpl.TipoCuentaDaoImpl;
import entity.TipoCuenta;
import logic.ITipoCuentaLogic;
import max.data.Dictionary;
import max.data.IRecordLogic;
import max.data.Response;
import max.data.TransactionResponse;
import max.oops.SchemaValidationException;

public class TipoCuentaLogicImpl implements IRecordLogic<TipoCuenta,String>, ITipoCuentaLogic{

	public TipoCuentaLogicImpl() {}
	TipoCuentaDaoImpl tpDao= new TipoCuentaDaoImpl();
	/* (non-Javadoc)
	 * @see logicImpl.ITipoCuentaLogic#validate(entity.TipoCuenta, boolean)
	 */
	@Override
	public Response<TipoCuenta> validate(TipoCuenta data, boolean validatePKDuplicates) {
		Response<TipoCuenta> res = new Response<TipoCuenta>();
		try {
			res.status = validatePKDuplicates 
					? TipoCuentaDaoImpl._model.validate(data.toDictionary()) 
					: TipoCuentaDaoImpl.tablaTP.validate(data.toDictionary());
		} catch (SchemaValidationException e) {
			e.printStackTrace();
			res.die(false, e.getMessage());
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see logicImpl.ITipoCuentaLogic#insert(entity.TipoCuenta)
	 */
	@Override
	public Response<TipoCuenta> insert(TipoCuenta data) {
		Response<TipoCuenta> res = new Response<TipoCuenta>();
		TransactionResponse<?> tpr;
		try {
			tpr = tpDao.insert(data);
			if(tpr.rowsAffected > 0) {
				res.die(true, 201, "El registro se insertó con éxito. ");
			} else res.die(false, 500, "Hubo un error al intentar insertar el registro. ");
		} catch (SQLException e) {
			res.die(false, 500, " Hubo un error al intentar insertar el registro. ");
			e.printStackTrace();
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see logicImpl.ITipoCuentaLogic#modify(entity.TipoCuenta)
	 */
	@Override
	public Response<TipoCuenta> modify(TipoCuenta data) {
		Response<TipoCuenta> res = new Response<TipoCuenta>();
		TransactionResponse<?> tpr;
		try {
			tpr = tpDao.modify(data);
			if(tpr.rowsAffected > 0) {
				res.die(true, 200, "El registro se modificó con éxito. ");
			} else res.die(false, 500, "Hubo un error al intentar modificar el registro. ");
		} catch (SQLException e) {
			res.die(false, 500, " Hubo un error al intentar modificar el registro. ");
			e.printStackTrace();
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see logicImpl.ITipoCuentaLogic#delete(entity.TipoCuenta)
	 */
	@Override
	public Response<TipoCuenta> delete(TipoCuenta data) {
		Response<TipoCuenta> res = new Response<TipoCuenta>();
		TransactionResponse<?> tpr;
		try {
			tpr = tpDao.delete(data);
			if(tpr.rowsAffected > 0) {
				res.die(true, 200, "El registro se eliminó con éxito. ");
			} else res.die(false, 500, "Hubo un error al intentar eliminar el registro. ");
		} catch (SQLException e) {
			res.die(false, 500, " Hubo un error al intentar eliminar el registro. ");
			e.printStackTrace();
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see logicImpl.ITipoCuentaLogic#getAll()
	 */
	@Override
	public Response<TipoCuenta> getAll() {
		Response<TipoCuenta> res = new Response<TipoCuenta>();
		TransactionResponse<TipoCuenta> tpr = new TransactionResponse<TipoCuenta>();
		try {
			tpr = tpDao.getAll();
			if(tpr.nonEmptyResult()) {
				res.fill(tpr.rowsReturned);
			} else res.die(false, "Hubo un error al intentar realizar la consulta. ");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, " Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see logicImpl.ITipoCuentaLogic#getById(java.lang.String)
	 */
	@Override
	public Response<TipoCuenta> getById(String id) {
		Response<TipoCuenta> res = new Response<TipoCuenta>();
		TransactionResponse<TipoCuenta> tpr = new TransactionResponse<TipoCuenta>();
		try {
			tpr = tpDao.getById(id);
			if(tpr.nonEmptyResult()) {
				res.fill(tpr.rowsReturned);
			} else res.die(false, "Hubo un error al intentar realizar la consulta. ");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, " Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see logicImpl.ITipoCuentaLogic#exists(java.lang.String)
	 */
	@Override
	public Response<TipoCuenta> exists(String id) {
		Response<TipoCuenta> res = new Response<TipoCuenta>();
		boolean existe = false;
		try {
			existe = tpDao.exists(id);
			if(existe) {
				res.die(true, "El registro existe. ");
			} else res.die(false, "No existe un registro con esas características. ");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, " Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see logicImpl.ITipoCuentaLogic#convert(max.data.Dictionary)
	 */
	@Override
	public TipoCuenta convert(Dictionary row) {
		TipoCuenta tipo = new TipoCuenta();
		if(row.$("Cod_TPCT") != null) {
			tipo.setCod_TPCT(row.$("Cod_TPCT"));
		} if(row.$("Descripcion_TPCT") != null) {
			tipo.setDescripcion_TPCT(row.$("Descripcion_TPCT"));
		}
		return tipo;
	}

	/* (non-Javadoc)
	 * @see logicImpl.ITipoCuentaLogic#convert(java.util.List)
	 */
	@Override
	public List<TipoCuenta> convert(List<Dictionary> rows) {
		List<TipoCuenta> list = new ArrayList<TipoCuenta>();
		for(Dictionary data : rows) {
			list.add(convert(data));
		}
		return list;
	}


}
