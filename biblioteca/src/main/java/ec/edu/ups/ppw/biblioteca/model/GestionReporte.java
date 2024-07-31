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

    /**
     * Obtiene una lista de los libros más solicitados en un rango de fechas.
     * @param startDate La fecha de inicio del rango.
     * @param endDate La fecha de fin del rango.
     * @return Una lista de libros más solicitados.
     */
    public List<Libros> getLibrosMasSolicitados(Date startDate, Date endDate) {
        return daoReporte.findLibrosMasSolicitados(startDate, endDate);
    }

    /**
     * Obtiene una lista de todos los libros más solicitados.
     * @return Una lista de libros más solicitados.
     */
   

    /**
     * Obtiene una lista de los usuarios más activos en un rango de fechas.
     * @param startDate La fecha de inicio del rango.
     * @param endDate La fecha de fin del rango.
     * @return Una lista de usuarios más activos.
     */
    public List<Usuario> getUsuariosMasActivos(Date startDate, Date endDate) {
        return daoReporte.findUsuariosMasActivos(startDate, endDate);
    }

    /**
     * Obtiene una lista de todos los usuarios más activos.
     * @return Una lista de usuarios más activos.
     */
   
}
