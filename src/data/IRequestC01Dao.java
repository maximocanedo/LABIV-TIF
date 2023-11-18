package data;

import java.sql.SQLException;

import entity.SolicitudCambioClave;
import max.Dictionary;
import max.TransactionResponse;

public interface IRequestC01Dao {

	String printTDB();

	TransactionResponse<?> insert(SolicitudCambioClave data) throws SQLException;

	TransactionResponse<?> issue(SolicitudCambioClave data) throws SQLException;

	TransactionResponse<?> close(SolicitudCambioClave data) throws SQLException;

	TransactionResponse<?> delete(SolicitudCambioClave data) throws SQLException;

	TransactionResponse<?> modify(SolicitudCambioClave data) throws SQLException;

	TransactionResponse<SolicitudCambioClave> getAll() throws SQLException;

	TransactionResponse<SolicitudCambioClave> getById(Integer id) throws SQLException;

	boolean exists(Integer arg0) throws SQLException;

	boolean exists(Dictionary d) throws SQLException;

}