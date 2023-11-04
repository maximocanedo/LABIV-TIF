package max.data;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class Response extends LogicResponse<IEntity> {
	public String message = null;
	public boolean status;
	public String eField = null;
	public IEntity objectReturned = null;
	public List<IEntity> listReturned = null;
	public Integer http = 200;
	
	public JsonObject toJsonObject() {
		JsonObject obj = new JsonObject();
		if(message != null || message != "")
			obj.addProperty("message", message);
		obj.addProperty("status", status);
		if(objectReturned != null)
			obj.add("objectReturned", objectReturned.toJsonObject());
		if(listReturned != null) {
			JsonArray arr = new JsonArray();
			for(IEntity e : listReturned) {
				JsonObject entityObj = e.toJsonObject();
				arr.add(entityObj);
			}
			obj.add("listReturned", arr);
		}
		return obj;
	}
	
	public String toFinalJSON() {
		return toJsonObject().toString();
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
	public Response(IEntity obj) {
		fill(obj);
	}
	public Response(IEntity[] arr) {
		fill(arr);
	}
	public Response(List<IEntity> list) {
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
	public void fill(IEntity object) {
		if(object != null) {
			this.objectReturned = object;
			this.status = true;
		}
	}
	public void fill(IEntity[] arr) {
		if(arr != null) {
			this.arrayReturned = arr;
			this.status = arr.length >= 0;
		}
	}
	public void fill(List<IEntity> list) {
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
	public IEntity getObjectReturned() {
		return objectReturned;
	}
	public void setObjectReturned(IEntity objectReturned) {
		this.objectReturned = objectReturned;
	}
	public IEntity[] getArrayReturned() {
		return arrayReturned;
	}
	public void setArrayReturned(IEntity[] arrayReturned) {
		this.arrayReturned = arrayReturned;
	}
	public List<IEntity> getListReturned() {
		return listReturned;
	}
	public void setListReturned(List<IEntity> listReturned) {
		this.listReturned = listReturned;
	}
}
