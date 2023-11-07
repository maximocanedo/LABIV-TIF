package logicImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataImpl.ClienteDao;
import dataImpl.RequestC01Dao;
import entity.Cliente;
import entity.RequestC01;
import logic.IRequestC01Logic;
import max.data.Dictionary;
import max.data.IRecordLogic;
import max.data.Response;
import max.data.TransactionResponse;
import max.oops.SchemaValidationException;

public class RequestC01Logic implements IRecordLogic<RequestC01, Integer>, IRequestC01Logic {

	private RequestC01Dao dao = new RequestC01Dao();
	
	/* (non-Javadoc)
	 * @see logicImpl.IRequestC01Logic#validate(entity.RequestC01, boolean)
	 */
	@Override
	public Response<RequestC01> validate(RequestC01 arg0, boolean validateConstraints) {
		Response<RequestC01> res = new Response<RequestC01>();
		try {
			res.status = validateConstraints 
					? RequestC01Dao._model.validate(arg0.toDictionary()) 
					: RequestC01Dao._schema.validate(arg0.toDictionary());
		} catch (SchemaValidationException e) {
			e.printStackTrace();
			res.die(false, e.getMessage());
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IRequestC01Logic#insert(entity.RequestC01)
	 */
	@Override
	public Response<RequestC01> insert(RequestC01 arg0) {
		Response<RequestC01> result = new Response<RequestC01>();
		try {
			result = convertWildcard(dao.insert(arg0));
			result.objectReturned = arg0;
			result.message = result.status ? "El registro se insertó correctamente. " : "No se insertó ningún registro. ";
			result.http = result.status ? 201 : 500;
		} catch (SQLException e) {
			result.die(false, 500, "Hubo un error al intentar realizar la transacción. ");
			e.printStackTrace();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IRequestC01Logic#modify(entity.RequestC01)
	 */
	@Override
	public Response<RequestC01> modify(RequestC01 arg0) {
		Response<RequestC01> result = new Response<RequestC01>();
		try {
			result = convertWildcard(dao.modify(arg0));
			result.message = result.status ? "El registro se modificó correctamente. " : "No se modificó ningún registro. ";
			result.http = result.status ? 200 : 500;
		} catch (SQLException e) {
			result.die(false, 500, "Hubo un error al intentar realizar la transacción. ");
			e.printStackTrace();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IRequestC01Logic#delete(entity.RequestC01)
	 */
	@Override
	public Response<RequestC01> delete(RequestC01 data) {
		TransactionResponse<?> res;
		Response<RequestC01> result = new Response<RequestC01>();
		try {
			res = dao.delete(data);
			result = convertWildcard(res);
			result.message = result.status ? "La solicitud fue eliminada correctamente. " : "No se eliminó la solicitud. ";
			result.http = result.status ? 200 : 500;
		} catch (SQLException e) {
			result.die(false, 500, "Hubo un error al intentar realizar la transacción. ");
			e.printStackTrace();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IRequestC01Logic#getAll()
	 */
	@Override
	public Response<RequestC01> getAll() {
		Response<RequestC01> res = new Response<RequestC01>();
		try {
			res = convert(dao.getAll());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, "");
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IRequestC01Logic#getById(java.lang.Integer)
	 */
	@Override
	public Response<RequestC01> getById(Integer id) {
		Response<RequestC01> res = new Response<RequestC01>();
		try {
			res = convert(dao.getById(id));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, 501, "");
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IRequestC01Logic#exists(java.lang.Integer)
	 */
	@Override
	public Response<RequestC01> exists(Integer id) {
		Response<RequestC01> res = new Response<RequestC01>();
		boolean o = false;
		try {
			o = dao.exists(id);
			res.die(o, o ? 200 : 404, "");
		} catch (SQLException e) {
			e.printStackTrace();
			res.die(false, 500, "");
		}
		return res;
	}

	
	/* (non-Javadoc)
	 * @see logicImpl.IRequestC01Logic#convert(max.data.TransactionResponse)
	 */
	@Override
	public Response<RequestC01> convert(TransactionResponse<RequestC01> data) {
		Response<RequestC01> x = new Response<RequestC01>();
		x.status = data.status;
		x.errorMessage = data.dbError == null ? null : data.dbError.getMessage();
		x.listReturned = data.rowsReturned;
		x.exception = data.error;
		return x;
	}
	
	/* (non-Javadoc)
	 * @see logicImpl.IRequestC01Logic#convertWildcard(max.data.TransactionResponse)
	 */
	@Override
	public Response<RequestC01> convertWildcard(TransactionResponse<?> data) {
		Response<RequestC01> x = new Response<RequestC01>();
		x.status = data.status;
		x.errorMessage = data.dbError == null ? null : data.dbError.getMessage();
		x.exception = data.error;
		return x;
	}
	
	/* (non-Javadoc)
	 * @see logicImpl.IRequestC01Logic#convert(max.data.Dictionary)
	 */
	@Override
	public RequestC01 convert(Dictionary row) {
		RequestC01 req = new RequestC01();
		if(row.containsKey(RequestC01Dao.Fields.id.name)) {
			req.setId(row.$(RequestC01Dao.Fields.id.name));
		}
		if(row.containsKey(RequestC01Dao.Fields.client.name)) {
			Cliente cl = new Cliente();
			cl.setDNI(row.$(RequestC01Dao.Fields.client.name));
			req.setIssuer(cl);
		}
		if(row.containsKey(RequestC01Dao.Fields.status.name)) {
			req.setStatus(row.$(RequestC01Dao.Fields.status.name));
		}
		if(row.containsKey(RequestC01Dao.Fields.issuedOn.name)) {
			req.setIssuedOn(row.$(RequestC01Dao.Fields.issuedOn.name));
		}
		if(row.containsKey(RequestC01Dao.Fields.closedOn.name)) {
			req.setClosedOn(row.$(RequestC01Dao.Fields.closedOn.name));
		}
		if(row.containsKey(RequestC01Dao.Fields.message.name)) {
			req.setMessage(row.$(RequestC01Dao.Fields.message.name));
		}
		if(row.containsKey(RequestC01Dao.Fields.newPassword.name)) {
			Cliente cl = new Cliente();
			cl.setContraseña(row.$(RequestC01Dao.Fields.newPassword.name));
			req.setData(cl);
		}
		return req;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IRequestC01Logic#convert(java.util.List)
	 */
	@Override
	public List<RequestC01> convert(List<Dictionary> rows) {
		List<RequestC01> list = new ArrayList<RequestC01>();
		for(Dictionary row : rows) {
			list.add(convert(row));
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IRequestC01Logic#issue(entity.Cliente)
	 */
	@Override
	public Response<RequestC01> issue(Cliente client) {
		Response<RequestC01> res = new Response<RequestC01>();
		RequestC01 req = new RequestC01();
		req.setIssuer(client);
		req.setIssuedOn(new java.sql.Timestamp(System.currentTimeMillis()));
		try {
			res = convertWildcard(dao.issue(req));
			res.http = res.status ? 201 : 500;
			res.message = res.status
					? "Se envió una solicitud de cambio de contraseña. "
					: "No se pudo enviar la solicitud de cambio de contraseña. ";
		} catch(SQLException e) {
			res.die(false, 500, "Hubo un error al intentar enviar la solicitud. ");
			e.printStackTrace();
		}
		return res;
	}
	
	/* (non-Javadoc)
	 * @see logicImpl.IRequestC01Logic#close(java.lang.Integer)
	 */
	@Override
	public Response<RequestC01> close(Integer id) {
		Response<RequestC01> res = new Response<RequestC01>();
		// Verificar que exista el ID y que esté abierto.
		Response<RequestC01> buscarId = getById(id);
		if(buscarId != null && !buscarId.listReturned.isEmpty()) {
			if(buscarId.listReturned.get(0).isStatus()) {
				res.die(false, 403, "Esta solicitud ya fue aprobada. ");
				return res;
			} 
			if(buscarId.listReturned.get(0).getIssuer() == null) {
				res.die(false, 500, "Esta solicitud no tiene cliente asignado. ");
			}
		} else {
			res.die(false, 500, "Error desconocido. ");
			return res;
		}
		RequestC01 req = buscarId.listReturned.get(0);
		String dni = req.getIssuer().getDNI();
		// Generar clave nueva
		ClienteLogic clogic = new ClienteLogic();
		Response<Cliente> buscarCliente = clogic.getByDNI(dni);
		if(buscarCliente == null) {
			res.die(false, 500, "Error desconocido");
			return res;
		} else if(buscarCliente.listReturned.isEmpty()) {
			res.die(false, buscarCliente.http, "Error encontrando al cliente: " +  buscarCliente.message);
			return res;
		}
		Cliente cliente = buscarCliente.listReturned.get(0);
		String newlyGeneratedPassword = clogic.generatePassword(cliente);
		// Modificar cliente con clave nueva
		cliente.setContraseña(newlyGeneratedPassword);
		Response<Cliente> cambiarClaveCliente = clogic.updatePassword(cliente, Dictionary.fromArray(ClienteDao.Fields.contraseña.name, cliente.getContraseña()));
		if(cambiarClaveCliente == null || !cambiarClaveCliente.status) {
			res.die(false, 500, "Hubo un error actualizando la contraseña del cliente. ");
			return res;
		}		
		// Enviar clave al cliente / Guardar en solicitud hasta que esté lista la funcionalidad de correos.
		Cliente data = new Cliente();
		data.setContraseña(newlyGeneratedPassword);
		req.setData(data);
		req.setStatus(true);
		req.setClosedOn(new java.sql.Timestamp(System.currentTimeMillis()));
		res = modify(req);
		res.message = res.status
			? "La solicitud se cerró correctamente. "
			: "Hubo un problema al cerrar la solicitud. ";
		// Listo
		return res;
	}
	
	
}
