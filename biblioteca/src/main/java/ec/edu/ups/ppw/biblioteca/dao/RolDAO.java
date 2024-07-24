package ec.edu.ups.ppw.biblioteca.dao;

import java.util.List;

import ec.edu.ups.ppw.biblioteca.Rol;
import ec.edu.ups.ppw.enums.RolNombre;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;


@Stateless
public class RolDAO {
    
    @PersistenceContext
    private EntityManager em;

    public void insert(Rol rol) {
        em.persist(rol);
    }

    public void update(Rol rol) {
        em.merge(rol);
    }

    public Rol read(int id) {
        return em.find(Rol.class, id);
    }

    public void delete(int id) {
        Rol rol = this.read(id);
        if (rol != null) {
            em.remove(rol);
        }
    }

    public List<Rol> getAll() {
        String jpql = "SELECT r FROM Rol r";
        Query query = em.createQuery(jpql, Rol.class);
        return query.getResultList();
    }

    public Rol findByName(RolNombre rolNombre) {
        TypedQuery<Rol> query = em.createQuery("SELECT r FROM Rol r WHERE r.rolNombre = :rolNombre", Rol.class);
        query.setParameter("rolNombre", rolNombre);
        List<Rol> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
}