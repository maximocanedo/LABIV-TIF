package data;

import java.sql.SQLException;

import entity.TipoCuenta;
import max.TransactionResponse;

public interface ITipoCuentaDao {

	String printTDB();

	TransactionResponse<?> insert(TipoCuenta data) throws SQLException;

	TransactionResponse<?> delete(TipoCuenta data) throws SQLException;

	TransactionResponse<?> modify(TipoCuenta data) throws SQLException;

	TransactionResponse<TipoCuenta> getAll() throws SQLException;

	TransactionResponse<TipoCuenta> getById(String Cod_TPCT) throws SQLException;

	boolean exists(String Cod_TPCT) throws SQLException;

}