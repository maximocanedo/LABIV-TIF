package logic;

import java.util.List;

import entity.Administrador;
import max.Dictionary;
import max.Response;
import max.TransactionResponse;
import max.oops.SchemaValidationException;
import max.schema.Schema;

public interface IAdministradorLogic {
	public Administrador convert(Dictionary data);
	public List<Administrador> convert(List<Dictionary> data);
	public List<Administrador> convert(List<Dictionary> data, boolean convertPrivateFields);
	public Response<Administrador> convert(TransactionResponse<Administrador> daoResponse);
	public Response<Administrador> convertWildcard(TransactionResponse<?> daoResponse);
	public Response<Administrador> createAccount(Dictionary data);
	public Response<Administrador> CUILExists(String cuil);
	public Response<Administrador> delete(Administrador admin);
	public Response<Administrador> DNIExists(String dni);
	public Response<Administrador> exists(String username);
	public Response<Administrador> getAll();
	public Response<Administrador> getById(String username);
	public Schema getInitialSchema();
	public Schema getSchema();
	public Response<Administrador> insert(Administrador admin);
	public Response<Administrador> isActive(String username);
	public boolean isRoot(Administrador admin);
	public Response<Administrador> login(Dictionary data);
	public Response<Administrador> login(String username, String password);
	public Response<Administrador> modify(Administrador admin);
	public Response<Administrador> modify(Dictionary newData, Administrador admin);
	public Response<Administrador> updatePassword(Administrador admin, Dictionary data);
	public Response<Administrador> validate(Administrador admin, boolean validateConstraints);
	public boolean validateInitialSchema(Dictionary data) throws SchemaValidationException;
	public Response<Administrador> validatePassword(Administrador admin, String password);
}
