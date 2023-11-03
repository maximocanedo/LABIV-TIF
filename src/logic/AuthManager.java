package logic;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import data.AdministradorDao;
import data.ClienteDao;
import entity.Administrador;
import entity.Cliente;
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
        			.parse((CharSequence)data);
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
    	res.setHeader("Authorization", token);
    }
    
    protected static class Error {
    	public static LogicResponse<Administrador> InvalidOrCorruptToken = 
    			new LogicResponse<Administrador>(false, 401, "Token is corrupt or invalid. ");
    	public static LogicResponse<Administrador> RejectedRole = 
    			new LogicResponse<Administrador>(false, 403, "You don't have access right to this resource. ");
    	public static LogicResponse<Administrador> emptyAuthHeader = 
    			new LogicResponse<Administrador>(false, 401, "You must log in to access to this resource. ");
    	public static LogicResponse<Administrador> sqlError = 
    			new LogicResponse<Administrador>(false, 500, "An error occured while trying to fetch some data. ");
    	public static LogicResponse<Administrador> actualUserDoesNotExistAnymore = 
    			new LogicResponse<Administrador>(false, 403, "Your user may have been deleted or blocked from the database. ");
    
    }
    
    public static void send(HttpServletResponse res, LogicResponse<Administrador> mes) {
    	res.setStatus(mes.http == null ? 200 : mes.http);
    	try {
    		String jsn = mes.toFinalJSON();
			res.getWriter().append(jsn);
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
    		if(td.role.equalsIgnoreCase(roleRequired)) {
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
    
    public static TokenData readToken(HttpServletRequest request) {
    	String authHeader = request.getHeader("Authorization");
    	if(authHeader != null && authHeader.startsWith("Bearer ")) {
    		String token = authHeader.substring(7);
    		TokenData td = readJWT(token);
    		return td;
    	}
    	return null;
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
					if(!adm.isEstado()) {
						send(res, Error.actualUserDoesNotExistAnymore);
						return null;
					}
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
    
    public static Cliente getActualClient(HttpServletRequest req, HttpServletResponse res) {
    	boolean auth = authenticate(req, res, CLIENT);
    	if(auth) {
    		String authHeader = req.getHeader("Authorization");
    		String token = authHeader.substring(7);
    		TokenData td = readJWT(token);
    		ClienteDao admindao = new ClienteDao();
    		try {
				TransactionResponse<Cliente> findResult = admindao.getById(td.username);
				if(findResult.nonEmptyResult()) {
					Cliente adm = findResult.rowsReturned.get(0);
					if(!adm.isEstado()) {
						send(res, Error.actualUserDoesNotExistAnymore);
						return null;
					}
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
    
    
    
    public static void maine(String[] args) {
    	String js = Error.InvalidOrCorruptToken.toFinalJSON();
    	System.out.println(js);
    	
    	
    	
    }
    
}
