package data;

import java.sql.SQLException;

import entity.Administrador;
import logic.AdministradorLogic;
import max.data.Dictionary;
import max.data.IRecord;
import max.data.TransactionResponse;
import max.net.Connector;
import max.oops.SchemaValidationException;

public class AdministradorDao implements IRecord<Administrador, String> {
	
	private Connector db = new Connector(Administrador._model.getDatabaseName());
	private AdministradorLogic logic = new AdministradorLogic();
	public String printTDB() {
		return Administrador._model.getDatabaseName() + "." + Administrador._model.getTableName();
	}
	

	@Override
	public TransactionResponse<?> delete(Administrador a) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = Administrador._model.delete(a.toIdentifiableDictionary());
		} catch (SchemaValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public boolean exists(String arg0) throws SQLException {
		Administrador a = new Administrador();
		a.setUsuario(arg0);
		return Administrador._model.exists(a.toIdentifiableDictionary());
	}

	@Override
	public TransactionResponse<Administrador> getAll() throws SQLException {
		return select("SELECT * FROM " + printTDB());
	}

	@Override
	public TransactionResponse<Administrador> getById(String arg0) throws SQLException {
		return select("SELECT * FROM " + printTDB() + " WHERE usuario_admin = @user", Dictionary.fromArray("user", arg0));
	}

	@Override
	public TransactionResponse<?> insert(Administrador arg0) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = Administrador._model.delete(arg0.toIdentifiableDictionary());
		} catch (SchemaValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public TransactionResponse<?> modify(Administrador arg0) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = Administrador._model.modify(arg0.toDictionary(), arg0.toIdentifiableDictionary());
		} catch (SchemaValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public TransactionResponse<Administrador> select(String arg0) throws SQLException {
		TransactionResponse<Administrador> res = new TransactionResponse<Administrador>();
		TransactionResponse<Dictionary> rd = db.fetch(arg0);
		if(rd.nonEmptyResult()) {
			res.rowsReturned = logic.convert(rd.rowsReturned);
		}
		return res;
	}

	@Override
	public TransactionResponse<Administrador> select(String arg0, Dictionary arg1) throws SQLException {
		TransactionResponse<Administrador> res = new TransactionResponse<Administrador>();
		TransactionResponse<Dictionary> rd = db.fetch(arg0, arg1);
		if(rd.nonEmptyResult()) {
			res.rowsReturned = logic.convert(rd.rowsReturned);
		}
		return res;
	}

	@Override
	public TransactionResponse<Administrador> select(String arg0, Object[] arg1) throws SQLException {
		TransactionResponse<Administrador> res = new TransactionResponse<Administrador>();
		TransactionResponse<Dictionary> rd = db.fetch(arg0, arg1);
		if(rd.nonEmptyResult()) {
			res.rowsReturned = logic.convert(rd.rowsReturned);
		}
		return res;
	}

}