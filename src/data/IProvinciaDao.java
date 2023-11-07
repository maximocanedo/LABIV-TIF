package data;

import java.sql.SQLException;

import entity.Provincia;
import max.data.TransactionResponse;

public interface IProvinciaDao {

	String printTDB();

	/**
	 * No est� previsto el uso de este m�todo.
	 */
	TransactionResponse<?> delete(Provincia obj) throws SQLException;

	boolean exists(Integer arg0) throws SQLException;

	TransactionResponse<Provincia> getAll() throws SQLException;

	TransactionResponse<Provincia> getById(Integer id) throws SQLException;

	TransactionResponse<?> insert(Provincia p) throws SQLException;

	TransactionResponse<?> modify(Provincia p) throws SQLException;

}