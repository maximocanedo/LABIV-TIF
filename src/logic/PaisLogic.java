package logic;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import data.ContinenteDao;
import data.PaisDao;
import entity.*;
import max.data.*;
import max.oops.SchemaValidationException;
import max.schema.Schema;
import max.schema.SchemaProperty;

public class PaisLogic implements IRecordLogic<Pais, String> {
	
	private static PaisDao data = new PaisDao();
	public static final Schema _schema = new Schema(
			new SchemaProperty("code") {{
				required = true;
				primary = true;
				type = Types.VARCHAR;
				maxlength = 2;
			}},
			new SchemaProperty("name") {{
				required = true;
				type = Types.VARCHAR;
				maxlength = 255;
			}},
			new SchemaProperty("full_name") {{
				required = true;
				type = Types.VARCHAR;
				maxlength = 255;
			}},
			new SchemaProperty("iso3") {{
				required = true;
				type = Types.VARCHAR;
				maxlength = 3;
			}},
			new SchemaProperty("number") {{
				required = true;
				type = Types.VARCHAR;
				maxlength = 3;
			}},
		//	new SchemaProperty("demonym") {{
			//	required = true;
		//		type = Types.VARCHAR;
			//	maxlength = 75;
		//	}},
			new SchemaProperty("continent_code") {{
				required = true;
				type = Types.VARCHAR;
				maxlength = 2;
				ref = ContinenteDao._model.ref("code");
			}}
			
		);
	
	public PaisLogic() {
		// TODO Auto-generated constructor stub 
		
	}

	@Override
	public Pais convert(Dictionary data) {
		Pais p = new Pais();
		if(data.$("code") != null) {
			p.setCodigo(data.$("code"));
		} if(data.$("name") != null) {
			p.setNombre(data.$("name"));
		}
		return p;
	}

	@Override
	public List<Pais> convert(List<Dictionary> list) {
		List<Pais> arrP = new ArrayList<Pais>();
		for(Dictionary data : list) {
			arrP.add(convert(data));
		}
		return arrP;
	}

	@Override
	public LogicResponse<Pais> delete(Pais arg0) throws SQLException {
		LogicResponse<Pais> res = new LogicResponse<Pais>();
		TransactionResponse<?> tn = data.delete(arg0);
		if(tn.rowsAffected > 0) {
			res.die(true, "El registro se eliminó con éxito. ");
		} else res.die(false, "Hubo un error al intentar eliminar el registro. ");
		return res;
		
	}

	@Override
	public LogicResponse<Pais> exists(String arg0) {
		LogicResponse<Pais> res = new LogicResponse<Pais>();
		boolean tn = false;
		try {
			tn = data.exists(arg0);
			if(tn) {
				res.die(true, "El registro existe. ");
			} else res.die(false, "No existe un registro con esas características. ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, "Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}

	@Override
	public LogicResponse<Pais> getAll() {
		LogicResponse<Pais> res = new LogicResponse<Pais>();
		TransactionResponse<Pais> tn = new TransactionResponse<Pais>();
		try {
			tn = data.getAll();
			if(tn.nonEmptyResult()) {
				res.fill(tn.rowsReturned);
			} else res.die(false, "Hubo un error al intentar realizar la consulta. ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, "Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}

	@Override
	public LogicResponse<Pais> getById(String arg0) {
		LogicResponse<Pais> res = new LogicResponse<Pais>();
		TransactionResponse<Pais> tn = new TransactionResponse<Pais>();
		try {
			tn = data.getById(arg0);
			if(tn.nonEmptyResult()) {
				res.fill(tn.rowsReturned);
			} else res.die(false, "Hubo un error al intentar realizar la consulta. ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, "Hubo un error al intentar realizar la consulta. ");
		}
		return res;
	}

	@Override
	public LogicResponse<Pais> insert(Pais arg0) throws SQLException {
		LogicResponse<Pais> res = new LogicResponse<Pais>();
		TransactionResponse<?> tn = data.insert(arg0);
		if(tn.rowsAffected > 0) {
			res.die(true, "El registro se insertó con éxito. ");
		} else res.die(false, "Hubo un error al intentar insertar el registro. ");
		return res;
	}

	@Override
	public LogicResponse<Pais> modify(Pais arg0) throws SQLException {
		LogicResponse<Pais> res = new LogicResponse<Pais>();
		TransactionResponse<?> tn = data.modify(arg0);
		if(tn.rowsAffected > 0) {
			res.die(true, "El registro se modificó con éxito. ");
		} else res.die(false, "Hubo un error al intentar modificar el registro. ");
		return res;
	}

	/**
	 * Valida un objeto Pais.
	 * Si "validateConstraints" es falso, se valida usando el método Schema.validate().
	 * Si "validateConstraints" es verdadero, se valida usando el método IModel.validate(), que incluye validaciones en la base de datos.
	 */
	@Override
	public LogicResponse<Pais> validate(Pais arg0, boolean validateConstraints) {
		LogicResponse<Pais> res = new LogicResponse<Pais>();
		try {
			res.status = validateConstraints 
					? PaisDao._model.validate(arg0.toDictionary()) 
					: _schema.validate(arg0.toDictionary());
		} catch (SchemaValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.die(false, e.getMessage());
		}
		return res;
	}
	
	

}
