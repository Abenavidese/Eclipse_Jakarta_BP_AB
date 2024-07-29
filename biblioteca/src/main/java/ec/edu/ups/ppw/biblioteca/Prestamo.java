package ec.edu.ups.ppw.biblioteca;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Clase que representa un préstamo de un libro en la biblioteca.
 * Está mapeada a la tabla "TBL_PRESTAMO" en la base de datos.
 */
@Entity
@Table(name = "TBL_PRESTAMO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pres_id")
    private int prestamoId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_inicio")
    private Date fechaPrestamo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_devolucion")
    private Date fechaDevolucion;

    @ManyToOne
    @JoinColumn(name = "lib_id")
    private Libros libro;

    @ManyToOne
    @JoinColumn(name = "usu_id")
    private Usuario usuario;
    
    @Column(name = "devuelto")
    private boolean devuelto;

    /**
     * Obtiene el ID del préstamo.
     * @return El ID del préstamo.
     */
    public int getPrestamoId() {
        return prestamoId;
    }

    /**
     * Establece el ID del préstamo.
     * @param prestamoId El ID del préstamo.
     */
    public void setPrestamoId(int prestamoId) {
        this.prestamoId = prestamoId;
    }

    /**
     * Obtiene la fecha de inicio del préstamo.
     * @return La fecha de inicio del préstamo.
     */

    /**
     * Obtiene la fecha de devolución del préstamo.
     * @return La fecha de devolución del préstamo.
     */
    

    public Date getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(Date fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	/**
     * Establece la fecha de devolución del préstamo.
     * @param fechaDevolucion La fecha de devolución del préstamo.
     */
   

    /**
     * Obtiene el libro asociado al préstamo.
     * @return El libro asociado al préstamo.
     */
    public Libros getLibro() {
        return libro;
    }

    public Date getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	/**
     * Establece el libro asociado al préstamo.
     * @param libro El libro asociado al préstamo.
     */
    public void setLibro(Libros libro) {
        this.libro = libro;
    }

    /**
     * Obtiene el usuario que realizó el préstamo.
     * @return El usuario que realizó el préstamo.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario que realizó el préstamo.
     * @param usuario El usuario que realizó el préstamo.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    /**
     * Verifica si el préstamo ha sido devuelto.
     * @return true si el préstamo ha sido devuelto, false en caso contrario.
     */
    public boolean isDevuelto() {
        return devuelto;
    }

    /**
     * Establece si el préstamo ha sido devuelto.
     * @param devuelto true si el préstamo ha sido devuelto, false en caso contrario.
     */
    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }
}
