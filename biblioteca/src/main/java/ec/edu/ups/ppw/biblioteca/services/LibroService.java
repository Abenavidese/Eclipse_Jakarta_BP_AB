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

@Path("/libros")
public class LibroService {

	@Inject
	private GestionLibro gLibros;
	
	@POST
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Libros libro) {
		try {
			gLibros.createLibro(libro);
			return Response.ok(libro).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Error en BD")).build();
		}
	}
	
	@PUT
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Libros libro) {
		try {
			gLibros.updateLibro(libro);
			return Response.ok(libro).build();
		} catch (Exception e) {
			// TODO: handle exceptione.printStackTrace();
			return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Error en BD")).build();
		}
		
	}
	
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
	
	@GET
	@Path("/{id}")
	public Libros get(int id) {
		Libros libro;
		try {
			libro = gLibros.getLibro(id);
			return libro;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GET
	@Produces("application/json")
	public List<Libros> list(){
		List<Libros> libros = gLibros.getLibros();
		return libros;
	}
}