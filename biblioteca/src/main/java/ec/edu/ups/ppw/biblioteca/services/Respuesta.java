package ec.edu.ups.ppw.biblioteca.services;

/**
 * Clase que representa una respuesta estándar utilizada en los servicios REST.
 * Contiene un código y un mensaje para indicar el resultado de una operación.
 */
public class Respuesta {
    
    private int codigo;
    private String mensaje;
    
    public static final int OK = 1;
    public static final int ERROR = 99;
    
    /**
     * Constructor que inicializa la respuesta con un código y un mensaje.
     * @param codigo El código de la respuesta.
     * @param mensaje El mensaje de la respuesta.
     */
    public Respuesta(int codigo, String mensaje) {
        super();
        this.codigo = codigo;
        this.mensaje = mensaje;
    }
    
    /**
     * Obtiene el código de la respuesta.
     * @return El código de la respuesta.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Establece el código de la respuesta.
     * @param codigo El código de la respuesta.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el mensaje de la respuesta.
     * @return El mensaje de la respuesta.
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Establece el mensaje de la respuesta.
     * @param mensaje El mensaje de la respuesta.
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
