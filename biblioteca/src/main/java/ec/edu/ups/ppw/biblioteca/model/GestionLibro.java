package ec.edu.ups.ppw.biblioteca.model;

import java.util.List;
import ec.edu.ups.ppw.biblioteca.Libros;
import ec.edu.ups.ppw.biblioteca.dao.LibroDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

/**
 * Clase que maneja la lógica de negocio para la gestión de libros.
 */
@Stateless
public class GestionLibro {

    @Inject
    private LibroDAO daoLibro;

    /**
     * Obtiene un libro por su ID.
     * @param id El ID del libro.
     * @return El libro encontrado.
     * @throws Exception Si el libro no se encuentra.
     */
    public Libros getLibro(int id) throws Exception {
        Libros libro = daoLibro.read(id);
        if (libro == null) {
            throw new Exception("Libro no existe");
        }
        return libro;
    }

    /**
     * Obtiene una lista de todos los libros.
     * @return Una lista de todos los libros.
     */
    public List<Libros> getLibros() {
        return daoLibro.getAll();
    }

    /**
     * Crea un nuevo libro.
     * @param libro El libro a crear.
     * @throws Exception Si el ID del libro es incorrecto.
     */
    public void createLibro(Libros libro) throws Exception {
        if (libro.getLibroId() < 0) {
            throw new Exception("Id Incorrecto");
        }
        daoLibro.insert(libro);
    }

    /**
     * Actualiza un libro existente.
     * @param libro El libro a actualizar.
     * @throws Exception Si el ID del libro es incorrecto.
     */
    public void updateLibro(Libros libro) throws Exception {
        if (libro.getLibroId() < 0) {
            throw new Exception("Id Incorrecta");
        }
        daoLibro.update(libro);
    }

    /**
     * Elimina un libro por su ID.
     * @param id El ID del libro a eliminar.
     * @throws Exception Si el libro no se encuentra.
     */
    public void deleteLibro(int id) throws Exception {
        Libros libro = daoLibro.read(id);
        if (libro == null) {
            throw new Exception("Libro no existe");
        } else {
            daoLibro.delete(id);
        }
    }
}