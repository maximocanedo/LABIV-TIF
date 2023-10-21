package data;

import java.sql.SQLException;

import entity.Provincia;
import logic.*;
import max.data.*;
import max.net.*;
import max.oops.SchemaValidationException;

public class ProvinciaDao implements IRecord<Provincia, Integer> {
	
	private Connector db = new Connector(Provincia._model.getDatabaseName());
	private ProvinciaLogic logic = new ProvinciaLogic();
	
	public ProvinciaDao() {
		// TODO Auto-generated constructor stub
	}

	public String printTDB() {
		return Provincia._model.getDatabaseName() + "." + Provincia._model.getTableName();
	}
	
	/**
	 * No está previsto el uso de este método.
	 */
	@Override
	public TransactionResponse<?> delete(Provincia obj) throws SQLException {
		TransactionResponse<?> res = null;
		try {
			res = Provincia._model.delete(obj.toIdentifiableDictionary());
		} catch (SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public boolean exists(Integer arg0) throws SQLException {
		return Provincia._model.exists(Dictionary.fromArray("id_provincia", arg0));
	}

	@Override
	public TransactionResponse<Provincia> getAll() throws SQLException {
		TransactionResponse<Dictionary> res = db.fetch(
			"SELECT * FROM " + printTDB()
		);
		TransactionResponse<Provincia> fin = new TransactionResponse<Provincia>();
		if(res.nonEmptyResult()) {
			fin.rowsReturned = logic.convert(res.rowsReturned);
		}
		return fin;
	}

	@Override
	public TransactionResponse<Provincia> getById(Integer id) throws SQLException {
		TransactionResponse<Dictionary> res = db.fetch(
			"SELECT * FROM " + printTDB() + " WHERE id_provincia = @id",
			Dictionary.fromArray("id", id)
		);
		TransactionResponse<Provincia> fin = new TransactionResponse<Provincia>();
		if(res.nonEmptyResult()) {
			fin.rowsReturned = logic.convert(res.rowsReturned);
		}
		return fin;
	}

	@Override
	public TransactionResponse<?> insert(Provincia p) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = Provincia._model.create(p.toDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public TransactionResponse<?> modify(Provincia p) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = Provincia._model.modify(p.toDictionary(), p.toIdentifiableDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public TransactionResponse<Provincia> select(String arg0) throws SQLException {
		TransactionResponse<Dictionary> r = db.fetch(arg0);
		TransactionResponse<Provincia> res = new TransactionResponse<Provincia>();
		if(r.nonEmptyResult()) {
			res.rowsReturned = logic.convert(r.rowsReturned);
		}
		return res;
	}

	@Override
	public TransactionResponse<Provincia> select(String arg0, Dictionary arg1) throws SQLException {
		TransactionResponse<Dictionary> r = db.fetch(arg0, arg1);
		TransactionResponse<Provincia> res = new TransactionResponse<Provincia>();
		if(r.nonEmptyResult()) {
			res.rowsReturned = logic.convert(r.rowsReturned);
		}
		return res;
	}

	@Override
	public TransactionResponse<Provincia> select(String arg0, Object[] arg1) throws SQLException {
		TransactionResponse<Dictionary> r = db.fetch(arg0, arg1);
		TransactionResponse<Provincia> res = new TransactionResponse<Provincia>();
		if(r.nonEmptyResult()) {
			res.rowsReturned = logic.convert(r.rowsReturned);
		}
		return res;
	}

}
