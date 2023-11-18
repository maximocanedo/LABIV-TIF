package logic;

import java.util.List;

import entity.Prestamo;
import max.Dictionary;
import max.Response;
import max.TransactionResponse;

public interface IPrestamoClienteLogic {

	Response<Prestamo> validate(Prestamo data, boolean validatePKDuplicates);

	Response<Prestamo> convertWildcard(TransactionResponse<?> data);

	Response<Prestamo> insert(Prestamo data);

	Response<Prestamo> modify(Prestamo data);

	Response<Prestamo> delete(Prestamo data);

	Response<Prestamo> getAll();

	Prestamo convert(Dictionary d);

	List<Prestamo> convert(List<Dictionary> rows);

	Response<Prestamo> getById(Integer id);

	Response<Prestamo> getById(String usuario_cl_PxC);

	Response<Prestamo> exists(Integer id);

}