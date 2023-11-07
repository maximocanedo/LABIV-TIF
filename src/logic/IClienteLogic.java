package logic;

import java.util.List;

import entity.Cliente;
import filter.ClienteFilter;
import max.Dictionary;
import max.Response;
import max.Schema;
import max.TransactionResponse;
import max.oops.SchemaValidationException;

public interface IClienteLogic {
	public Cliente convert(Dictionary data);
	public List<Cliente> convert(List<Dictionary> data);
	public List<Cliente> convert(List<Dictionary> data, boolean includePrivateData);
	public Response<Cliente> convert(TransactionResponse<Cliente> data);
	public Response<Cliente> convertWildcard(TransactionResponse<?> data);
	public Response<Cliente> createAccount(Dictionary data);
	public Response<Cliente> CUILExists(String cuil);
	public Response<Cliente> delete(Cliente cliente);
	public Response<Cliente> DNIExists(String dni);
	public Response<Cliente> exists(String username);
	public String generatePassword(Cliente cliente);
	public String generateUsername(Cliente cliente);
	public Response<Cliente> getAll();
	public Response<Cliente> getByDNI(String dni);
	public Response<Cliente> getById(String username);
	public Schema getInitialSchema();
	public Schema getSchema();
	public String getTwoRandomChars(String str);
	public Response<Cliente> insert(Cliente cliente);
	public Response<Cliente> isActive(String username);
	public Response<Cliente> login(Dictionary data);
	public Response<Cliente> login(String username, String password);
	public Response<Cliente> modify(Cliente cliente);
	public Response<Cliente> modify(Dictionary data, Cliente cliente);
	public String normalizeString(String str);
	public Response<Cliente> search(ClienteFilter filtro);
	public Response<Cliente> updatePassword(Cliente cliente, Dictionary data);
	public Response<Cliente> validate(Cliente cliente, boolean validateConstraints);
	public boolean validateInitialSchema(Dictionary data) throws SchemaValidationException;
	public Response<Cliente> validatePassword(Cliente cliente, String password);
}
