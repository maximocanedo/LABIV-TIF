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
 * Endpoints para Implementar validaci�n de correo electr�nico al registrar clientes.
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
 * Deber� recibir por par�metro m�nimo el correo electr�nico a validar.
 *
 *	1. Generar un c�digo aleatoriamente, de 4 o 6 d�gitos.
 *	2. Guardarlo en la variable SESSION.
 *	3. Enviar ese c�digo al correo deseado.
 *	Posibles salidas:
 *
 *	HTTP 200 OK: Si se envi� el correo con el c�digo generado de forma exitosa.
 *	HTTP 400 Bad Request: Si el usuario envi� un correo inv�lido, no envi� el 
 *  par�metro necesario o hay un problema de validaci�n.
 *	HTTP 500 Internal Server Error: Si hubo un problema al intentar generar un 
 *  c�digo aleatorio, al guardarlo, o al enviarlo por mail.
 **/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destinatario;
		// Obtener parametros
    	Dictionary parameters = getParameters(request);
    	
    	/* HTTP 400 Bad Request: Si el usuario envi� un correo inv�lido, no envi� el 
    	  par�metro necesario o hay un problema de validaci�n.*/
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
	 * M�todo GET. 
	 * Deber� recibir por par�metro m�nimo un c�digo enviado por el usuario.
     *
	 * 1. Recibir el c�digo enviado por el usuario. Validar que sea un c�digo v�lido. (*)
	 * 2. Consultar el c�digo enviado en la variable SESSION.
	 * 3. Comparar ambos c�digos.
	 * Posibles salidas:
     *	
	 * HTTP 200 OK: Si el c�digo enviado por el usuario coincide con el c�digo enviado a su correo.
	 * HTTP 400 Bad Request: Si el usuario envi� un c�digo inv�lido, no envi� el par�metro necesario o hay un problema de validaci�n.
	 * HTTP 404 Not Found: Si no hay ning�n c�digo a validar. (**)
	 * HTTP 500 Internal Server Error: Si hubo un problema al comparar los c�digos o una excepci�n no controlada.
	 * (*) Se puede definir una estructura de c�digos, por ejemplo, seis n�meros del 0 al 9.
 	 * (**) Puede ocurrir que el usuario haya solicitado un c�digo, el servidor lo haya enviado, pero antes de que el usuario ingrese el c�digo se haya borrado el c�digo de SESSION.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String codigo;
			// Obtener parametros
	    	Dictionary parameters = getParameters(request);
	    	//HTTP 404 Not Found: Si no hay ning�n c�digo a validar.
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
			//HTTP 200 OK: Si el c�digo enviado por el usuario coincide con el c�digo enviado a su correo.
			if(cod==code) {
				die(response, true, 200, "Mail enviado!");
			}else {
				//HTTP 400 Bad Request: Si el usuario envi� un c�digo inv�lido, no envi� el par�metro necesario o hay un problema de validaci�n.
				die(response, false, 400, "El codigo no coincide con el enviado a tu mail");
			}
		}catch(exception e){
			//HTTP 500 Internal Server Error: Si hubo un problema al comparar los c�digos o una excepci�n no controlada.
			die(response, false, 500, e.getMessage());
			response.setStatus(500);
		}
	}
	
	/**
	 * M�todo OPTIONS. Informar sobre m�todos disponibles.
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