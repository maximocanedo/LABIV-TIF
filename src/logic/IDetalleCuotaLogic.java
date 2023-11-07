package logic;

import entity.DetalleCuota;
import max.data.Dictionary;
import max.data.Response;

public interface IDetalleCuotaLogic {

    Response<DetalleCuota> validate(DetalleCuota data, boolean validatePKDuplicates);

    Response<DetalleCuota> insert(DetalleCuota data);

    Response<DetalleCuota> modify(DetalleCuota data);

    Response<DetalleCuota> delete(DetalleCuota data);

    Response<DetalleCuota> getAll();

    Response<DetalleCuota> getById(Integer id);

    Response<DetalleCuota> exists(Integer id);

    DetalleCuota convert(Dictionary d);
}
