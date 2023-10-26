package max.data;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class LogicResponse<T> {
	@Expose(serialize = true)
	public String message = "";
	@Expose(serialize = true)
	public boolean status;
	
	@Expose(serialize = false)
	public String eField = null;
	
	@Expose(serialize = false)
	public String errorMessage = "";
	
	@Expose(serialize = false)
	public Exception exception = null;

	@Expose(serialize = true)
	public T objectReturned = null;
	@Expose(serialize = true)
	public T[] arrayReturned = null;
	@Expose(serialize = true)
	public List<T> listReturned = null;
	
	@Expose(serialize = false)
	public int http = 200;
	
	
	public String toFinalJSON() {
		Gson gson = new GsonBuilder()
		        .setPrettyPrinting()
		        .excludeFieldsWithoutExposeAnnotation()
		        .create();
		return gson.toJson(this);
	}
	
	public LogicResponse() {}
	public LogicResponse(boolean status, String message) {
		die(status, message);
	}
	public LogicResponse(boolean status, int code, String message) {
		http = code;
		die(status, message);
	}
	public LogicResponse(Exception err) {
		err(err);
	}
	public LogicResponse(T obj) {
		fill(obj);
	}
	public LogicResponse(T[] arr) {
		fill(arr);
	}
	public LogicResponse(List<T> list) {
		fill(list);
	}
	public void die(boolean status, String message) {
		this.status = status;
		this.message = message;
	}
	public void die(boolean status, int code, String message) {
		die(status, message);
		this.http = code;
	}
	public void err(Exception err) {
		this.status = false;
		this.exception = err;
		this.errorMessage = err.getMessage();
		
	}
	public void fill(T object) {
		if(object != null) {
			this.objectReturned = object;
			this.status = true;
		}
	}
	public void fill(T[] arr) {
		if(arr != null) {
			this.arrayReturned = arr;
			this.status = arr.length >= 0;
		}
	}
	public void fill(List<T> list) {
		if(list != null) {
			this.listReturned = list;
			this.status = list.size() >= 0;
		}
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Exception getException() {
		return exception;
	}
	public void setException(Exception exception) {
		this.exception = exception;
	}
	public T getObjectReturned() {
		return objectReturned;
	}
	public void setObjectReturned(T objectReturned) {
		this.objectReturned = objectReturned;
	}
	public T[] getArrayReturned() {
		return arrayReturned;
	}
	public void setArrayReturned(T[] arrayReturned) {
		this.arrayReturned = arrayReturned;
	}
	public List<T> getListReturned() {
		return listReturned;
	}
	public void setListReturned(List<T> listReturned) {
		this.listReturned = listReturned;
	}
}
