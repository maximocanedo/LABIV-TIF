package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import data.TelefonoXClienteDao;
import entity.TelefonosXCliente;
import max.*;
import oops.*;

public class TelefonoXClienteLogic implements IRecordLogic<TelefonosXCliente, String>{

	public TelefonoXClienteLogic() {}
	TelefonoXClienteDao tpDao= new TelefonoXClienteDao();
	@Override
	public Response<TelefonosXCliente> validate(TelefonosXCliente data, boolean validatePKDuplicates) {
		Response<TelefonosXCliente> res = new Response<TelefonosXCliente>();
		try {
			res.status = validatePKDuplicates 
					? TelefonoXClienteDao._model.validate(data.toDictionary()) 
					: TelefonoXClienteDao.tablaTP.validate(data.toDictionary());
		} catch (SchemaValidationException e) {
			e.printStackTrace();
			res.die(false, e.getMessage());
		}
		return res;
	}

	@Override
	public Response<TelefonosXCliente> insert(TelefonosXCliente data) {
		Response<TelefonosXCliente> res = new Response<TelefonosXCliente>();
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
	public Response<TelefonosXCliente> modify(TelefonosXCliente data) {
		Response<TelefonosXCliente> res = new Response<TelefonosXCliente>();
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
	public Response<TelefonosXCliente> delete(TelefonosXCliente data) {
		Response<TelefonosXCliente> res = new Response<TelefonosXCliente>();
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
	public Response<TelefonosXCliente> getAll() {
		Response<TelefonosXCliente> res = new Response<TelefonosXCliente>();
		TransactionResponse<TelefonosXCliente> tpr = new TransactionResponse<TelefonosXCliente>();
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
	public Response<TelefonosXCliente> getById(String DNI) {
		Response<TelefonosXCliente> res = new Response<TelefonosXCliente>();
		TransactionResponse<TelefonosXCliente> tpr = new TransactionResponse<TelefonosXCliente>();
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
	public Response<TelefonosXCliente> exists(String DNI) {
		Response<TelefonosXCliente> res = new Response<TelefonosXCliente>();
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
	public TelefonosXCliente convert(Dictionary row) {
		TelefonosXCliente Telefono = new TelefonosXCliente();
		if(row.$("Tel_TxC") != null) {
			Telefono.setTelefono(row.$("Tel_TxC"));
		} if(row.$("Dni_Cl_TxC") != null) {
			Telefono.setDNI_Usuario(row.$("Dni_Cl_TxC"));
		}if(row.$("Activo_TxC") != null) {
			Telefono.setActivo(row.$("Activo_TxC"));
		}
		return Telefono;
	}

	@Override
	public List<TelefonosXCliente> convert(List<Dictionary> rows) {
		List<TelefonosXCliente> list = new ArrayList<TelefonosXCliente>();
		for(Dictionary data : rows) {
			list.add(convert(data));
		}
		return list;
	}

	
}
