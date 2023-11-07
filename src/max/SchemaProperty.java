package max;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oops.SchemaValidationException;

import java.sql.Types;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
	
	public E getEByQValue(int qValue) {
	    for (E e : tipoDeDatoMap.values()) {
	        if (e.q == qValue) {
	            return e;
	        }
	    }
	    return null;
	}
	
	private boolean validateSqlType(Object obj, int expectedSqlType) {
	    E actualSqlType = sqlTypeNameFor(obj);
	    E expected = getEByQValue(expectedSqlType);
	    return actualSqlType.q == expectedSqlType || (expected != null ? expected.sup == actualSqlType.sup : false);
	}
	
	public int sqlTypeFor(Object obj) {
    	if(obj == null) {
		return Types.NULL;
	} else if(obj instanceof Number ) {
		if (obj instanceof Integer) {
			return Types.INTEGER;
		} else if (obj instanceof Long) {
			return Types.BIGINT;
		} else if (obj instanceof java.math.BigDecimal) {
			return Types.DECIMAL;
		} else if (obj instanceof Double) {
			return Types.DOUBLE;
		} else if (obj instanceof Float) {
			return Types.FLOAT;
		} else if (obj instanceof Integer) {
			return Types.INTEGER;
		} else if (obj instanceof Short) {
			return Types.SMALLINT;
		}
	} else if (obj instanceof Byte[]) {
        return Types.BINARY;
    } else if (obj instanceof Boolean) {
        return Types.BIT;
    } else if (obj instanceof byte[]) {
        return Types.VARBINARY;
    } else if (obj instanceof String) {
        return Types.VARCHAR;
    } else if (obj instanceof java.sql.Date) {
        return Types.DATE;
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
    }  else if (obj instanceof String) {
        return Types.VARBINARY;
    }
    
    return Types.OTHER; // Tipo de datos no reconocido.
}
	
	private static class E {
		public String type;
		public String sup;
		public int q;
		public E(String type, String sup, int q) {
			this.type = type;
			this.sup = sup;
			this.q = q;
		}
	}

	 // Crear un HashMap estático para mapear los tipos de datos
    private static final Map<Class<?>, E> tipoDeDatoMap = new HashMap<>();

    static {
        // Mapear los tipos de datos con las instancias de E
        tipoDeDatoMap.put(null, new E("NULL", "NULL", Types.NULL));
        tipoDeDatoMap.put(Integer.class, new E("INTEGER", "NUMERIC", Types.INTEGER));
        tipoDeDatoMap.put(Long.class, new E("BIGINT", "NUMERIC", Types.BIGINT));
        tipoDeDatoMap.put(java.math.BigDecimal.class, new E("DECIMAL", "NUMERIC", Types.DECIMAL));
        tipoDeDatoMap.put(Double.class, new E("DOUBLE", "NUMERIC", Types.DOUBLE));
        tipoDeDatoMap.put(Float.class, new E("FLOAT", "NUMERIC", Types.FLOAT));
        tipoDeDatoMap.put(Short.class, new E("SMALLINT", "NUMERIC", Types.SMALLINT));
        tipoDeDatoMap.put(Byte[].class, new E("BINARY", "BINARY", Types.BINARY));
        tipoDeDatoMap.put(Boolean.class, new E("BIT", "BINARY", Types.BIT));
        tipoDeDatoMap.put(byte[].class, new E("VARBINARY", "BINARY", Types.VARBINARY));
        tipoDeDatoMap.put(String.class, new E("VARCHAR", "STRING", Types.VARCHAR));
        tipoDeDatoMap.put(java.sql.Date.class, new E("DATE", "DATE", Types.DATE));
        tipoDeDatoMap.put(java.sql.Timestamp.class, new E("TIMESTAMP", "BINARY", Types.TIMESTAMP));
        tipoDeDatoMap.put(java.sql.Time.class, new E("TIME", "DATE", Types.TIME));
    }

    public E sqlTypeNameFor(Object obj) {
        // Verificar si el tipo de dato está mapeado en el HashMap
        E tipoDeDato = obj == null ? new E("NULL", "NULL", Types.NULL) : tipoDeDatoMap.get(obj.getClass());

        if (tipoDeDato != null) {
            return tipoDeDato;
        } else {
            return new E("OTHER", "OTHER", Types.OTHER);
        }
    }




	
	public E sqlTypeNameForOLD(Object obj) {
		if(obj == null) {
			return new E("NULL", "NULL", Types.NULL);
		} else if(obj instanceof Number) {
			if (obj instanceof Integer) {
				return new E("INTEGER", "NUMERIC", Types.INTEGER);
			} else if (obj instanceof Long) {
				return new E("BIGINT", "NUMERIC", Types.BIGINT);
			} else if (obj instanceof java.math.BigDecimal) {
				return new E("DECIMAL", "NUMERIC", Types.DECIMAL);
			} else if (obj instanceof Double) {
				return new E("DOUBLE", "NUMERIC", Types.DOUBLE);
			} else if (obj instanceof Float) {
				return new E("FLOAT", "NUMERIC", Types.FLOAT);
			} else if (obj instanceof Short) {
				return new E("SMALLINT", "NUMERIC", Types.SMALLINT);
			}
		} else if (obj instanceof Byte[]) {
	        return new E("BINARY", "BINARY", Types.BINARY);
	    } else if (obj instanceof Boolean) {
	        return new E("BIT", "BINARY", Types.BIT);
	    } else if (obj instanceof byte[]) {
	        return new E("VARBINARY", "BINARY", Types.VARBINARY);
	    } else if (obj instanceof String) {
	        return new E("VARCHAR", "STRING", Types.VARCHAR);
	    } else if (obj instanceof java.sql.Date) {
	        return new E("DATE", "DATE", Types.DATE);
	    }  else if (obj instanceof byte[]) {
	        return new E("LONGVARBINARY", "BINARY", Types.LONGVARBINARY);
	    } else if (obj instanceof String) {
	        return new E("LONGVARCHAR", "STRING", Types.LONGVARCHAR);
	    } else if (obj instanceof String) {
	        return new E("CLOB", "BINARY", Types.CLOB);
	    } else if (obj instanceof java.sql.Timestamp) {
	        return new E("TIMESTAMP", "BINARY", Types.TIMESTAMP);
	    } else if (obj instanceof java.sql.Time) {
	        return new E("TIME", "DATE", Types.TIME);
	    } else if (obj instanceof java.sql.Date) {
	        return new E("DATE", "DATE", Types.DATE);
	    }  else if (obj instanceof String) {
	        return new E("VARBINARY", "BINARY", Types.VARBINARY);
	    }
	    
	    return new E("OTHER", "OTHER", Types.OTHER); // Tipo de datos no reconocido.
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
			SchemaValidationException err = new SchemaValidationException(key, "A " + sqlTypeNameFor(obj).type + " object was provided, but a " + getSQLTypeName(this.type) + " was expected. ");
			if(type == Types.INTEGER) {
				try {
					Integer.parseInt(obj + "");
		            validateType = true;
				} catch(NumberFormatException errr) {
					throw err;
				}
			} else if(type == Types.DATE && sqlTypeNameFor(obj).q == Types.VARCHAR) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            java.util.Date parsedDate;
				try {
					parsedDate = dateFormat.parse(obj + "");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw err;
				}
	            obj = new java.sql.Date(parsedDate.getTime());
	            validateType = true;
			} else throw err;
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
