package ec.edu.ups.ppw.biblioteca;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="TBL_LIBRO")
@Entity
public class Libros {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="lib_id")
private int LibroId;

@Column(name="lib_titulo")
private String Titulo;

@Column(name="lib_autor")
private String Autor;

@Column(name="lib_descripcion")
private String Descripcion;

@Column(name="lib_genero")
private String Genero;

@Column(name="lib_editorial")
private String Editorial;

@Column(name="lib_portada")
private String portada;

@Column(name="lib_disponibilidad")
private Boolean disponibilidad;



public String getPortada() {
	return portada;
}

public Boolean getDisponibilidad() {
	return disponibilidad;
}

public void setDisponibilidad(Boolean disponibilidad) {
	this.disponibilidad = disponibilidad;
}

public void setPortada(String portada) {
	this.portada = portada;
}

public int getLibroId() {
	return LibroId;
}

public void setLibroId(int libroId) {
	LibroId = libroId;
}

public String getTitulo() {
	return Titulo;
}

public void setTitulo(String titulo) {
	Titulo = titulo;
}

public String getAutor() {
	return Autor;
}

public void setAutor(String autor) {
	Autor = autor;
}

public String getDescripcion() {
	return Descripcion;
}

public void setDescripcion(String descripcion) {
	Descripcion = descripcion;
}

public String getGenero() {
	return Genero;
}

public void setGenero(String genero) {
	Genero = genero;
}

public String getEditorial() {
	return Editorial;
}

public void setEditorial(String editorial) {
	Editorial = editorial;
}





	}
