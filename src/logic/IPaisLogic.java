package logic;

import java.util.List;

import entity.Pais;
import max.data.Dictionary;
import max.data.Response;

public interface IPaisLogic {

	/**
	 * Convierte un objeto Dictionary a un objeto País.
	 */
	Pais convert(Dictionary data);

	/**
	 * Convierte una lista de Dictionary a una lista País.
	 */
	List<Pais> convert(List<Dictionary> list);

	/**
	 * Elimina un registro.
	 */
	Response<Pais> delete(Pais arg0);

	/**
	 * Determina si existe un registro.
	 */
	Response<Pais> exists(String arg0);

	/**
	 * Obtiene todos los registros de la base de datos.
	 */
	Response<Pais> getAll();

	/**
	 * Busca un registro por su ID.
	 */
	Response<Pais> getById(String arg0);

	/**
	 * Inserta un registro.
	 */
	Response<Pais> insert(Pais arg0);

	/**
	 * Modifica un registro. 
	 */
	Response<Pais> modify(Pais arg0);

	/**
	 * Valida un objeto Pais.
	 * Si "validateConstraints" es falso, se valida usando el método Schema.validate().
	 * Si "validateConstraints" es verdadero, se valida usando el método IModel.validate(), que incluye validaciones en la base de datos.
	 */
	Response<Pais> validate(Pais arg0, boolean validateConstraints);

}