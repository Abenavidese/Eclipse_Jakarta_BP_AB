package ec.edu.ups.ppw.biblioteca;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Table(name = "TBL_USUARIO")
@Entity
public class Usuario {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_ID")
    private int id;

    @NotNull
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    @Column(name = "user_password")
    private String password;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER) // Añadir FetchType.EAGER para cargar los roles junto con el usuario
    @JoinTable(
        name = "usuario_rol", // Nombre de la tabla intermedia
        joinColumns = @JoinColumn(name = "usuario_id"), // Columna de Usuario
        inverseJoinColumns = @JoinColumn(name = "rol_id") // Columna de Rol
    )
    private Set<Rol> roles = new HashSet<>();

    @NotNull
    @Column(unique = true)
    private String email;

    
    public Usuario() {}
    


	public Usuario(int id, @NotNull String username, @NotNull String password, @NotNull Set<Rol> roles,
			@NotNull String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.email = email;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}
	




	
	
	
	

}