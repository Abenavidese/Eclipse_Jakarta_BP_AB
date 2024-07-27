package ec.edu.ups.ppw.biblioteca.model;

import java.util.List;

import ec.edu.ups.ppw.biblioteca.Usuario;
import ec.edu.ups.ppw.biblioteca.dao.UsuarioDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Stateless
public class GestionUsuario {
	
	@PersistenceContext(unitName = "bibliotecaPersistenceUnit")
    private EntityManager em;
	
	@Inject
	private UsuarioDAO daoUsuario;
	
	public Usuario getUsuario(int id) throws Exception {
		Usuario usuario = daoUsuario.read(id);
		if(usuario == null)
			throw new Exception("Usuario no existe");
		return usuario;
	}
	
	public List<Usuario> getUsuarios(){
		return daoUsuario.getAll();
	}
	
	public void createUsuario(Usuario usuario) throws Exception {
		if(usuario.getId() < 0) {
			throw new Exception("Id Incorrecto");
		}
		daoUsuario.insert(usuario);
	}
	
	public void updateUsuario(Usuario usuario) throws Exception{
		if(usuario.getId() < 0) {
			throw new Exception("Id Incorrecta");
		}
		daoUsuario.update(usuario);
	}
	
	public void deleteUsuario(int id) throws Exception{
		Usuario usuario = daoUsuario.read(id);
		if(usuario == null) {				
			throw new Exception("Id no existe");
		}
		else {
			daoUsuario.delete(id);
		}
		
	}
	


    public Usuario findByUsername(String username) {
        String jpql = "SELECT u FROM Usuario u WHERE u.username = :username";
        try {
            return em.createQuery(jpql, Usuario.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            // Manejar la excepción según tu aplicación
            e.printStackTrace();
            return null;
        }
    }
    
    public Usuario iniciarSesion(String username, String password) {
        String jpql = "SELECT u FROM Usuario u WHERE u.username = :username AND u.password = :password";
        try {
            return em.createQuery(jpql, Usuario.class)
                     .setParameter("username", username)
                     .setParameter("password", password)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}