package data;

import java.sql.SQLException;

import entity.Cliente;
import entity.Cuenta;
import entity.Movimiento;
import entity.Paginator;
import max.TransactionResponse;

public interface IMovimientoDao {

	String printTDB();

	TransactionResponse<?> insert(Movimiento obj) throws SQLException;

	TransactionResponse<?> delete(Movimiento obj) throws SQLException;

	TransactionResponse<?> modify(Movimiento obj) throws SQLException;

	TransactionResponse<Movimiento> getAll() throws SQLException;
	TransactionResponse<Movimiento> getAll(Paginator paginator) throws SQLException;
	TransactionResponse<Movimiento> getAll(Cliente cliente, Paginator paginator) throws SQLException;
	TransactionResponse<Movimiento> getAll(Cuenta cuenta, Paginator paginator) throws SQLException;

	TransactionResponse<Movimiento> getById(Integer id) throws SQLException;


	boolean exists(Integer id) throws SQLException;

}