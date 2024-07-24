package ec.edu.ups.ppw.biblioteca;

import java.util.Date;

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


@Entity
@Table(name="TBL_PRESTAMO")
public class Prestamo {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pre_id")
    private int prestamoId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_prestamo")
    private Date fechaPrestamo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_devolucion")
    private Date fechaDevolucion;

    @ManyToOne
    @JoinColumn(name="lib_id")
    private Libros libros;

    @ManyToOne
    @JoinColumn(name="user_ID")
    private Usuario usuario;

    // Getters y setters
    public int getPrestamoId() {
        return prestamoId;
    }

    public void setPrestamoId(int prestamoId) {
        this.prestamoId = prestamoId;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }



    public Libros getLibros() {
		return libros;
	}

	public void setLibros(Libros libros) {
		this.libros = libros;
	}

	public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Prestamo [prestamoId=" + prestamoId + ", fechaPrestamo=" + fechaPrestamo + ", fechaDevolucion="
                + fechaDevolucion + ", libros=" + libros.getLibroId() + ", usuario=" + usuario.getId() + "]";
    }
}