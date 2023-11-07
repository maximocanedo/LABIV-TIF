package entity;

public class Paginator {
	// Cantidad de elementos por p�gina.
	private int size; 
	// P�gina actual. Comienza siempre por 0.
	private int page; 
	// Cantidad de p�ginas en total.
	private int totalPages;
	
	public Paginator(int size, int page, int totalPages) {
		this.size = size;
		this.page = page;
		this.totalPages = totalPages;
	}
	public Paginator(int size, int page) {
		this(size, page, 0);
	}
	public Paginator(int size) {
		this(size, 1);
	}
	public Paginator() {}
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
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	
}
