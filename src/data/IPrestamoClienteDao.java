package data;

import java.sql.SQLException;

import entity.Prestamo;
import max.TransactionResponse;

public interface IPrestamoClienteDao {

	String printTDB();

	TransactionResponse<?> insert(Prestamo data) throws SQLException;

	TransactionResponse<?> delete(Prestamo data) throws SQLException;

	TransactionResponse<?> modify(Prestamo data) throws SQLException;

	TransactionResponse<Prestamo> getAll() throws SQLException;

	TransactionResponse<Prestamo> getById(Integer id) throws SQLException;

	TransactionResponse<Prestamo> getById(String usuario_cl_PxC) throws SQLException;

	boolean exists(Integer id) throws SQLException;

}