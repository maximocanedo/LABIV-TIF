package data;

import java.sql.SQLException;

import entity.Localidad;
import entity.Provincia;
import max.data.TransactionResponse;

public interface ILocalidadDao {

	String printTDB();

	/**
	 * No está previsto el uso de este método.
	 */
	TransactionResponse<?> delete(Localidad obj) throws SQLException;

	boolean exists(Integer arg0) throws SQLException;

	TransactionResponse<Localidad> getAll() throws SQLException;

	TransactionResponse<Localidad> getById(Integer id) throws SQLException;

	TransactionResponse<?> insert(Localidad p) throws SQLException;

	TransactionResponse<?> modify(Localidad p) throws SQLException;

	TransactionResponse<Localidad> filterByProvince(Provincia p) throws SQLException;

}