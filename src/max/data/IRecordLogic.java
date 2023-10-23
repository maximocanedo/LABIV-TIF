package max.data;

import java.sql.SQLException;
import java.util.List;

public interface IRecordLogic<X, Y> {
	public LogicResponse<X> validate(X data, boolean validatePKDuplicates);
	public LogicResponse<X> insert(X data) throws SQLException;
	public LogicResponse<X> modify(X data) throws SQLException;
	public LogicResponse<X> delete(X data) throws SQLException;
	public LogicResponse<X> getAll();
	public LogicResponse<X> getById(Y id);
	public LogicResponse<X> exists(Y id);
	public X convert(Dictionary row);
	public List<X> convert(List<Dictionary> rows);
}
