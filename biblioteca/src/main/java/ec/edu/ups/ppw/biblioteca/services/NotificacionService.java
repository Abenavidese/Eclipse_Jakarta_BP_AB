package ec.edu.ups.ppw.biblioteca.services;

import java.util.Date;
import java.util.List;

import ec.edu.ups.ppw.biblioteca.Prestamo;
import ec.edu.ups.ppw.biblioteca.dao.PrestamoDAO;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import jakarta.mail.MessagingException;

@Singleton
@Startup
public class NotificacionService {

    @Inject
    private PrestamoDAO prestamoDAO;

    @Inject
    private EmailService emailService;

    @Schedule(hour = "0", minute = "0", second = "0", persistent = false)
    public void sendReturnReminder() {
        List<Prestamo> prestamos = prestamoDAO.getAll();
        for (Prestamo prestamo : prestamos) {
            if (isReturnDateClose(prestamo.getFechaDevolucion())) {
                String email = prestamo.getUsuario().getEmail();
                String subject = "Recordatorio de Devolución de Libro";
                String body = "Estimado " + prestamo.getUsuario().getUsername() + ",\n\n"
                        + "Este es un recordatorio para devolver el libro \"" + prestamo.getLibro().getTitulo() + "\" "
                        + "antes del " + prestamo.getFechaDevolucion() + ".\n\n"
                        + "Gracias,\nTu Biblioteca";

                try {
                    emailService.sendEmail(email, subject, body);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean isReturnDateClose(Date fechaDevolucion) {
        long diff = fechaDevolucion.getTime() - new Date().getTime();
        return diff <= 3 * 24 * 60 * 60 * 1000; // 3 días en milisegundos
    }
}