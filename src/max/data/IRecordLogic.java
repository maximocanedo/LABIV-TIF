package max.data;

import java.util.List;

public interface IRecordLogic<X, Y> {
	public LogicResponse<X> validate(X data, boolean validatePKDuplicates);
	public LogicResponse<X> insert(X data);
	public LogicResponse<X> modify(X data);
	public LogicResponse<X> delete(X data);
	public LogicResponse<X> getAll();
	public LogicResponse<X> getById(Y id);
	public LogicResponse<X> exists(Y id);
	public X convert(Dictionary row);
	public List<X> convert(List<Dictionary> rows);
}
