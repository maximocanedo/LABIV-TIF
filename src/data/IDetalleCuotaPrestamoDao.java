package data;

import java.sql.SQLException;

import entity.Cliente;
import entity.DetalleCuotaPrestamo;
import max.TransactionResponse;

public interface IDetalleCuotaPrestamoDao {

    TransactionResponse<?> insert(DetalleCuotaPrestamo data) throws SQLException;

    TransactionResponse<?> modify(DetalleCuotaPrestamo data) throws SQLException;

    TransactionResponse<?> delete(DetalleCuotaPrestamo data) throws SQLException;

    TransactionResponse<DetalleCuotaPrestamo> getAll() throws SQLException;

    TransactionResponse<DetalleCuotaPrestamo> getById(Integer id) throws SQLException;

    boolean exists(Integer id) throws SQLException;

    TransactionResponse<DetalleCuotaPrestamo> filterByUserName(Cliente client) throws SQLException;
}