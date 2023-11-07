package dataImpl;

import java.sql.SQLException;
import java.sql.Types;

import data.ILocalidadDao;
import entity.Localidad;
import entity.Provincia;
import logicImpl.LocalidadLogicImpl;
import max.Connector;
import max.Dictionary;
import max.IModel;
import max.IRecord;
import max.MySQLSchemaModel;
import max.Schema;
import max.SchemaProperty;
import max.TransactionResponse;
import max.oops.SchemaValidationException;

public class LocalidadDaoImpl implements IRecord<Localidad, Integer>, ILocalidadDao {
	
	public final static Schema _schema = new Schema(
			new SchemaProperty("id_loc") {{
				required = true;
				type = Types.INTEGER;
				primary = true;
			}},
			new SchemaProperty("nombre_loc") {{
				required = true;
				type = Types.VARCHAR;
				maxlength = 75;
			}},
			new SchemaProperty("provincia_loc") {{
				required = true;
				type = Types.INTEGER;
				ref = ProvinciaDaoImpl._model.ref("id_provincia");
			}}
		);
	
	public final static IModel _model = new MySQLSchemaModel("localidades", "tif", _schema) {{
		compile();
	}};
	
	private Connector db = new Connector(_model.getDatabaseName());
	private LocalidadLogicImpl logic = new LocalidadLogicImpl();
	
	public LocalidadDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see dataImpl.ILocalidadDao#printTDB()
	 */
	@Override
	public String printTDB() {
		return _model.getDatabaseName() + "." + _model.getTableName();
	}
	
	/* (non-Javadoc)
	 * @see dataImpl.ILocalidadDao#delete(entity.Localidad)
	 */
	@Override
	public TransactionResponse<?> delete(Localidad obj) throws SQLException {
		TransactionResponse<?> res = null;
		try {
			res = _model.delete(obj.toIdentifiableDictionary());
		} catch (SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see dataImpl.ILocalidadDao#exists(java.lang.Integer)
	 */
	@Override
	public boolean exists(Integer arg0) throws SQLException {
		return _model.exists(Dictionary.fromArray("id_loc", arg0));
	}

	/* (non-Javadoc)
	 * @see dataImpl.ILocalidadDao#getAll()
	 */
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

	/* (non-Javadoc)
	 * @see dataImpl.ILocalidadDao#getById(java.lang.Integer)
	 */
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

	/* (non-Javadoc)
	 * @see dataImpl.ILocalidadDao#insert(entity.Localidad)
	 */
	@Override
	public TransactionResponse<?> insert(Localidad p) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = _model.create(p.toDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see dataImpl.ILocalidadDao#modify(entity.Localidad)
	 */
	@Override
	public TransactionResponse<?> modify(Localidad p) throws SQLException {
		TransactionResponse<?> res = TransactionResponse.create();
		try {
			res = _model.modify(p.toDictionary(), p.toIdentifiableDictionary());
		} catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		return res;
	}

	private TransactionResponse<Localidad> select(String arg0, Dictionary arg1) throws SQLException {
		TransactionResponse<Dictionary> r = db.fetch(arg0, arg1);
		TransactionResponse<Localidad> res = new TransactionResponse<Localidad>();
		if(r.nonEmptyResult()) {
			res.rowsReturned = logic.convert(r.rowsReturned);
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see dataImpl.ILocalidadDao#filterByProvince(entity.Provincia)
	 */
	@Override
	public TransactionResponse<Localidad> filterByProvince(Provincia p) throws SQLException {
		return select("SELECT * FROM " + printTDB() + " WHERE provincia_loc = @provincia", Dictionary.fromArray( "provincia", p.getId() ));
	}


}
