package dataImpl;

import java.sql.SQLException;
import java.sql.Types;

import data.IContinenteDao;
import entity.Continente;
import logicImpl.*;
import max.Dictionary;
import max.IRecord;
import max.TransactionResponse;
import max.data.*;
import max.net.*;
import max.oops.SchemaValidationException;
import max.schema.IModel;
import max.schema.MySQLSchemaModel;
import max.schema.Schema;
import max.schema.SchemaProperty;

public class ContinenteDaoImpl implements IRecord<Continente, String>, IContinenteDao {
	
	private Connector db = new Connector(_model.getDatabaseName());
	private ContinenteLogicImpl logic = new ContinenteLogicImpl();
	public static Schema _schema = new Schema(
			new SchemaProperty("code") {{
				primary = true;
				required = true;
				type = Types.VARCHAR;
				maxlength = 2;
			}},
			new SchemaProperty("name") {{
				primary = true;
				required = true;
				type = Types.VARCHAR;
				maxlength = 255;
			}}
		);
	public static final IModel _model = new MySQLSchemaModel("continents", "tif", _schema);
	
	public ContinenteDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	private String printTDB() {
		return _model.getDatabaseName() + "." + _model.getTableName();
	}
	
	/**
	 * No está previsto el uso de este método.
	 */
	@Override
	public TransactionResponse<?> delete(Continente obj) throws SQLException {
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
		return _model.exists(Dictionary.fromArray("id_Continente", arg0));
	}

	@Override
	public TransactionResponse<Continente> getAll() throws SQLException {
		TransactionResponse<Dictionary> res = db.fetch(
			"SELECT * FROM " + printTDB()
		);
		TransactionResponse<Continente> fin = new TransactionResponse<Continente>();
		if(res.nonEmptyResult()) {
			fin.rowsReturned = logic.convert(res.rowsReturned);
		}
		return fin;
	}

	@Override
	public TransactionResponse<Continente> getById(String id) throws SQLException {
		TransactionResponse<Dictionary> res = db.fetch(
			"SELECT * FROM " + printTDB() + " WHERE id_Continente = @id",
			Dictionary.fromArray("id", id)
		);
		TransactionResponse<Continente> fin = new TransactionResponse<Continente>();
		if(res.nonEmptyResult()) {
			fin.rowsReturned = logic.convert(res.rowsReturned);
		}
		return fin;
	}

	@Override
	public TransactionResponse<?> insert(Continente p) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = _model.create(p.toDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public TransactionResponse<?> modify(Continente p) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = _model.modify(p.toDictionary(), p.toIdentifiableDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

}
