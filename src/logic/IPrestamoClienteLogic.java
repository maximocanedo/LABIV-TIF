package logic;

import java.util.List;

import entity.PrestamosCliente;
import max.Dictionary;
import max.Response;
import max.TransactionResponse;

public interface IPrestamoClienteLogic {

	Response<PrestamosCliente> validate(PrestamosCliente data, boolean validatePKDuplicates);

	Response<PrestamosCliente> convertWildcard(TransactionResponse<?> data);

	Response<PrestamosCliente> insert(PrestamosCliente data);

	Response<PrestamosCliente> modify(PrestamosCliente data);

	Response<PrestamosCliente> delete(PrestamosCliente data);

	Response<PrestamosCliente> getAll();

	PrestamosCliente convert(Dictionary d);

	List<PrestamosCliente> convert(List<Dictionary> rows);

	Response<PrestamosCliente> getById(Integer id);

	Response<PrestamosCliente> getById(String usuario_cl_PxC);

	Response<PrestamosCliente> exists(Integer id);

}