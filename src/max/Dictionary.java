package max;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

import max.oops.KeyIsNotAStringException;
import max.oops.OddNumberOfElementsException;
import max.oops.ParameterNotExistsException;


public class Dictionary extends HashMap<String, Object> {

	private static final long serialVersionUID = 2L;
	
	/**
	 * Crea un Dictionary en base a un array.
	 * @param arr El arreglo base.
	 * @return El Dictionary parseado.
	 * @throws OddNumberOfElementsException Si el arreglo recibido tiene un número impar de elementos.
	 * @throws KeyIsNotAStringException Si alguno de los elementos pares del arreglo recibido no es una instancia de String.
	 */
	public static Dictionary setFromArray(Object[] arr) throws OddNumberOfElementsException, KeyIsNotAStringException {
		Dictionary obj = new Dictionary();
		if(arr.length % 2 == 0) {
			for(int i = 0; i < arr.length; i = i+2) {
				String key = "";
				// Key 0 (Par)
				Object tkey = arr[i];
				if(tkey instanceof String) {
					key = (String) tkey;
				} else {
					throw new KeyIsNotAStringException();
				}
				// Value 1 (Impar)
				Object tvalue = arr[i+1];
				obj.put(key, tvalue);
			}
		} else {
			throw new OddNumberOfElementsException();
		}
		return obj;
	}
	
	/**
	 * Crea un Dictionary en base a un parámetro varargs.
	 * Captura las posibles excepciones y las muestra por consola.
	 * @param arr Elementos a agregar.
	 * @return El Dictionary listo.
	 */
	public static Dictionary fromArray(Object... arr) {
		Dictionary d = new Dictionary();
		try {
			d = Dictionary.setFromArray(arr);
		} catch (OddNumberOfElementsException | KeyIsNotAStringException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	/**
	 * Conocer si un elemento existe en la lista a partir de su key.
	 * @param key La clave por la cual se busca el elemento en la lista.
	 * @return true, si existe. False, si no existe.
	 */
	public boolean exists(String key) {
		for(String v : keySet()) {
			if(v.equals(key)) return true;
		}
		return false;
	}
	
	/**
	 * Cuenta los parámetros presentes en una consulta SQL.
	 * @param str La consulta SQL a ser analizada
	 * @return La cantidad de parámetros presentes en la consulta.
	 */
	public static int countKeys(String str) {
		int z = 0;
		Pattern pattern = Pattern.compile("@\\w+");
		Matcher matcher = pattern.matcher(str);
		while(matcher.find()) {
			z++;
		}
		return z;
	}
	
	/**
	 * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
	 * @param key - the key whose associated value is to be returned
	 */
	@SuppressWarnings("unchecked")
	public <T> T $(String key) {
		return (T)get(key);
	}
	
	/**
	 * Analiza una consulta SQL y extrae los parámetros de esa consulta que están en forma de \@key. 
	 * @param query La consulta SQL a ser analizada.
	 * @return Array con los valores correspondientes a los parámetros, en el orden en el que aparecen en la consulta.
	 */
	public Object[] getParameters(String query) throws ParameterNotExistsException {
        Pattern pattern = Pattern.compile("@(\\w+)");
        Matcher matcher = pattern.matcher(query);
        int coincidences = countKeys(query);
        Object[] params = new Object[coincidences];
        int i = 0;
        while (matcher.find()) {
            String paramName = matcher.group(1);
            boolean e = exists(paramName);
            if(e) {
            	Object val = get(paramName);
            	params[i] = val;
            	
            } else {
            	throw new ParameterNotExistsException(paramName);
            }
            i++;
        }
        return params;
	}
	
	 /**
     * Crea un Dictionary a partir de una representación JSON.
     * @param json El JSON a partir del cual se creará el Dictionary.
     * @return El Dictionary creado a partir del JSON.
     */
    public static Dictionary fromJSON(String json) {
        Gson gson = new Gson();
        Dictionary dictionary = gson.fromJson(json, Dictionary.class);
        return dictionary;
    }
    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
	
}