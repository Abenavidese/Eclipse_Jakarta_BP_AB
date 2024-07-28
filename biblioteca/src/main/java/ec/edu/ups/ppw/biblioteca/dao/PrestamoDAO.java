package ec.edu.ups.ppw.biblioteca.dao;

import java.util.List;

import ec.edu.ups.ppw.biblioteca.Prestamo;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless

public class PrestamoDAO {

	@PersistenceContext
	private EntityManager em; 
	
	public void insert(Prestamo prestamo) {
		em.persist(prestamo);
	}
	
	public void update(Prestamo prestamo) {
		em.merge(prestamo);
	}
	
	public void delete(int id) throws Exception{
		Prestamo prestamo = this.read(id);
	    if (prestamo != null) {
	        em.remove(prestamo);
	        System.out.println("Eliminado");
	    } else {
	        throw new Exception("No se puede eliminar, prestamo con id " + id + " no encontrado.");
	    }
	}
	
	public Prestamo read(int id) throws Exception{
		Prestamo prestamo = em.find(Prestamo.class, id);
	    if (prestamo == null) {
	        throw new Exception("Prestamo con id " + id + " no encontrado.");
	    }
	    return prestamo;
	}
	
	public List<Prestamo> getAll(){
		String jpql = "SELECT p FROM Prestamo p";
		Query query = em.createQuery(jpql, Prestamo.class);
		return query.getResultList();
	}
}