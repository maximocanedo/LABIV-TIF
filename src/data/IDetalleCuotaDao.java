package data;

import java.sql.SQLException;

import entity.DetalleCuota;
import max.TransactionResponse;

public interface IDetalleCuotaDao {

    TransactionResponse<?> insert(DetalleCuota data) throws SQLException;

    TransactionResponse<?> delete(DetalleCuota data) throws SQLException;

    TransactionResponse<?> modify(DetalleCuota data) throws SQLException;

    TransactionResponse<DetalleCuota> getAll() throws SQLException;

    TransactionResponse<DetalleCuota> getById(Integer id) throws SQLException;

    boolean exists(Integer id) throws SQLException;
}