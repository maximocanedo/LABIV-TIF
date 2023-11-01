import com.microsoft.webservices.EnvioMailSoap;
import com.microsoft.webservices.EnvioMailSoapImpl;
import com.microsoft.webservices.EnvioMailLocator;

public class WStestOOOAAAA {

    public static void main(String[] args) {/*
            try {
            EnvioMailLocator locator = new EnvioMailLocator();
            EnvioMailSoap envioMailSoap = locator.getEnvioMailSoap();
        	System.setProperty("axis.socketSecureFactory", "org.apache.axis.components.net.SunFakeTrustSocketFactory");

            String email = "patriciobordon22232@gmail.com";
            String asunto = "TEST";
            String mensaje = "Hola que tal";
            String resultado = envioMailSoap.enviarMail(email, asunto, mensaje);

            System.out.println("Resultado: " + resultado);//SI DA "OK" se envio bien
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    	EnvioMailSoapImpl WS=new EnvioMailSoapImpl();
    	System.out.println(WS.enviarMail("patriciobordon123@gmail.com", "asdas", "asdsad"));
    }
}