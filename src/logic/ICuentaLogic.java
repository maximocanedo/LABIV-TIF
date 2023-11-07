package logic;

import max.data.Response;

import entity.Cliente;
import entity.Cuenta;

public interface ICuentaLogic {

    Response<Cuenta> verifyLimitAccount(Cuenta data);

    Response<Cuenta> getAllFor(Cliente obj);

}
