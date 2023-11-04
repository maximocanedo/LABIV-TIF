package entity;

import max.data.Dictionary;
import max.data.IEntity;
import java.sql.Date;

import com.google.gson.JsonObject;

public class DetalleCuotaPrestamo implements IEntity {
	
	private int id;
	private Cliente cliente;
	private Date fechaPago;
	private int numeroCuotaPagada;
	private PrestamosCliente prestamo;
	
	public JsonObject toJsonObject() {
		JsonObject obj = new JsonObject();
		obj.addProperty("id", id);
		obj.add("cliente", cliente.toJsonObject());
		obj.addProperty("fechaPago", fechaPago.toString());
		obj.addProperty("numeroCuotaPagada", numeroCuotaPagada);
		obj.add("prestamo", prestamo.toJsonObject());
		return obj;
	}
	
	public String toJSON() {
		return toJsonObject().toString();
	}
	
	public DetalleCuotaPrestamo() {}

	public DetalleCuotaPrestamo(int id_DTPT, Cliente usuario_cl_DTPT, Date fechaPago_DTPT, Integer numCuotaPagada_DTPT,
			PrestamosCliente cod_Sol_DTPT) {
		this.id = id_DTPT;
		this.cliente = usuario_cl_DTPT;
		this.fechaPago = fechaPago_DTPT;
		this.numeroCuotaPagada = numCuotaPagada_DTPT;
		this.prestamo = cod_Sol_DTPT;
	}
	
	public int getId_DTPT() {
		return id;
	}

	public void setId_DTPT(int id_DTPT) {
		this.id = id_DTPT;
	}

	public Cliente getUsuario_cl_DTPT() {
		return cliente;
	}

	public void setUsuario_cl_DTPT(Cliente usuario_cl_DTPT) {
		this.cliente = usuario_cl_DTPT;
	}

	public Date getFechaPago_DTPT() {
		return fechaPago;
	}

	public void setFechaPago_DTPT(Date fechaPago_DTPT) {
		this.fechaPago = fechaPago_DTPT;
	}

	public int getNumCuotaPagada_DTPT() {
		return numeroCuotaPagada;
	}

	public void setNumCuotaPagada_DTPT(int numCuotaPagada_DTPT) {
		this.numeroCuotaPagada = numCuotaPagada_DTPT;
	}

	public PrestamosCliente getCod_Sol_DTPT() {
		return prestamo;
	}

	public void setCod_Sol_DTPT(PrestamosCliente cod_Sol_DTPT) {
		this.prestamo = cod_Sol_DTPT;
	}

	@Override
	public Dictionary toDictionary() {
		return Dictionary.fromArray(
				"id_DTPT",id,
				"usuario_cl_DTPT",cliente.getUsuario(),
				"fechaPago_DTPT",fechaPago,
				"numCuotaPagada_DTPT",numeroCuotaPagada,
				"cod_Sol_DTPT",prestamo.getSolicitud().getCodigo());
	}

	@Override
	public Dictionary toIdentifiableDictionary() {
		return Dictionary.fromArray("id_DTPT",id);
	}

}