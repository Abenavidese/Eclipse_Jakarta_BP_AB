package ec.edu.ups.ppw.biblioteca.dao;

import java.util.List;
import ec.edu.ups.ppw.biblioteca.Libros;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

/**
 * Clase que maneja las operaciones de acceso a datos para la entidad Libros.
 */
@Stateless
public class LibroDAO {

    @PersistenceContext
    private EntityManager em;

    /**
     * Inserta un nuevo libro en la base de datos.
     * @param libro El libro a insertar.
     */
    public void insert(Libros libro) {
        em.persist(libro);
    }

    /**
     * Actualiza un libro existente en la base de datos.
     * @param libro El libro a actualizar.
     */
    public void update(Libros libro) {
        em.merge(libro);
    }

    /**
     * Elimina un libro de la base de datos por su ID.
     * @param id El ID del libro a eliminar.
     * @throws Exception Si el libro no se encuentra.
     */
	public void delete(int id) throws Exception{
	    Libros libros = this.read(id);
	    if (libros != null) {
	        em.remove(libros);
	        System.out.println("Eliminado");
	    } else {
	        throw new Exception("No se puede eliminar el libro. Libro con id " + id + " no encontrado.");
	    }
	}
	

    /**
     * Lee un libro de la base de datos por su ID.
     * @param id El ID del libro a leer.
     * @return El libro encontrado.
     * @throws Exception Si el libro no se encuentra.
     */
    public Libros read(int id) throws Exception {
        Libros libro = em.find(Libros.class, id);
        if (libro == null) {
            throw new Exception("Libro con id " + id + " no encontrado.");
        }
        return libro;
    }

    /**
     * Obtiene una lista de todos los libros en la base de datos.
     * @return Una lista de todos los libros.
     */
    public List<Libros> getAll() {
        String jpql = "SELECT l FROM Libros l";
        Query query = em.createQuery(jpql, Libros.class);
        return query.getResultList();
    }
}