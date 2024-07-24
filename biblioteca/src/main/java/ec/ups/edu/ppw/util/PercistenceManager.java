package ec.ups.edu.ppw.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PercistenceManager {
	
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bibliotecaPersistenceUnit");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

}