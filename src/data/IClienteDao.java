package data;

import java.sql.SQLException;

import entity.Cliente;
import filter.ClienteFilter;
import max.Dictionary;
import max.TransactionResponse;

public interface IClienteDao {
	public TransactionResponse<?> delete(Cliente cliente) throws SQLException;
	public boolean exists(Dictionary data) throws SQLException;
	public boolean exists(String username) throws SQLException;
	public TransactionResponse<Cliente> getAll() throws SQLException;
	public TransactionResponse<Cliente> getByDNI(String dni) throws SQLException;
	public TransactionResponse<Cliente> getById(String username) throws SQLException;
	public TransactionResponse<Cliente> getFullById(String username) throws SQLException;
	public TransactionResponse<?> insert(Cliente cliente) throws SQLException;
	public TransactionResponse<?> modify(Cliente cliente) throws SQLException;
	public TransactionResponse<Cliente> search(ClienteFilter filters) throws SQLException;
	public TransactionResponse<?> updatePassword(String username, byte[] hash, byte[] salt) throws SQLException;
}
