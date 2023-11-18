package logic;

import java.util.List;

import entity.Cliente;
import entity.Cuota;
import max.Dictionary;
import max.Response;

public interface IDetalleCuotaPrestamoLogic {

	Response<Cuota> validate(Cuota obj, boolean validar);

	Response<Cuota> insert(Cuota obj);

	Response<Cuota> modify(Cuota obj);

	Response<Cuota> delete(Cuota obj);

	Response<Cuota> getAll();

	Response<Cuota> getById(Integer id);

	Response<Cuota> filterByUserName(Cliente c);

	Response<Cuota> exists(Integer id);

	Cuota convert(Dictionary row);

	List<Cuota> convert(List<Dictionary> rows);

}