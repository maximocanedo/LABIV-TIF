package max;

import java.util.List;

public interface IRecordLogic<X, Y> {
	public Response<X> validate(X data, boolean validatePKDuplicates);
	public Response<X> insert(X data);
	public Response<X> modify(X data);
	public Response<X> delete(X data);
	public Response<X> getAll();
	public Response<X> getById(Y id);
	public Response<X> exists(Y id);
	public X convert(Dictionary row);
	public List<X> convert(List<Dictionary> rows);
}
