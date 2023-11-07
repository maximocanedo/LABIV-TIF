package data;

import java.sql.SQLException;

import entity.Continente;
import max.TransactionResponse;

public interface IContinenteDao {
	public TransactionResponse<?> delete(Continente continente) throws SQLException;
	public boolean exists(String id) throws SQLException;
	public TransactionResponse<Continente> getAll() throws SQLException;
	public TransactionResponse<Continente> getById(String id) throws SQLException;
	public TransactionResponse<?> insert(Continente continente) throws SQLException;
	public TransactionResponse<?> modify(Continente continente) throws SQLException;
}
