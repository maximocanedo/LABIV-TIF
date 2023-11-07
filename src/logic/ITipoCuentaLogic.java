package logic;

import java.util.List;

import entity.TipoCuenta;
import max.Dictionary;
import max.Response;

public interface ITipoCuentaLogic {

	Response<TipoCuenta> validate(TipoCuenta data, boolean validatePKDuplicates);

	Response<TipoCuenta> insert(TipoCuenta data);

	Response<TipoCuenta> modify(TipoCuenta data);

	Response<TipoCuenta> delete(TipoCuenta data);

	Response<TipoCuenta> getAll();

	Response<TipoCuenta> getById(String id);

	Response<TipoCuenta> exists(String id);

	TipoCuenta convert(Dictionary row);

	List<TipoCuenta> convert(List<Dictionary> rows);

}