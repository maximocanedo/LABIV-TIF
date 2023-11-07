package data;

import java.sql.SQLException;

import entity.Concepto;
import max.data.TransactionResponse;

public interface IConceptoDao {
	public TransactionResponse<?> delete(Concepto concepto) throws SQLException;
	public boolean exists(String id) throws SQLException;
	public TransactionResponse<Concepto> getAll() throws SQLException;
	public TransactionResponse<Concepto> getById(String id) throws SQLException;
	public TransactionResponse<?> insert(Concepto concepto) throws SQLException;
	public TransactionResponse<?> modify(Concepto concepto) throws SQLException;
}
