package ec.edu.ups.ppw.biblioteca.services;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

/**
 * Filtro CORS (Cross-Origin Resource Sharing) para permitir solicitudes entre diferentes orígenes.
 * Este filtro añade los encabezados necesarios para permitir solicitudes CORS.
 */
@Provider
public class CORSFilter implements ContainerResponseFilter {

    /**
     * Método que se ejecuta para cada respuesta de una solicitud HTTP.
     * Añade los encabezados CORS a la respuesta.
     * @param requestContext El contexto de la solicitud.
     * @param responseContext El contexto de la respuesta.
     */
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }
}
