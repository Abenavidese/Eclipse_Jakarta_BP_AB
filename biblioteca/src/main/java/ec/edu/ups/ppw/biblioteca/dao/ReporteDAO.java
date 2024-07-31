package ec.edu.ups.ppw.biblioteca.dao;

import java.util.Date;
import java.util.List;
import ec.edu.ups.ppw.biblioteca.Libros;
import ec.edu.ups.ppw.biblioteca.Usuario;
import ec.edu.ups.ppw.biblioteca.Prestamo;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

/**
 * Clase que maneja las operaciones de acceso a datos para reportes.
 */
@Stateless
public class ReporteDAO {

    @PersistenceContext
    private EntityManager em;

 // Método en ReporteDAO para obtener la cantidad de préstamos por usuario
    public List<Object[]> findCantidadPrestamosPorUsuario() {
        String jpql = "SELECT p.usuario.username, COUNT(p) FROM Prestamo p GROUP BY p.usuario.username ORDER BY COUNT(p) DESC";
        return em.createQuery(jpql, Object[].class).getResultList();
    }
    
    
    public List<Libros> findLibrosMasPopulares() {
        String jpql = "SELECT p.libro, COUNT(p) AS prestamos FROM Prestamo p GROUP BY p.libro ORDER BY prestamos DESC";
        TypedQuery<Libros> query = em.createQuery(jpql, Libros.class);
        return query.getResultList();
    }


}
