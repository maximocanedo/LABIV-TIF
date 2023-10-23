package max.schema;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import max.oops.SchemaValidationException;

import java.sql.Types;
import java.text.NumberFormat;
import java.text.ParseException;

public class SchemaProperty {
	
	// Nombre de la columna
	public String name = "";
	
	// Validadores
	public boolean required = false;  // (OK) Si el valor es requerido o si es opcional.
	public int type = Types.VARCHAR;  // (OK) El tipo de valor que se espera guardar.
	public double min = Double.NEGATIVE_INFINITY;  // (OK) Para valores numéricos, el menor valor permitido.
	public double max = Double.POSITIVE_INFINITY;  // (OK) Para valores numéricos, el mayor valor permitido.
	public int minlength = 0;   // (OK) Para cadenas de texto, la mínima cantidad de caracteres permitida.
	public int maxlength = Integer.MAX_VALUE; // (OK) Para cadenas de texto, la máxima cantidad de caracteres permitida.
	public String matches = null;   // (OK) Para cadenas de texto, expresión regular que se usará para validar.
	public Object defaultValue = null;  // (OK) Si el valor es nulo o no existe, usar este valor por defecto.
	// "Ref" sólo se valida con IModel.validate(). No se valida con Schema.validate().
	public ReferenceInfo ref = null;  // (OK) Si el valor hace referencia a otra columna en otra tabla. *NO* valida si esa columna es PK o no.
	
	// Cosas que no se validan, sino que sirven para modificar el dato antes de subirlo a una bd.
	public boolean trim = false; // (OK) Si se corta el espacio en blanco antes de validar (Para cadenas de texto).
	public boolean lowercase = false; // (OK) Si se convierte a minúsculas antes de guardar (Para cadenas de texto).
	public boolean uppercase = false; // (OK) Si se convierte a mayúsculas antes de guardar (Para cadenas de texto).
	
	// Validadores SQL
	public boolean primary = false;
	public boolean autoIncrement = false;
	public boolean unique = false;
	
	// Banderas para consultas
	public boolean select = true; // Si se selecciona automáticamente en cada consulta.
	public boolean searchable = true; // Si al realizar búsquedas se usará esta propiedad.
	public boolean modifiable = true; // Si al realizar consultas tipo UPDATE se podrá modificar esta propiedad.
	
	
	public SchemaValidationResult prepare(Object obj) {
		if(obj instanceof String) {
			String x = (String) obj;
			if(trim) {
				x = x.trim();
			}
			if(lowercase) {
				x = x.toLowerCase();
			}
			if(uppercase) {
				x = x.toUpperCase();
			}
			SchemaValidationResult svr = new SchemaValidationResult();
			try {
				boolean e = validate(x, this.name);
				svr.status = e;
			} catch (SchemaValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				svr.message = e.getMessage();
			}
			svr.transformedValue = x;
			return svr;
		}
		SchemaValidationResult svr = new SchemaValidationResult();
		try {
			boolean f = validate(obj, this.name);
			svr.status = f;
		} catch (SchemaValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			svr.message = e.getMessage();
		}
		svr.transformedValue = obj;
		return svr;
	}
	
	
	public SchemaProperty(String columnName) {
		this.name = columnName;
	}
	protected void out(Object e) {
		System.out.println(e);
	}
	
	
	private boolean validateSqlType(Object obj, int expectedSqlType) {
	    int actualSqlType = sqlTypeFor(obj);
	    //System.out.println("Actual: " + sqlTypeFor(obj) + "; Expected: " + expectedSqlType);
	    return expectedSqlType == actualSqlType;
	}
	
	public int sqlTypeFor(Object obj) {
	    if (obj == null) {
	        return Types.NULL;
	    } else if (obj instanceof Integer) {
	        return Types.INTEGER;
	    } else if (obj instanceof Long) {
	        return Types.BIGINT;
	    } else if (obj instanceof Byte[]) {
	        return Types.BINARY;
	    } else if (obj instanceof Boolean) {
	        return Types.BIT;
	    } else if (obj instanceof byte[]) {
	        return Types.BLOB;
	    } else if (obj instanceof String) {
	        return Types.VARCHAR;
	    } else if (obj instanceof java.sql.Date) {
	        return Types.DATE;
	    } else if (obj instanceof java.math.BigDecimal) {
	        return Types.DECIMAL;
	    } else if (obj instanceof Double) {
	        return Types.DOUBLE;
	    } else if (obj instanceof Float) {
	        return Types.FLOAT;
	    }  else if (obj instanceof byte[]) {
	        return Types.LONGVARBINARY;
	    } else if (obj instanceof String) {
	        return Types.LONGVARCHAR;
	    } else if (obj instanceof String) {
	        return Types.CLOB;
	    } else if (obj instanceof java.sql.Timestamp) {
	        return Types.TIMESTAMP;
	    } else if (obj instanceof java.sql.Time) {
	        return Types.TIME;
	    } else if (obj instanceof java.sql.Date) {
	        return Types.DATE;
	    } else if (obj instanceof Short) {
	        return Types.SMALLINT;
	    } else if (obj instanceof String) {
	        return Types.VARBINARY;
	    }
	    
	    return Types.OTHER; // Tipo de datos no reconocido.
	}

	public String sqlTypeNameFor(Object obj) {
	    if (obj == null) {
	        return "NULL";
	    } else if (obj instanceof Integer) {
	        return "INTEGER";
	    } else if (obj instanceof Long) {
	        return "BIGINT";
	    } else if (obj instanceof Byte[]) {
	        return "BINARY";
	    } else if (obj instanceof Boolean) {
	        return "BIT";
	    } else if (obj instanceof byte[]) {
	        return "BLOB";
	    } else if (obj instanceof String) {
	        return "VARCHAR";
	    } else if (obj instanceof java.sql.Date) {
	        return "DATE";
	    } else if (obj instanceof java.math.BigDecimal) {
	        return "DECIMAL";
	    } else if (obj instanceof Double) {
	        return "DOUBLE";
	    } else if (obj instanceof Float) {
	        return "FLOAT";
	    } else if (obj instanceof Integer) {
	        return "INTEGER";
	    } else if (obj instanceof byte[]) {
	        return "LONGVARBINARY";
	    } else if (obj instanceof String) {
	        return "LONGVARCHAR";
	    } else if (obj instanceof String) {
	        return "CLOB";
	    } else if (obj instanceof java.sql.Timestamp) {
	        return "TIMESTAMP";
	    } else if (obj instanceof java.sql.Time) {
	        return "TIME";
	    } else if (obj instanceof java.sql.Date) {
	        return "DATE";
	    } else if (obj instanceof Short) {
	        return "SMALLINT";
	    } else if (obj instanceof String) {
	        return "VARBINARY";
	    }
	    
	    return "OTHER"; // Tipo de datos no reconocido.
	}
	
	public boolean isText() {
		return type == Types.CHAR || type == Types.LONGNVARCHAR || type == Types.VARCHAR || type == Types.NVARCHAR || type == Types.NCHAR;
	}
	
	public Map<Integer, String> getAllJdbcTypeNames() {

	    Map<Integer, String> result = new HashMap<Integer, String>();

	    for (Field field : Types.class.getFields()) {
	        try {
				result.put((Integer)field.get(null), field.getName());
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    return result;
	}

	public String getSQLTypeName(int t) {
		return getAllJdbcTypeNames().get(t);
	}
	
	public boolean validate(Object obj, String key) throws SchemaValidationException {
		boolean validatedLimits = true;
		boolean validatedLength = true;
		boolean validatedPattern = true;
		boolean validatedRef = true;

		boolean validateType = (type == Types.OTHER) || (type == Types.NULL);
		if (type != Types.OTHER) {
			validateType = (obj != null) && (type == Types.NULL || validateSqlType(obj, type));
	    }
		if(!validateType) { 
			throw new SchemaValidationException(key, "A " + sqlTypeNameFor(obj) + " object was provided, but a " + getSQLTypeName(this.type) + " was expected. ");
		}
		boolean isParseableToNumber = false;
		double val = 0;
		if(obj instanceof String) {
			try {
		        val = NumberFormat.getInstance().parse((String) obj).doubleValue();
		        isParseableToNumber = true; 
		    } catch (ParseException e) {
		    	isParseableToNumber = false; 
		    }
		}
		if(obj instanceof Number || isParseableToNumber) {
			if(!isParseableToNumber) val = ((Number) obj).doubleValue();
			// Validamos cosas de números.
			validatedLimits = (val >= min) && (val <= max);
				if(!validatedLimits) {
					throw new SchemaValidationException(key, "The number is out of the allowed range, which is " + min + "-" + max + ". ");
				}
		}
		if(obj instanceof String) {
			String s = (String) obj;
			validatedLength = (s.length() >= minlength) && (s.length() <= maxlength);
			if(matches != null || matches == "") {
				Pattern p = Pattern.compile(matches);
				Matcher m = p.matcher(s);
				validatedPattern = m.matches();
			}
			if(!validatedLength) {
				throw new SchemaValidationException(key, "The string length is out of the specified range. ");
			}
			if(!validatedPattern) {
				throw new SchemaValidationException(key, "The string does not match the specified regular expression. ");
			}
		}
		
		return validateType 
			&& validatedLimits 
			&& validatedLength 
			&& validatedPattern
			&& validatedRef;
	}
	
	

}
