package ec.edu.ups.ppw.biblioteca.model;

import java.util.List;

import ec.edu.ups.ppw.biblioteca.Libros;
import ec.edu.ups.ppw.biblioteca.Reservado;
import ec.edu.ups.ppw.biblioteca.dao.ReservadoDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

/**
 * Clase que maneja la lógica de negocio para la gestión de reservas.
 */
@Stateless
public class GestionReserva {

    @Inject
    private ReservadoDAO daoReserva;

    /**
     * Obtiene una reserva por su ID.
     * @param id El ID de la reserva.
     * @return La reserva encontrada.
     * @throws Exception Si la reserva no se encuentra.
     */
    public Reservado getReserva(int id) throws Exception {
        Reservado reserva = daoReserva.read(id);
        if (reserva == null)
            throw new Exception("Reserva no existe");
        return reserva;
    }

    /**
     * Obtiene una lista de todas las reservas.
     * @return Una lista de todas las reservas.
     */
    public List<Reservado> getReservas() {
        return daoReserva.getAll();
    }

    /**
     * Obtiene una lista de todas las reservas activas (no canceladas).
     * @return Una lista de todas las reservas activas.
     */
    public List<Reservado> getReservasActivas() {
        return daoReserva.getReservasActivas();
    }

    /**
     * Crea una nueva reserva.
     * @param reserva La reserva a crear.
     * @throws Exception Si el ID de la reserva es incorrecto.
     */
    public void createReserva(Reservado reserva) throws Exception {
        if (reserva.getReservaId() < 0) {
            throw new Exception("Id Incorrecto");
        }
        daoReserva.insert(reserva);
    }

    /**
     * Actualiza una reserva existente.
     * @param reserva La reserva a actualizar.
     * @throws Exception Si el ID de la reserva es incorrecto.
     */
    public void updateReserva(Reservado reserva) throws Exception {
        if (reserva.getReservaId() < 0) {
            throw new Exception("Id Incorrecta");
        }
        daoReserva.update(reserva);
    }


    /**
     * Elimina una reserva por su ID.
     * @param id El ID de la reserva a eliminar.
     * @throws Exception Si la reserva no se encuentra.
     */
    public void deleteReserva(int id) throws Exception {
        Reservado reserva = daoReserva.read(id);
        if (reserva == null) {
            throw new Exception("Reserva no existe");
        } else {
            daoReserva.delete(id);
        }
    }
    
    
    
    public List<Reservado> getReservasActivasDelUsuario(String username) {
        return daoReserva.getReservasActivasPorUsuario(username);
    }


    /**
     * Marca una reserva como cancelada y actualiza la disponibilidad del libro.
     * @param id El ID de la reserva a cancelar.
     * @throws Exception Si la reserva no se encuentra.
     */
    public void cancelarReserva(int id) throws Exception {
        Reservado reserva = daoReserva.read(id);
        if (reserva == null) {
            throw new Exception("Reserva no existe");
        }
        reserva.setDevuelto(true); // Marcar la reserva como cancelada
        daoReserva.update(reserva);

        // Actualizar la disponibilidad del libro y el estado de reserva
        Libros libro = reserva.getLibro();
        libro.setDisponibilidad(true); // Marcar el libro como disponible
        libro.setReservado(false); // Marcar el libro como no reservado
        daoReserva.update(libro);
    }


}
