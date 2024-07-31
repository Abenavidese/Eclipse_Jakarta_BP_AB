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

    /**
     * Encuentra los libros más solicitados en un rango de fechas.
     * @param startDate La fecha de inicio del rango.
     * @param endDate La fecha de fin del rango.
     * @return Una lista de los libros más solicitados.
     */
    public List<Libros> findLibrosMasSolicitados(Date startDate, Date endDate) {
        String jpql = "SELECT p.libro FROM Prestamo p WHERE p.fechaPrestamo BETWEEN :startDate AND :endDate GROUP BY p.libro ORDER BY COUNT(p) DESC";
        TypedQuery<Libros> query = em.createQuery(jpql, Libros.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

    /**
     * Encuentra los usuarios más activos en un rango de fechas.
     * @param startDate La fecha de inicio del rango.
     * @param endDate La fecha de fin del rango.
     * @return Una lista de los usuarios más activos.
     */
    public List<Usuario> findUsuariosMasActivos(Date startDate, Date endDate) {
        String jpql = "SELECT p.usuario FROM Prestamo p WHERE p.fechaPrestamo BETWEEN :startDate AND :endDate GROUP BY p.usuario ORDER BY COUNT(p) DESC";
        TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }
}
