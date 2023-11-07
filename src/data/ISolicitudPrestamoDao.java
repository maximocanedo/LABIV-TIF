package data;

import java.sql.SQLException;

import entity.SolicitudPrestamo;
import max.TransactionResponse;

public interface ISolicitudPrestamoDao {

	String printTDB();

	TransactionResponse<?> insert(SolicitudPrestamo data) throws SQLException;

	TransactionResponse<?> delete(SolicitudPrestamo data) throws SQLException;

	TransactionResponse<?> modify(SolicitudPrestamo data) throws SQLException;

	TransactionResponse<SolicitudPrestamo> getAll() throws SQLException;

	TransactionResponse<SolicitudPrestamo> getById(String arg0) throws SQLException;

	boolean exists(String codigo) throws SQLException;

}