package data;

import java.sql.SQLException;

import entity.Cliente;
import entity.Cuenta;
import max.data.TransactionResponse;

public interface ICuentaDao {

    TransactionResponse<?> insert(Cuenta data) throws SQLException;

    TransactionResponse<?> delete(Cuenta data) throws SQLException;

    TransactionResponse<?> modify(Cuenta data) throws SQLException;

    TransactionResponse<Cuenta> getAll() throws SQLException;

    TransactionResponse<Cuenta> getById(String Num_Cuenta_CxC) throws SQLException;

    boolean exists(String Num_Cuenta_CxC) throws SQLException;

    TransactionResponse<Cuenta> getAllFor(Cliente obj) throws SQLException;

    int countUserAccounts(String user) throws SQLException;

    String printTDB();
}

