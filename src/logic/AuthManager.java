package logic;

import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import max.data.Dictionary;

public class AuthManager {
	
    private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour
    
    public static final String ADMIN = "ADMIN";
    public static final String CLIENT = "CLIENT";
    public static final String NOUSER = "NOUSER";
    
    public static class TokenData {
    	public String username;
    	public String role;
    }

    public static String generateJWT(String username, String role) {
		return Jwts.builder()
				.subject(username)
				.claim("role", role)
				.issuer("GRUPO3LAB")
				.issuedAt(new Date())
				.expiration(new Date(new Date().getTime() + EXPIRATION_TIME)) // Expira ocho horas luego de su emisión.
				.signWith(SECRET_KEY)
				.compact();
	}
    public static TokenData readJWT(String data) {
    	Jwt<?, ?> jwt = Jwts.parser()
    			.verifyWith(SECRET_KEY)
    			.build()
    			.parse((CharSequence)data);
    	DefaultClaims claims = (DefaultClaims) jwt.getPayload();
    	System.out.println(claims.get("sub"));
    	System.out.println(claims.get("role"));
    	
    	return new TokenData() {{
    		username = (String) claims.get("sub");
    		role = (String) claims.get("role");
    	}};
    }
    public static void main(String[] args) {
    	String jwt = generateJWT("root", AuthManager.ADMIN);
    	System.out.println(jwt);
    	TokenData td = readJWT(jwt);
    	System.out.println(td.username + "; " + td.role);
    	
    }
    
}
