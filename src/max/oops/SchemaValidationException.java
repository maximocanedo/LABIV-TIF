package max.oops;

@SuppressWarnings("serial")
public class SchemaValidationException extends Exception {

	public SchemaValidationException(String propertyName, String message) {
		super("Could not validate '" + propertyName + "': " + message);
	}

}
