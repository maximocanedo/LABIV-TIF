package data;

import java.sql.SQLException;

import entity.Cliente;
import entity.Cuota;
import max.TransactionResponse;

public interface IDetalleCuotaPrestamoDao {

    TransactionResponse<?> insert(Cuota data) throws SQLException;

    TransactionResponse<?> modify(Cuota data) throws SQLException;

    TransactionResponse<?> delete(Cuota data) throws SQLException;

    TransactionResponse<Cuota> getAll() throws SQLException;

    TransactionResponse<Cuota> getById(Integer id) throws SQLException;

    boolean exists(Integer id) throws SQLException;

    TransactionResponse<Cuota> filterByUserName(Cliente client) throws SQLException;
}