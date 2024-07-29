package ec.edu.ups.ppw.biblioteca.dao;

import java.util.List;
import ec.edu.ups.ppw.biblioteca.Libros;
import ec.edu.ups.ppw.biblioteca.Prestamo;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

/**
 * Clase que maneja las operaciones de acceso a datos para la entidad Prestamo.
 */
@Stateless
public class PrestamoDAO {

    @PersistenceContext
    private EntityManager em;
    
    /**
     * Actualiza la información de un libro en la base de datos.
     * @param libros El libro a actualizar.
     */
    public void update(Libros libros) {
        em.merge(libros);
    }

    /**
     * Inserta un nuevo préstamo en la base de datos.
     * @param prestamo El préstamo a insertar.
     */
    public void insert(Prestamo prestamo) {
        em.persist(prestamo);
    }

    /**
     * Actualiza la información de un préstamo en la base de datos.
     * @param prestamo El préstamo a actualizar.
     */
    public void update(Prestamo prestamo) {
        em.merge(prestamo);
    }

    /**
     * Elimina un préstamo de la base de datos por su ID.
     * @param id El ID del préstamo a eliminar.
     * @throws Exception Si el préstamo no se encuentra.
     */
    public void delete(int id) throws Exception {
        Prestamo prestamo = this.read(id);
        if (prestamo != null) {
            em.remove(prestamo);
            System.out.println("Eliminado");
        } else {
            throw new Exception("No se puede eliminar, prestamo con id " + id + " no encontrado.");
        }
    }
    public void marcarComoDevuelto(int id) throws Exception {
        Prestamo prestamo = this.read(id);
        if (prestamo == null) {
            throw new Exception("Prestamo no existe");
        }
        prestamo.setDevuelto(true);
        em.merge(prestamo);
    }

    /**
     * Lee un préstamo de la base de datos por su ID.
     * @param id El ID del préstamo a leer.
     * @return El préstamo encontrado.
     * @throws Exception Si el préstamo no se encuentra.
     */
    public Prestamo read(int id) throws Exception {
        Prestamo prestamo = em.find(Prestamo.class, id);
        if (prestamo == null) {
            throw new Exception("Prestamo con id " + id + " no encontrado.");
        }
        return prestamo;
    }

    /**
     * Obtiene una lista de todos los préstamos en la base de datos.
     * @return Una lista de todos los préstamos.
     */
    public List<Prestamo> getAll() {
        String jpql = "SELECT p FROM Prestamo p";
        Query query = em.createQuery(jpql, Prestamo.class);
        return query.getResultList();
    }

    /**
     * Obtiene una lista de todos los préstamos activos (no devueltos) en la base de datos.
     * @return Una lista de todos los préstamos activos.
     */
    public List<Prestamo> getPrestamosActivos() {
        String jpql = "SELECT p FROM Prestamo p WHERE p.devuelto = false";
        TypedQuery<Prestamo> query = em.createQuery(jpql, Prestamo.class);
        return query.getResultList();
    }
}