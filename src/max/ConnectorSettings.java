package max;

/**
 * Clase que representa la configuración de conexión a la base de datos.
 * Define los parámetros de conexión como el host, el usuario, la codificación de caracteres, etc.
 */
public class ConnectorSettings {
	public String host;
	public SQLAccount user;
	public boolean useUnicode;
	public String characterEncoding;
	public boolean useSSL;
	
	/**
     * Configuración predeterminada para la conexión a la base de datos.
     * Esta configuración se utiliza cuando no se proporcionan valores específicos.
     */
	public static ConnectorSettings DEFAULT = new ConnectorSettings() {{
		host = "jdbc:mysql://localhost:3306/";
		user = SQLAccount.ADMIN;
		useUnicode = true;
		characterEncoding = "UTF-8";
		useSSL = false;
	}};
	public static ConnectorSettings SERVIDOR_DE_MAXIMO = new ConnectorSettings() {{
		host = "jdbc:mysql://186.23.64.37:3306/";
		user = SQLAccount.GRUPO3__SERVER_MAXIMO;
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
