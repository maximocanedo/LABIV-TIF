package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.*;
import max.data.*;

public class ProvinciaLogic implements IRecordLogic<Provincia, Integer> {
	
	public ProvinciaLogic() {
		// TODO Auto-generated constructor stub 
		
	}

	@Override
	public Provincia convert(Dictionary data) {
		Provincia p = new Provincia();
		if(data.$("id_provincia") != null) {
			p.setId(data.$("id_provincia"));
		} if(data.$("nombre_provincia") != null) {
			p.setNombre(data.$("nombre_provincia"));
		}
		return p;
	}

	@Override
	public List<Provincia> convert(List<Dictionary> list) {
		List<Provincia> arrP = new ArrayList<Provincia>();
		for(Dictionary data : list) {
			arrP.add(convert(data));
		}
		return arrP;
	}

	@Override
	public LogicResponse<Provincia> delete(Provincia arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<Provincia> exists(Integer arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<Provincia> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<Provincia> getById(Integer arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<Provincia> insert(Provincia arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<Provincia> modify(Provincia arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<Provincia> validate(Provincia arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
