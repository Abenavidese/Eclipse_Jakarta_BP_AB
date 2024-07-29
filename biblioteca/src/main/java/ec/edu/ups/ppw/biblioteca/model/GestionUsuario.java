package ec.edu.ups.ppw.biblioteca.model;

import java.util.List;
import ec.edu.ups.ppw.biblioteca.Usuario;
import ec.edu.ups.ppw.biblioteca.dao.UsuarioDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

/**
 * Clase que maneja la lógica de negocio para la gestión de usuarios.
 */
@Stateless
public class GestionUsuario {

    @PersistenceContext(unitName = "bibliotecaPersistenceUnit")
    private EntityManager em;

    @Inject
    private UsuarioDAO daoUsuario;

    /**
     * Obtiene un usuario por su ID.
     * @param id El ID del usuario.
     * @return El usuario encontrado.
     * @throws Exception Si el usuario no se encuentra.
     */
    public Usuario getUsuario(int id) throws Exception {
        Usuario usuario = daoUsuario.read(id);
        if (usuario == null) {
            throw new Exception("Usuario no existe");
        }
        return usuario;
    }

    /**
     * Obtiene una lista de todos los usuarios.
     * @return Una lista de todos los usuarios.
     */
    public List<Usuario> getUsuarios() {
        return daoUsuario.getAll();
    }

    /**
     * Crea un nuevo usuario.
     * @param usuario El usuario a crear.
     * @throws Exception Si el ID del usuario es incorrecto.
     */
    public void createUsuario(Usuario usuario) throws Exception {
        if (usuario.getId() < 0) {
            throw new Exception("Id Incorrecto");
        }
        daoUsuario.insert(usuario);
    }

    /**
     * Actualiza un usuario existente.
     * @param usuario El usuario a actualizar.
     * @throws Exception Si el ID del usuario es incorrecto.
     */
    public void updateUsuario(Usuario usuario) throws Exception {
        if (usuario.getId() < 0) {
            throw new Exception("Id Incorrecta");
        }
        daoUsuario.update(usuario);
    }

    /**
     * Elimina un usuario por su ID.
     * @param id El ID del usuario a eliminar.
     * @throws Exception Si el usuario no se encuentra.
     */
    public void deleteUsuario(int id) throws Exception {
        Usuario usuario = daoUsuario.read(id);
        if (usuario == null) {
            throw new Exception("Id no existe");
        } else {
            daoUsuario.delete(id);
        }
    }

    /**
     * Busca un usuario por su nombre de usuario.
     * @param username El nombre de usuario.
     * @return El usuario encontrado o null si no se encuentra.
     */
    public Usuario findByUsername(String username) {
        String jpql = "SELECT u FROM Usuario u WHERE u.username = :username";
        try {
            return em.createQuery(jpql, Usuario.class)
                     .setParameter("username", username)
                     .getSingleResult();
        } catch (Exception e) {
            // Manejar la excepción según tu aplicación
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Inicia sesión con el nombre de usuario y la contraseña.
     * @param username El nombre de usuario.
     * @param password La contraseña.
     * @return El usuario encontrado o null si las credenciales son incorrectas.
     */
    public Usuario iniciarSesion(String username, String password) {
        String jpql = "SELECT u FROM Usuario u WHERE u.username = :username AND u.password = :password";
        try {
            return em.createQuery(jpql, Usuario.class)
                     .setParameter("username", username)
                     .setParameter("password", password)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
