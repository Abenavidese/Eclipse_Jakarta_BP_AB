package ec.edu.ups.ppw.bean;

import ec.edu.ups.ppw.biblioteca.Usuario;
import ec.edu.ups.ppw.biblioteca.dao.UsuarioDAO;
import ec.edu.ups.ppw.jwt.JwtProvider;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Path;

/**
 * Bean de gestión de inicio de sesión.
 * Maneja la autenticación de usuarios y la generación de tokens JWT.
 */
@Named
@RequestScoped
@Path("/auth1")
public class LoginBean {

    private String username;
    private String password;

    @Inject
    private UsuarioDAO usuarioDAO;
    
    @Inject
    private JwtProvider jwtProvider; // Inyectar JwtProvider

    /**
     * Método de inicio de sesión.
     * Valida las credenciales del usuario y genera un token JWT si son correctas.
     * Redirige al usuario a la aplicación Angular con el token JWT o a una página de error en caso de fallo.
     */
    public void login() {
        Usuario usuario = usuarioDAO.validateUser(username, password);
        if (usuario != null) {
            String jwt = jwtProvider.createToken(username, usuario.getRoles()); // Generar el JWT

            // Redirigir a Angular con el token JWT
            try {
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                response.sendRedirect("http://localhost:4200/inicio?token=" + jwt);
                FacesContext.getCurrentInstance().responseComplete(); // Marcar la respuesta como completada
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Redirigir a la página de error
            try {
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                response.sendRedirect("failure.xhtml");
                FacesContext.getCurrentInstance().responseComplete(); // Marcar la respuesta como completada
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Obtiene el nombre de usuario.
     * @return El nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     * @param username El nombre de usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene la contraseña del usuario.
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     * @param password La contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
