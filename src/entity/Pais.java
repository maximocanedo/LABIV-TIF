package entity;

import com.google.gson.JsonObject;

import max.Dictionary;
import max.IEntity;

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
	
	public Pais() {}
	public JsonObject toJsonObject() {
		JsonObject obj = new JsonObject();
		obj.addProperty("codigo", codigo);
		obj.addProperty("nombre", nombre);
		obj.addProperty("nombre_completo", nombre_completo);
		obj.addProperty("iso3", iso3);
		obj.addProperty("numero", numero);
		obj.add("continente", continente == null ? null : continente.toJsonObject());
		obj.addProperty("gentilicio", gentilicio);
		return obj;
	}
	@Override
	public String toJSON() {
		return toJsonObject().toString();
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
				"continent_code", continente.getCodigo()//,
				//"demonym", gentilicio
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
