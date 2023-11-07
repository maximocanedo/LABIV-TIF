package max;

public interface IAction<T> {
	void exec(T data);
}