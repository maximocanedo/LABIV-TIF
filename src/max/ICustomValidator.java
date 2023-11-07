package max;

public interface ICustomValidator<T> {
	boolean exec(T data);
}
