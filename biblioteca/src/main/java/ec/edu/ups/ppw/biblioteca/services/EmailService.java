package ec.edu.ups.ppw.biblioteca.services;

import java.util.Properties;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmailService {
	

    private final String username = "correoelectronicoele@gmail.com";
    private final String password = "zxhr iwxn fzpt oqnp";  // Contraseña de aplicación

    private Properties getMailProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        return props;
    }

    public void sendEmail(String to, String subject, String body) throws MessagingException {
        Session session = Session.getInstance(getMailProperties(), new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(body);

        Transport.send(message);
        System.out.println("Correo enviado a: " + to);
    }
}