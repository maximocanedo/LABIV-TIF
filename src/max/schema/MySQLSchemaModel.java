package max.schema;

import java.sql.SQLException;
import java.util.Map;
import max.data.Dictionary;
import max.data.TransactionResponse;
import max.net.Connector;
import max.oops.SchemaValidationException;

public class MySQLSchemaModel implements IModel {
	
	private Schema schema;
	private String tableName;
	private String databaseName;
	
	
	public MySQLSchemaModel(String tableName, String databaseName, Schema schema) {
		this.tableName = tableName;
		this.databaseName = databaseName;
		this.schema = schema;
	}

	@Override
	public TransactionResponse<?> create(Dictionary... data) throws SQLException, SchemaValidationException {
		TransactionResponse<?> f = TransactionResponse.create();
		f.status = true;
		for(Dictionary dic : data) {
			TransactionResponse<?> e = create(dic);
			f.status = f.status && e.status;
			f.rowsAffected += e.rowsAffected;
		}
		return f;
	}
	@Override
	public TransactionResponse<?> create(Dictionary data) throws SQLException, SchemaValidationException {
		boolean svr = validate(data);
		TransactionResponse<?> res = TransactionResponse.create();
		if(svr) {
			QueryAndParameters q = create__generateQuery(data);
			res = new Connector(this.databaseName).transact(q.query, q.params);
			
		} else {
			res.status = false;
		}
		return res;
		
    }

	@Override
	public TransactionResponse<?> modify(Dictionary newValues, Dictionary where) throws SQLException, SchemaValidationException {
		boolean svr = validate(newValues);
		TransactionResponse<?> res = TransactionResponse.create();
		if(svr) {
			QueryAndParameters q = modify__generateQuery(newValues, where);
			res = new Connector(this.databaseName).transact(q.query, q.params);
			
		} else {
			res.status = false;
		}
		return res;
	}

	@Override
	public TransactionResponse<?> delete(Dictionary where) throws SQLException, SchemaValidationException {
		TransactionResponse<?> res = TransactionResponse.create();
		QueryAndParameters q = delete__generateQuery(where);
		res = new Connector(this.databaseName).transact(q.query, q.params);
		return res;
	}

	@Override
	public String getTableName() {
		return this.tableName;
	}

	@Override
	public String getDatabaseName() {
		return this.databaseName;
	}

	@Override
	public int count(Dictionary select) {
		System.out.println("COMIENZA CONTEO");
		QueryAndParameters q = count__generateQuery(select, this.tableName, this.databaseName);
		try {
			TransactionResponse<Dictionary> res = new Connector(this.databaseName).fetch(
				q.query,
				q.params
			);
			if(res.nonEmptyResult()) {
				Dictionary row = res.rowsReturned.get(0);
				long counted = row.$("counted");
				System.out.println("TERMINA CONTEO = " + counted);
				return Integer.parseInt("" + counted);
			}
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		System.out.println("TERMINA CONTEO");
		return -1;
	}

	@Override
	public boolean exists(Dictionary select) {
		return count(select) > 0;
	}

	public boolean validateReference(ReferenceInfo ref, Object obj) {
		QueryAndParameters q = count__generateQuery(
				Dictionary.fromArray(ref.getColumnName(), obj), 
				ref.getTableName(), 
				ref.getDbName()
		);
		System.out.println(Dictionary.fromArray(ref.getColumnName(), obj));
		try {
			System.out.println(q.query);
			System.out.println(q.params);
			TransactionResponse<Dictionary> res = new Connector(this.databaseName).fetch(
				q.query,
				q.params
			);
			if(res.nonEmptyResult()) {
				Dictionary row = res.rowsReturned.get(0);
				long counted = row.$("counted");
				return Integer.parseInt("" + counted) > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		return false;
	}
	
	@Override
	public ReferenceInfo ref(String propertyName) {
		if(schema.get(propertyName) != null) {
			SchemaProperty p = (SchemaProperty) schema.get(propertyName);
			String columnName = p.name;
			return new ReferenceInfo(this.tableName, columnName, this.databaseName);
		}
		return null;
	}

	@Override
	public boolean validate(Dictionary data) throws SchemaValidationException {
	    for (Map.Entry<String, SchemaProperty> entry : schema.entrySet()) {
	        String key = entry.getKey();
	        SchemaProperty sp = entry.getValue();
	        // Ver si existe
	        if (data.$(key) != null && sp.ref != null) {
	            // Validar en caso de haber referencias
	            	boolean vref = validateReference(sp.ref, data.$(key));
	            	if(!vref) {
	            		throw new SchemaValidationException(key, "Value does not exist in the referenced table. ");
	            	}
	        }
	        if(data.$(key) != null && sp.unique) {
	        	boolean exists = exists(Dictionary.fromArray(sp.name, data.$(key)));
	        	if(exists) throw new SchemaValidationException(key, "Duplicate entry. ");
	        }
	    }
	    return schema.validate(data);
	}
	
	public void compile() {
		compile(false);
	}
	
	public void compile(boolean verbose) {
		Connector c = new Connector();
		try {
			c.transact("CREATE DATABASE IF NOT EXISTS " + this.databaseName + ";");
			StringBuilder t = new StringBuilder();
			t.append("CREATE TABLE IF NOT EXISTS ");
			t.append(this.tableName);
			t.append("( ");
			for(SchemaProperty prop : schema.values()) {
				t
				 .append(prop.name)
				 .append(" " + prop.getSQLTypeName(prop.type))
				 .append(prop.maxlength < Integer.MAX_VALUE ? "(" + prop.maxlength + ")" : "")
				 .append(prop.autoIncrement ? " AUTO_INCREMENT" : "")
				 .append(prop.required ? " NOT NULL" : "")
				 .append(prop.primary ? " PRIMARY KEY" : (prop.unique ? " UNIQUE" : ""))
				 .append(prop.ref != null ? " REFERENCES " + 
						 (prop.ref.getDbName() == this.databaseName ? "" : prop.ref.getDbName() + ".") 
						 + prop.ref.getTableName() + "(" + prop.ref.getColumnName() + ")" : "")
				 .append(", ");
			}
			t.setLength(t.length() - 2);
			t.append(" );");
			if(verbose) System.out.println("Query: " + t.toString());
			new Connector(this.databaseName).transact(t.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// CLASES PRIVADAS AUXILIARES PARA GENERAR QUERIES
	private class QueryAndParameters {
		public String query;
		public Dictionary params;
	}
	private QueryAndParameters create__generateQuery(Dictionary data) {
		Dictionary parameters = new Dictionary();
		StringBuilder _query = new StringBuilder();
		_query
		.append("INSERT INTO ")
		.append(tableName)
		.append(" (");
		// Por cada par�metro:
		for(Map.Entry<String, SchemaProperty> prop : schema.entrySet()) {
			String key = prop.getKey();
			_query.append(key).append(", ");
		}
		_query.setLength(_query.length() - 2);
		_query.append(") SELECT ");
		for(Map.Entry<String, SchemaProperty> prop : schema.entrySet()) {
			String key = prop.getKey();
			_query.append("@").append(key).append(", ");
			Object valueToAdd = data.$(key) == null ? prop.getValue().defaultValue : data.$(key);
			parameters.put(key, valueToAdd);
		}
		_query.setLength(_query.length() - 2);
		return new QueryAndParameters() {{
			query = _query.toString();
			params = parameters;
		}};
	}
	private QueryAndParameters modify__generateQuery(Dictionary data, Dictionary where) {
		Dictionary parameters = new Dictionary();
		StringBuilder _query = new StringBuilder();
		_query
		.append("UPDATE ")
		.append(tableName)
		.append(" SET ");
		// Por cada par�metro:
		for(Map.Entry<String, Object> prop : data.entrySet()) {
			String key = prop.getKey();
			if(schema.containsKey(key) && data.$(key) != null) {
				_query.append(key).append(" = @").append(key + "m").append(", ");
				parameters.put(key + "m", data.$(key));
			}
		}
		_query.setLength(_query.length() - 2);
		
		
		_query.append(" WHERE ");
		for(Map.Entry<String, Object> prop : where.entrySet()) {
			String key = prop.getKey();
			if(schema.containsKey(key) && where.$(key) != null) {
				_query.append(key).append(" = @").append(key + "w").append(", ");
				parameters.put(key + "w", where.$(key));
			}
		}
		_query.setLength(_query.length() - 2);
		System.out.println(_query.toString());
		System.out.println(parameters.toString());
		return new QueryAndParameters() {{
			query = _query.toString();
			params = parameters;
		}};
	}
	private QueryAndParameters delete__generateQuery(Dictionary where) {
		Dictionary parameters = new Dictionary();
		StringBuilder _query = new StringBuilder();
		_query
		.append("DELETE FROM ")
		.append(tableName)
		.append(" WHERE ");
		for(Map.Entry<String, Object> prop : where.entrySet()) {
			String key = prop.getKey();
			if(schema.containsKey(key) && where.$(key) != null) {
				_query.append(key).append(" = @").append(key).append(", ");
				parameters.put(key, where.$(key));
			}
		}
		_query.setLength(_query.length() - 2);
		System.out.println(_query.toString());
		System.out.println(parameters.toString());
		return new QueryAndParameters() {{
			query = _query.toString();
			params = parameters;
		}};
	}
	private QueryAndParameters count__generateQuery(Dictionary where, String t, String d) {
		Dictionary parameters = new Dictionary();
		StringBuilder _query = new StringBuilder();
		_query
		.append("SELECT COUNT(*) AS counted FROM ")
		.append(d)
		.append(".")
		.append(t + "  ");
		System.out.println("WHERE A CONT IN " + d + "." + t);
		System.out.println(where.toString());
		if(where != null && where.size() > 0) {
				
			int l = 0;
			for(Map.Entry<String, Object> prop : where.entrySet()) {
				String key = prop.getKey();
				if(schema.containsKey(key) && where.$(key) != null) {
					System.out.println(94);
					if(l == 0) _query.append(" WHERE ");
					_query.append(key).append(" = @").append(key).append(", ");
					System.out.println(_query.toString());
					parameters.put(key, where.$(key));
					l++;
				} else {
					System.out.println("Pas� por ac� en " + d + "." + t);
				}
			}
			_query.setLength(_query.length() - 2);
		}
		return new QueryAndParameters() {{
			query = _query.toString();
			params = parameters;
		}};
	}
	



	
	
	
}