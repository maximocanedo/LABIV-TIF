package max.net;

/**
 * Clase que representa la configuraci�n de conexi�n a la base de datos.
 * Define los par�metros de conexi�n como el host, el usuario, la codificaci�n de caracteres, etc.
 */
public class ConnectorSettings {
	public String host;
	public SQLAccount user;
	public boolean useUnicode;
	public String characterEncoding;
	public boolean useSSL;
	
	/**
     * Configuraci�n predeterminada para la conexi�n a la base de datos.
     * Esta configuraci�n se utiliza cuando no se proporcionan valores espec�ficos.
     */
	public static ConnectorSettings DEFAULT = new ConnectorSettings() {{
		host = "jdbc:mysql://localhost:3306/";
		user = SQLAccount.ADMIN;
		useUnicode = true;
		characterEncoding = "UTF-8";
		useSSL = false;
	}};
	
	/**
	 * Construye el URI para conectar a la base de datos.
	 * @return El URI para conectar a la base de datos.
	 */
	public String buildURI(String db) {
		String e = this.host 
				+ db 
				+ "?useUnicode=" 
				+ (this.useUnicode ? "yes" : "no") 
				+ "&characterEncoding=" 
				+ this.characterEncoding 
				+ "&useSSL=" 
				+ (this.useSSL ? "true" : "false");
		return e;
	}
	
	public ConnectorSettings() {
		
	}
}