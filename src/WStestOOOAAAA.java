import com.microsoft.webservices.EnvioMailSoap;
import email.Mail;
import com.microsoft.webservices.EnvioMailLocator;

public class WStestOOOAAAA {

    public static void main(String[] args) {
    	String email = "patriciobordon22232@gmail.com";
        String asunto = "TEST";
        String mensaje = "Hola que tal";
            try {
            EnvioMailLocator locator = new EnvioMailLocator();
            EnvioMailSoap envioMailSoap = locator.getEnvioMailSoap();
        	System.setProperty("axis.socketSecureFactory", "org.apache.axis.components.net.SunFakeTrustSocketFactory");
            String resultado = envioMailSoap.enviarMail(email, asunto, mensaje);

            System.out.println("Resultado: " + resultado);//SI DA "OK" se envio bien
        } catch (Exception e) {
            System.out.println(Mail.enviar(email, asunto, mensaje));
        }
    }
}