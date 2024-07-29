package ec.edu.ups.ppw.bean;

import java.util.HashSet;
import java.util.Set;
import ec.edu.ups.ppw.biblioteca.Rol;
import ec.edu.ups.ppw.biblioteca.Usuario;
import ec.edu.ups.ppw.biblioteca.dao.RolDAO;
import ec.edu.ups.ppw.biblioteca.dao.UsuarioDAO;
import ec.edu.ups.ppw.enums.RolNombre;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

/**
 * Bean de gestión de registro de usuarios.
 * Maneja el proceso de registro de nuevos usuarios.
 */
@Named
@RequestScoped
public class SignupBean {

    private String username;
    private String email;
    private String password;

    @EJB
    private UsuarioDAO usuarioDAO;

    @EJB
    private RolDAO rolDAO;

    /**
     * Método de registro de usuarios.
     * Crea un nuevo usuario con el rol de usuario estándar y lo inserta en la base de datos.
     * Redirige al login después del registro exitoso.
     * @return La página a la que redirigir después del registro.
     */
    @Transactional
    public String signup() {
        try {
            // Crear un conjunto de roles (suponiendo que ya existen en la base de datos)
            Set<Rol> roles = new HashSet<>();
            Rol rolUser = rolDAO.findByName(RolNombre.ROLE_USER);
            if (rolUser == null) {
                rolUser = new Rol(RolNombre.ROLE_USER);
                rolDAO.insert(rolUser);
            }
            roles.add(rolUser);

            // Crear un usuario de ejemplo
            Usuario usuario = new Usuario();
            usuario.setUsername(username);
            usuario.setPassword(password);
            usuario.setEmail(email);
            usuario.setRoles(roles);

            // Insertar el usuario en la base de datos
            usuarioDAO.insert(usuario);

            // Redirigir al login después del registro
            return "login.xhtml?faces-redirect=true";
        } catch (Exception e) {
            // Manejar errores de registro
            e.printStackTrace();
            return null;
        }
    }

    // Getters y setters para username, email, y password

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
     * Obtiene el correo electrónico del usuario.
     * @return El correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     * @param email El correo electrónico del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
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
