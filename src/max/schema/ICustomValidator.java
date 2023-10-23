package max.schema;

public interface ICustomValidator<T> {
	boolean exec(T data);
}
