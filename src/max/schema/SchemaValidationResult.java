package max.schema;

public class SchemaValidationResult {
	public boolean status;
	public String message;
	public String key;
	public Object transformedValue;
	public SchemaValidationResult() {}
	public SchemaValidationResult(String key, boolean status, String message) {
		this.key = key;
		this.message = message;
		this.status = status;
	}
}
