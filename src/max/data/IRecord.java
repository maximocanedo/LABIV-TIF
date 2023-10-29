package max.data;

import java.sql.SQLException;

// Agregar Exceptions como p.ej. UnformattedDataException, RecordNotExistsException
public interface IRecord<T, Y> {
	public TransactionResponse<?> insert(T data) throws SQLException;
	public TransactionResponse<?> delete(T data) throws SQLException;
	public TransactionResponse<?> modify(T data) throws SQLException;
	public TransactionResponse<T> getAll() throws SQLException;
	public TransactionResponse<T> getById(Y id) throws SQLException;
	public boolean exists(Y id) throws SQLException;
	
}
