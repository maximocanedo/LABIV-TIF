package max;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnector {
	
	/* Métodos */
	public Connection openConnection() throws SQLException;
	// Función Fetch original
	public TransactionResponse<Dictionary> fetch(String query, Object[] parameters) throws SQLException;
	// Funciones Fetch que en algún momento llaman al original.
	public TransactionResponse<Dictionary> fetch(String query, Dictionary parameters) throws SQLException;
	public TransactionResponse<Dictionary> fetch(String query) throws SQLException;
	// Función Transact original
	public TransactionResponse<?> transact(String query, Object[] parameters) throws SQLException;
	// Funciones Transact que en algún momento llaman al original.
	public TransactionResponse<?> transact(String query, Dictionary parameters) throws SQLException;
	public TransactionResponse<?> transact(String query) throws SQLException;
	
}