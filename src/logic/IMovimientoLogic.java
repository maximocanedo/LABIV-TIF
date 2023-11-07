package logic;

import java.util.List;

import entity.Cuenta;
import entity.Movimiento;
import max.Dictionary;
import max.Response;

public interface IMovimientoLogic {

	Response<Movimiento> validate(Movimiento obj, boolean validar);

	Response<Movimiento> insert(Movimiento obj);

	Response<Movimiento> modify(Movimiento obj);

	Response<Movimiento> delete(Movimiento obj);

	Response<Movimiento> getAll();

	Response<Movimiento> getById(Integer id);

	Response<Movimiento> filterByAccountNumber(Cuenta c);

	Response<Movimiento> exists(Integer id);

	Movimiento convert(Dictionary row);

	List<Movimiento> convert(List<Dictionary> rows);

}