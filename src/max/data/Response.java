package max.data;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Response<T> {
	public String message = null;
	public boolean status;
	
	public String eField = null;
	
	public String errorMessage = null;
	
	public Exception exception = null;

	public T objectReturned = null;
	
	public T[] arrayReturned = null;
	
	public List<T> listReturned = null;
	
	public Integer http = 200;
	
	public void clean(boolean clearMessages) {
		this.http = null;
		this.exception = null;
		this.errorMessage = null;
		this.eField = null;
		if(clearMessages) message = null;
	}
	public void clean() {
		clean(false);
	}
	
	
	public JsonObject toFinalJSONObj() {
		JsonObject obj = new JsonObject();
		if(message != null || message != "")
			obj.addProperty("message", message);
		obj.addProperty("status", status);
		if(objectReturned != null)
			obj.add("objectReturned", ((IEntity) objectReturned).toJsonObject());
		if(listReturned != null) {
			JsonArray arr = new JsonArray();
			for(T e : listReturned) {
				JsonObject entityObj = ((IEntity)e).toJsonObject();
				arr.add(entityObj);
			}
			obj.add("listReturned", arr);
		}
		return obj;
	}
	public String toFinalJSON() {
		return toFinalJSONObj().toString();
	}
	public Response() {}
	public Response(boolean status, String message) {
		die(status, message);
	}
	public Response(boolean status, int code, String message) {
		http = code;
		die(status, message);
	}
	public Response(Exception err) {
		err(err);
	}
	public Response(T obj) {
		fill(obj);
	}
	public Response(T[] arr) {
		fill(arr);
	}
	public Response(List<T> list) {
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
