package data;

import java.sql.SQLException;

import entity.PrestamosCliente;
import max.data.TransactionResponse;

public interface IPrestamoClienteDao {

	String printTDB();

	TransactionResponse<?> insert(PrestamosCliente data) throws SQLException;

	TransactionResponse<?> delete(PrestamosCliente data) throws SQLException;

	TransactionResponse<?> modify(PrestamosCliente data) throws SQLException;

	TransactionResponse<PrestamosCliente> getAll() throws SQLException;

	TransactionResponse<PrestamosCliente> getById(Integer id) throws SQLException;

	TransactionResponse<PrestamosCliente> getById(String usuario_cl_PxC) throws SQLException;

	boolean exists(Integer id) throws SQLException;

}