package ec.edu.ups.ppw.biblioteca.model;

import java.util.List;
import ec.edu.ups.ppw.biblioteca.Prestamo;
import ec.edu.ups.ppw.biblioteca.dao.PrestamoDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

/**
 * Clase que maneja la lógica de negocio para la gestión de préstamos.
 */
@Stateless
public class GestionPrestamo {

    @Inject
    private PrestamoDAO daoPrestamo;

    /**
     * Obtiene un préstamo por su ID.
     * @param id El ID del préstamo.
     * @return El préstamo encontrado.
     * @throws Exception Si el préstamo no se encuentra.
     */
    public Prestamo getPrestamo(int id) throws Exception {
        Prestamo prestamo = daoPrestamo.read(id);
        if (prestamo == null)
            throw new Exception("Prestamo no existe");
        return prestamo;
    }

    /**
     * Obtiene una lista de todos los préstamos.
     * @return Una lista de todos los préstamos.
     */
    public List<Prestamo> getPrestamos() {
        return daoPrestamo.getAll();
    }

    /**
     * Obtiene una lista de todos los préstamos activos (no devueltos).
     * @return Una lista de todos los préstamos activos.
     */
    public List<Prestamo> getPrestamosActivos() {
        return daoPrestamo.getPrestamosActivos();
    }

    /**
     * Crea un nuevo préstamo.
     * @param prestamo El préstamo a crear.
     * @throws Exception Si el ID del préstamo es incorrecto.
     */
    public void createPrestamo(Prestamo prestamo) throws Exception {
        if (prestamo.getPrestamoId() < 0) {
            throw new Exception("Id Incorrecto");
        }
        daoPrestamo.insert(prestamo);
    }

    /**
     * Actualiza un préstamo existente.
     * @param prestamo El préstamo a actualizar.
     * @throws Exception Si el ID del préstamo es incorrecto.
     */
    public void updatePrestamo(Prestamo prestamo) throws Exception {
        if (prestamo.getPrestamoId() < 0) {
            throw new Exception("Id Incorrecta");
        }
        daoPrestamo.update(prestamo);
    }

    /**
     * Elimina un préstamo por su ID.
     * @param id El ID del préstamo a eliminar.
     * @throws Exception Si el préstamo no se encuentra.
     */
    public void deletePrestamo(int id) throws Exception {
        Prestamo prestamo = daoPrestamo.read(id);
        if (prestamo == null) {
            throw new Exception("Prestamo no existe");
        } else {
            daoPrestamo.delete(id);
        }
    }
    
    /**
     * Marca un préstamo como devuelto y actualiza la disponibilidad del libro.
     * @param id El ID del préstamo a devolver.
     * @throws Exception Si el préstamo no se encuentra.
     */
    public void returnPrestamo(int id) throws Exception {
        Prestamo prestamo = daoPrestamo.read(id);
        if (prestamo == null) {
            throw new Exception("Prestamo no existe");
        }
        prestamo.setDevuelto(true);
        daoPrestamo.update(prestamo);

        // Actualizar la disponibilidad del libro
        prestamo.getLibro().setDisponibilidad(true);
        daoPrestamo.update(prestamo.getLibro());
    }
}
