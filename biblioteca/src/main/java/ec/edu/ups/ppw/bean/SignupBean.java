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

    // Getters and Setters for username, email, and password

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

    // Getters and setters for username, email, and password
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}