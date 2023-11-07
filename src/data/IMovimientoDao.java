package data;

import java.sql.SQLException;

import entity.Cuenta;
import entity.Movimiento;
import max.TransactionResponse;

public interface IMovimientoDao {

	String printTDB();

	TransactionResponse<?> insert(Movimiento obj) throws SQLException;

	TransactionResponse<?> delete(Movimiento obj) throws SQLException;

	TransactionResponse<?> modify(Movimiento obj) throws SQLException;

	TransactionResponse<Movimiento> getAll() throws SQLException;

	TransactionResponse<Movimiento> getById(Integer id) throws SQLException;

	TransactionResponse<Movimiento> filterByAccountNumber(Cuenta c) throws SQLException;

	boolean exists(Integer id) throws SQLException;

}