package ec.ups.edu.ppw.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Clase utilitaria para la gestión de la persistencia.
 * Proporciona un EntityManager para interactuar con la base de datos.
 */
public class PercistenceManager {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bibliotecaPersistenceUnit");

    /**
     * Obtiene una instancia de EntityManager para interactuar con la base de datos.
     * @return Una instancia de EntityManager.
     */
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
