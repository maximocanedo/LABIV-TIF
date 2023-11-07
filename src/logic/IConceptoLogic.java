package logic;

import java.util.List;

import entity.Concepto;
import max.Dictionary;
import max.Response;

public interface IConceptoLogic {
	public Concepto convert(Dictionary data);
	public List<Concepto> convert(List<Dictionary> list);
	public Response<Concepto> delete(Concepto concepto);
	public Response<Concepto> exists(String id);
	public Response<Concepto> getAll();
	public Response<Concepto> getById(String id);
	public Response<Concepto> insert(Concepto concepto);
	public Response<Concepto> modify(Concepto concepto);
	public Response<Concepto> validate(Concepto concepto, boolean validateConstraints);
}
