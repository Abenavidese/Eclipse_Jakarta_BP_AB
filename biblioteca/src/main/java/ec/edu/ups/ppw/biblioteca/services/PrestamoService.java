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

@Path("/prestamos")
public class PrestamoService {

    @Inject
    private GestionPrestamo gPrestamos;

    @Inject
    private EmailService emailService;

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
                    + "Fecha de Préstamo: " + prestamo.getFechaInicio() + "\n"
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

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Prestamo getPrestamoById(@PathParam("id") int id) {
        Prestamo prestamo;
        try {
            prestamo = gPrestamos.getPrestamo(id);
            return prestamo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GET
    @Produces("application/json")
    public List<Prestamo> list() {
        return gPrestamos.getPrestamos();
    }

    @GET
    @Path("/activos")
    @Produces("application/json")
    public List<Prestamo> getPrestamosActivos() {
        return gPrestamos.getPrestamosActivos();
    }
    
    @POST
    @Path("/devolver/{id}")
    @Produces("application/json")
    public Response devolverPrestamo(@PathParam("id") int id) {
        try {
            gPrestamos.returnPrestamo(id);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Error al procesar devolución")).build();
        }
    }
    
    
}
