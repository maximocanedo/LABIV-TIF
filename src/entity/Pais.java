package entity;

import java.sql.Types;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import max.data.Dictionary;
import max.data.IEntity;
import max.schema.*;

public class Pais implements IEntity {
	private String codigo;
	private String nombre;
	private String nombre_completo;
	private String iso3;
	private String numero;
	private Continente continente;
	private String gentilicio;
	
	public String getGentilicio() {
		return gentilicio;
	}
	public void setGentilicio(String gentilicio) {
		this.gentilicio = gentilicio;
	}
	@Expose(serialize = false)
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
		new SchemaProperty("demonym") {{
			required = true;
			type = Types.VARCHAR;
			maxlength = 75;
		}},
		new SchemaProperty("continent_code") {{
			required = true;
			type = Types.VARCHAR;
			maxlength = 2;
			ref = data.ContinenteDao._model.ref("code");
		}}
		
	);
	public Pais() {}
	@Override
	public String toJSON() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre_completo() {
		return nombre_completo;
	}
	public void setNombre_completo(String nombre_completo) {
		this.nombre_completo = nombre_completo;
	}
	public String getIso3() {
		return iso3;
	}
	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	@Override
	public Dictionary toDictionary() {
		return Dictionary.fromArray(
				"code", codigo,
				"name", nombre,
				"full_name", nombre_completo,
				"iso3", iso3,
				"number", numero,
				"continent_code", continente.getCodigo(),
				"demonym", gentilicio
		);
	}
	@Override
	public Dictionary toIdentifiableDictionary() {
		return Dictionary.fromArray(
			"code", codigo
		);
	}
	public Continente getContinente() {
		return continente;
	}
	public void setContinente(Continente continente) {
		this.continente = continente;
	}
	
}
