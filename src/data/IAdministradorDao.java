package data;

import java.sql.SQLException;

import entity.Administrador;
import max.data.Dictionary;
import max.data.TransactionResponse;

public interface IAdministradorDao {
	/**
	 * Elimina un administrador
	 * @param admin Administrador a eliminar. 
	 * @return Resultado de la operación.
	 * @throws SQLException Error SQL.
	 */
	public TransactionResponse<?> delete(Administrador admin) throws SQLException;
	/**
	 * Comprueba si existe un registro con los campos dados.
	 * @param data Dictionary con los datos a buscar.
	 * @return True si existe, False si no existe. 
	 * @throws SQLException Error SQL.
	 */
	public boolean exists(Dictionary data) throws SQLException;
	/**
	 * Comprueba si existe un registro con el nombre de usuario dado.
	 * @param username Nombre de usuario a buscar.
	 * @return True si existe, False si no existe.
	 * @throws SQLException Error SQL.
	 */
	public boolean exists(String username) throws SQLException;
	/**
	 * Obtiene todos los administradores.
	 * @return Resultado de la operación. 
	 * @throws SQLException Error SQL.
	 */
	public TransactionResponse<Administrador> getAll() throws SQLException;
	/**
	 * Busca un administrador por nombre de usuario.
	 * @param username Nombre de usuario a buscar.
	 * @return Resultado de la operación.
	 * @throws SQLException Error SQL.
	 */
	public TransactionResponse<Administrador> getById(String username) throws SQLException;
	/**
	 * Busca un administrador y obtiene todos sus datos, incluídos el hash y el salt, con el nombre de usuario dado.
	 * @param username Nombre de usuario a buscar.
	 * @return Resultado de la operación.
	 * @throws SQLException Error SQL.
	 */
	public TransactionResponse<Administrador> getFullById(String username) throws SQLException;
	/**
	 * Inserta un registro de Administrador en la tabla.
	 * @param admin Objeto Administrador a agregar.
	 * @return Resultado de la transacción. 
	 * @throws SQLException Error SQL.
	 */
	public TransactionResponse<?> insert(Administrador admin) throws SQLException;
	public TransactionResponse<?> modify(Administrador admin) throws SQLException;
	public TransactionResponse<?> updatePassword(String username, byte[] hash, byte[] salt) throws SQLException;
}
