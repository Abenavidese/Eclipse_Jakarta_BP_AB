package ec.edu.ups.ppw.biblioteca.dao;

import java.util.List;

import ec.edu.ups.ppw.biblioteca.Libros;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class LibroDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void insert(Libros libro) {
		em.persist(libro);
	}
	
	public void update(Libros libro) {
		em.merge(libro);
	}
	
	public void delete(int id) throws Exception{
	    Libros libro = this.read(id);
	    if (libro != null) {
	        em.remove(libro);
	        System.out.println("Eliminado");
	    } else {
	        throw new Exception("No se puede eliminar el libro. Libro con id " + id + " no encontrado.");
	    }
	}
	
	public Libros read(int id) throws Exception{
		Libros libro = em.find(Libros.class, id);
	    if (libro == null) {
	        throw new Exception("Libro con id " + id + " no encontrado.");
	    }
	    return libro;
	}
	
	public List<Libros> getAll(){
		String jpql = "SELECT l FROM Libros l";
		
		Query query = em.createQuery(jpql, Libros.class);
		return query.getResultList();
	}
}