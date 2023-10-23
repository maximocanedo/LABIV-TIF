package max.data;

import com.google.gson.Gson;

public interface IEntity {
	public String toString();
	public default String toJSON() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	public default IEntity fromJSON(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, IEntity.class);
	}
	public Dictionary toDictionary();
	public Dictionary toIdentifiableDictionary();
}
