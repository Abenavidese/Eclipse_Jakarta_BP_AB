package ec.edu.ups.ppw.biblioteca;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Clase que representa un libro en la biblioteca.
 * Está mapeada a la tabla "TBL_LIBRO" en la base de datos.
 */
@Table(name = "TBL_LIBRO")
@Entity
public class Libros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lib_id")
    private int LibroId;

    @Column(name = "lib_titulo")
    private String Titulo;

    @Column(name = "lib_autor")
    private String Autor;

    @Column(name = "lib_descripcion")
    private String Descripcion;

    @Column(name = "lib_genero")
    private String Genero;

    @Column(name = "lib_editorial")
    private String Editorial;

    @Column(name = "lib_portada")
    private String portada;

    @Column(name = "lib_disponibilidad")
    private Boolean disponibilidad;

    /**
     * Obtiene la portada del libro.
     * @return La portada del libro.
     */
    public String getPortada() {
        return portada;
    }

    /**
     * Establece la portada del libro.
     * @param portada La portada del libro.
     */
    public void setPortada(String portada) {
        this.portada = portada;
    }

    /**
     * Obtiene la disponibilidad del libro.
     * @return La disponibilidad del libro.
     */
    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    /**
     * Establece la disponibilidad del libro.
     * @param disponibilidad La disponibilidad del libro.
     */
    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    /**
     * Obtiene el ID del libro.
     * @return El ID del libro.
     */
    public int getLibroId() {
        return LibroId;
    }

    /**
     * Establece el ID del libro.
     * @param libroId El ID del libro.
     */
    public void setLibroId(int libroId) {
        LibroId = libroId;
    }

    /**
     * Obtiene el título del libro.
     * @return El título del libro.
     */
    public String getTitulo() {
        return Titulo;
    }

    /**
     * Establece el título del libro.
     * @param titulo El título del libro.
     */
    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    /**
     * Obtiene el autor del libro.
     * @return El autor del libro.
     */
    public String getAutor() {
        return Autor;
    }

    /**
     * Establece el autor del libro.
     * @param autor El autor del libro.
     */
    public void setAutor(String autor) {
        Autor = autor;
    }

    /**
     * Obtiene la descripción del libro.
     * @return La descripción del libro.
     */
    public String getDescripcion() {
        return Descripcion;
    }

    /**
     * Establece la descripción del libro.
     * @param descripcion La descripción del libro.
     */
    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    /**
     * Obtiene el género del libro.
     * @return El género del libro.
     */
    public String getGenero() {
        return Genero;
    }

    /**
     * Establece el género del libro.
     * @param genero El género del libro.
     */
    public void setGenero(String genero) {
        Genero = genero;
    }

    /**
     * Obtiene la editorial del libro.
     * @return La editorial del libro.
     */
    public String getEditorial() {
        return Editorial;
    }

    /**
     * Establece la editorial del libro.
     * @param editorial La editorial del libro.
     */
    public void setEditorial(String editorial) {
        Editorial = editorial;
    }
}
