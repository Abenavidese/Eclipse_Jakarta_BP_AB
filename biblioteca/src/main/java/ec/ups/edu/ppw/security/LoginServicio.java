package ec.ups.edu.ppw.security;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ec.edu.ups.ppw.biblioteca.Libros;
import ec.edu.ups.ppw.biblioteca.Rol;
import ec.edu.ups.ppw.biblioteca.Usuario;
import ec.edu.ups.ppw.biblioteca.dao.UsuarioDAO;
import ec.edu.ups.ppw.biblioteca.model.GestionUsuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/auth")
public class LoginServicio {
	
	

	@Inject
	private GestionUsuario gUsuarios;

    private static final String SECRET_KEY = "Miclave12341111AleMiclave12341111Ale123213322Miclave12341111AleMiclave12341111Ale123213322";
    private static final long TOKEN_VALIDITY = 900000; // 15 minutos en milisegundos

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validar(Usuario usuario) {
        Usuario usuarioBD = UsuarioDAO.obtenerUsuario(usuario.getUsername(), usuario.getPassword());

        if (usuarioBD != null) {
            long currentTimeMillis = System.currentTimeMillis();
            Set<Rol> roles = usuarioBD.getRoles();
            String rolesString = roles.stream()
                                      .map(rol -> rol.getRolNombre().name())
                                      .collect(Collectors.joining(","));

            // Generar el token JWT
            String jwt = Jwts.builder()
                    .setSubject(usuario.getUsername())
                    .claim("roles", rolesString)
                    .setIssuedAt(new Date(currentTimeMillis))
                    .setExpiration(new Date(currentTimeMillis + TOKEN_VALIDITY))
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .compact();



            // Construir el JSON de respuesta con el token y los roles
            JsonObject json = Json.createObjectBuilder()
                                  .add("JWT", jwt)
                                  .add("roles", rolesString)
                                  .build();
            
            // Imprimir el token en la consola
            System.out.println("Generated JWT: " + json);
            System.out.println("Generated JWT: " + jwt);

            // Retornar una respuesta con el JSON y estado 201 (CREATED)
            return Response.status(Response.Status.CREATED).entity(json).build();
        }

        // Si las credenciales no son válidas, retornar UNAUTHORIZED
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
    

    

	@GET
	@Produces("application/json")
	public List<Usuario> list(){
		List<Usuario> usuarios = gUsuarios.getUsuarios();
		return usuarios;
	}
}
