package max.net;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import max.Dictionary;
import max.TransactionResponse;
import max.oops.ParameterNotExistsException;

public class Connector implements IConnector {
	
	/**
	 * Clase que contiene constantes para los nombres de las bases de datos.
	 * Proporciona acceso a los nombres de las bases de datos utilizados en la aplicación.
	 */
	public static class DB {
		public static final String bdregistro = "bdregistro";
		public static final String bdPersonas = "bdPersonas";
		public static final String tif = "tif";
	}
	
	public ConnectorSettings settings;
	public String database;
	
	private static String defaultDatabase = DB.tif;
	private static ConnectorSettings defaultSettings = ConnectorSettings.SERVIDOR_DE_MAXIMO;
	public static void setDefaultDatabase(String database) {
		defaultDatabase = database;
	}
	public static void setDefaultSettings(ConnectorSettings data) {
		defaultSettings = data;
	}
	public static String getDefaultDatabase() { return defaultDatabase; }
	public static ConnectorSettings getDefaultSettings() { return defaultSettings; }
	
	public Connector(ConnectorSettings data, String database) {
		this.settings = data;
		this.database = database;
	}
	
	public Connector(ConnectorSettings data) {
		this(data, defaultDatabase);
	}
	
	public Connector(String database) {
		this(defaultSettings, database);
	}
	
	public Connector() {
		this(defaultSettings, defaultDatabase);
	}
	
	/**
	 * Abre una nueva conexión al servidor de base de datos.
	 */
	@Override
	public Connection openConnection() throws SQLException {
		try{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e ) {
			e.printStackTrace();
		}
		Connection connection = null;
        try {
            connection = DriverManager.getConnection(this.settings.buildURI(this.database), this.settings.user.username, this.settings.user.password);
        } catch (SQLException e) {
            throw e;
        }
        return connection;
	}

	/**
	 * Realiza una consulta que devuelve un conjunto de datos.
	 * Está diseñado para ser usado con consultas de tipo SELECT.
	 * @param query La consulta a ser ejecutada.
	 * @param parameters Los parámetros, en caso de usarse.
	 * @returns Objeto TransactionResponse con el resultado de la operación.
	 */
	@Override
	public TransactionResponse<Dictionary> fetch(String query, Object[] parameters) throws SQLException {
		TransactionResponse<Dictionary> finalResult = new TransactionResponse<Dictionary>();
	    Connection cn = null;
	    ResultSet rs = null;
	    List<Dictionary> dataList = new ArrayList<Dictionary>();
	    try {
	        cn = this.openConnection();
	        PreparedStatement ps = cn.prepareStatement(query);
	        
	        if (parameters != null) {
	            for (int i = 0; i < parameters.length; i++) {
	                ps.setObject(i + 1, parameters[i]);
	            }
	        }
	        rs = ps.executeQuery();
	        ResultSetMetaData metaData = rs.getMetaData();
	        int columnCount = metaData.getColumnCount();
	        while (rs.next()) {
	            Dictionary row = new Dictionary();
	            for (int i = 1; i <= columnCount; i++) {
	                String columnName = metaData.getColumnLabel(i);
	                Object columnValue = rs.getObject(i);
	                row.put(columnName, columnValue);
	            }
	            dataList.add(row);
	        }
	        finalResult.rowsReturned = dataList;
	        finalResult.status = true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        finalResult.dbError = e;
	        finalResult.status = false;
	        throw e;
	    } finally {
	        try {
	            if (cn != null) {
	                cn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            finalResult.dbError = e;
	            finalResult.status = false;
	        }
	    }
        return finalResult;
	}
	
	/**
	 * Realiza una consulta que devuelve un conjunto de datos.
	 * Está diseñado para ser usado con consultas de tipo SELECT.
	 * @param query La consulta a ser ejecutada.
	 * @param parameters Los parámetros, en caso de usarse.
	 * @returns Objeto TransactionResponse con el resultado de la operación.
	 */
	@Override
	public TransactionResponse<Dictionary> fetch(String query, Dictionary parameters) throws SQLException {
		try {
			Object[] params = parameters.getParameters(query);
			query = query.replaceAll("@\\w+", "?");
			return fetch(query, params);
		} catch (ParameterNotExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new TransactionResponse<Dictionary>() {{
				error = e;
			}};
		}
	}

	/**
	 * Realiza una consulta que devuelve un conjunto de datos.
	 * Está diseñado para ser usado con consultas de tipo SELECT.
	 * @param query La consulta a ser ejecutada.
	 * @returns Objeto TransactionResponse con el resultado de la operación.
	 */
	@Override
	public TransactionResponse<Dictionary> fetch(String query) throws SQLException {
        Object[] params = new Object[] {};
	    return fetch(query, params);
	}

	/**
	 * Realiza una consulta que no devuelve un conjunto de datos.
	 * Está diseñado para usarse con consultas de tipo INSERT, UPDATE, DELETE.
	 * @param query La consulta a ejecutar.
	 * @param parameters Los parámetros, en caso de usarse.
	 * @return Objeto TransactionResponse con el resultado de la operación.
	 */
	@Override
	public TransactionResponse<?> transact(String query, Object[] parameters) throws SQLException {
	    Connection cn = null;
	    TransactionResponse<?> t = TransactionResponse.create();
	    try {
	        cn = this.openConnection();
	        cn.setAutoCommit(false); // Desactivamos el modo de autocommit para empezar la transacción
	        
	        PreparedStatement ps = cn.prepareStatement(query);
	        
	        if (parameters != null) {
	            for (int i = 0; i < parameters.length; i++) {
	                ps.setObject(i + 1, parameters[i]);
	            }
	        }

	        int rowsAffected = ps.executeUpdate();
	        cn.commit();
	        t.rowsAffected = rowsAffected;
	        t.status = true;
	         // ÉXITO
	    } catch (SQLException e) {
	        if (cn != null) {
	            cn.rollback(); // En caso de excepción, DESHACER CAMBIOS EN EL DB
	        }
	        t.dbError = e;
	        t.status = false;
	        e.printStackTrace();
	        throw e;
	    } finally {
	        try {
	            if (cn != null) {
	                cn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
		        t.status = false;
		        t.dbError = e;
	        }
	    }
	    return t;
	}
	
	/**
	 * Realiza una consulta que no devuelve un conjunto de datos.
	 * Está diseñado para usarse con consultas de tipo INSERT, UPDATE, DELETE.
	 * @param query La consulta a ejecutar.
	 * @param parameters Los parámetros, en caso de usarse.
	 * @return Objeto TransactionResponse con el resultado de la operación.
	 */
	@Override
	public TransactionResponse<?> transact(String query, Dictionary parameters) throws SQLException {
		try {
			Object[] params = parameters.getParameters(query);
			query = query.replaceAll("@\\w+", "?");
			return transact(query, params);
		} catch (ParameterNotExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new TransactionResponse<Dictionary>() {{
				status = false;
				error = e;
			}};
		}
	}
	
	/**
	 * Realiza una consulta que no devuelve un conjunto de datos.
	 * Está diseñado para usarse con consultas de tipo INSERT, UPDATE, DELETE.
	 * @param query La consulta a ejecutar.
	 * @param parameters Los parámetros, en caso de usarse.
	 * @return Objeto TransactionResponse con el resultado de la operación.
	 */
	@Override
	public TransactionResponse<?> transact(String query) throws SQLException {
		Object[] params = new Object[] {};
		return transact(query, params);
	}

}