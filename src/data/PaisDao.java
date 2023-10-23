package data;

import java.sql.SQLException;
import java.sql.Types;

import entity.Pais;
import logic.PaisLogic;
import max.data.Dictionary;
import max.data.IRecord;
import max.data.TransactionResponse;
import max.net.Connector;
import max.oops.SchemaValidationException;
import max.schema.IModel;
import max.schema.MySQLSchemaModel;
import max.schema.Schema;
import max.schema.SchemaProperty;

public class PaisDao implements IRecord<Pais, String> {
	public static final Schema _schema = new Schema(
			new SchemaProperty("code") {{
				required = true;
				primary = true;
				type = Types.VARCHAR;
				maxlength = 2;
			}},
			new SchemaProperty("name") {{
				required = true;
				type = Types.VARCHAR;
				maxlength = 255;
			}},
			new SchemaProperty("full_name") {{
				required = true;
				type = Types.VARCHAR;
				maxlength = 255;
			}},
			new SchemaProperty("iso3") {{
				required = true;
				type = Types.VARCHAR;
				maxlength = 3;
			}},
			new SchemaProperty("number") {{
				required = true;
				type = Types.VARCHAR;
				maxlength = 3;
			}},
		//	new SchemaProperty("demonym") {{
			//	required = true;
		//		type = Types.VARCHAR;
			//	maxlength = 75;
		//	}},
			new SchemaProperty("continent_code") {{
				required = true;
				type = Types.VARCHAR;
				maxlength = 2;
				ref = ContinenteDao._model.ref("code");
			}}
			
		);
	public static final IModel _model = new MySQLSchemaModel("countries", "tif", _schema);
	private Connector db = new Connector(_model.getDatabaseName());
	private PaisLogic logic = new PaisLogic();
	
	public PaisDao() {
		// TODO Auto-generated constructor stub
	}

	public String printTDB() {
		return _model.getDatabaseName() + "." + _model.getTableName();
	}
	
	/**
	 * No está previsto el uso de este método.
	 */
	@Override
	public TransactionResponse<?> delete(Pais obj) throws SQLException {
		TransactionResponse<?> res = null;
		try {
			res = _model.delete(obj.toIdentifiableDictionary());
		} catch (SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public boolean exists(String arg0) throws SQLException {
		return _model.exists(Dictionary.fromArray("code", arg0));
	}

	@Override
	public TransactionResponse<Pais> getAll() throws SQLException {
		TransactionResponse<Dictionary> res = db.fetch(
			"SELECT * FROM " + printTDB()
		);
		TransactionResponse<Pais> fin = new TransactionResponse<Pais>();
		if(res.nonEmptyResult()) {
			fin.rowsReturned = logic.convert(res.rowsReturned);
		}
		return fin;
	}

	@Override
	public TransactionResponse<Pais> getById(String id) throws SQLException {
		TransactionResponse<Dictionary> res = db.fetch(
			"SELECT * FROM " + printTDB() + " WHERE code = @id",
			Dictionary.fromArray("id", id)
		);
		TransactionResponse<Pais> fin = new TransactionResponse<Pais>();
		if(res.nonEmptyResult()) {
			fin.rowsReturned = logic.convert(res.rowsReturned);
		}
		return fin;
	}

	@Override
	public TransactionResponse<?> insert(Pais p) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = _model.create(p.toDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public TransactionResponse<?> modify(Pais p) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = _model.modify(p.toDictionary(), p.toIdentifiableDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public TransactionResponse<Pais> select(String arg0) throws SQLException {
		TransactionResponse<Dictionary> r = db.fetch(arg0);
		TransactionResponse<Pais> res = new TransactionResponse<Pais>();
		if(r.nonEmptyResult()) {
			res.rowsReturned = logic.convert(r.rowsReturned);
		}
		return res;
	}

	@Override
	public TransactionResponse<Pais> select(String arg0, Dictionary arg1) throws SQLException {
		TransactionResponse<Dictionary> r = db.fetch(arg0, arg1);
		TransactionResponse<Pais> res = new TransactionResponse<Pais>();
		if(r.nonEmptyResult()) {
			res.rowsReturned = logic.convert(r.rowsReturned);
		}
		return res;
	}

	@Override
	public TransactionResponse<Pais> select(String arg0, Object[] arg1) throws SQLException {
		TransactionResponse<Dictionary> r = db.fetch(arg0, arg1);
		TransactionResponse<Pais> res = new TransactionResponse<Pais>();
		if(r.nonEmptyResult()) {
			res.rowsReturned = logic.convert(r.rowsReturned);
		}
		return res;
	}
	


}
