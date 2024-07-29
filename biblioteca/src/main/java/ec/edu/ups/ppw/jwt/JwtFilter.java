package ec.edu.ups.ppw.jwt;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filtro para validar los tokens JWT en las solicitudes HTTP.
 */
public class JwtFilter implements Filter {

    private JwtProvider jwtProvider;

    /**
     * Constructor que inicializa el filtro con un proveedor de JWT.
     * @param jwtProvider El proveedor de JWT.
     */
    public JwtFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    /**
     * Filtra cada solicitud HTTP para validar el token JWT.
     * @param req La solicitud HTTP.
     * @param res La respuesta HTTP.
     * @param chain La cadena de filtros.
     * @throws IOException Si ocurre un error de entrada/salida.
     * @throws ServletException Si ocurre un error en el servlet.
     * @throws java.io.IOException Si ocurre un error de entrada/salida.
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException, java.io.IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String token = jwtProvider.resolveToken(request);

        if (token != null && jwtProvider.validateToken(token)) {
            // Aquí puedes autenticar al usuario si lo necesitas
            // Por ejemplo, puedes obtener los claims del token y establecer un contexto de seguridad
        } else {
            try {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        try {
            chain.doFilter(req, res);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inicializa el filtro.
     * @param filterConfig La configuración del filtro.
     * @throws ServletException Si ocurre un error en el servlet.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    /**
     * Destruye el filtro.
     */
    @Override
    public void destroy() {}
}
