package ec.edu.ups.ppw.biblioteca.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import ec.edu.ups.ppw.biblioteca.Libros;
import ec.edu.ups.ppw.biblioteca.Prestamo;
import ec.edu.ups.ppw.biblioteca.Rol;
import ec.edu.ups.ppw.biblioteca.Usuario;
import ec.edu.ups.ppw.biblioteca.dao.LibroDAO;
import ec.edu.ups.ppw.biblioteca.dao.PrestamoDAO;
import ec.edu.ups.ppw.biblioteca.dao.RolDAO;
import ec.edu.ups.ppw.biblioteca.dao.UsuarioDAO;
import ec.edu.ups.ppw.enums.RolNombre;
import ec.ups.edu.ppw.util.DateGenerator;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;


@Startup
@Singleton
public class Inicio {
	 @Inject
	    private LibroDAO daoLibro;
	 @Inject
	    private LibroDAO daoLibro1;
	 @Inject
	    private LibroDAO daoLibro2;
	 
	    @Inject
	    private UsuarioDAO daoUsuario;
	    
	    @Inject
	    private RolDAO daoRol;
	    
		@Inject
		private PrestamoDAO daoPrestamo;
		
		

	    @PostConstruct
	    public void init() {
	    	
	        // Crear y persistir roles
	        Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
	        daoRol.insert(rolAdmin);
	        
	        Rol rolUser = new Rol(RolNombre.ROLE_USER);
	        daoRol.insert(rolUser);
		    	
	        // Crear un conjunto de roles
	        Set<Rol> rolesAdmin = new HashSet<>();
	        rolesAdmin.add(rolAdmin);

	        Set<Rol> rolesUser = new HashSet<>();
	        rolesUser.add(rolUser);

	        // Crear y persistir usuarios
	        Usuario usuarioAdmin = new Usuario();
	        usuarioAdmin.setUsername("adminbiblio");
	        usuarioAdmin.setPassword("adminbiblio");
	        usuarioAdmin.setEmail("anthonybenavides2014@yahoo.com");
	        usuarioAdmin.setRoles(rolesAdmin);
	        daoUsuario.insert(usuarioAdmin);

	        Usuario usuarioUser = new Usuario();
	        usuarioUser.setUsername("ussr");
	        usuarioUser.setPassword("ussr");
	        usuarioUser.setEmail("user@gmail.com");
	        usuarioUser.setRoles(rolesUser);
	        daoUsuario.insert(usuarioUser);


	     // Libro 2
	        Libros libro2 = new Libros();
	        libro2.setAutor("J. K. Rowling");
	        libro2.setDescripcion("La historia sigue las aventuras de un niño huérfano, quien descubre que es un mago y es invitado a asistir a la escuela de magia.");
	        libro2.setEditorial("Bloomsbury");
	        libro2.setGenero("Fantasía");
	        libro2.setTitulo("Harry Potter y la piedra filosofal");
	        libro2.setPortada("https://th.bing.com/th/id/R.e1e28ac7a43986234f2167ee74279e2f?rik=45su2odF%2buCxSA&pid=ImgRaw&r=0");
	        libro2.setDisponibilidad(false);
	        libro2.setReservado(true); // Establece el estado de reserva
	        daoLibro.insert(libro2);

	        // Libro 3
	        Libros libro3 = new Libros();
	        libro3.setAutor("J. R. R. Tolkien");
	        libro3.setDescripcion("La novela sigue el viaje del hobbit Bilbo Bolsón, quien se embarca en una aventura para reclamar un tesoro robado.");
	        libro3.setEditorial("Allen & Unwin");
	        libro3.setGenero("Fantasía");
	        libro3.setTitulo("El hobbit");
	        libro3.setPortada("https://th.bing.com/th/id/R.f89526de5ee0fa74d32f2964234bd1fa?rik=sG1dlqxlGPVAJg&pid=ImgRaw&r=0");
	        libro3.setDisponibilidad(true);
	        libro3.setReservado(false); // Establece el estado de reserva
	        daoLibro.insert(libro3);

	        // Libro 4
	        Libros libro4 = new Libros();
	        libro4.setAutor("George Orwell");
	        libro4.setDescripcion("La novela es una distopía que presenta un futuro totalitario en el que el estado controla todos los aspectos de la vida.");
	        libro4.setEditorial("Secker & Warburg");
	        libro4.setGenero("Distopía");
	        libro4.setTitulo("1984");
	        libro4.setPortada("https://th.bing.com/th/id/OIP.8weipgxeZASSI2nt9APKNAAAAA?rs=1&pid=ImgDetMain");
	        libro4.setDisponibilidad(false);
	        libro4.setReservado(true); // Establece el estado de reserva
	        daoLibro.insert(libro4);

	        // Libro 5
	        Libros libro5 = new Libros();
	        libro5.setAutor("Jane Austen");
	        libro5.setDescripcion("Una novela que sigue la vida de Elizabeth Bennet mientras navega por cuestiones de moralidad, educación, y matrimonio en la sociedad británica de principios del siglo XIX.");
	        libro5.setEditorial("T. Egerton, Whitehall");
	        libro5.setGenero("Romance");
	        libro5.setTitulo("Orgullo y prejuicio");
	        libro5.setPortada("https://imagessl2.casadellibro.com/a/l/t0/82/9788415618782.jpg");
	        libro5.setDisponibilidad(true);
	        libro5.setReservado(false); // Establece el estado de reserva
	        daoLibro.insert(libro5);

	        // Libro 6
	        Libros libro6 = new Libros();
	        libro6.setAutor("F. Scott Fitzgerald");
	        libro6.setDescripcion("La novela explora temas de decadencia, idealismo, resistencia al cambio, socialismo, y exceso, creando un retrato de la Era del Jazz o de la Era del Prohibicionismo en los Estados Unidos.");
	        libro6.setEditorial("Charles Scribner's Sons");
	        libro6.setGenero("Novela");
	        libro6.setTitulo("El gran Gatsby");
	        libro6.setPortada("https://th.bing.com/th/id/OIP.XctOmmqwwc0T2c7HaXpRYAAAAA?rs=1&pid=ImgDetMain");
	        libro6.setDisponibilidad(false);
	        libro6.setReservado(true); // Establece el estado de reserva
	        daoLibro.insert(libro6);

	        // Libro 7
	        Libros libro7 = new Libros();
	        libro7.setAutor("Markus Zusak");
	        libro7.setDescripcion("La novela se ambienta en la Alemania nazi y sigue la vida de Liesel Meminger, una niña adoptada por una familia alemana durante la Segunda Guerra Mundial.");
	        libro7.setEditorial("Knopf");
	        libro7.setGenero("Novela histórica");
	        libro7.setTitulo("La ladrona de libros");
	        libro7.setPortada("https://th.bing.com/th/id/R.e0bdb5793f0da56b184afad565ae4c6b?rik=087LtVN5lATdww&riu=http%3a%2f%2f2.bp.blogspot.com%2f-TUJHvf7owv4%2fUuoejln66NI%2fAAAAAAAAMjo%2fjwRMsBNEDio%2fs1600%2flas-ladrona-de-libros.jpg&ehk=9NHP0cpqdd3kXxnfuBvgMfPqfsaQGbpsey2yFCegihM%3d&risl=&pid=ImgRaw&r=0");
	        libro7.setDisponibilidad(true);
	        libro7.setReservado(false); // Establece el estado de reserva
	        daoLibro.insert(libro7);

	        // Libro 8
	        Libros libro8 = new Libros();
	        libro8.setAutor("Harper Lee");
	        libro8.setDescripcion("La novela aborda temas de racismo y justicia en el sur de Estados Unidos durante la Gran Depresión, a través de la historia de Atticus Finch, un abogado defensor de un hombre negro injustamente acusado de violar a una mujer blanca.");
	        libro8.setEditorial("J. B. Lippincott & Co.");
	        libro8.setGenero("Novela");
	        libro8.setTitulo("Matar a un ruiseñor");
	        libro8.setPortada("https://th.bing.com/th/id/OIP.-WxgdvenGlqf2dZvYbVeSgHaK1?rs=1&pid=ImgDetMain");
	        libro8.setDisponibilidad(false);
	        libro8.setReservado(true); // Establece el estado de reserva
	        daoLibro.insert(libro8);

	        // Libro 9
	        Libros libro9 = new Libros();
	        libro9.setAutor("Leo Tolstoy");
	        libro9.setDescripcion("La novela narra la vida de la aristocracia rusa durante la invasión de Napoleón en 1812. Es una de las obras más importantes de la literatura mundial.");
	        libro9.setEditorial("The Russian Messenger");
	        libro9.setGenero("Novela histórica");
	        libro9.setTitulo("Guerra y paz");
	        libro9.setPortada("https://www.elsotano.com/imagenes_grandes/9788494/978849474582.JPG");
	        libro9.setDisponibilidad(true);
	        libro9.setReservado(false); // Establece el estado de reserva
	        daoLibro.insert(libro9);

	        // Libro 10
	        Libros libro10 = new Libros();
	        libro10.setAutor("Ernest Hemingway");
	        libro10.setDescripcion("La novela está ambientada durante la Guerra Civil Española y sigue a un grupo de soldados republicanos en su lucha contra las fuerzas franquistas.");
	        libro10.setEditorial("Charles Scribner's Sons");
	        libro10.setGenero("Novela bélica");
	        libro10.setTitulo("Por quién doblan las campanas");
	        libro10.setPortada("https://th.bing.com/th/id/OIP.C4D3SIbxzbCoFveJzGiXJgHaKm?rs=1&pid=ImgDetMain");
	        libro10.setDisponibilidad(false);
	        libro10.setReservado(true); // Establece el estado de reserva
	        daoLibro.insert(libro10);

	        // Libro 11
	        Libros libro11 = new Libros();
	        libro11.setAutor("Virginia Woolf");
	        libro11.setDescripcion("La novela sigue un día en la vida de Clarissa Dalloway, una mujer de alta sociedad en Londres, mientras prepara una fiesta para esa noche.");
	        libro11.setEditorial("Hogarth Press");
	        libro11.setGenero("Ficción");
	        libro11.setTitulo("Mrs Dalloway");
	        libro11.setPortada("https://th.bing.com/th/id/OIP.nnAAzfkU8JMcjnNbvo-v_AHaLG?rs=1&pid=ImgDetMain");
	        libro11.setDisponibilidad(true);
	        libro11.setReservado(false); // Establece el estado de reserva
	        daoLibro.insert(libro11);

	      
}}