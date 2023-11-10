package logic;

import entity.Cliente;
import entity.Cuenta;
import entity.Paginator;
import max.Response;

public interface ICuentaLogic {

    boolean verifyLimitAccount(Cuenta data);
    Response<Cuenta> getAll(Paginator paginator);
    Response<Cuenta> getAll();
    Response<Cuenta> getAllFor(Cliente obj);

}
