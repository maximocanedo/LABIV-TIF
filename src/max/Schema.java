package max;

import java.util.HashMap;
import java.util.Map;

import oops.SchemaValidationException;

@SuppressWarnings("serial")
public class Schema extends HashMap<String, SchemaProperty> {
	
	public Schema(SchemaProperty...properties) {
		for(SchemaProperty property : properties) {
			put(property.name, property);
		}
	}
	public void setProperties(SchemaProperty... properties) {
		for(SchemaProperty property : properties) {
			put(property.name, property);
		}
	}
	
	public boolean validate(Dictionary data) throws SchemaValidationException {
	    for (Map.Entry<String, SchemaProperty> entry : entrySet()) {
	        String key = entry.getKey();
	        SchemaProperty sp = entry.getValue();
	        // Ver si existe
	        if (data.$(key) != null) {
	            // El dato existe.
	            if(!sp.validate(data.$(key), key)) return false;	           
	        } else if (sp.required) {
	            // El dato no existe y es requerido.
	        	throw new SchemaValidationException(key, "Missing required property. ");
	        }
	    }
	    // Si no se encontró ningún resultado en 'false', devolvemos un resultado en 'true'.
	    return true;
	}
	

	


	
	
	
	
	
}
