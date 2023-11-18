package api;

import com.microsoft.webservices.EnvioMailSoapImpl;
import email.Mail;
import servlets.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet("/v2/me/verify")
public class VerifyMe extends BaseServlet {
    public VerifyMe() {
        super();
    }
    /// METODOS

    /**
     * Funcion que genera un codigo aleatorio simple de 6 digitos del 0 al 9
     */

    private String codigoAleatorio() {
        return String.format("%06d", new Random().nextInt(1000000));
    }

    /**
     * Funcion que valida que string sea numero entero
     */

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
     * <p>
     * 1. Generar un c�digo aleatoriamente, de 4 o 6 d�gitos.
     * 2. Guardarlo en la variable SESSION.
     * 3. Enviar ese c�digo al correo deseado.
     * Posibles salidas:
     * <p>
     * HTTP 200 OK: Si se envi� el correo con el c�digo generado de forma exitosa.
     * HTTP 400 Bad Request: Si el usuario envi� un correo inv�lido, no envi� el
     * par�metro necesario o hay un problema de validaci�n.
     * HTTP 500 Internal Server Error: Si hubo un problema al intentar generar un
     * c�digo aleatorio, al guardarlo, o al enviarlo por mail.
     **/
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destinatario;
        // Obtener parametros
        String email = request.getParameter("email");
        String asunto = "TEST Emprolijar con html el cuerpo del mail";
    	/* HTTP 400 Bad Request: Si el usuario envi� un correo inv�lido, no envi� el
    	  par�metro necesario o hay un problema de validaci�n.*/
        try {

            if (email == "") {
                die(response, false, 400, "Bad request");
                return;
            } else {
                destinatario = email;
            }

            //variable Session
            request.getSession().setAttribute("codigoAleatorio", codigoAleatorio());
            ;


            // Guardo el codigo en String asi lo paso al mail
            String cod = request.getSession().getAttribute("codigoAleatorio").toString();

            // Envio el mail
            EnvioMailSoapImpl WS = new EnvioMailSoapImpl();
            String ok = WS.enviarMail(destinatario, asunto, cod);
            if (ok == "OK") {
                die(response, true, 200, "Mail enviado!");
                request.setAttribute("codigo", cod);
                //RequestDispatcher rd = request.getRequestDispatcher("/TPINT_GRUPO_3_LAB/clientes/IngresarCodigo.jsp");
                //rd.forward(request, response);

            } else {
                ok = Mail.enviar(destinatario, asunto, cod);
                die(response, true, 200, "Mail enviado!");
                request.setAttribute("codigo", cod);
                //RequestDispatcher rd = request.getRequestDispatcher("/TPINT_GRUPO_3_LAB/clientes/IngresarCodigo.jsp");
                //rd.forward(request, response);
            }
        } catch (Exception e) {
            die(response, false, 500, e.getMessage());
            //RequestDispatcher rd = request.getRequestDispatcher("");
            //rd.forward(request, response);
        }
    }

    /**
     * M�todo GET.
     * Deber� recibir por par�metro m�nimo un c�digo enviado por el usuario.
     * <p>
     * 1. Recibir el c�digo enviado por el usuario. Validar que sea un c�digo v�lido. (*)
     * 2. Consultar el c�digo enviado en la variable SESSION.
     * 3. Comparar ambos c�digos.
     * Posibles salidas:
     * <p>
     * HTTP 200 OK: Si el c�digo enviado por el usuario coincide con el c�digo enviado a su correo.
     * HTTP 400 Bad Request: Si el usuario envi� un c�digo inv�lido, no envi� el par�metro necesario o hay un problema de validaci�n.
     * HTTP 404 Not Found: Si no hay ning�n c�digo a validar. (**)
     * HTTP 500 Internal Server Error: Si hubo un problema al comparar los c�digos o una excepci�n no controlada.
     * (*) Se puede definir una estructura de c�digos, por ejemplo, seis n�meros del 0 al 9.
     * (**) Puede ocurrir que el usuario haya solicitado un c�digo, el servidor lo haya enviado, pero antes de que el usuario ingrese el c�digo se haya borrado el c�digo de SESSION.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String codigo, cod;
            // Obtener parametros

            //HTTP 404 Not Found: Si no hay ning�n c�digo a validar.
            if (request.getParameter("code") == null) {
                die(response, false, 404, "Bad request");
                return;
            } else {
                codigo = request.getParameter("code");
                cod = request.getSession().getAttribute("codigoAleatorio").toString();
            }
            //valido que este bien escrito el codigo
            if (codigo.length() < 6 || codigo.length() > 6 || !esNumero(codigo)) {
                die(response, false, 400, "Bad request");
                return;
            }

            //Obtengo la variable session
            //HttpSession session = Session.getAttribute("codigoAleatorio");
            //HTTP 200 OK: Si el c�digo enviado por el usuario coincide con el c�digo enviado a su correo.
            if (cod.equals(codigo)) {


                die(response, true, 200, "Codigo correcto");
            } else {
                //HTTP 400 Bad Request: Si el usuario envi� un c�digo inv�lido, no envi� el par�metro necesario o hay un problema de validaci�n.
                die(response, false, 404, "El codigo no coincide con el enviado a tu mail");
            }
        } catch (Exception e) {
            //HTTP 500 Internal Server Error: Si hubo un problema al comparar los c�digos o una excepci�n no controlada.
            die(response, false, 500, e.getMessage());
            response.setStatus(500);

        }

    }


    @Override
    protected String[] getAllowedMethods() {
        return new String[]{GET, POST, OPTIONS};
    }
}
