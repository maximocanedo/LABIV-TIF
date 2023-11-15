package logicImpl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataImpl.MovimientoDaoImpl;
import entity.Cliente;
import entity.Concepto;
import entity.Cuenta;
import entity.Movimiento;
import entity.Paginator;
import entity.TipoMovimiento;
import logic.IMovimientoLogic;
import max.Dictionary;
import max.IRecordLogic;
import max.Response;
import max.TransactionResponse;
import oops.SchemaValidationException;

public class MovimientoLogicImpl implements IRecordLogic<Movimiento,Integer>, IMovimientoLogic {
	
	private static MovimientoDaoImpl daoMov = new MovimientoDaoImpl();
	
	public MovimientoLogicImpl() {}
	
	/* (non-Javadoc)
	 * @see logicImpl.IMovimientoLogic#validate(entity.Movimiento, boolean)
	 */
	@Override
	public Response<Movimiento> validate(Movimiento obj, boolean validar) {
		
		Response<Movimiento> lg = new Response<>();
		
		try {
				lg.status = validar
						? MovimientoDaoImpl._model.validate(obj.toDictionary())
						: MovimientoDaoImpl._schema.validate(obj.toDictionary());
		}catch(SchemaValidationException e) {
			e.printStackTrace();
			lg.die(false,e.getMessage());
		}
		
		return lg;
	}

	/* (non-Javadoc)
	 * @see logicImpl.IMovimientoLogic#insert(entity.Movimiento)
	 */
	@Override
	public Response<Movimiento> insert(Movimiento obj) {
		
		Response<Movimiento> lg = new Response<>();
		
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

	/* (non-Javadoc)
	 * @see logicImpl.IMovimientoLogic#modify(entity.Movimiento)
	 */
	@Override
	public Response<Movimiento> modify(Movimiento obj) {
		
		Response<Movimiento> lg = new Response<>();
		
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

	/* (non-Javadoc)
	 * @see logicImpl.IMovimientoLogic#delete(entity.Movimiento)
	 */
	@Override
	public Response<Movimiento> delete(Movimiento obj) {
		
		Response<Movimiento> lg = new Response<>();
		
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

	/* (non-Javadoc)
	 * @see logicImpl.IMovimientoLogic#getAll()
	 */
	@Override
	public Response<Movimiento> getAll() {
		return getAll(Paginator.DEFAULT);
	}

	/* (non-Javadoc)
	 * @see logicImpl.IMovimientoLogic#getById(java.lang.Integer)
	 */
	@Override
	public Response<Movimiento> getById(Integer id) {
		
		Response<Movimiento> lg = new Response<>();
		
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
	

	/* (non-Javadoc)
	 * @see logicImpl.IMovimientoLogic#exists(java.lang.Integer)
	 */
	@Override
	public Response<Movimiento> exists(Integer id) {
		
		Response<Movimiento> lg = new Response<>();
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

	/* (non-Javadoc)
	 * @see logicImpl.IMovimientoLogic#convert(max.data.Dictionary)
	 */
	@Override
	public Movimiento convert(Dictionary row) {
		
		Movimiento obj = new Movimiento();
		BigDecimal num;
		
		if(row.$("id_Mv") != null) obj.setId_Mv(row.$("id_Mv"));
		if(row.$("num_cuenta_CxC_Mv") != null) {
			obj.setNum_cuenta_CxC_Mv(
				(new CuentaLogicImpl()).convert(row)
			);
		}
		if(row.$("cod_Con_Mv") != null) {
			obj.setCod_Con_Mv(
					(new ConceptoLogicImpl()).convert(row)
					);
		}
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
		if(row.$("cod_TPMV_Mv") != null) {
			obj.setCod_TPMV_Mv(
					(new TipoMovimientoLogicImpl()).convert(row)
			);
		}
		
		 
		
		return obj; 
	}

	/* (non-Javadoc)
	 * @see logicImpl.IMovimientoLogic#convert(java.util.List)
	 */
	@Override
	public List<Movimiento> convert(List<Dictionary> rows) {
		
		List<Movimiento> ls = new ArrayList<>();
		
		for(Dictionary d : rows) {
			ls.add(convert(d));
		}		
		
		return ls;
	}

	@Override
	public Response<Movimiento> getAll(Paginator paginator) {
		Response<Movimiento> lg = new Response<>();
		try {
			TransactionResponse<Movimiento> t = daoMov.getAll(paginator);
			if(t.nonEmptyResult()) {
				lg.fill(t.rowsReturned);
			} else {
				lg.die(false, "Error al intentar obtener todos los registros");
			}
		}catch(SQLException e) {
			e.printStackTrace();
			lg.die(false, "Error al intentar obtener todos los registros");
		}		
		
		return lg;
	}

	@Override
	public Response<Movimiento> getAll(Cliente cliente, Paginator paginator) {
		Response<Movimiento> lg = new Response<>();
		try {
			TransactionResponse<Movimiento> t = daoMov.getAll(cliente, paginator);
			if(t.nonEmptyResult()) {
				lg.fill(t.rowsReturned);
			} else {
				lg.die(false, "Error al intentar obtener todos los registros");
			}
		}catch(SQLException e) {
			e.printStackTrace();
			lg.die(false, "Error al intentar obtener todos los registros");
		}		
		
		return lg;
	}

	@Override
	public Response<Movimiento> getAll(Cuenta cuenta, Paginator paginator) {
		Response<Movimiento> lg = new Response<>();
		try {
			TransactionResponse<Movimiento> t = daoMov.getAll(cuenta, paginator);
			if(t.nonEmptyResult()) {
				lg.fill(t.rowsReturned);
			} else {
				lg.die(false, "Error al intentar obtener todos los registros");
			}
		}catch(SQLException e) {
			e.printStackTrace();
			lg.die(false, "Error al intentar obtener todos los registros");
		}		
		
		return lg;
	}

	public ArrayList<Integer> getInforme(){
		ArrayList<Integer> listaCantidad = new ArrayList<>();
		try {
			TransactionResponse<Dictionary> data = daoMov.getInformeMovimientos();
			if(data.nonEmptyResult()) {
				long casteado;		
				//1
				if(data.rowsReturned.get(0).$("Cantidad") != null) {
				casteado = data.rowsReturned.get(0).$("Cantidad");
				listaCantidad.add((int)casteado);
				} else {listaCantidad.add(0); }
				//2
				if(data.rowsReturned.size()>1) {
				if(data.rowsReturned.get(1).$("Cantidad") != null) {
					casteado = data.rowsReturned.get(1).$("Cantidad");
					listaCantidad.add((int)casteado);
					} else {listaCantidad.add(0); }
				}else {listaCantidad.add(0);}
				//3
				if(data.rowsReturned.size()>2) {
				if(data.rowsReturned.get(2).$("Cantidad") != null) {
					casteado = data.rowsReturned.get(2).$("Cantidad");
					listaCantidad.add((int)casteado);
					} else {listaCantidad.add(0); }
				} else {listaCantidad.add(0);}
				//4
				if(data.rowsReturned.size()>3) {
				if(data.rowsReturned.get(3).$("Cantidad") != null) {
					casteado = data.rowsReturned.get(3).$("Cantidad");
					listaCantidad.add((int)casteado);
					} else {listaCantidad.add(0); }
				} else {listaCantidad.add(0);}
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return listaCantidad;	
	}
}
