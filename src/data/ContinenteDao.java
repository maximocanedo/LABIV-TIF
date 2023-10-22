package data;

import java.sql.SQLException;

import entity.Continente;
import logic.*;
import max.data.*;
import max.net.*;
import max.oops.SchemaValidationException;

public class ContinenteDao implements IRecord<Continente, String> {
	
	private Connector db = new Connector(Continente._model.getDatabaseName());
	private ContinenteLogic logic = new ContinenteLogic();
	
	public ContinenteDao() {
		// TODO Auto-generated constructor stub
	}

	public String printTDB() {
		return Continente._model.getDatabaseName() + "." + Continente._model.getTableName();
	}
	
	/**
	 * No está previsto el uso de este método.
	 */
	@Override
	public TransactionResponse<?> delete(Continente obj) throws SQLException {
		TransactionResponse<?> res = null;
		try {
			res = Continente._model.delete(obj.toIdentifiableDictionary());
		} catch (SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public boolean exists(String arg0) throws SQLException {
		return Continente._model.exists(Dictionary.fromArray("id_Continente", arg0));
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
			res = Continente._model.create(p.toDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public TransactionResponse<?> modify(Continente p) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = Continente._model.modify(p.toDictionary(), p.toIdentifiableDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public TransactionResponse<Continente> select(String arg0) throws SQLException {
		TransactionResponse<Dictionary> r = db.fetch(arg0);
		TransactionResponse<Continente> res = new TransactionResponse<Continente>();
		if(r.nonEmptyResult()) {
			res.rowsReturned = logic.convert(r.rowsReturned);
		}
		return res;
	}

	@Override
	public TransactionResponse<Continente> select(String arg0, Dictionary arg1) throws SQLException {
		TransactionResponse<Dictionary> r = db.fetch(arg0, arg1);
		TransactionResponse<Continente> res = new TransactionResponse<Continente>();
		if(r.nonEmptyResult()) {
			res.rowsReturned = logic.convert(r.rowsReturned);
		}
		return res;
	}

	@Override
	public TransactionResponse<Continente> select(String arg0, Object[] arg1) throws SQLException {
		TransactionResponse<Dictionary> r = db.fetch(arg0, arg1);
		TransactionResponse<Continente> res = new TransactionResponse<Continente>();
		if(r.nonEmptyResult()) {
			res.rowsReturned = logic.convert(r.rowsReturned);
		}
		return res;
	}

}
