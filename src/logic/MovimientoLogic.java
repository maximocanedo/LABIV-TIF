package logic;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.MovimientoDao;
import entity.Concepto;
import entity.Cuenta;
import entity.Movimiento;
import entity.TipoMovimiento;
import max.data.*;
import max.oops.SchemaValidationException;

public class MovimientoLogic implements IRecordLogic<Movimiento,Integer> {
	
	private static MovimientoDao daoMov = new MovimientoDao();
	
	public MovimientoLogic() {}
	
	@Override
	public LogicResponse<Movimiento> validate(Movimiento obj, boolean validar) {
		
		LogicResponse<Movimiento> lg = new LogicResponse<>();
		
		try {
				lg.status = validar
						? MovimientoDao._model.validate(obj.toDictionary())
						: MovimientoDao._schema.validate(obj.toDictionary());
		}catch(SchemaValidationException e) {
			e.printStackTrace();
			lg.die(false,e.getMessage());
		}
		
		return lg;
	}

	@Override
	public LogicResponse<Movimiento> insert(Movimiento obj) {
		
		LogicResponse<Movimiento> lg = new LogicResponse<>();
		
		try {
				TransactionResponse<?> t = daoMov.insert(obj);
				if(t.rowsAffected > 0) {
					lg.die(true, 201, "El registro ha sido ingresado con éxito");
				}else {
					lg.die(false, 500, "Error al intentar ingresar el registro");
				}
		}catch(SQLException e) {
			e.printStackTrace();
			lg.die(false, 500, "Error al intentar ingresar el registro");
		}		
		
		return lg;
	}

	@Override
	public LogicResponse<Movimiento> modify(Movimiento obj) {
		
		LogicResponse<Movimiento> lg = new LogicResponse<>();
		
		try {
				TransactionResponse<?> t = daoMov.modify(obj);
				if(t.rowsAffected > 0) {
					lg.die(true, 200, "El registro ha sido modificado con éxito");
				}else {
					lg.die(false, 500, "Error al intentar modificar el registro");
				}				
		}catch(SQLException e) {
			e.printStackTrace();
			lg.die(false, 500, "Error al intentar modificar el registro");
		}		
		
		return lg;
	}

	@Override
	public LogicResponse<Movimiento> delete(Movimiento obj) {
		
		LogicResponse<Movimiento> lg = new LogicResponse<>();
		
		try {
				TransactionResponse<?> t = daoMov.delete(obj);
				if(t.rowsAffected > 0) {
					lg.die(true, 200, "El registro ha sido borrado con éxito");
				}else {
					lg.die(false, 500, "Error al intentar borrar el registro");
				}
		}catch(SQLException e) {
			e.printStackTrace();
			lg.die(false, 500, "Error al intentar borrar el registro");
		}
		
		return lg;
	}

	@Override
	public LogicResponse<Movimiento> getAll() {
		
		LogicResponse<Movimiento> lg = new LogicResponse<>();
		
		try {
				TransactionResponse<Movimiento> t = daoMov.getAll();
				if(t.nonEmptyResult()) {
					lg.fill(t.rowsReturned);
				}else {
					lg.die(false, "Error al intentar obtener todos los registros");
				}
		}catch(SQLException e) {
			e.printStackTrace();
			lg.die(false, "Error al intentar obtener todos los registros");
		}		
		
		return lg;
	}

	@Override
	public LogicResponse<Movimiento> getById(Integer id) {
		
		LogicResponse<Movimiento> lg = new LogicResponse<>();
		
		try {
				TransactionResponse<Movimiento> t = daoMov.getById(id);
				if(t.nonEmptyResult()) {
					lg.fill(t.rowsReturned);
				}else {
					lg.die(false, "Error al intentar obtener registro por id");
				}
		}catch(SQLException e) {
			e.printStackTrace();
			lg.die(false, "Error al intentar obtener registro por id");
		}		
		
		return lg;
	}
	
	public LogicResponse<Movimiento> filterByAccountNumber(Cuenta c){
		
		LogicResponse<Movimiento> lg = new LogicResponse<>();
		
		try {
				TransactionResponse<Movimiento> t = daoMov.filterByAccountNumber(c);
				if(t.nonEmptyResult()) {
					lg.fill(t.rowsReturned);
					lg.die(true, "Los registros por número de cuenta se han obtenido exitosamente");
				}else {
					lg.die(false, "Error al intentar obtener los registros por número de cuenta");
				}
		}catch(SQLException e) {
			e.printStackTrace();
			lg.die(false, "Error al intentar obtener los registros por número de cuenta");
		}
		
		return lg;
	}

	@Override
	public LogicResponse<Movimiento> exists(Integer id) {
		
		LogicResponse<Movimiento> lg = new LogicResponse<>();
		boolean estado = false;
		
		try {
				estado = daoMov.exists(id);
				if(estado) {
					lg.die(true, "El registro ya existe");
				}else {
					lg.die(false, "El registro buscado no existe");
				}
		}catch(SQLException e) {
			e.printStackTrace();
			lg.die(false, "El registro buscado no existe");
		}		
		
		return lg;
	}

	@Override
	public Movimiento convert(Dictionary row) {
		
		Movimiento obj = new Movimiento();
		BigDecimal num;
		
		if(row.$("id_Mv") != null) obj.setId_Mv(row.$("id_Mv"));
		if(row.$("num_cuenta_CxC_Mv") != null) obj.setNum_cuenta_CxC_Mv(new Cuenta() {{
			this.setNum_Cuenta_CxC(row.$("num_cuenta_CxC_Mv"));
		}});				
		if(row.$("cod_Con_Mv") != null) obj.setCod_Con_Mv(new Concepto() {{
			this.setCod_Con(row.$("cod_Con_Mv"));		
		}});				
		if(row.$("saldo_anterior_Mv") != null) {
			num = row.$("saldo_anterior_Mv");
			obj.setSaldo_anterior_Mv(num.doubleValue());
		}
		if(row.$("importe_Mv") != null) {
			num = row.$("importe_Mv");
			obj.setImporte_Mv(num.doubleValue());
		}
		if(row.$("saldo_posterior_Mv") != null) {
			num = row.$("saldo_posterior_Mv");
			obj.setSaldo_posterior_Mv(num.doubleValue());
		}
		if(row.$("fechaMov_Mv") != null) obj.setFechaMov_Mv(row.$("fechaMov_Mv"));
		if(row.$("cod_TPMV_Mv") != null) obj.setCod_TPMV_Mv(new TipoMovimiento() {{
			this.setCod_TPMV(row.$("cod_TPMV_Mv"));		
		}});
		
		 
		
		return obj; 
	}

	@Override
	public List<Movimiento> convert(List<Dictionary> rows) {
		
		List<Movimiento> ls = new ArrayList<>();
		
		for(Dictionary d : rows) {
			ls.add(convert(d));
		}		
		
		return ls;
	}

}
