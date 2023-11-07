package logic;

import java.util.List;

import entity.SolicitudPrestamo;
import max.data.Dictionary;
import max.data.Response;
import max.data.TransactionResponse;

public interface ISolicitudPrestamoLogic {

	Response<SolicitudPrestamo> validate(SolicitudPrestamo data, boolean validateConstraints);

	/**
	 * Convierte un objeto TransactionResponse con wildcard a un LogicResponse parametrizado.
	 * @param data Objeto a convertir.
	 * @return Objeto convertido.
	 */
	Response<SolicitudPrestamo> convertWildcard(TransactionResponse<?> data);

	Response<SolicitudPrestamo> insert(SolicitudPrestamo arg0);

	Response<SolicitudPrestamo> modify(SolicitudPrestamo data);

	Response<SolicitudPrestamo> delete(SolicitudPrestamo data);

	Response<SolicitudPrestamo> getAll();

	Response<SolicitudPrestamo> getById(String id);

	Response<SolicitudPrestamo> exists(String id);

	SolicitudPrestamo convert(Dictionary d);

	List<SolicitudPrestamo> convert(List<Dictionary> rows);

}