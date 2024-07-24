package ec.edu.ups.ppw.biblioteca.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ec.edu.ups.ppw.biblioteca.Rol;
import ec.edu.ups.ppw.biblioteca.Usuario;
import ec.edu.ups.ppw.enums.RolNombre;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class UsuarioDAO {
	
	
	@PersistenceContext
    private EntityManager em;
	

	public void insert(Usuario usuario) {
		em.persist(usuario);
	}
	
	public void update(Usuario usuario) {
		em.merge(usuario);
	}
	
	public void delete(int id) throws Exception{
	    Usuario usuario = this.read(id);
	    if (usuario != null) {
	        em.remove(usuario);
	        System.out.println("Eliminado");
	    } else {
	        throw new Exception("No se puede eliminar el usuario. Usuario con id " + id + " no encontrado.");
	    }
	}
	
	public Usuario read(int id) throws Exception {
	    Usuario usuario = em.find(Usuario.class, id);
	    if (usuario == null) {
	        throw new Exception("Usuario con id " + id + " no encontrado.");
	    }
	    // Los roles se cargan automáticamente debido a FetchType.EAGER
	    return usuario;
	}
	public List<Usuario> getAll() {
	    String jpql = "SELECT u FROM Usuario u LEFT JOIN FETCH u.roles";
	    TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
	    return query.getResultList();
	}
		

    public static boolean validar(String username, String password) {
        return (username.equals("admin") && password.equals("admin"));
    }

    public static Usuario obtenerUsuario(String username, String password) {
        Set<Rol> roles = new HashSet<>();
        
        if (validar(username, password)) {
            roles.add(new Rol(RolNombre.ROLE_ADMIN));
            return new Usuario(1, "admin", "admin", roles , "admin@gmail");
        } else {
            roles.add(new Rol(RolNombre.ROLE_USER));
            return new Usuario(2, username, password, roles, "user@gmail.com");
        }
    }
    
    
    public Usuario validateUser(String username, String password) {
        try {
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.username = :username AND u.password = :password", Usuario.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (Exception e) {
            return null; // Usuario no encontrado o contraseña incorrecta
        }
    }
    
    

}