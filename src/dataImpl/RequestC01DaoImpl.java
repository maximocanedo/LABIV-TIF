package dataImpl;

import max.Connector;
import max.Dictionary;
import max.IRecord;
import max.TransactionResponse;
import max.oops.SchemaValidationException;
import max.schema.MySQLSchemaModel;
import max.schema.Schema;
import max.schema.SchemaProperty;

import java.sql.SQLException;
import java.sql.Types;

import data.IRequestC01Dao;
import entity.*;
import logicImpl.RequestC01LogicImpl;

public class RequestC01DaoImpl implements IRecord<RequestC01, Integer>, IRequestC01Dao {
	
	public static class Fields {
		public static SchemaProperty id = new SchemaProperty("id_rc01") {{
			primary = true;
			type = Types.INTEGER;
			autoIncrement = true;
		}};
		public static SchemaProperty client = new SchemaProperty("client_rc01") {{
			required = true;
			type = Types.VARCHAR;
			matches = "^[0-9]+$";
			maxlength = 12;
			trim = true;
			ref = ClienteDaoImpl._model.ref(ClienteDaoImpl.Fields.dni.name);
		}};
		public static SchemaProperty status = new SchemaProperty("status_rc01") {{
			required = true;
			type = Types.BIT;
			defaultValue = false;
		}};
		/** TODO: Manejar hora y minutos **/
		public static SchemaProperty issuedOn = new SchemaProperty("issuedOn_rc01") {{
			required = true;
			type = Types.TIMESTAMP;
		}};
		public static SchemaProperty closedOn = new SchemaProperty("closedOn_rc01") {{
			type = Types.TIMESTAMP;
		}};
		public static SchemaProperty message = new SchemaProperty("message_rc01") {{
			type = Types.VARCHAR;
			maxlength = 256;
		}};
		public static SchemaProperty newPassword = new SchemaProperty("nuevaClave_rc01") {{
			type = Types.VARCHAR;
			maxlength = 72;
		}};
		
	}
	public Connector db = new Connector();
	public static Schema _schema = new Schema(
		Fields.id,
		Fields.client,
		Fields.status,
		Fields.issuedOn,
		Fields.closedOn,
		Fields.message,
		Fields.newPassword
	);
	public static Schema _issuer = new Schema(
		Fields.client,
		Fields.issuedOn,
		Fields.closedOn,
		Fields.status
	);
	public static Schema _response = new Schema(
		Fields.id,
		Fields.closedOn,
		Fields.status,
		Fields.message,
		Fields.newPassword
	);
	public static MySQLSchemaModel _model = new MySQLSchemaModel("C01Requests", "tif", _schema) {{
		compile();
	}};
	public static MySQLSchemaModel _issuer_model = new MySQLSchemaModel("C01Requests", "tif", _issuer);
	public static MySQLSchemaModel _response_model = new MySQLSchemaModel("C01Requests", "tif", _response);
	/* (non-Javadoc)
	 * @see dataImpl.IRequestC01Dao#printTDB()
	 */
	@Override
	public String printTDB() {
		return _model.getDatabaseName() + "." + _model.getTableName();
	}
	public RequestC01DaoImpl() {}

	/* (non-Javadoc)
	 * @see dataImpl.IRequestC01Dao#insert(entity.RequestC01)
	 */
	@Override
	public TransactionResponse<?> insert(RequestC01 data) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		Dictionary dict = data.toDictionary();
		try {
			res = _model.create(dict);
		} catch (SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/* (non-Javadoc)
	 * @see dataImpl.IRequestC01Dao#issue(entity.RequestC01)
	 */
	@Override
	public TransactionResponse<?> issue(RequestC01 data) throws SQLException {
		data.setStatus(false);
		TransactionResponse<?> res = TransactionResponse.create();
		data.setClosedOn(new java.sql.Timestamp(System.currentTimeMillis()));
		Dictionary dict = data.toDictionary();
		try {
			res = _issuer_model.create(dict);
		} catch (SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}
	/* (non-Javadoc)
	 * @see dataImpl.IRequestC01Dao#close(entity.RequestC01)
	 */
	@Override
	public TransactionResponse<?> close(RequestC01 data) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		Dictionary dict = data.toDictionary();
		Dictionary id = data.toIdentifiableDictionary();
		System.out.println("IDENTIFY: " + id.toString());
		try {
			res = _model.modify(dict, id);
		} catch (SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see dataImpl.IRequestC01Dao#delete(entity.RequestC01)
	 */
	@Override
	public TransactionResponse<?> delete(RequestC01 data) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		Dictionary dict = data.toIdentifiableDictionary();
		try {
			res = _model.delete(dict);
		} catch (SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see dataImpl.IRequestC01Dao#modify(entity.RequestC01)
	 */
	@Override
	public TransactionResponse<?> modify(RequestC01 data) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		Dictionary dict = data.toDictionary();
		Dictionary id = data.toIdentifiableDictionary();
		try {
			res = _model.modify(dict, id);
		} catch (SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see dataImpl.IRequestC01Dao#getAll()
	 */
	@Override
	public TransactionResponse<RequestC01> getAll() throws SQLException {
		return select("SELECT * FROM " + printTDB());
	}

	/* (non-Javadoc)
	 * @see dataImpl.IRequestC01Dao#getById(java.lang.Integer)
	 */
	@Override
	public TransactionResponse<RequestC01> getById(Integer id) throws SQLException {
		return select(
			"SELECT * FROM " + printTDB() + " WHERE " + Fields.id.name + " = @id", 
			Dictionary.fromArray("id", id)
		);
	}

	/* (non-Javadoc)
	 * @see dataImpl.IRequestC01Dao#exists(java.lang.Integer)
	 */
	@Override
	public boolean exists(Integer arg0) throws SQLException {
		RequestC01 a = new RequestC01();
		a.setId(arg0);
		return _model.exists(a.toIdentifiableDictionary());
	}
	/* (non-Javadoc)
	 * @see dataImpl.IRequestC01Dao#exists(max.data.Dictionary)
	 */
	@Override
	public boolean exists(Dictionary d) throws SQLException {
		return _model.exists(d);
	}
	
	private TransactionResponse<RequestC01> select(String arg0) throws SQLException {
		RequestC01LogicImpl logic = new RequestC01LogicImpl();
		TransactionResponse<RequestC01> res = new TransactionResponse<RequestC01>();
		TransactionResponse<Dictionary> rd = db.fetch(arg0);
		if(rd.nonEmptyResult()) {
			res.rowsReturned = logic.convert(rd.rowsReturned);
		}
		return res;
	}
	private TransactionResponse<RequestC01> select(String arg0, Dictionary arg1) throws SQLException {
		RequestC01LogicImpl logic = new RequestC01LogicImpl();
		TransactionResponse<RequestC01> res = new TransactionResponse<RequestC01>();
		TransactionResponse<Dictionary> rd = db.fetch(arg0, arg1);
		if(rd.nonEmptyResult()) {
			res.rowsReturned = logic.convert(rd.rowsReturned);
		}
		return res;
	}
	

}
