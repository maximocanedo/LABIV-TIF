package dataImpl;

import java.sql.SQLException;
import java.sql.Types;

import data.IProvinciaDao;
import entity.Provincia;
import logicImpl.*;
import max.Connector;
import max.Dictionary;
import max.IModel;
import max.IRecord;
import max.MySQLSchemaModel;
import max.Schema;
import max.SchemaProperty;
import max.TransactionResponse;
import oops.SchemaValidationException;

public class ProvinciaDaoImpl implements IRecord<Provincia, Integer>, IProvinciaDao {
	
	public static Schema _schema = new Schema(
			new SchemaProperty("id_provincia") {{
				required = true;
				type = Types.INTEGER;
				primary = true;
			}},
			new SchemaProperty("nombre_provincia") {{
				required = true;
				type = Types.VARCHAR;
				maxlength = 75;
			}}
		);
	public static IModel _model = new MySQLSchemaModel("provincias", "tif", _schema) {{
		compile();
	}};
	private Connector db = new Connector(_model.getDatabaseName());
	private ProvinciaLogicImpl logic = new ProvinciaLogicImpl();
	
	public ProvinciaDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see dataImpl.IProvinciaDao#printTDB()
	 */
	@Override
	public String printTDB() {
		return _model.getDatabaseName() + "." + _model.getTableName();
	}
	
	/* (non-Javadoc)
	 * @see dataImpl.IProvinciaDao#delete(entity.Provincia)
	 */
	@Override
	public TransactionResponse<?> delete(Provincia obj) throws SQLException {
		TransactionResponse<?> res = null;
		try {
			res = _model.delete(obj.toIdentifiableDictionary());
		} catch (SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see dataImpl.IProvinciaDao#exists(java.lang.Integer)
	 */
	@Override
	public boolean exists(Integer arg0) throws SQLException {
		return _model.exists(Dictionary.fromArray("id_provincia", arg0));
	}

	/* (non-Javadoc)
	 * @see dataImpl.IProvinciaDao#getAll()
	 */
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

	/* (non-Javadoc)
	 * @see dataImpl.IProvinciaDao#getById(java.lang.Integer)
	 */
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

	/* (non-Javadoc)
	 * @see dataImpl.IProvinciaDao#insert(entity.Provincia)
	 */
	@Override
	public TransactionResponse<?> insert(Provincia p) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = _model.create(p.toDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see dataImpl.IProvinciaDao#modify(entity.Provincia)
	 */
	@Override
	public TransactionResponse<?> modify(Provincia p) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = _model.modify(p.toDictionary(), p.toIdentifiableDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

}
