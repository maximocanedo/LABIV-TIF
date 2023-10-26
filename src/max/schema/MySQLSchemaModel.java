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
		Dictionary preparedValues = prepareForEditing(newValues);
		QueryAndParameters q = modify__generateQuery(preparedValues, where);
		return new Connector(this.databaseName).transact(q.query, q.params);
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
		return count(select, true);
	}
	public int count(Dictionary select, boolean sameSchema) {
		QueryAndParameters q = count__generateQuery(select, this.tableName, this.databaseName, true);
		try {
			TransactionResponse<Dictionary> res = new Connector(this.databaseName).fetch(
				q.query,
				q.params
			);
			if(res.nonEmptyResult()) {
				Dictionary row = res.rowsReturned.get(0);
				long counted = row.$("counted");
				return Integer.parseInt("" + counted);
			}
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		return -1;
	}

	@Override
	public boolean exists(Dictionary select) {
		return count(select) > 0;
	}
	public boolean validateReference(ReferenceInfo ref, Object obj) {
		return validateReference(ref, obj, true);
	}
	public boolean validateReference(ReferenceInfo ref, Object obj, boolean sameSchema) {
		Dictionary oo = Dictionary.fromArray(ref.getColumnName(), obj);
		QueryAndParameters q = count__generateQuery(
				oo, 
				ref.getTableName(), 
				ref.getDbName(),
				sameSchema
		);
		try {
			TransactionResponse<Dictionary> res = new Connector(this.databaseName).fetch(
				q.query,
				q.params
			);
			if( res != null && res.nonEmptyResult()) {
				Dictionary row = res.rowsReturned.get(0);
				long counted = row.$("counted");
				// SYSOUT
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
 	        		boolean sameSchema = sp.ref.getDbName() == this.databaseName && sp.ref.getTableName() == this.tableName;
	            	boolean vref = validateReference(sp.ref, data.$(key), sameSchema);
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
	
	public boolean validateLimited(Dictionary data) throws SchemaValidationException {
		for(String key : data.keySet()) {
			Object object = data.$(key);
			if(schema.containsKey(key)) {
				SchemaProperty prop = schema.get(key);
				if(object != null && prop.ref != null) {
					// Validar si hay referencias
					boolean sameSchema = prop.ref.getDbName() == this.databaseName && prop.ref.getTableName() == this.tableName;
					boolean vref = validateReference(prop.ref, object, sameSchema);
					if(!vref) {
						throw new SchemaValidationException(key, "Value does not existe in the referenced table. ");
					}
				}
				if(object != null && prop.unique) {
					boolean exists = exists(Dictionary.fromArray(prop.name, object));
					if(exists) {
						throw new SchemaValidationException(key, "Duplicate entry. ");
					}
				}
				return prop.validate(object, key);
			} else {
				throw new SchemaValidationException(key, "Could not found a Schema that matches that key. ");
			}
		}
		return true;
	}
	
	public Dictionary cleanNullValues(Dictionary data) {
		Dictionary newValues = new Dictionary();
		for(String key : data.keySet()) {
			if(data.$(key) != null) {
				newValues.put(key, data.$(key));
			}
		}
		return newValues;
	}
	
	public Dictionary prepareForEditing(Dictionary rawData) throws SchemaValidationException {
		Dictionary newValues = new Dictionary();
		Dictionary data = cleanNullValues(rawData);
		for(String key : data.keySet()) {
			Object object = data.$(key);
			if(schema.containsKey(key)) {
				SchemaProperty prop = schema.get(key);
				if(prop.modifiable) {
					newValues.put(key, object);
				} else continue;
				if(object != null && prop.ref != null) {
					// Validar si hay referencias
					boolean sameSchema = prop.ref.getDbName() == this.databaseName && prop.ref.getTableName() == this.tableName;
					boolean vref = validateReference(prop.ref, object, sameSchema);
					if(!vref) {
						throw new SchemaValidationException(key, "Value does not existe in the referenced table. ");
					} 
				}
				if(object != null && prop.unique) {
					boolean exists = exists(Dictionary.fromArray(prop.name, object));
					if(exists) {
						throw new SchemaValidationException(key, "Duplicate entry. ");
					}
				}
				prop.validate(object, key);
			} else {
				continue;
				//throw new SchemaValidationException(key, "Could not found a Schema that matches that key. ");
			}
		}
		System.out.println("newValues MSSM: " + newValues.toString());
		return newValues;
	}
	
	public void compile() {
		compile(false);
	}
	
	public void compile(boolean verbose) {
		verbose = false;
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
		// Por cada parámetro:
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
		// Por cada parámetro:
		for(Map.Entry<String, Object> prop : data.entrySet()) {
			String key = prop.getKey();
			if(schema.containsKey(key) && data.$(key) != null) {
				_query.append(key).append(" = @").append(key + "m").append(", ");
				parameters.put(key + "m", data.$(key));
			}
		}
		_query.setLength(_query.length() - 2);
		
		
		_query.append(" WHERE  ");
		for(Map.Entry<String, Object> prop : where.entrySet()) {
			String key = prop.getKey();
			if(schema.containsKey(key) && where.$(key) != null) {
				_query.append(key).append(" = @").append(key + "w").append(", ");
				parameters.put(key + "w", where.$(key));
			}
		}
		_query.setLength(_query.length() - 2);
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
		return new QueryAndParameters() {{
			query = _query.toString();
			params = parameters;
		}};
	}
	private QueryAndParameters count__generateQuery(Dictionary where, String t, String d, boolean useSchemaProps) {
		Dictionary parameters = new Dictionary();
		StringBuilder _query = new StringBuilder();
		_query
		.append("SELECT COUNT(*) AS counted FROM ")
		.append(d)
		.append(".")
		.append(t + "  ");
		if(where != null && where.size() > 0) {
			int l = 0;
			for(Map.Entry<String, Object> prop : where.entrySet()) {
				String key = prop.getKey();
				if((useSchemaProps && schema.containsKey(key)) || where.$(key) != null) {
					if(l == 0) _query.append(" WHERE ");
					_query.append(key).append(" = @").append(key).append(", ");
					parameters.put(key, where.$(key));
					l++;
				} else {
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
