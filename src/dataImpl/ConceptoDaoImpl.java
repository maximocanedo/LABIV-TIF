package dataImpl;

import java.sql.SQLException;
import java.sql.Types;

import data.IConceptoDao;
import entity.Concepto;
import logicImpl.ConceptoLogicImpl;
import max.data.*;
import max.net.*;
import max.oops.SchemaValidationException;
import max.schema.*;

public class ConceptoDaoImpl implements IRecord<Concepto,String>, IConceptoDao {
	
	public final static Schema _schema = new Schema(
			new SchemaProperty("cod_Con") {{
				required=true;
				primary=true;
				type=Types.VARCHAR;
				maxlength=4;
				minlength=1;			
			}},
			new SchemaProperty("descripcion_Con") {{
				required=true;
				type=Types.VARCHAR;
				maxlength=100;
				minlength=1;
			}}			
			);
	
	public final static IModel _model = new MySQLSchemaModel("conceptos","tif",_schema) {{
		compile();
	}};
	
	private Connector dbCon = new Connector(_model.getDatabaseName());
	private ConceptoLogicImpl conlg = new ConceptoLogicImpl();
	
	private String printTDB() {
		return _model.getDatabaseName() + "." + _model.getTableName();
	}
	

	@Override
	public TransactionResponse<?> insert(Concepto obj) throws SQLException {
		
		TransactionResponse<?> t = TransactionResponse.create();
		
		try {
				t = _model.create(obj.toDictionary());
		}catch(SchemaValidationException e) {
			e.printStackTrace();			
		}
		
		return t;
	}

	@Override
	public TransactionResponse<?> delete(Concepto obj) throws SQLException {
		
		TransactionResponse<?> t = TransactionResponse.create();
		
		try {
				t = _model.delete(obj.toIdentifiableDictionary());
		}catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		
		return t;
	}

	@Override
	public TransactionResponse<?> modify(Concepto obj) throws SQLException {
		
		TransactionResponse<?> t = TransactionResponse.create();
		
		try {
				t = _model.modify(obj.toDictionary(),obj.toIdentifiableDictionary());
		}catch(SchemaValidationException e) {
			e.printStackTrace();
		}
		
		return t;
	}

	@Override
	public TransactionResponse<Concepto> getAll() throws SQLException {
		
		TransactionResponse<Dictionary> td = TransactionResponse.create();
		TransactionResponse<Concepto>  tc = TransactionResponse.create();
		
		td=dbCon.fetch("SELECT * FROM " + printTDB());
		
		if(td.nonEmptyResult()) {
			tc.rowsReturned = conlg.convert(td.rowsReturned);
		}		
		
		return tc;
	}

	@Override
	public TransactionResponse<Concepto> getById(String cod_Con) throws SQLException {
		
		TransactionResponse<Dictionary> td = TransactionResponse.create();
		TransactionResponse<Concepto> tc = TransactionResponse.create();
		
		td=dbCon.fetch("SELECT * FROM " + printTDB() + " WHERE cod_Con=@codigo_Con",
				Dictionary.fromArray("codigo_Con",cod_Con));
		
		if(td.nonEmptyResult()) {
			tc.rowsReturned = conlg.convert(td.rowsReturned);
			
		}
		
		return tc;
	}

	@Override
	public boolean exists(String cod_Con) throws SQLException {
		
		return _model.exists(Dictionary.fromArray("cod_Con",cod_Con));
	}

}
