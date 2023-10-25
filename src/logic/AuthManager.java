package logic;

import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import max.data.Dictionary;

public class AuthManager {
	
    private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    public static String generateJWT(String username) {
		return Jwts.builder()
				.subject(username)
				.issuer("GRUPO3LAB")
				.issuedAt(new Date())
				.expiration(new Date(new Date().getTime() + EXPIRATION_TIME)) // Expira ocho horas luego de su emisión.
				.signWith(SECRET_KEY)
				.compact();
	}
    public static boolean validateJWT(String jwt) {
    	return false;
    }
    public static Dictionary readJWT(String jwt) {
    	return new Dictionary();
    }
    public static void main(String[] args) {
    	String jwt = generateJWT("root");
    	System.out.println(jwt);
    }
    
}
