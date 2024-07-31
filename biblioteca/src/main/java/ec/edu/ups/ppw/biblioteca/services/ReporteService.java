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

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @GET
    @Path("/libros/{startDate}/{endDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLibrosMasSolicitados(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate) {
        try {
            Date start = dateFormat.parse(startDate);
            Date end = dateFormat.parse(endDate);
            List<Libros> librosMasSolicitados = gestionReporte.getLibrosMasSolicitados(start, end);
            return Response.ok(librosMasSolicitados).build();
        } catch (ParseException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Formato de fecha incorrecto. Use yyyy-MM-dd.")
                    .build();
        }
    }

    @GET
    @Path("/usuarios/{startDate}/{endDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuariosMasActivos(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate) {
        try {
            Date start = dateFormat.parse(startDate);
            Date end = dateFormat.parse(endDate);
            List<Usuario> usuariosMasActivos = gestionReporte.getUsuariosMasActivos(start, end);
            return Response.ok(usuariosMasActivos).build();
        } catch (ParseException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Formato de fecha incorrecto. Use yyyy-MM-dd.")
                    .build();
        }
    }
}
