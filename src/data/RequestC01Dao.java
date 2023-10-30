package data;

import max.data.IRecord;
import max.data.TransactionResponse;
import max.schema.SchemaProperty;

import java.sql.SQLException;
import java.sql.Types;

import entity.*;

public class RequestC01Dao implements IRecord<RequestC01, Integer> {
	
	public static class Fields {
		public static SchemaProperty id = new SchemaProperty("id_rc01") {{
			required = true;
			primary = true;
			type = Types.INTEGER;
			autoIncrement = true;
		}};
		public static SchemaProperty client = new SchemaProperty("client_rc01") {{
			required = true;
			type = Types.VARCHAR;
			ref = ClienteDao._model.ref(ClienteDao.Fields.usuario.name);
		}};
		public static SchemaProperty status = new SchemaProperty("status_rc01") {{
			required = true;
			type = Types.BOOLEAN;
			defaultValue = false;
		}};
		/** TODO: Manejar hora y minutos **/
		public static SchemaProperty issuedOn = new SchemaProperty("issuedOn_rc01") {{
			required = true;
			type = Types.DATE;
		}};
		public static SchemaProperty closedOn = new SchemaProperty("closedOn_rc01") {{
			type = Types.DATE;
		}};
		public static SchemaProperty message = new SchemaProperty("message_rc01") {{
			type = Types.VARCHAR;
			max = 256;
		}};
		
	}

	public RequestC01Dao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public TransactionResponse<?> insert(RequestC01 data) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionResponse<?> delete(RequestC01 data) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionResponse<?> modify(RequestC01 data) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionResponse<RequestC01> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionResponse<RequestC01> getById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
