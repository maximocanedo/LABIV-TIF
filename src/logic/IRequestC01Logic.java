package logic;

import java.util.List;

import entity.Cliente;
import entity.RequestC01;
import max.data.Dictionary;
import max.data.Response;
import max.data.TransactionResponse;

public interface IRequestC01Logic {

	Response<RequestC01> validate(RequestC01 arg0, boolean validateConstraints);

	Response<RequestC01> insert(RequestC01 arg0);

	Response<RequestC01> modify(RequestC01 arg0);

	Response<RequestC01> delete(RequestC01 data);

	Response<RequestC01> getAll();

	Response<RequestC01> getById(Integer id);

	Response<RequestC01> exists(Integer id);

	/**
	 * Convierte un objeto TransactionResponse a un LogicResponse.
	 * @param data Objeto a convertir.
	 * @return Objeto convertido.
	 */
	Response<RequestC01> convert(TransactionResponse<RequestC01> data);

	/**
	 * Convierte un objeto TransactionResponse con wildcard a un LogicResponse parametrizado.
	 * @param data Objeto a convertir.
	 * @return Objeto convertido.
	 */
	Response<RequestC01> convertWildcard(TransactionResponse<?> data);

	RequestC01 convert(Dictionary row);

	List<RequestC01> convert(List<Dictionary> rows);

	Response<RequestC01> issue(Cliente client);

	Response<RequestC01> close(Integer id);

}