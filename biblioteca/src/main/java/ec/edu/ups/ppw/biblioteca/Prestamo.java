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
    @Column(name="pres_id")
    private int prestamoId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_inicio")
    private Date fechaInicio;
    
   

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_devolucion")
    private Date fechaDevolucion;

    @ManyToOne
    @JoinColumn(name="lib_id")
    private Libros libro;

    @ManyToOne
    @JoinColumn(name="usu_id")
    private Usuario usuario;

    // Getters y setters
    public int getPrestamoId() {
        return prestamoId;
    }

    public void setPrestamoId(int prestamoId) {
        this.prestamoId = prestamoId;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaPrestamo) {
        this.fechaInicio = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Libros getLibro() {
        return libro;
    }

    public void setLibro(Libros libro) {
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}