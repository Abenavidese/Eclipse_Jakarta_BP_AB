package ec.edu.ups.ppw.biblioteca.services;

import java.util.List;
import ec.edu.ups.ppw.biblioteca.Reservado;
import ec.edu.ups.ppw.biblioteca.model.GestionReserva;
import jakarta.inject.Inject;
import jakarta.mail.MessagingException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Servicio RESTful para gestionar reservas.
 * Proporciona endpoints para crear, listar y cancelar reservas.
 */
@Path("/reservas")
public class ReservadoService {

    @Inject
    private GestionReserva gReservas;

    @Inject
    private EmailService emailService;

    /**
     * Crea una nueva reserva.
     * @param reserva La reserva a crear.
     * @return Una respuesta HTTP con la reserva creada o un mensaje de error.
     */
    @POST
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Reservado reserva) {
        try {
            gReservas.createReserva(reserva);
            
            // Enviar correo de confirmación
            String email = reserva.getUsuario().getEmail();
            String subject = "Reserva de Libro Confirmada";
            String body = "Estimado " + reserva.getUsuario().getUsername() + ",\n\n"
                    + "El libro \"" + reserva.getLibro().getTitulo() + "\" ha sido reservado exitosamente.\n"
                    + "Fecha de Reserva: " + reserva.getFechaReserva() + "\n\n"
                    + "Gracias,\nTu Biblioteca";
            
            try {
                emailService.sendEmail(email, subject, body);
            } catch (MessagingException e) {
                e.printStackTrace();
                return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Error al enviar correo")).build();
            }

            return Response.ok(reserva).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Error en BD")).build();
        }
    }

    /**
     * Obtiene una reserva por su ID.
     * @param id El ID de la reserva.
     * @return La reserva encontrada o null si ocurre un error.
     */
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Reservado getReservaById(@PathParam("id") int id) {
        try {
            return gReservas.getReserva(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Lista todas las reservas.
     * @return Una lista de todas las reservas.
     */
    @GET
    @Produces("application/json")
    public List<Reservado> list() {
        return gReservas.getReservas();
    }

    /**
     * Lista todas las reservas activas.
     * @return Una lista de todas las reservas activas.
     */
    @GET
    @Path("/activas")
    @Produces("application/json")
    public List<Reservado> getReservasActivas() {
        return gReservas.getReservasActivas();
    }

    /**
     * Cancela una reserva.
     * @param id El ID de la reserva a cancelar.
     * @return Una respuesta HTTP indicando el resultado de la operación.
     */
    @POST
    @Path("/cancelar/{id}")
    @Produces("application/json")
    public Response cancelarReserva(@PathParam("id") int id) {
        try {
            gReservas.cancelarReserva(id);

            // Obtener la reserva para enviar el correo
            Reservado reserva = gReservas.getReserva(id);
            if (reserva != null) {
                // Enviar correo de confirmación de cancelación
                String email = reserva.getUsuario().getEmail();
                String subject = "Cancelación de Reserva Confirmada";
                String body = "Estimado " + reserva.getUsuario().getUsername() + ",\n\n"
                        + "La reserva del libro \"" + reserva.getLibro().getTitulo() + "\" ha sido cancelada exitosamente.\n\n"
                        + "Gracias,\nTu Biblioteca";

                try {
                    emailService.sendEmail(email, subject, body);
                } catch (MessagingException e) {
                    e.printStackTrace();
                    return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Error al enviar correo")).build();
                }
            }

            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Error al procesar cancelación")).build();
        }
    }
}
