package logic;

import java.util.List;

import entity.Telefono;
import max.Dictionary;
import max.Response;

public interface ITelefonoLogic {
	Response<Telefono> validate(Telefono data, boolean validatePKDuplicates);

	Response<Telefono> insert(Telefono data);

	Response<Telefono> modify(Telefono data);

	Response<Telefono> delete(Telefono data);

	Response<Telefono> getAll();

	Response<Telefono> getById(String id);

	Response<Telefono> exists(String id);

	Telefono convert(Dictionary row);

	List<Telefono> convert(List<Dictionary> rows);

}
