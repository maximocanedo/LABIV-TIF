package entity.filter;

import max.Dictionary;

public class ClienteFilter {
	public String q = "";
	public String provinceId = null;
	public String localtyId = null;
	public String sex = null;
	public String countryId = null;
	public Boolean status = null; // False para mostrar usuarios activos e inactivos, True para mostrar sólo activos.
	public Dictionary toDictionary() {
		return Dictionary.fromArray(
			"q", q,
			"provinciaId", provinceId,
			"localidadId", localtyId,
			"sexo", sex,
			"nacionalidadId", countryId,
			"estado", status
		);
	}
}
