package ec.edu.ups.ppw.biblioteca;

import ec.edu.ups.ppw.enums.RolNombre;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Clase que representa un rol en el sistema.
 * Está mapeada a la tabla "TBL_ROL" en la base de datos.
 */
@Entity
@Table(name = "TBL_ROL")
public class Rol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RolNombre rolNombre;

    /**
     * Constructor por defecto.
     */
    public Rol() {}

    /**
     * Constructor que inicializa el nombre del rol.
     * @param rolNombre El nombre del rol.
     */
    public Rol(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }

    /**
     * Obtiene el ID del rol.
     * @return El ID del rol.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del rol.
     * @param id El ID del rol.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del rol.
     * @return El nombre del rol.
     */
    public RolNombre getRolNombre() {
        return rolNombre;
    }

    /**
     * Establece el nombre del rol.
     * @param rolNombre El nombre del rol.
     */
    public void setRolNombre(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }
}
