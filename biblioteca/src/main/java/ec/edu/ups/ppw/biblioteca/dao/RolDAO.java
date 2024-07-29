package ec.edu.ups.ppw.biblioteca.dao;

import java.util.List;
import ec.edu.ups.ppw.biblioteca.Rol;
import ec.edu.ups.ppw.enums.RolNombre;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

/**
 * Clase que maneja las operaciones de acceso a datos para la entidad Rol.
 */
@Stateless
public class RolDAO {
    
    @PersistenceContext
    private EntityManager em;

    /**
     * Inserta un nuevo rol en la base de datos.
     * @param rol El rol a insertar.
     */
    public void insert(Rol rol) {
        em.persist(rol);
    }

    /**
     * Actualiza la información de un rol en la base de datos.
     * @param rol El rol a actualizar.
     */
    public void update(Rol rol) {
        em.merge(rol);
    }

    /**
     * Lee un rol de la base de datos por su ID.
     * @param id El ID del rol a leer.
     * @return El rol encontrado.
     */
    public Rol read(int id) {
        return em.find(Rol.class, id);
    }

    /**
     * Elimina un rol de la base de datos por su ID.
     * @param id El ID del rol a eliminar.
     */
    public void delete(int id) {
        Rol rol = this.read(id);
        if (rol != null) {
            em.remove(rol);
        }
    }

    /**
     * Obtiene una lista de todos los roles en la base de datos.
     * @return Una lista de todos los roles.
     */
    public List<Rol> getAll() {
        String jpql = "SELECT r FROM Rol r";
        Query query = em.createQuery(jpql, Rol.class);
        return query.getResultList();
    }

    /**
     * Busca un rol por su nombre en la base de datos.
     * @param rolNombre El nombre del rol a buscar.
     * @return El rol encontrado o null si no se encuentra.
     */
    public Rol findByName(RolNombre rolNombre) {
        TypedQuery<Rol> query = em.createQuery("SELECT r FROM Rol r WHERE r.rolNombre = :rolNombre", Rol.class);
        query.setParameter("rolNombre", rolNombre);
        List<Rol> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
}
