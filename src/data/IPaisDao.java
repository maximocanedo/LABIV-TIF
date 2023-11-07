package data;

import java.sql.SQLException;

import entity.Pais;
import max.data.TransactionResponse;

public interface IPaisDao {

	String printTDB();

	/**
	 * No está previsto el uso de este método.
	 */
	TransactionResponse<?> delete(Pais obj) throws SQLException;

	boolean exists(String arg0) throws SQLException;

	TransactionResponse<Pais> getAll() throws SQLException;

	TransactionResponse<Pais> getById(String id) throws SQLException;

	TransactionResponse<?> insert(Pais p) throws SQLException;

	TransactionResponse<?> modify(Pais p) throws SQLException;

}