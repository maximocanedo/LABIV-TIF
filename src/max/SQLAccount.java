package max;

public class SQLAccount {
	public String username;
	public String password;
	public SQLAccount(String user, String pass) {
		this.username = user;
		this.password = pass;
	}
	public SQLAccount(String user) {
		this(user, "");
	}
	public static final SQLAccount ROOT__PC_MAXIMO = new SQLAccount("root", "@*k6m%9#q5Cb9Mm!9Ej82HQ!99!Z#&e!zHLp36V3J&hvB%!Q");
	public static final SQLAccount ADMIN = new SQLAccount("admin", "admin");
	public static final SQLAccount GRUPO3__SERVER_MAXIMO = new SQLAccount("grupo3", "klosterrules");
	
}
