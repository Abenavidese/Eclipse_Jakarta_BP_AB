package ec.edu.ups.ppw.biblioteca.services;

import java.util.List;
import ec.edu.ups.ppw.biblioteca.Libros;
import ec.edu.ups.ppw.biblioteca.model.GestionLibro;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Servicio RESTful para gestionar libros.
 * Proporciona endpoints para crear, actualizar, eliminar y listar libros.
 */
@Path("/libros")
public class LibroService {

    @Inject
    private GestionLibro gLibros;

    /**
     * Crea un nuevo libro.
     * @param libro El libro a crear.
     * @return Una respuesta HTTP con el libro creado o un mensaje de error.
     */
    @POST
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Libros libro) {
        try {
            gLibros.createLibro(libro);
            return Response.ok(libro).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Error en BD")).build();
        }
    }

    /**
     * Actualiza un libro existente.
     * @param libro El libro a actualizar.
     * @return Una respuesta HTTP con el libro actualizado o un mensaje de error.
     */
    @PUT
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Libros libro) {
        try {
            gLibros.updateLibro(libro);
            return Response.ok(libro).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Error en BD")).build();
        }
    }

    /**
     * Elimina un libro por su ID.
     * @param id El ID del libro a eliminar.
     * @return Una respuesta HTTP con el ID del libro eliminado o un mensaje de error.
     */
    @DELETE
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@QueryParam("id") int id) {
        try {
            gLibros.deleteLibro(id);
            return Response.ok(id).build();
        } catch (Exception e) {
            return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Error en BD")).build();
        }
    }

    /**
     * Obtiene un libro por su ID.
     * @param id El ID del libro.
     * @return El libro encontrado o null si ocurre un error.
     */
    @GET
    @Path("/{id}")
    public Libros get(int id) {
        try {
            return gLibros.getLibro(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Lista todos los libros.
     * @return Una lista de todos los libros.
     */
    @GET
    @Produces("application/json")
    public List<Libros> list() {
        return gLibros.getLibros();
    }
}
