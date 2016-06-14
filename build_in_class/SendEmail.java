package build_in_class;
import java.util.Properties;
import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

interface constant
{
	public static final String smtp_port_noauth = "25";
	public static final String gmail_smtp_host = "smtp.gmail.com";
	public static final String gmail_pop3_host = "pop.gmail.com";
	public static final String gmail_imap_host = "imap.gmail.com";
	public static final String gmail_smtp_port_ssl = "465";
	public static final String gmail_smtp_port_tls = "587";
	public static final String gmail_pop3_port_ssl = "995";
	public static final String gmail_imap_port_ssl = "993";
	
	public static final String yahoo_smtp_host = "smtp.mail.yahoo.com";
	public static final String yahoo_pop3_host = "pop.mail.yahoo.com";
	public static final String yahoo_imap_host = "imap.mail.yahoo.com";
	public static final String yahoo_smtp_port_ssl = "465";
	public static final String yahoo_pop3_port_ssl = "995";
	public static final String yahoo_imap_port_ssl = "993";
}

public class SendEmail
{

	public static void main(String[] args) throws AddressException, MessagingException
	{
		SendEmail s = new SendEmail();
	}
	
	public void send(String to, String subj, String content, String user, String pass) throws AddressException, MessagingException
	{
		String host = "";
		host = user.substring(user.indexOf('@') + 1, user.indexOf('.', user.indexOf('@')));
		//System.out.println("Host: " + host);
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		if(host.equals("gmail"))
			props.put("mail.smtp.host", constant.gmail_smtp_host);
		if(host.equals("yahoo"))
			props.put("mail.smtp.host", constant.yahoo_smtp_host);
		props.put("mail.smtp.socketFactory.port", constant.gmail_smtp_port_ssl);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.port", constant.gmail_smtp_port_ssl);
		
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, pass);
				}
				});
		
		// Create message
		Message mess = new MimeMessage(session);
		mess.setFrom(new InternetAddress(user));
		mess.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		mess.setSubject(subj);
		mess.setText(content);
		// Send Message
		Transport.send(mess);
		System.out.println("Message Sent...");
	}
}
