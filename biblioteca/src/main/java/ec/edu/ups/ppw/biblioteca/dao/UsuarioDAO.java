package ec.edu.ups.ppw.biblioteca.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import ec.edu.ups.ppw.biblioteca.Rol;
import ec.edu.ups.ppw.biblioteca.Usuario;
import ec.edu.ups.ppw.enums.RolNombre;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

/**
 * Clase que maneja las operaciones de acceso a datos para la entidad Usuario.
 */
@Stateless
public class UsuarioDAO {

    @PersistenceContext
    private EntityManager em;

    /**
     * Inserta un nuevo usuario en la base de datos.
     * @param usuario El usuario a insertar.
     */
    public void insert(Usuario usuario) {
        em.persist(usuario);
    }

    /**
     * Actualiza la información de un usuario en la base de datos.
     * @param usuario El usuario a actualizar.
     */
    public void update(Usuario usuario) {
        em.merge(usuario);
    }

    /**
     * Elimina un usuario de la base de datos por su ID.
     * @param id El ID del usuario a eliminar.
     * @throws Exception Si el usuario no se encuentra.
     */
    public void delete(int id) throws Exception {
        Usuario usuario = this.read(id);
        if (usuario != null) {
            em.remove(usuario);
            System.out.println("Eliminado");
        } else {
            throw new Exception("No se puede eliminar el usuario. Usuario con id " + id + " no encontrado.");
        }
    }

    /**
     * Lee un usuario de la base de datos por su ID.
     * @param id El ID del usuario a leer.
     * @return El usuario encontrado.
     * @throws Exception Si el usuario no se encuentra.
     */
    public Usuario read(int id) throws Exception {
        Usuario usuario = em.find(Usuario.class, id);
        if (usuario == null) {
            throw new Exception("Usuario con id " + id + " no encontrado.");
        }
        // Los roles se cargan automáticamente debido a FetchType.EAGER
        return usuario;
    }

    /**
     * Obtiene una lista de todos los usuarios en la base de datos.
     * @return Una lista de todos los usuarios.
     */
    public List<Usuario> getAll() {
        String jpql = "SELECT u FROM Usuario u LEFT JOIN FETCH u.roles";
        TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
        return query.getResultList();
    }

    /**
     * Valida las credenciales de un usuario.
     * @param username El nombre de usuario.
     * @param password La contraseña del usuario.
     * @return true si las credenciales son válidas, false en caso contrario.
     */
    public static boolean validar(String username, String password) {
        return (username.equals("admin") && password.equals("admin"));
    }

    /**
     * Obtiene un usuario basado en su nombre de usuario y contraseña.
     * @param username El nombre de usuario.
     * @param password La contraseña del usuario.
     * @return El usuario encontrado.
     */
    public static Usuario obtenerUsuario(String username, String password) {
        Set<Rol> roles = new HashSet<>();
        
        if (validar(username, password)) {
            roles.add(new Rol(RolNombre.ROLE_ADMIN));
            return new Usuario(1, "admin", "admin", roles, "admin@gmail");
        } else {
            roles.add(new Rol(RolNombre.ROLE_USER));
            return new Usuario(2, username, password, roles, "user@gmail.com");
        }
    }

    /**
     * Valida un usuario basado en su nombre de usuario y contraseña.
     * @param username El nombre de usuario.
     * @param password La contraseña del usuario.
     * @return El usuario encontrado o null si no se encuentra.
     */
    public Usuario validateUser(String username, String password) {
        try {
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.username = :username AND u.password = :password", Usuario.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (Exception e) {
            return null; // Usuario no encontrado o contraseña incorrecta
        }
    }
}
