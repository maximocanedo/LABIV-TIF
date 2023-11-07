package data;

import java.sql.SQLException;

import entity.RequestC01;
import max.data.Dictionary;
import max.data.TransactionResponse;

public interface IRequestC01Dao {

	String printTDB();

	TransactionResponse<?> insert(RequestC01 data) throws SQLException;

	TransactionResponse<?> issue(RequestC01 data) throws SQLException;

	TransactionResponse<?> close(RequestC01 data) throws SQLException;

	TransactionResponse<?> delete(RequestC01 data) throws SQLException;

	TransactionResponse<?> modify(RequestC01 data) throws SQLException;

	TransactionResponse<RequestC01> getAll() throws SQLException;

	TransactionResponse<RequestC01> getById(Integer id) throws SQLException;

	boolean exists(Integer arg0) throws SQLException;

	boolean exists(Dictionary d) throws SQLException;

}