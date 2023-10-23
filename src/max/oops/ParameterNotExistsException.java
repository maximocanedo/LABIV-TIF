package max.oops;
/**
 * Excepción que se produce cuando se intenta acceder al valor de un parámetro que no existe.
 */
@SuppressWarnings("serial")
public class ParameterNotExistsException extends Exception {
    private String parameterName;
    
    public ParameterNotExistsException() {
        super("El parámetro especificado no existe.");
    }

    public ParameterNotExistsException(String parameterName) {
        super("El parámetro '" + parameterName + "' no existe.");
        this.parameterName = parameterName;
    }

    public String getParameterName() {
        return parameterName;
    }
}