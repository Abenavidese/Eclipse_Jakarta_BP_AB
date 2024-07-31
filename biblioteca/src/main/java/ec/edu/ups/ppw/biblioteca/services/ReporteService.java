package ec.edu.ups.ppw.biblioteca.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import ec.edu.ups.ppw.biblioteca.Libros;
import ec.edu.ups.ppw.biblioteca.Usuario;
import ec.edu.ups.ppw.biblioteca.model.GestionReporte;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Servicio REST para manejar las solicitudes de reportes.
 */
@Path("/reportes")
public class ReporteService {

    @Inject
    private GestionReporte gestionReporte;

    @GET
    @Path("/cantidad-prestamos-por-usuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCantidadPrestamosPorUsuario() {
        List<Object[]> result = gestionReporte.getCantidadPrestamosPorUsuario();
        return Response.ok(result).build();
    }
    
    
    
    @GET
    @Path("/libros-populares")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLibrosMasPopulares() {
        List<Libros> librosMasPopulares = gestionReporte.getLibrosMasPopulares();
        return Response.ok(librosMasPopulares).build();
    }

}
