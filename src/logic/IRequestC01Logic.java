package logic;

import java.util.List;

import entity.Cliente;
import entity.SolicitudCambioClave;
import max.Dictionary;
import max.Response;
import max.TransactionResponse;

public interface IRequestC01Logic {

	Response<SolicitudCambioClave> validate(SolicitudCambioClave arg0, boolean validateConstraints);

	Response<SolicitudCambioClave> insert(SolicitudCambioClave arg0);

	Response<SolicitudCambioClave> modify(SolicitudCambioClave arg0);

	Response<SolicitudCambioClave> delete(SolicitudCambioClave data);

	Response<SolicitudCambioClave> getAll();

	Response<SolicitudCambioClave> getById(Integer id);

	Response<SolicitudCambioClave> exists(Integer id);

	/**
	 * Convierte un objeto TransactionResponse a un LogicResponse.
	 * @param data Objeto a convertir.
	 * @return Objeto convertido.
	 */
	Response<SolicitudCambioClave> convert(TransactionResponse<SolicitudCambioClave> data);

	/**
	 * Convierte un objeto TransactionResponse con wildcard a un LogicResponse parametrizado.
	 * @param data Objeto a convertir.
	 * @return Objeto convertido.
	 */
	Response<SolicitudCambioClave> convertWildcard(TransactionResponse<?> data);

	SolicitudCambioClave convert(Dictionary row);

	List<SolicitudCambioClave> convert(List<Dictionary> rows);

	Response<SolicitudCambioClave> issue(Cliente client);

	Response<SolicitudCambioClave> close(Integer id);

}