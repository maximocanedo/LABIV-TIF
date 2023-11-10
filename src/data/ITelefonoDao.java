package data;

import java.sql.SQLException;

import entity.Telefono;
import max.TransactionResponse;

public interface ITelefonoDao {
	String printTDB();

	TransactionResponse<?> insert(Telefono data) throws SQLException;

	TransactionResponse<?> delete(Telefono data) throws SQLException;

	TransactionResponse<?> modify(Telefono data) throws SQLException;

	TransactionResponse<Telefono> getAll() throws SQLException;

	TransactionResponse<Telefono> getById(String Dni_Cl_TxC) throws SQLException;

	boolean exists(String Dni_Cl_TxC) throws SQLException;
}
