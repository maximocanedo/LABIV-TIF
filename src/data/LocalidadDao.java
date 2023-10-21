package data;

import java.sql.SQLException;

import entity.Localidad;
import entity.Provincia;
import logic.LocalidadLogic;
import max.data.Dictionary;
import max.data.IRecord;
import max.data.TransactionResponse;
import max.net.Connector;
import max.oops.SchemaValidationException;

public class LocalidadDao implements IRecord<Localidad, Integer> {
	
	private Connector db = new Connector(Localidad._model.getDatabaseName());
	private LocalidadLogic logic = new LocalidadLogic();
	
	public LocalidadDao() {
		// TODO Auto-generated constructor stub
	}

	public String printTDB() {
		return Localidad._model.getDatabaseName() + "." + Localidad._model.getTableName();
	}
	
	/**
	 * No está previsto el uso de este método.
	 */
	@Override
	public TransactionResponse<?> delete(Localidad obj) throws SQLException {
		TransactionResponse<?> res = null;
		try {
			res = Localidad._model.delete(obj.toIdentifiableDictionary());
		} catch (SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public boolean exists(Integer arg0) throws SQLException {
		return Localidad._model.exists(Dictionary.fromArray("id_loc", arg0));
	}

	@Override
	public TransactionResponse<Localidad> getAll() throws SQLException {
		TransactionResponse<Dictionary> res = db.fetch(
			"SELECT * FROM " + printTDB()
		);
		TransactionResponse<Localidad> fin = new TransactionResponse<Localidad>();
		if(res.nonEmptyResult()) {
			fin.rowsReturned = logic.convert(res.rowsReturned);
		}
		return fin;
	}

	@Override
	public TransactionResponse<Localidad> getById(Integer id) throws SQLException {
		TransactionResponse<Dictionary> res = db.fetch(
			"SELECT * FROM " + printTDB() + " WHERE id_loc = @id",
			Dictionary.fromArray("id", id)
		);
		TransactionResponse<Localidad> fin = new TransactionResponse<Localidad>();
		if(res.nonEmptyResult()) {
			fin.rowsReturned = logic.convert(res.rowsReturned);
		}
		return fin;
	}

	@Override
	public TransactionResponse<?> insert(Localidad p) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = Localidad._model.create(p.toDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public TransactionResponse<?> modify(Localidad p) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = Localidad._model.modify(p.toDictionary(), p.toIdentifiableDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public TransactionResponse<Localidad> select(String arg0) throws SQLException {
		TransactionResponse<Dictionary> r = db.fetch(arg0);
		TransactionResponse<Localidad> res = new TransactionResponse<Localidad>();
		if(r.nonEmptyResult()) {
			res.rowsReturned = logic.convert(r.rowsReturned);
		}
		return res;
	}

	@Override
	public TransactionResponse<Localidad> select(String arg0, Dictionary arg1) throws SQLException {
		TransactionResponse<Dictionary> r = db.fetch(arg0, arg1);
		TransactionResponse<Localidad> res = new TransactionResponse<Localidad>();
		if(r.nonEmptyResult()) {
			res.rowsReturned = logic.convert(r.rowsReturned);
		}
		return res;
	}

	@Override
	public TransactionResponse<Localidad> select(String arg0, Object[] arg1) throws SQLException {
		TransactionResponse<Dictionary> r = db.fetch(arg0, arg1);
		TransactionResponse<Localidad> res = new TransactionResponse<Localidad>();
		if(r.nonEmptyResult()) {
			res.rowsReturned = logic.convert(r.rowsReturned);
		}
		return res;
	}
	
	public TransactionResponse<Localidad> filterByProvince(Provincia p) throws SQLException {
		return select("SELECT * FROM " + printTDB() + " WHERE provincia_loc = @provincia", Dictionary.fromArray( "provincia", p.getId() ));
	}


}
