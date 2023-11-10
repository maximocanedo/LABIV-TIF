package logic;

import entity.Cliente;
import entity.Cuenta;
import max.Response;

public interface ICuentaLogic {

    boolean verifyLimitAccount(Cuenta data);

    Response<Cuenta> getAllFor(Cliente obj);

}
