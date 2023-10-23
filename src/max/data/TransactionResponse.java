package max.data;

import java.sql.SQLException;
import java.util.List;

public class TransactionResponse<T> {
	public int rowsAffected = 0;
	public List<T> rowsReturned = null;
	public T[] arrayReturned = null;
	public T objectReturned = null;
	public SQLException dbError = null;
	public Exception error = null;
	public boolean status = false;
	public boolean nonEmptyResult() {
		return rowsReturned != null && rowsReturned.size() > 0;
	}
	
	
	public int getRowsAffected() {
		return rowsAffected;
	}
	public static <U> TransactionResponse<U> create() {
		return new TransactionResponse<U>();
	}
	public void setRowsAffected(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}
	public List<T> getRowsReturned() {
		return rowsReturned;
	}
	public void setRowsReturned(List<T> rowsReturned) {
		this.rowsReturned = rowsReturned;
	}
	public T[] getArrayReturned() {
		return arrayReturned;
	}
	public void setArrayReturned(T[] arrayReturned) {
		this.arrayReturned = arrayReturned;
	}
	public T getObjectReturned() {
		return objectReturned;
	}
	public void setObjectReturned(T objectReturned) {
		this.objectReturned = objectReturned;
	}
	public SQLException getDbError() {
		return dbError;
	}
	public void setDbError(SQLException dbError) {
		this.dbError = dbError;
	}
	public Exception getError() {
		return error;
	}
	public void setError(Exception error) {
		this.error = error;
	}
	public TransactionResponse() {}
}
