package logic;

import java.util.List;

import entity.Cliente;
import entity.DetalleCuotaPrestamo;
import max.Dictionary;
import max.Response;

public interface IDetalleCuotaPrestamoLogic {

	Response<DetalleCuotaPrestamo> validate(DetalleCuotaPrestamo obj, boolean validar);

	Response<DetalleCuotaPrestamo> insert(DetalleCuotaPrestamo obj);

	Response<DetalleCuotaPrestamo> modify(DetalleCuotaPrestamo obj);

	Response<DetalleCuotaPrestamo> delete(DetalleCuotaPrestamo obj);

	Response<DetalleCuotaPrestamo> getAll();

	Response<DetalleCuotaPrestamo> getById(Integer id);

	Response<DetalleCuotaPrestamo> filterByUserName(Cliente c);

	Response<DetalleCuotaPrestamo> exists(Integer id);

	DetalleCuotaPrestamo convert(Dictionary row);

	List<DetalleCuotaPrestamo> convert(List<Dictionary> rows);

}