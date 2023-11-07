package logic;

import java.util.List;

import entity.Localidad;
import entity.Provincia;
import max.Dictionary;
import max.Response;

public interface ILocalidadLogic {

	/**
	 * Convierte un Dictionary a un objeto Localidad.
	 */
	Localidad convert(Dictionary data);

	/**
	 * Convierte una lista de Dictionary a una lista de localidades.
	 */
	List<Localidad> convert(List<Dictionary> list);

	/**
	 * Elimina un registro.
	 */
	Response<Localidad> delete(Localidad arg0);

	/**
	 * Determina si un registro existe.
	 */
	Response<Localidad> exists(Integer arg0);

	/**
	 * Obtiene todos los registros de la base de datos.
	 */
	Response<Localidad> getAll();

	/**
	 * Obtiene todas las localidades de una provincia.
	 * @param p Objeto Provincia con el ID de la provincia a filtrar.
	 * @return Resultado de la operación.
	 */
	Response<Localidad> filterByProvince(Provincia p);

	/**
	 * Busca una localidad por su ID.
	 */
	Response<Localidad> getById(Integer arg0);

	/**
	 * Inserta un registro.
	 */
	Response<Localidad> insert(Localidad arg0);

	/**
	 * Modifica un registro.
	 */
	Response<Localidad> modify(Localidad arg0);

	/**
	 * Valida un objeto Localidad.
	 * Si "validateConstraints" es falso, se valida usando el método Schema.validate().
	 * Si "validateConstraints" es verdadero, se valida usando el método IModel.validate(), que incluye validaciones en la base de datos.
	 */
	Response<Localidad> validate(Localidad arg0, boolean validateConstraints);

}