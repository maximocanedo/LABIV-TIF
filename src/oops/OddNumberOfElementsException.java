package oops;

@SuppressWarnings("serial")
public class OddNumberOfElementsException extends Exception {
    
    public OddNumberOfElementsException() {
    	// TODO: Internacionalizar esto.
        super("An odd number of elements was provided, but an even number was expected.");
    }
}