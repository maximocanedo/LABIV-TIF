package logic;

import java.util.ArrayList;
import java.util.List;
import entity.TipoCuenta;
import max.data.Dictionary;
import max.data.IRecordLogic;
import max.data.LogicResponse;

public class TipoCuentaLogic implements IRecordLogic<TipoCuenta,String>{

	public TipoCuentaLogic() {}

	@Override
	public LogicResponse<TipoCuenta> validate(TipoCuenta data, boolean validatePKDuplicates) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<TipoCuenta> insert(TipoCuenta data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<TipoCuenta> modify(TipoCuenta data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<TipoCuenta> delete(TipoCuenta data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<TipoCuenta> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<TipoCuenta> getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<TipoCuenta> exists(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoCuenta convert(Dictionary row) {
		TipoCuenta tipo = new TipoCuenta();
		if(row.$("Cod_TPCT") != null) {
			tipo.setCod_TPCT(row.$("Cod_TPCT"));
		} if(row.$("Descripcion_TPCT") != null) {
			tipo.setDescripcion_TPCT(row.$("Descripcion_TPCT"));
		}
		return tipo;
	}

	@Override
	public List<TipoCuenta> convert(List<Dictionary> rows) {
		List<TipoCuenta> list = new ArrayList<TipoCuenta>();
		for(Dictionary data : rows) {
			list.add(convert(data));
		}
		return list;
	}


}
