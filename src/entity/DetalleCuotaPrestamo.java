package entity;

import max.data.Dictionary;
import max.data.IEntity;
import java.sql.Date;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

public class DetalleCuotaPrestamo implements IEntity {
	
	@Expose(serialize = true)
	private int id_DTPT;
	@Expose(serialize = true)
	private Cliente usuario_cl_DTPT;
	@Expose(serialize = true)
	private Date fechaPago_DTPT;
	@Expose(serialize = true)
	private int numCuotaPagada_DTPT;
	@Expose(serialize = true)
	private PrestamosCliente cod_Sol_DTPT;
	
	public String toJSON() {
		return new Gson().toJson(this);
	}
	
	public DetalleCuotaPrestamo() {}

	public DetalleCuotaPrestamo(int id_DTPT, Cliente usuario_cl_DTPT, Date fechaPago_DTPT, Integer numCuotaPagada_DTPT,
			PrestamosCliente cod_Sol_DTPT) {
		this.id_DTPT = id_DTPT;
		this.usuario_cl_DTPT = usuario_cl_DTPT;
		this.fechaPago_DTPT = fechaPago_DTPT;
		this.numCuotaPagada_DTPT = numCuotaPagada_DTPT;
		this.cod_Sol_DTPT = cod_Sol_DTPT;
	}
	
	public int getId_DTPT() {
		return id_DTPT;
	}

	public void setId_DTPT(int id_DTPT) {
		this.id_DTPT = id_DTPT;
	}

	public Cliente getUsuario_cl_DTPT() {
		return usuario_cl_DTPT;
	}

	public void setUsuario_cl_DTPT(Cliente usuario_cl_DTPT) {
		this.usuario_cl_DTPT = usuario_cl_DTPT;
	}

	public Date getFechaPago_DTPT() {
		return fechaPago_DTPT;
	}

	public void setFechaPago_DTPT(Date fechaPago_DTPT) {
		this.fechaPago_DTPT = fechaPago_DTPT;
	}

	public int getNumCuotaPagada_DTPT() {
		return numCuotaPagada_DTPT;
	}

	public void setNumCuotaPagada_DTPT(int numCuotaPagada_DTPT) {
		this.numCuotaPagada_DTPT = numCuotaPagada_DTPT;
	}

	public PrestamosCliente getCod_Sol_DTPT() {
		return cod_Sol_DTPT;
	}

	public void setCod_Sol_DTPT(PrestamosCliente cod_Sol_DTPT) {
		this.cod_Sol_DTPT = cod_Sol_DTPT;
	}

	@Override
	public Dictionary toDictionary() {
		return Dictionary.fromArray(
				"id_DTPT",id_DTPT,
				"usuario_cl_DTPT",usuario_cl_DTPT.getUsuario(),
				"fechaPago_DTPT",fechaPago_DTPT,
				"numCuotaPagada_DTPT",numCuotaPagada_DTPT,
				"cod_Sol_DTPT",cod_Sol_DTPT.getSolicitud().getCodigo());
	}

	@Override
	public Dictionary toIdentifiableDictionary() {
		return Dictionary.fromArray("id_DTPT",id_DTPT);
	}

}