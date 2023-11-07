package logic;

import java.util.List;

import entity.TipoMovimiento;
import max.Dictionary;
import max.Response;

public interface ITipoMovimientoLogic {

	Response<TipoMovimiento> validate(TipoMovimiento obj, boolean validar);

	Response<TipoMovimiento> insert(TipoMovimiento obj);

	Response<TipoMovimiento> modify(TipoMovimiento obj);

	Response<TipoMovimiento> delete(TipoMovimiento obj);

	Response<TipoMovimiento> getAll();

	Response<TipoMovimiento> getById(String cod_TPMV);

	Response<TipoMovimiento> exists(String cod_TPMV);

	TipoMovimiento convert(Dictionary row);

	List<TipoMovimiento> convert(List<Dictionary> rows);

}