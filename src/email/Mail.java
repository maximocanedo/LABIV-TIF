package email;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class Mail {
	private static String claveApp="jkmbidqnvmtzwxtr";
	private static String grupoMail="grupotif3@gmail.com";
	private static Session session;
	private static Properties props;
	
	public static String enviar(String destinatario, String asunto, String mail) {
		initAttributes();
	    MimeMessage mimeMail = new MimeMessage(session);
	    try {
	    	mimeMail.setFrom(new InternetAddress(grupoMail));
	    	mimeMail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
	    	mimeMail.setSubject(asunto);
	    	mimeMail.setText(mail);
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", grupoMail, claveApp);
	        transport.sendMessage(mimeMail, mimeMail.getAllRecipients());
	        transport.close();
	    }
	    catch (Exception e) {
	        return "ERRROR: Exception "+ e.getMessage();
	    }
	    return "OK";
	  }
	
	private static void initAttributes() {
		props = System.getProperties();
	    props.put("mail.smtp.host", "smtp.gmail.com");  
	    props.put("mail.smtp.user", grupoMail);
	    props.put("mail.smtp.clave", claveApp);   
	    props.put("mail.smtp.auth", "true");   
	    props.put("mail.smtp.starttls.enable", "true"); 
	    props.put("mail.smtp.port", "587"); 
	    session = Session.getDefaultInstance(props);
	}
}