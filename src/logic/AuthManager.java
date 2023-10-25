package logic;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.AdministradorDao;
import entity.Administrador;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import max.data.LogicResponse;
import max.data.TransactionResponse;

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
    	TokenData td = new TokenData();
    	try {
    		Jwt<?, ?> jwt = Jwts.parser()
        			.verifyWith(SECRET_KEY)
        			.build()
        			.parseSignedClaims((CharSequence)data);
        	DefaultClaims claims = (DefaultClaims) jwt.getPayload();
        	td.username = (String) (claims.get("sub"));
        	td.role = (String) (claims.get("role"));
    	} catch (JwtException e) {
    		e.printStackTrace();
    		return null;
    	}
    	return td;
    }
    
    public static void sendToken(HttpServletResponse res, String token) {
    	res.setHeader("Authorization", "Bearer " + token);
    }
    
    private static class Error {
    	public static LogicResponse<Object> InvalidOrCorruptToken = new LogicResponse<Object>() {{
    		status = false;
    		message = "Token is corrupt or invalid. ";
    		http = 401;
    	}};
    	public static LogicResponse<Object> RejectedRole = new LogicResponse<Object>() {{
    		status = false;
    		message = "You don't have access right to this resource. ";
    		http = 403;
    	}};
    	public static LogicResponse<Object> emptyAuthHeader = new LogicResponse<Object>() {{
    		status = false;
    		message = "You must log in to access to this resource. ";
    		http = 401;
    	}};
    	public static LogicResponse<Object> sqlError = new LogicResponse<Object>() {{
    		status = false;
    		message = "An error occured while trying to fetch some data. ";
    		http = 500;
    	}};
    	public static LogicResponse<Object> actualUserDoesNotExistAnymore = new LogicResponse<Object>() {{
    		status = false;
    		message = "Your user may have been deleted or blocked from the database. ";
    		http = 404;
    	}};
    }
    
    public static void send(HttpServletResponse res, LogicResponse<Object> mes) {
    	res.setStatus(mes.http);
    	try {
			res.getWriter().append(mes.toFinalJSON());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return;
    }
    
    public static boolean authenticate(HttpServletRequest req, HttpServletResponse res, String roleRequired) {
    	String authHeader = req.getHeader("Authorization");
    	if(authHeader != null && authHeader.startsWith("Bearer ")) {
    		// Autenticable
    		String token = authHeader.substring(7);
    		TokenData td = readJWT(token);
    		if(td == null) {
    			send(res, Error.InvalidOrCorruptToken);
    			return false;
    		}
    		if(td.role == roleRequired) {
    			return true;
    		} else {
    			send(res, Error.RejectedRole);
    			return false;
    		}
    	} else {
    		send(res, Error.emptyAuthHeader); 
    		return false;
    	}
    }
    
    public static Administrador getActualAdmin(HttpServletRequest req, HttpServletResponse res) {
    	boolean auth = authenticate(req, res, ADMIN);
    	if(auth) {
    		String authHeader = req.getHeader("Authorization");
    		String token = authHeader.substring(7);
    		TokenData td = readJWT(token);
    		AdministradorDao admindao = new AdministradorDao();
    		try {
				TransactionResponse<Administrador> findResult = admindao.getById(td.username);
				if(findResult.nonEmptyResult()) {
					Administrador adm = findResult.rowsReturned.get(0);
					return adm;
				} else {
					send(res, Error.actualUserDoesNotExistAnymore);
					return null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				send(res, Error.sqlError);
				return null;
			}
    	} else return null;
    }
    
    
    
    public static void maina(String[] args) {
    	
    	
    	
    }
    
}
