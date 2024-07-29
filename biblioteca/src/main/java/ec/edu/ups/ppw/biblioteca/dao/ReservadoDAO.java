package ec.edu.ups.ppw.biblioteca.dao;

import java.util.List;
import ec.edu.ups.ppw.biblioteca.Libros;
import ec.edu.ups.ppw.biblioteca.Prestamo;
import ec.edu.ups.ppw.biblioteca.Reservado;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

/**
 * Clase que maneja las operaciones de acceso a datos para la entidad Reserva.
 */
@Stateless
public class ReservadoDAO {

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
     * Inserta una nueva reserva en la base de datos.
     * @param reserva La reserva a insertar.
     */
    public void insert(Reservado reserva) {
        em.persist(reserva);
    }

    /**
     * Actualiza la información de una reserva en la base de datos.
     * @param reserva La reserva a actualizar.
     */
    public void update(Reservado reserva) {
        em.merge(reserva);
    }

    /**
     * Elimina una reserva de la base de datos por su ID.
     * @param id El ID de la reserva a eliminar.
     * @throws Exception Si la reserva no se encuentra.
     */
    public void delete(int id) throws Exception {
        Reservado reserva = this.read(id);
        if (reserva != null) {
            em.remove(reserva);
            System.out.println("Eliminado");
        } else {
            throw new Exception("No se puede eliminar, reserva con id " + id + " no encontrada.");
        }
    }
    public void marcarComoDevuelto(int id) throws Exception {
        Reservado reservado = this.read(id);
        if (reservado == null) {
            throw new Exception("Prestamo no existe");
        }
        reservado.setDevuelto(true);
        em.merge(reservado);
    }

    /**
     * Lee una reserva de la base de datos por su ID.
     * @param id El ID de la reserva a leer.
     * @return La reserva encontrada.
     * @throws Exception Si la reserva no se encuentra.
     */
    public Reservado read(int id) throws Exception {
        Reservado reserva = em.find(Reservado.class, id);
        if (reserva == null) {
            throw new Exception("Reserva con id " + id + " no encontrada.");
        }
        return reserva;
    }

    /**
     * Obtiene una lista de todas las reservas en la base de datos.
     * @return Una lista de todas las reservas.
     */
    public List<Reservado> getAll() {
        String jpql = "SELECT r FROM Reservado r";
        Query query = em.createQuery(jpql, Reservado.class);
        return query.getResultList();
    }


    /**
     * Obtiene una lista de todas las reservas activas (no canceladas) en la base de datos.
     * @return Una lista de todas las reservas activas.
     */
    public List<Reservado> getReservasActivas() {
    	String jpql = "SELECT r FROM Reservado r WHERE r.devuelto = false";
        TypedQuery<Reservado> query = em.createQuery(jpql, Reservado.class);
        return query.getResultList();
    }
}
