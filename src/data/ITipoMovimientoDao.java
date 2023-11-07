package data;

import java.sql.SQLException;

import entity.TipoMovimiento;
import max.data.TransactionResponse;

public interface ITipoMovimientoDao {

	String printTDB();

	TransactionResponse<?> insert(TipoMovimiento obj) throws SQLException;

	TransactionResponse<?> delete(TipoMovimiento obj) throws SQLException;

	TransactionResponse<?> modify(TipoMovimiento obj) throws SQLException;

	TransactionResponse<TipoMovimiento> getAll() throws SQLException;

	TransactionResponse<TipoMovimiento> getById(String cod_TPMV) throws SQLException;

	boolean exists(String codTPMV) throws SQLException;

}