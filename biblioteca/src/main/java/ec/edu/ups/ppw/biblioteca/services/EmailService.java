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

/**
 * Servicio de envío de correos electrónicos.
 * Proporciona funcionalidad para enviar correos electrónicos usando el protocolo SMTP.
 */
@ApplicationScoped
public class EmailService {

    private final String username = "correoelectronicoele@gmail.com";
    private final String password = "zxhr iwxn fzpt oqnp";  // Contraseña de aplicación

    /**
     * Obtiene las propiedades de configuración para el servidor de correo.
     * @return Las propiedades de configuración para el servidor de correo.
     */
    private Properties getMailProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        return props;
    }

    /**
     * Envía un correo electrónico.
     * @param to La dirección de correo del destinatario.
     * @param subject El asunto del correo.
     * @param body El contenido del correo.
     * @throws MessagingException Si ocurre un error al enviar el correo.
     */
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
