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
 * Clase que representa una reserva de un libro en la biblioteca.
 * Está mapeada a la tabla "TBL_RESERVA" en la base de datos.
 */
@Entity
@Table(name = "TBL_RESERVADO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Reservado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_id")
    private int reservaId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_reserva")
    private Date fechaReserva;

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
     * Obtiene el ID de la reserva.
     * @return El ID de la reserva.
     */
    public int getReservaId() {
        return reservaId;
    }

    /**
     * Establece el ID de la reserva.
     * @param reservaId El ID de la reserva.
     */
    public void setReservaId(int reservaId) {
        this.reservaId = reservaId;
    }

    /**
     * Obtiene la fecha de la reserva.
     * @return La fecha de la reserva.
     */
    public Date getFechaReserva() {
        return fechaReserva;
    }

    /**
     * Establece la fecha de la reserva.
     * @param fechaReserva La fecha de la reserva.
     */
    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    /**
     * Obtiene la fecha de devolución de la reserva.
     * @return La fecha de devolución de la reserva.
     */
    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    /**
     * Establece la fecha de devolución de la reserva.
     * @param fechaDevolucion La fecha de devolución de la reserva.
     */
    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    /**
     * Obtiene el libro asociado a la reserva.
     * @return El libro asociado a la reserva.
     */
    public Libros getLibro() {
        return libro;
    }

    /**
     * Establece el libro asociado a la reserva.
     * @param libro El libro asociado a la reserva.
     */
    public void setLibro(Libros libro) {
        this.libro = libro;
    }

    /**
     * Obtiene el usuario que realizó la reserva.
     * @return El usuario que realizó la reserva.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario que realizó la reserva.
     * @param usuario El usuario que realizó la reserva.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public boolean isDevuelto() {
        return devuelto;
    }

   
    public void setDevuelto (boolean devuelto) {
        this.devuelto = devuelto;
    }

}