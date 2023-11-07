package max.schema;

import java.sql.SQLException;

import max.Dictionary;
import max.TransactionResponse;
import max.oops.SchemaValidationException;

public interface IModel {
	
	/**
	 * Crea un registro en la base de datos.
	 * @param data Los datos a a�adir
	 * @return Objeto TransactionResponse con el resultado de la operaci�n.
	 */
	public TransactionResponse<?> create(Dictionary... data) throws SQLException, SchemaValidationException;
	public TransactionResponse<?> create(Dictionary data) throws SQLException, SchemaValidationException;

	
	/**
	 * Modifica un registro en la base de datos.
	 * @param newValues Los valores a escribir.
	 * @param select Los elementos a modificar.
	 * @return Objeto TransactionResponse con el resultado de la operaci�n.
	 */
	public TransactionResponse<?> modify(Dictionary newValues, Dictionary select) throws SQLException, SchemaValidationException;

	/**
	 * Elimina un registro en la base de datos.
	 * @param select Los elementos a eliminar.
	 * @return Objeto TransactionResponse con el resultado de la operaci�n.
	 */
	public TransactionResponse<?> delete(Dictionary select) throws SQLException, SchemaValidationException;

	/**
	 * Devuelve el nombre de la tabla.
	 */
	public String getTableName();
	
	/**
	 * Devuelve el nombre de la base de datos.
	 */
	public String getDatabaseName();
	
	/**
	 * Cuenta los registros que cumplan con la condici�n.
	 * @param select Elementos a seleccionar
	 * @return Cantidad de registros que cumplen con la condici�n.
	 */
	public int count(Dictionary select);
	
	/**
	 * Verifica si existe un registro en base a la condici�n dada.
	 * @param select Elementos a comprobar / Condici�n a validar.
	 * @return true, si existe al menos una ocurrencia. False, en caso contrario.
	 */
	public boolean exists(Dictionary select);
	
	/**
	 * Devuelve un objeto ReferenceInfo 
	 * @param propertyName
	 * @return
	 */
	public ReferenceInfo ref(String propertyName);
	
	public boolean validate(Dictionary data) throws SchemaValidationException;
	
	public void compile();
	
	
}
