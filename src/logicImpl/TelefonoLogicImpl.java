package logicImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataImpl.TelefonoDaoImpl;
import logic.ITelefonoLogic;
import entity.Telefono;
import max.Dictionary;
import max.IRecordLogic;
import max.Response;
import max.TransactionResponse;
import oops.SchemaValidationException;

public class TelefonoLogicImpl implements IRecordLogic<Telefono,String>, ITelefonoLogic{
	public TelefonoLogicImpl() {}
	TelefonoDaoImpl tpDao= new TelefonoDaoImpl();
	@Override
	public Response<Telefono> validate(Telefono data, boolean validatePKDuplicates) {
		Response<Telefono> res = new Response<Telefono>();
		try {
			res.status = validatePKDuplicates 
					? TelefonoDaoImpl._model.validate(data.toDictionary()) 
					: TelefonoDaoImpl.tablaTP.validate(data.toDictionary());
		} catch (SchemaValidationException e) {
			e.printStackTrace();
			res.die(false, e.getMessage());
		}
		return res;
	}

	@Override
	public Response<Telefono> insert(Telefono data) {
		Response<Telefono> res = new Response<Telefono>();
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

	@Override
	public Response<Telefono> modify(Telefono data) {
		Response<Telefono> res = new Response<Telefono>();
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

	@Override
	public Response<Telefono> delete(Telefono data) {
		Response<Telefono> res = new Response<Telefono>();
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

	@Override
	public Response<Telefono> getAll() {
		Response<Telefono> res = new Response<Telefono>();
		TransactionResponse<Telefono> tpr = new TransactionResponse<Telefono>();
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

	@Override
	public Response<Telefono> getById(String DNI) {
		Response<Telefono> res = new Response<Telefono>();
		TransactionResponse<Telefono> tpr = new TransactionResponse<Telefono>();
		try {
			tpr = tpDao.getById(DNI);
			if(tpr.nonEmptyResult()) {
				res.fill(tpr.rowsReturned);
			} else res.die(false, "Hubo un error al intentar realizar la consulta. ");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, " Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}

	@Override
	public Response<Telefono> exists(String DNI) {
		Response<Telefono> res = new Response<Telefono>();
		boolean existe = false;
		try {
			existe = tpDao.exists(DNI);
			if(existe) {
				res.die(true, "El registro existe. ");
			} else res.die(false, "No existe un registro con esas características. ");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, " Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}

	@Override
	public Telefono convert(Dictionary row) {
		Telefono data = new Telefono();
		if(row.$("Tel") != null) {
			data.setTelefono(row.$("Tel"));
		} if(row.$("Dni") != null) {
			data.setDNI_Usuario(row.$("Dni"));
		}if(row.$("Activo") != null) {
			data.setActivo(row.$("Activo"));
		}
		return data;
	}

	@Override
	public List<Telefono> convert(List<Dictionary> rows) {
		List<Telefono> list = new ArrayList<Telefono>();
		for(Dictionary data : rows) {
			list.add(convert(data));
		}
		return list;
	}

}
