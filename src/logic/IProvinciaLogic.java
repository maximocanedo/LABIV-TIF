package logic;

import java.util.List;

import entity.Provincia;
import max.Dictionary;
import max.Response;

public interface IProvinciaLogic {

	/**
	 * Convierte un Dictionary en un objeto Provincia.
	 */
	Provincia convert(Dictionary data);

	/**
	 * Convierte una lista de Dictionary en una lista de provincias.
	 */
	List<Provincia> convert(List<Dictionary> list);

	/**
	 * Elimina un registro.
	 */
	Response<Provincia> delete(Provincia arg0);

	/**
	 * Determina si existe un registro. 
	 */
	Response<Provincia> exists(Integer arg0);

	/**
	 * Obtiene todos los registros en la base de datos.
	 */
	Response<Provincia> getAll();

	/**
	 * Busca un registro por su ID.
	 */
	Response<Provincia> getById(Integer arg0);

	/**
	 * Inserta un registro.
	 */
	Response<Provincia> insert(Provincia arg0);

	/**
	 * Modifica un registro.
	 */
	Response<Provincia> modify(Provincia arg0);

	/**
	 * Valida un objeto Provincia.
	 * Si "validateConstraints" es falso, se valida usando el método Schema.validate().
	 * Si "validateConstraints" es verdadero, se valida usando el método IModel.validate(), que incluye validaciones en la base de datos.
	 */
	Response<Provincia> validate(Provincia arg0, boolean validateConstraints);

}