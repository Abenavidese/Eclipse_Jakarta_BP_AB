package ec.edu.ups.ppw.biblioteca.services;

import java.util.Date;
import java.util.List;

import ec.edu.ups.ppw.biblioteca.Prestamo;
import ec.edu.ups.ppw.biblioteca.dao.PrestamoDAO;

public class NotificacionService {

    private PrestamoDAO prestamoDAO; // Tu DAO de préstamos
    private EmailService emailService;

    public NotificacionService(PrestamoDAO prestamoDAO, EmailService emailService) {
        this.prestamoDAO = prestamoDAO;
        this.emailService = emailService;
    }

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

                emailService.sendEmail(email, subject, body);
            }
        }
    }

    private boolean isReturnDateClose(Date fechaDevolucion) {
        // Implementa la lógica para determinar si la fecha de devolución está próxima (por ejemplo, en los próximos 3 días)
        long diff = fechaDevolucion.getTime() - new Date().getTime();
        return diff <= 3 * 24 * 60 * 60 * 1000; // 3 días en milisegundos
    }
}