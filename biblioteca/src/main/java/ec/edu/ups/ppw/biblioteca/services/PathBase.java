package ec.edu.ups.ppw.biblioteca.services;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Clase de configuración para la aplicación JAX-RS.
 * Define la ruta base para los servicios RESTful.
 */
@ApplicationPath("rs")
public class PathBase extends Application {
    // Esta clase se utiliza para definir la configuración de la aplicación JAX-RS.
    // La anotación @ApplicationPath define la ruta base de todos los recursos JAX-RS.
}
