package entity;

import com.google.gson.JsonObject;

public class Paginator {
	public static Paginator DEFAULT = new Paginator(0, 1, 1);
	
	// Cantidad de elementos por página.
	private int size = 0; 
	// Página actual. Comienza siempre por 1.
	private int page = 1; 
	// Cantidad de páginas en total.
	private int total = 1;
	
	public Paginator(int size, int page, int totalPages) {
		this.size = size;
		this.page = page;
		this.total = totalPages;
	}
	public Paginator(int size, int page) {
		this(size, page, 0);
	}
	public Paginator(int size) {
		this(size, 1);
	}
	public Paginator() {}
	
	public JsonObject toJsonObject() {
		JsonObject obj = new JsonObject();
		obj.addProperty("size", size);
		obj.addProperty("page", page);
		obj.addProperty("total", total);
		return obj;
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalPages() {
		return total;
	}
	public void setTotalPages(int totalPages) {
		this.total = totalPages;
	}
	
	
}
