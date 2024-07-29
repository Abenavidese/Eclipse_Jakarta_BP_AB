package ec.edu.ups.ppw.biblioteca;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * Clase que representa un usuario en el sistema.
 * Está mapeada a la tabla "TBL_USUARIO" en la base de datos.
 */
@Table(name = "TBL_USUARIO")
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_ID")
    private int id;

    @NotNull
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    @Column(name = "user_password")
    private String password;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER) // Añadir FetchType.EAGER para cargar los roles junto con el usuario
    @JoinTable(
        name = "usuario_rol", // Nombre de la tabla intermedia
        joinColumns = @JoinColumn(name = "usuario_id"), // Columna de Usuario
        inverseJoinColumns = @JoinColumn(name = "rol_id") // Columna de Rol
    )
    private Set<Rol> roles = new HashSet<>();

    @NotNull
    @Column(unique = true)
    private String email;

    /**
     * Constructor por defecto.
     */
    public Usuario() {}

    /**
     * Constructor que inicializa todos los atributos del usuario.
     * @param id El ID del usuario.
     * @param username El nombre de usuario.
     * @param password La contraseña del usuario.
     * @param roles El conjunto de roles del usuario.
     * @param email El correo electrónico del usuario.
     */
    public Usuario(int id, @NotNull String username, @NotNull String password, @NotNull Set<Rol> roles,
                   @NotNull String email) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.email = email;
    }

    /**
     * Obtiene el ID del usuario.
     * @return El ID del usuario.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del usuario.
     * @param id El ID del usuario.
     */
    public void setId(int id) {
        this.id = id;
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

    /**
     * Obtiene el conjunto de roles del usuario.
     * @return El conjunto de roles del usuario.
     */
    public Set<Rol> getRoles() {
        return roles;
    }

    /**
     * Establece el conjunto de roles del usuario.
     * @param roles El conjunto de roles del usuario.
     */
    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
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
}
