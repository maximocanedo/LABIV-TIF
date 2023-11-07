package oops;

@SuppressWarnings("serial")
public class KeyIsNotAStringException extends Exception {
	
	public KeyIsNotAStringException() {
		super("The key provided is not an instance of String. ");
	}

}
