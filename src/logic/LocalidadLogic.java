package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.LocalidadDao;
import entity.*;
import max.data.*;
import max.oops.SchemaValidationException;

public class LocalidadLogic implements IRecordLogic<Localidad, Integer> {
	
	private static LocalidadDao data = new LocalidadDao();
	
	public LocalidadLogic() {
		
	}

	@Override
	public Localidad convert(Dictionary data) {
		Localidad p = new Localidad();
		if(data.$("id_loc") != null) {
			p.setId(data.$("id_loc"));
		} if(data.$("nombre_loc") != null) {
			p.setNombre(data.$("nombre_loc"));
		} if(data.$("provincia_loc") != null) {
			p.setProvincia(new Provincia() {{
				setId(data.$("provincia_loc"));
			}});
		}
		return p;
	}

	@Override
	public List<Localidad> convert(List<Dictionary> list) {
		List<Localidad> arrP = new ArrayList<Localidad>();
		for(Dictionary data : list) {
			arrP.add(convert(data));
		}
		return arrP;
	}

	@Override
	public LogicResponse<Localidad> delete(Localidad arg0) throws SQLException {
		LogicResponse<Localidad> res = new LogicResponse<Localidad>();
		TransactionResponse<?> tn = data.delete(arg0);
		if(tn.rowsAffected > 0) {
			res.die(true, "El registro se eliminó con éxito. ");
		} else res.die(false, "Hubo un error al intentar eliminar el registro. ");
		return res;
		
	}

	@Override
	public LogicResponse<Localidad> exists(Integer arg0) {
		LogicResponse<Localidad> res = new LogicResponse<Localidad>();
		boolean tn = false;
		try {
			tn = data.exists(arg0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(tn) {
			res.die(true, "El registro existe. ");
		} else res.die(false, "No existe un registro con esas características. ");
		return res;
	}

	@Override
	public LogicResponse<Localidad> getAll() {
		LogicResponse<Localidad> res = new LogicResponse<Localidad>();
		TransactionResponse<Localidad> tn = new TransactionResponse<Localidad>();
		try {
			tn = data.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(tn.nonEmptyResult()) {
			res.fill(tn.rowsReturned);
		} else res.die(false, "Hubo un error al intentar realizar la consulta. ");
		return res;
	}
	
	public LogicResponse<Localidad> filterByProvince(Provincia p) {
		LogicResponse<Localidad> res = new LogicResponse<Localidad>();
		TransactionResponse<Localidad> tn = new TransactionResponse<Localidad>();
		try {
			tn = data.filterByProvince(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(tn.nonEmptyResult()) {
			res.fill(tn.rowsReturned);
		} else res.die(false, "Hubo un error al intentar realizar la consulta. ");
		return res;
	}

	@Override
	public LogicResponse<Localidad> getById(Integer arg0) {
		LogicResponse<Localidad> res = new LogicResponse<Localidad>();
		TransactionResponse<Localidad> tn = new TransactionResponse<Localidad>();
		try {
			tn = data.getById(arg0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(tn.nonEmptyResult()) {
			res.fill(tn.rowsReturned);
		} else res.die(false, "Hubo un error al intentar realizar la consulta. ");
		return res;
	}

	@Override
	public LogicResponse<Localidad> insert(Localidad arg0) throws SQLException {
		LogicResponse<Localidad> res = new LogicResponse<Localidad>();
		TransactionResponse<?> tn = data.insert(arg0);
		if(tn.rowsAffected > 0) {
			res.die(true, "El registro se insertó con éxito. ");
		} else res.die(false, "Hubo un error al intentar insertar el registro. ");
		return res;
	}

	@Override
	public LogicResponse<Localidad> modify(Localidad arg0) throws SQLException {
		LogicResponse<Localidad> res = new LogicResponse<Localidad>();
		TransactionResponse<?> tn = data.modify(arg0);
		if(tn.rowsAffected > 0) {
			res.die(true, "El registro se modificó con éxito. ");
		} else res.die(false, "Hubo un error al intentar modificar el registro. ");
		return res;
	}

	/**
	 * Valida un objeto Localidad.
	 * Si "validateConstraints" es falso, se valida usando el método Schema.validate().
	 * Si "validateConstraints" es verdadero, se valida usando el método IModel.validate(), que incluye validaciones en la base de datos.
	 */
	@Override
	public LogicResponse<Localidad> validate(Localidad arg0, boolean validateConstraints) {
		LogicResponse<Localidad> res = new LogicResponse<Localidad>();
		try {
			res.status = validateConstraints 
					? Localidad._model.validate(arg0.toDictionary()) 
					: Localidad._schema.validate(arg0.toDictionary());
		} catch (SchemaValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, e.getMessage());
		}
		return res;
	}
	
	

}
