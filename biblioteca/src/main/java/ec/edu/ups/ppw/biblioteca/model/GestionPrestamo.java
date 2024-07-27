package ec.edu.ups.ppw.biblioteca.model;

import java.util.List;

import ec.edu.ups.ppw.biblioteca.Prestamo;
import ec.edu.ups.ppw.biblioteca.dao.PrestamoDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionPrestamo {
	@Inject
	private PrestamoDAO daoPrestamo;
	
	public Prestamo getPrestamo(int id) throws Exception {
		Prestamo prestamo = daoPrestamo.read(id);
		if(prestamo == null)
			throw new Exception("Prestamo no existe");
		return prestamo;
	}
	
	public List<Prestamo> getPrestamos(){
		return daoPrestamo.getAll();
	}
	
	public void createPrestamo(Prestamo prestamo) throws Exception {
		if(prestamo.getPrestamoId() < 0) {
			throw new Exception("Id Incorrecto");
		}
		daoPrestamo.insert(prestamo);
	}
	
	public void updatePrestamo(Prestamo prestamo) throws Exception{
		if(prestamo.getPrestamoId() < 0) {
			throw new Exception("Id Incorrecta");
		}
		daoPrestamo.update(prestamo);
	}
	
	public void deletePrestamo(int id) throws Exception{
		Prestamo prestamo = daoPrestamo.read(id);
		if(prestamo == null) {				
			throw new Exception("Prestamo no existe");
		}
		else {
			daoPrestamo.delete(id);
		}
		
	}
}