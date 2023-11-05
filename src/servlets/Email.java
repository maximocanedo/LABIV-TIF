package servlets;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microsoft.webservices.EnvioMailSoapImpl;

import max.data.Dictionary;


/**
 * Endpoints para Implementar validación de correo electrónico al registrar clientes.
 * @author Patricio
 *
 */
@WebServlet("/api/client/validateMail")
public class Email extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	
    /// METODOS
	
	/** Funcion que genera un codigo aleatorio simple de 6 digitos del 0 al 9
	 * 
	 * */
	
	private void codigoAleatorio(HttpSession session) {
	    session.setAttribute("codigoAleatorio", String.format("%06d", new Random().nextInt(1000000)));
	}
	
	/** Funcion que gvalida que string sea numero entero
	 * 
	 * */
	
    public static boolean esNumero(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (Exception e) {
        	return false;
        }
    }

        
	
/**	
 * Deberá recibir por parámetro mínimo el correo electrónico a validar.
 *
 *	1. Generar un código aleatoriamente, de 4 o 6 dígitos.
 *	2. Guardarlo en la variable SESSION.
 *	3. Enviar ese código al correo deseado.
 *	Posibles salidas:
 *
 *	HTTP 200 OK: Si se envió el correo con el código generado de forma exitosa.
 *	HTTP 400 Bad Request: Si el usuario envió un correo inválido, no envió el 
 *  parámetro necesario o hay un problema de validación.
 *	HTTP 500 Internal Server Error: Si hubo un problema al intentar generar un 
 *  código aleatorio, al guardarlo, o al enviarlo por mail.
 **/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destinatario;
		// Obtener parametros
    	Dictionary parameters = getParameters(request);
    	
    	/* HTTP 400 Bad Request: Si el usuario envió un correo inválido, no envió el 
    	  parámetro necesario o hay un problema de validación.*/
		if(parameters.get("email") == null) {
			die(response, false, 400, "Bad request");
			return;
		}else {
			destinatario= (String) parameters.get("email");
		}
		
		//variable httpSession
		HttpSession session = request.getSession();
		
		//La inicializo con el codigo generado
		codigoAleatorio(session);
		
		// Guardo el codigo en String asi lo paso al mail
		String cod = (String) session.getAttribute("codigoAleatorio");

		// Envio el mail
		EnvioMailSoapImpl WS = new EnvioMailSoapImpl();
		String ok = WS.enviarMail(destinatario, "TEST Emprolijar con html el cuerpo del mail", cod);
		if(ok=="OK") {
			die(response, true, 200, "Mail enviado!");
		}else {
			die(response, false, 500, "El mail no se pudo enviar");
			response.setStatus(500);
		}
	}

	/**
	 * Método GET. 
	 * Deberá recibir por parámetro mínimo un código enviado por el usuario.
     *
	 * 1. Recibir el código enviado por el usuario. Validar que sea un código válido. (*)
	 * 2. Consultar el código enviado en la variable SESSION.
	 * 3. Comparar ambos códigos.
	 * Posibles salidas:
     *	
	 * HTTP 200 OK: Si el código enviado por el usuario coincide con el código enviado a su correo.
	 * HTTP 400 Bad Request: Si el usuario envió un código inválido, no envió el parámetro necesario o hay un problema de validación.
	 * HTTP 404 Not Found: Si no hay ningún código a validar. (**)
	 * HTTP 500 Internal Server Error: Si hubo un problema al comparar los códigos o una excepción no controlada.
	 * (*) Se puede definir una estructura de códigos, por ejemplo, seis números del 0 al 9.
 	 * (**) Puede ocurrir que el usuario haya solicitado un código, el servidor lo haya enviado, pero antes de que el usuario ingrese el código se haya borrado el código de SESSION.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String codigo;
			// Obtener parametros
	    	Dictionary parameters = getParameters(request);
	    	//HTTP 404 Not Found: Si no hay ningún código a validar.
			if(parameters.get("code") == null) {
				die(response, false, 404, "Bad request");
				return;
			}else {
				codigo= (String) parameters.get("code");
			}
			//valido que este bien escrito el codigo
			if(codigo.lenght < 6 || codigo.lenght > 6 || !esNumero(codigo)) {
				die(response, false, 400, "Bad request");
				return;
			}
			//Obtengo la variable session
			String cod = (String) session.getAttribute("codigoAleatorio");
			//HTTP 200 OK: Si el código enviado por el usuario coincide con el código enviado a su correo.
			if(cod==code) {
				die(response, true, 200, "Mail enviado!");
			}else {
				//HTTP 400 Bad Request: Si el usuario envió un código inválido, no envió el parámetro necesario o hay un problema de validación.
				die(response, false, 400, "El codigo no coincide con el enviado a tu mail");
			}
		}catch(exception e){
			//HTTP 500 Internal Server Error: Si hubo un problema al comparar los códigos o una excepción no controlada.
			die(response, false, 500, e.getMessage());
			response.setStatus(500);
		}
	}
	
	/**
	 * Método OPTIONS. Informar sobre métodos disponibles.
	 */
	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Allow", "GET, POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

	}
	

	@Override
	protected String[] getAllowedMethods() {
		return new String[] { "GET", "POST", "OPTIONS" };
	}

}