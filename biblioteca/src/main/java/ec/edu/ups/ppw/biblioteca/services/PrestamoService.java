package ec.edu.ups.ppw.biblioteca.services;

import java.util.List;
import ec.edu.ups.ppw.biblioteca.Prestamo;
import ec.edu.ups.ppw.biblioteca.model.GestionPrestamo;
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
 * Servicio RESTful para gestionar préstamos.
 * Proporciona endpoints para crear, devolver y listar préstamos.
 */
@Path("/prestamos")
public class PrestamoService {

    @Inject
    private GestionPrestamo gPrestamos;

    @Inject
    private EmailService emailService;

    /**
     * Crea un nuevo préstamo.
     * @param prestamo El préstamo a crear.
     * @return Una respuesta HTTP con el préstamo creado o un mensaje de error.
     */
    @POST
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Prestamo prestamo) {
        try {
            gPrestamos.createPrestamo(prestamo);
            
            // Enviar correo de confirmación
            String email = prestamo.getUsuario().getEmail();
            String subject = "Préstamo de Libro Confirmado";
            String body = "Estimado " + prestamo.getUsuario().getUsername() + ",\n\n"
                    + "El libro \"" + prestamo.getLibro().getTitulo() + "\" ha sido prestado exitosamente.\n"
                    + "Fecha de Préstamo: " + prestamo.getFechaPrestamo() + "\n"
                    + "Fecha de Devolución: " + prestamo.getFechaDevolucion() + "\n\n"
                    + "Gracias,\nTu Biblioteca";
            
            try {
                emailService.sendEmail(email, subject, body);
            } catch (MessagingException e) {
                e.printStackTrace();
                return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Error al enviar correo")).build();
            }

            return Response.ok(prestamo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Error en BD")).build();
        }
    }

    /**
     * Obtiene un préstamo por su ID.
     * @param id El ID del préstamo.
     * @return El préstamo encontrado o null si ocurre un error.
     */
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Prestamo getPrestamoById(@PathParam("id") int id) {
        try {
            return gPrestamos.getPrestamo(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Lista todos los préstamos.
     * @return Una lista de todos los préstamos.
     */
    @GET
    @Produces("application/json")
    public List<Prestamo> list() {
        return gPrestamos.getPrestamos();
    }

    /**
     * Lista todos los préstamos activos (no devueltos).
     * @return Una lista de todos los préstamos activos.
     */
    @GET
    @Path("/activos")
    @Produces("application/json")
    public List<Prestamo> getPrestamosActivos() {
        return gPrestamos.getPrestamosActivos();
    }

    /**
     * Marca un préstamo como devuelto.
     * @param id El ID del préstamo a devolver.
     * @return Una respuesta HTTP indicando el resultado de la operación.
     */
    @POST
    @Path("/devolver/{id}")
    @Produces("application/json")
    public Response devolverPrestamo(@PathParam("id") int id) {
        try {
            gPrestamos.returnPrestamo(id);

            // Obtener el préstamo para enviar el correo
            Prestamo prestamo = gPrestamos.getPrestamo(id);
            if (prestamo != null) {
                // Enviar correo de confirmación de devolución
                String email = prestamo.getUsuario().getEmail();
                String subject = "Devolución de Libro Confirmada";
                String body = "Estimado " + prestamo.getUsuario().getUsername() + ",\n\n"
                        + "El libro \"" + prestamo.getLibro().getTitulo() + "\" ha sido devuelto exitosamente.\n"
                        + "Fecha de Devolución: " + prestamo.getFechaDevolucion() + "\n\n"
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
            return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Error al procesar devolución")).build();
        }
    }
}
