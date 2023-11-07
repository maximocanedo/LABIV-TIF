package max;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public interface IEntity {
	public String toString();
	public default String toJSON() {
		return toJsonObject().toString();
	}
	public JsonObject toJsonObject();
	public default IEntity fromJSON(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, IEntity.class);
	}
	public Dictionary toDictionary();
	public Dictionary toIdentifiableDictionary();
}
