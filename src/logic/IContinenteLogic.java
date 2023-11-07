package logic;

import java.util.List;

import entity.Continente;
import max.data.Dictionary;
import max.data.Response;

public interface IContinenteLogic {

    Continente convert(Dictionary data);

    List<Continente> convert(List<Dictionary> list);

    Response<Continente> delete(Continente arg0);

    Response<Continente> exists(String arg0);

    Response<Continente> getAll();

    Response<Continente> getById(String arg0);

    Response<Continente> insert(Continente arg0);

    Response<Continente> modify(Continente arg0);

    Response<Continente> validate(Continente arg0, boolean validateConstraints);
}
