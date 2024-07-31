package ec.edu.ups.ppw.biblioteca.model;

import java.util.Date;
import java.util.List;
import ec.edu.ups.ppw.biblioteca.Libros;
import ec.edu.ups.ppw.biblioteca.Usuario;
import ec.edu.ups.ppw.biblioteca.dao.ReporteDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

/**
 * Clase que maneja la lógica de negocio para la generación de reportes.
 */
@Stateless
public class GestionReporte {

    @Inject
    private ReporteDAO daoReporte;

    public List<Object[]> getCantidadPrestamosPorUsuario() {
        return daoReporte.findCantidadPrestamosPorUsuario();
    }
    
    
    public List<Libros> getLibrosMasPopulares() {
        return daoReporte.findLibrosMasPopulares();
    }
}
