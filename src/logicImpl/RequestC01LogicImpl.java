package logicImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataImpl.ClienteDaoImpl;
import dataImpl.RequestC01DaoImpl;
import entity.Cliente;
import entity.SolicitudCambioClave;
import logic.IRequestC01Logic;
import max.Dictionary;
import max.IRecordLogic;
import max.Response;
import max.TransactionResponse;
import oops.SchemaValidationException;

public class RequestC01LogicImpl implements IRecordLogic<SolicitudCambioClave, Integer>, IRequestC01Logic {

	private final RequestC01DaoImpl dao = new RequestC01DaoImpl();
	
	/* (non-Javadoc)
	 * @see logicImpl.IRequestC01Logic#validate(entity.RequestC01, boolean)
	 */
	@Override
	public Response<SolicitudCambioClave> validate(SolicitudCambioClave arg0, boolean validateConstraints) {
		Response<SolicitudCambioClave> res = new Response<>();
		try {
			res.status = validateConstraints 
					? RequestC01DaoImpl._model.validate(arg0.toDictionary()) 
					: RequestC01DaoImpl._schema.validate(arg0.toDictionary());
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
	public Response<SolicitudCambioClave> insert(SolicitudCambioClave arg0) {
		Response<SolicitudCambioClave> result = new Response<SolicitudCambioClave>();
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


	@Override
	public Response<SolicitudCambioClave> modify(SolicitudCambioClave arg0) {
		Response<SolicitudCambioClave> result = new Response<SolicitudCambioClave>();
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
	public Response<SolicitudCambioClave> delete(SolicitudCambioClave data) {
		TransactionResponse<?> res;
		Response<SolicitudCambioClave> result = new Response<SolicitudCambioClave>();
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
	public Response<SolicitudCambioClave> getAll() {
		Response<SolicitudCambioClave> res = new Response<SolicitudCambioClave>();
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
	public Response<SolicitudCambioClave> getById(Integer id) {
		Response<SolicitudCambioClave> res = new Response<SolicitudCambioClave>();
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
	public Response<SolicitudCambioClave> exists(Integer id) {
		Response<SolicitudCambioClave> res = new Response<SolicitudCambioClave>();
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
	public Response<SolicitudCambioClave> convert(TransactionResponse<SolicitudCambioClave> data) {
		Response<SolicitudCambioClave> x = new Response<SolicitudCambioClave>();
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
	public Response<SolicitudCambioClave> convertWildcard(TransactionResponse<?> data) {
		Response<SolicitudCambioClave> x = new Response<SolicitudCambioClave>();
		x.status = data.status;
		x.errorMessage = data.dbError == null ? null : data.dbError.getMessage();
		x.exception = data.error;
		return x;
	}
	
	/* (non-Javadoc)
	 * @see logicImpl.IRequestC01Logic#convert(max.data.Dictionary)
	 */
	@Override
	public SolicitudCambioClave convert(Dictionary row) {
		SolicitudCambioClave req = new SolicitudCambioClave();
		if(row.containsKey(RequestC01DaoImpl.Fields.id.name)) {
			req.setId(row.$(RequestC01DaoImpl.Fields.id.name));
		}
		if(row.containsKey(RequestC01DaoImpl.Fields.client.name)) {
			Cliente cl = new Cliente();
			cl.setDNI(row.$(RequestC01DaoImpl.Fields.client.name));
			req.setIssuer(cl);
		}
		if(row.containsKey(RequestC01DaoImpl.Fields.status.name)) {
			req.setStatus(row.$(RequestC01DaoImpl.Fields.status.name));
		}
		if(row.containsKey(RequestC01DaoImpl.Fields.issuedOn.name)) {
			req.setIssuedOn(row.$(RequestC01DaoImpl.Fields.issuedOn.name));
		}
		if(row.containsKey(RequestC01DaoImpl.Fields.closedOn.name)) {
			req.setClosedOn(row.$(RequestC01DaoImpl.Fields.closedOn.name));
		}
		if(row.containsKey(RequestC01DaoImpl.Fields.message.name)) {
			req.setMessage(row.$(RequestC01DaoImpl.Fields.message.name));
		}
		if(row.containsKey(RequestC01DaoImpl.Fields.newPassword.name)) {
			Cliente cl = new Cliente();
			cl.setContrasena(row.$(RequestC01DaoImpl.Fields.newPassword.name));
			req.setData(cl);
		}
		return req;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IRequestC01Logic#convert(java.util.List)
	 */
	@Override
	public List<SolicitudCambioClave> convert(List<Dictionary> rows) {
		List<SolicitudCambioClave> list = new ArrayList<SolicitudCambioClave>();
		for(Dictionary row : rows) {
			list.add(convert(row));
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IRequestC01Logic#issue(entity.Cliente)
	 */
	@Override
	public Response<SolicitudCambioClave> issue(Cliente client) {
		Response<SolicitudCambioClave> res = new Response<SolicitudCambioClave>();
		SolicitudCambioClave req = new SolicitudCambioClave();
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
	public Response<SolicitudCambioClave> close(Integer id) {
		Response<SolicitudCambioClave> res = new Response<SolicitudCambioClave>();
		// Verificar que exista el ID y que esté abierto.
		Response<SolicitudCambioClave> buscarId = getById(id);
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
		SolicitudCambioClave req = buscarId.listReturned.get(0);
		String dni = req.getIssuer().getDNI();
		// Generar clave nueva
		ClienteLogicImpl clogic = new ClienteLogicImpl();
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
		cliente.setContrasena(newlyGeneratedPassword);
		Response<Cliente> cambiarClaveCliente = clogic.updatePassword(cliente, Dictionary.fromArray(ClienteDaoImpl.Fields.contrasena.name, cliente.getContrasena()));
		if(cambiarClaveCliente == null || !cambiarClaveCliente.status) {
			res.die(false, 500, "Hubo un error actualizando la contraseña del cliente. ");
			return res;
		}		
		// Enviar clave al cliente / Guardar en solicitud hasta que esté lista la funcionalidad de correos.
		Cliente data = new Cliente();
		data.setContrasena(newlyGeneratedPassword);
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
