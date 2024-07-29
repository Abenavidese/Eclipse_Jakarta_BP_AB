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

	        // Libro 1
	        Libros libro1 = new Libros();
	        libro1.setAutor("Gabriel García Márquez");
	        libro1.setDescripcion("Cien años de soledad es una novela que cuenta la historia de la familia Buendía a lo largo de varias generaciones en el ficticio pueblo de Macondo.");
	        libro1.setEditorial("Editorial Sudamericana");
	        libro1.setGenero("Realismo mágico");
	        libro1.setTitulo("Cien años de soledad");
	        libro1.setPortada("https://th.bing.com/th/id/OIP.i1l4x9xyOWqAe0fHkss9NwHaLQ?w=202&h=308&c=7&r=0&o=5&dpr=1.3&pid=1.7");
	        libro1.setDisponibilidad(true);
	        daoLibro.insert(libro1);
	        
	        
	        // Crear préstamo
	        Prestamo prestamo = new Prestamo();
	        prestamo.setUsuario(usuarioAdmin);
	        prestamo.setLibro(libro1);
	        Date fechaPrestamo = DateGenerator.createDate(2023, 7, 18);
	        Date fechaDevolucion = DateGenerator.createDate(2023, 7, 25);
	        prestamo.setFechaPrestamo(fechaPrestamo);
	        prestamo.setFechaDevolucion(fechaDevolucion);

	        // Insertar préstamo
	        daoPrestamo.insert(prestamo);

	        System.out.println("Préstamo creado: " + prestamo);
	        // Libro 2
	        Libros libro2 = new Libros();
	        libro2.setAutor("J. K. Rowling");
	        libro2.setDescripcion("La historia sigue las aventuras de un niño huérfano, quien descubre que es un mago y es invitado a asistir a la escuela de magia.");
	        libro2.setEditorial("Bloomsbury");
	        libro2.setGenero("Fantasía");
	        libro2.setTitulo("Harry Potter y la piedra filosofal");
	        libro2.setPortada("https://th.bing.com/th/id/R.e1e28ac7a43986234f2167ee74279e2f?rik=45su2odF%2buCxSA&pid=ImgRaw&r=0");
	        libro2.setDisponibilidad(false);
	        daoLibro.insert(libro2);

	        // Libro 3
	        Libros libro3 = new Libros();
	        libro3.setAutor("J. R. R. Tolkien");
	        libro3.setDescripcion("La novela sigue el viaje del hobbit Bilbo Bolsón, quien se embarca en una aventura para reclamar un tesoro robado.");
	        libro3.setEditorial("Allen & Unwin");
	        libro3.setGenero("Fantasía");
	        libro3.setTitulo("El hobbit");
	        libro3.setPortada("https://th.bing.com/th/id/OIP.Oj2WXQ_tBXGSZdAKdHTR9gHaJl?w=202&h=263&c=7&r=0&o=5&dpr=1.3&pid=1.7");
	        libro3.setDisponibilidad(true);
	        daoLibro.insert(libro3);

	        // Libro 4
	        Libros libro4 = new Libros();
	        libro4.setAutor("George Orwell");
	        libro4.setDescripcion("La novela es una distopía que presenta un futuro totalitario en el que el estado controla todos los aspectos de la vida.");
	        libro4.setEditorial("Secker & Warburg");
	        libro4.setGenero("Distopía");
	        libro4.setTitulo("1984");
	        libro4.setPortada("https://th.bing.com/th/id/OIP.U9ur5_9eNAN-HEPjx5u8PAHaLH?w=202&h=306&c=7&r=0&o=5&dpr=1.3&pid=1.7");
	        libro4.setDisponibilidad(false);
	        daoLibro.insert(libro4);

	        // Libro 5
	        Libros libro5 = new Libros();
	        libro5.setAutor("Jane Austen");
	        libro5.setDescripcion("Una novela que sigue la vida de Elizabeth Bennet mientras navega por cuestiones de moralidad, educación, y matrimonio en la sociedad británica de principios del siglo XIX.");
	        libro5.setEditorial("T. Egerton, Whitehall");
	        libro5.setGenero("Romance");
	        libro5.setTitulo("Orgullo y prejuicio");
	        libro5.setPortada("https://th.bing.com/th/id/OIP.bf93-5WQMaWUDfqKc8QqZgHaKI?w=202&h=275&c=7&r=0&o=5&dpr=1.3&pid=1.7");
	        libro5.setDisponibilidad(true);
	        daoLibro.insert(libro5);

	        // Libro 6
	        Libros libro6 = new Libros();
	        libro6.setAutor("F. Scott Fitzgerald");
	        libro6.setDescripcion("La novela explora temas de decadencia, idealismo, resistencia al cambio, socialismo, y exceso, creando un retrato de la Era del Jazz o de la Era del Prohibicionismo en los Estados Unidos.");
	        libro6.setEditorial("Charles Scribner's Sons");
	        libro6.setGenero("Novela");
	        libro6.setTitulo("El gran Gatsby");
	        libro6.setPortada("https://th.bing.com/th/id/OIP.97jP8tZkHlOwI2aQ4J1aWgHaK3?w=202&h=296&c=7&r=0&o=5&dpr=1.3&pid=1.7");
	        libro6.setDisponibilidad(false);
	        daoLibro.insert(libro6);

	        // Libro 7
	        Libros libro7 = new Libros();
	        libro7.setAutor("Markus Zusak");
	        libro7.setDescripcion("La novela se ambienta en la Alemania nazi y sigue la vida de Liesel Meminger, una niña adoptada por una familia alemana durante la Segunda Guerra Mundial.");
	        libro7.setEditorial("Knopf");
	        libro7.setGenero("Novela histórica");
	        libro7.setTitulo("La ladrona de libros");
	        libro7.setPortada("https://th.bing.com/th/id/OIP.HuXZ1aCkGg0-JnoQ4Q9lGgHaK-?w=202&h=299&c=7&r=0&o=5&dpr=1.3&pid=1.7");
	        libro7.setDisponibilidad(true);
	        daoLibro.insert(libro7);

	        // Libro 8
	        Libros libro8 = new Libros();
	        libro8.setAutor("Harper Lee");
	        libro8.setDescripcion("La novela aborda temas de racismo y justicia en el sur de Estados Unidos durante la Gran Depresión, a través de la historia de Atticus Finch, un abogado defensor de un hombre negro injustamente acusado de violar a una mujer blanca.");
	        libro8.setEditorial("J. B. Lippincott & Co.");
	        libro8.setGenero("Novela");
	        libro8.setTitulo("Matar a un ruiseñor");
	        libro8.setPortada("https://th.bing.com/th/id/OIP.8P-E9mQbFgZiU-SZT5TWWwHaKI?w=202&h=275&c=7&r=0&o=5&dpr=1.3&pid=1.7");
	        libro8.setDisponibilidad(false);
	        daoLibro.insert(libro8);

	        // Libro 9
	        Libros libro9 = new Libros();
	        libro9.setAutor("Leo Tolstoy");
	        libro9.setDescripcion("La novela narra la vida de la aristocracia rusa durante la invasión de Napoleón en 1812. Es una de las obras más importantes de la literatura mundial.");
	        libro9.setEditorial("The Russian Messenger");
	        libro9.setGenero("Novela histórica");
	        libro9.setTitulo("Guerra y paz");
	        libro9.setPortada("https://th.bing.com/th/id/OIP.xX8kP0eQH-xO-sVP1PBw1gHaN4?w=202&h=358&c=7&r=0&o=5&dpr=1.3&pid=1.7");
	        libro9.setDisponibilidad(true);
	        daoLibro.insert(libro9);

	        // Libro 10
	        Libros libro10 = new Libros();
	        libro10.setAutor("Ernest Hemingway");
	        libro10.setDescripcion("La novela está ambientada durante la Guerra Civil Española y sigue a un grupo de soldados republicanos en su lucha contra las fuerzas franquistas.");
	        libro10.setEditorial("Charles Scribner's Sons");
	        libro10.setGenero("Novela bélica");
	        libro10.setTitulo("Por quién doblan las campanas");
	        libro10.setPortada("https://th.bing.com/th/id/OIP.VYDRgS2T0bg4ndfZG6hPHwHaKO?w=202&h=276&c=7&r=0&o=5&dpr=1.3&pid=1.7");
	        libro10.setDisponibilidad(false);
	        daoLibro.insert(libro10);

	        // Libro 11
	        Libros libro11 = new Libros();
	        libro11.setAutor("Virginia Woolf");
	        libro11.setDescripcion("La novela sigue un día en la vida de Clarissa Dalloway, una mujer de alta sociedad en Londres, mientras prepara una fiesta para esa noche.");
	        libro11.setEditorial("Hogarth Press");
	        libro11.setGenero("Ficción");
	        libro11.setTitulo("Mrs Dalloway");
	        libro11.setPortada("https://th.bing.com/th/id/OIP.SI8wx0-PXjRNO5o5DqRl0gHaKe?w=202&h=286&c=7&r=0&o=5&dpr=1.3&pid=1.7");
	        libro11.setDisponibilidad(true);
	        daoLibro.insert(libro11);

	        // Libro 12
	        Libros libro12 = new Libros();
	        libro12.setAutor("Emily Brontë");
	        libro12.setDescripcion("La novela sigue la vida de Heathcliff, un niño adoptado, y su relación con la familia Earnshaw en la salvaje y sombría región de Yorkshire.");
	        libro12.setEditorial("Thomas Cautley Newby");
	        libro12.setGenero("Novela gótica");
	        libro12.setTitulo("Cumbres borrascosas");
	        libro12.setPortada("https://th.bing.com/th/id/OIP.7EUpSSWxM13rJ4xIVJNCAwHaLH?w=202&h=303&c=7&r=0&o=5&dpr=1.3&pid=1.7");
	        libro12.setDisponibilidad(false);
	        daoLibro.insert(libro12);

	        // Libro 13
	        Libros libro13 = new Libros();
	        libro13.setAutor("Albert Camus");
	        libro13.setDescripcion("La novela explora temas de absurdo, revuelta y la indiferencia de la sociedad ante el sufrimiento humano.");
	        libro13.setEditorial("Gallimard");
	        libro13.setGenero("Filosofía");
	        libro13.setTitulo("El extranjero");
	        libro13.setPortada("https://th.bing.com/th/id/OIP.61aK-3T_xO4ypKeh4oetOgHaKV?w=202&h=279&c=7&r=0&o=5&dpr=1.3&pid=1.7");
	        libro13.setDisponibilidad(true);
	        daoLibro.insert(libro13);

	        // Libro 14
	        Libros libro14 = new Libros();
	        libro14.setAutor("Margaret Atwood");
	        libro14.setDescripcion("La novela está ambientada en una sociedad totalitaria futurista donde las mujeres son subyugadas y utilizadas para la reproducción.");
	        libro14.setEditorial("McClelland & Stewart");
	        libro14.setGenero("Ciencia ficción");
	        libro14.setTitulo("El cuento de la criada");
	        libro14.setPortada("https://th.bing.com/th/id/OIP.F2us-JYc9IzKwVlQG6iKbgHaK-?w=202&h=282&c=7&r=0&o=5&dpr=1.3&pid=1.7");
	        libro14.setDisponibilidad(false);
	        daoLibro.insert(libro14);

	        // Libro 15
	        Libros libro15 = new Libros();
	        libro15.setAutor("Gabriel García Márquez");
	        libro15.setDescripcion("La novela sigue la vida de Florentino Ariza y Fermina Daza a lo largo de más de 50 años, explorando temas de amor, pérdida y redención.");
	        libro15.setEditorial("Editorial Sudamericana");
	        libro15.setGenero("Realismo mágico");
	        libro15.setTitulo("El amor en los tiempos del cólera");
	        libro15.setPortada("https://th.bing.com/th/id/OIP.2o4qVDbBjD6wWbFwVX_MyQHaLH?w=202&h=303&c=7&r=0&o=5&dpr=1.3&pid=1.7");
	        libro15.setDisponibilidad(true);
	        daoLibro.insert(libro15);

	        // Libro 16
	        Libros libro16 = new Libros();
	        libro16.setAutor("Haruki Murakami");
	        libro16.setDescripcion("La novela sigue a Toru Watanabe mientras reflexiona sobre su vida universitaria, amores pasados y la misteriosa muerte de su amigo.");
	        libro16.setEditorial("Kodansha");
	        libro16.setGenero("Ficción contemporánea");
	        libro16.setTitulo("Tokio blues (Norwegian Wood)");
	        libro16.setPortada("https://th.bing.com/th/id/OIP.6qUG0g8wwGKNHKxyFdzMowHaJ5?w=202&h=267&c=7&r=0&o=5&dpr=1.3&pid=1.7");
	        libro16.setDisponibilidad(false);
	        daoLibro.insert(libro16);

	        // Libro 17
	        Libros libro17 = new Libros();
	        libro17.setAutor("Gabriel García Márquez");
	        libro17.setDescripcion("La novela sigue la vida de Simón Bolívar, el líder militar y político que jugó un papel fundamental en la independencia de varios países sudamericanos.");
	        libro17.setEditorial("Editorial Sudamericana");
	        libro17.setGenero("Novela histórica");
	        libro17.setTitulo("El general en su laberinto");
	        libro17.setPortada("https://th.bing.com/th/id/OIP.4HLvNuxunISCKS8Ft7Z2lQHaJl?w=202&h=256&c=7&r=0&o=5&dpr=1.3&pid=1.7");
	        libro17.setDisponibilidad(true);
	        daoLibro.insert(libro17);

	        // Libro 18
	        Libros libro18 = new Libros();
	        libro18.setAutor("Ken Follett");
	        libro18.setDescripcion("La novela sigue la construcción de una catedral en la ciudad ficticia de Kingsbridge, Inglaterra, a lo largo de varios siglos.");
	        libro18.setEditorial("Macmillan Publishers");
	        libro18.setGenero("Novela histórica");
	        libro18.setTitulo("Los pilares de la Tierra");
	        libro18.setPortada("https://th.bing.com/th/id/OIP.8qXK8XlI8GrpBQRR6KnkYgHaKS?w=202&h=279&c=7&r=0&o=5&dpr=1.3&pid=1.7");
	        libro18.setDisponibilidad(false);
	        daoLibro.insert(libro18);

	        // Libro 19
	        Libros libro19 = new Libros();
	        libro19.setAutor("Franz Kafka");
	        libro19.setDescripcion("La novela cuenta la historia de Gregor Samsa, quien se despierta un día transformado en un insecto gigante y enfrenta las reacciones de su familia y la sociedad.");
	        libro19.setEditorial("Kurt Wolff Verlag");
	        libro19.setGenero("Ficción existencialista");
	        libro19.setTitulo("La metamorfosis");
	        libro19.setPortada("https://th.bing.com/th/id/OIP.gQ3hvJtiE3KUns5gs7-i9gHaLM?w=202&h=305&c=7&r=0&o=5&dpr=1.3&pid=1.7");
	        libro19.setDisponibilidad(true);
	        daoLibro.insert(libro19);

	        // Libro 20
	        Libros libro20 = new Libros();
	        libro20.setAutor("Aldous Huxley");
	        libro20.setDescripcion("La novela presenta una sociedad futurista en la que se controlan todos los aspectos de la vida humana, incluidas las emociones y los pensamientos.");
	        libro20.setEditorial("Chatto & Windus");
	        libro20.setGenero("Ciencia ficción");
	        libro20.setTitulo("Un mundo feliz");
	        libro20.setPortada("https://th.bing.com/th/id/OIP.EJdcu4BmQOZw1-9mI60TDQHaKL?w=202&h=278&c=7&r=0&o=5&dpr=1.3&pid=1.7");
	        libro20.setDisponibilidad(false);
	        daoLibro.insert(libro20);

	        // Libro 21
	        Libros libro21 = new Libros();
	        libro21.setAutor("Jorge Luis Borges");
	        libro21.setDescripcion("La colección de cuentos explora temas de laberintos, espejos, identidad y realidad.");
	        libro21.setEditorial("Sur");
	        libro21.setGenero("Ficción");
	        libro21.setTitulo("Ficciones");
	        libro21.setPortada("https://th.bing.com/th/id/OIP.fj6d0CQzIvzH6Kb90_W9aQHaJ4?w=202&h=269&c=7&r=0&o=5&dpr=1.3&pid=1.7");
	        libro21.setDisponibilidad(true);
	        daoLibro.insert(libro21);

	        // Libro 22
	        Libros libro22 = new Libros();
	        libro22.setAutor("J. D. Salinger");
	        libro22.setDescripcion("La novela sigue a Holden Caulfield, un joven rebelde que lucha por encontrar su lugar en el mundo mientras lidia con la hipocresía y la falsedad de la sociedad adulta.");
	        libro22.setEditorial("Little, Brown and Company");
	        libro22.setGenero("Novela de iniciación");
	        libro22.setTitulo("El guardián entre el centeno");
	        libro22.setPortada("https://th.bing.com/th/id/OIP.j0GMNw7TNu8qVZjJh9yL_wHaLG?w=202&h=302&c=7&r=0&o=5&dpr=1.3&pid=1.7");
	        libro22.setDisponibilidad(false);
	        daoLibro.insert(libro22);

	        // Libro 23
	        Libros libro23 = new Libros();
	        libro23.setAutor("John Steinbeck");
	        libro23.setDescripcion("La novela sigue la vida de los trabajadores migrantes y su lucha por la supervivencia durante la Gran Depresión en los Estados Unidos.");
	        libro23.setEditorial("The Viking Press");
	        libro23.setGenero("Novela social");
	        libro23.setTitulo("Las uvas de la ira");
	        libro23.setPortada("https://th.bing.com/th/id/OIP.Q58BqXJ-sffkbFZVVSlQ8QHaKT?w=202&h=281&c=7&r=0&o=5&dpr=1.3&pid=1.7");
	        libro23.setDisponibilidad(true);
	        daoLibro.insert(libro23);

	        // Libro 24
	        Libros libro24 = new Libros();
	        libro24.setAutor("Gustave Flaubert");
	        libro24.setDescripcion("La novela sigue la vida de Emma Bovary, una mujer atrapada en un matrimonio insatisfactorio y en busca de pasión y romance.");
	        libro24.setEditorial("Revue de Paris");
	        libro24.setGenero("Novela realista");
	        libro24.setTitulo("Madame Bovary");
	        libro24.setPortada("https://th.bing.com/th/id/OIP.r7cOD0T8qVlo4g4CYbdZvAHaLH?w=202&h=303&c=7&r=0&o=5&dpr=1.3&pid=1.7");
	        libro24.setDisponibilidad(false);
	        daoLibro.insert(libro24);

	        // Libro 25
	        Libros libro25 = new Libros();
	        libro25.setAutor("Arthur Conan Doyle");
	        libro25.setDescripcion("La novela sigue las investigaciones del detective Sherlock Holmes y su amigo el Dr. Watson mientras resuelven misterios en el Londres victoriano.");
	        libro25.setEditorial("George Newnes Ltd");
	        libro25.setGenero("Novela detectivesca");
	        libro25.setTitulo("Estudio en escarlata");
	        libro25.setPortada("https://th.bing.com/th/id/OIP.AA0C5p3jLdS6Ml8iiXkT4wHaLH?w=202&h=303&c=7&r=0&o=5&dpr=1.3&pid=1.7");
	        libro25.setDisponibilidad(true);
	        daoLibro.insert(libro25);

	        // Libro 26
	        Libros libro26 = new Libros();
	        libro26.setAutor("Fyodor Dostoevsky");
	        libro26.setDescripcion("La novela explora temas de moralidad, libre albedrío y la naturaleza del mal a través de la historia de Rodion Raskolnikov, un estudiante pobre que comete un asesinato y lidia con las consecuencias.");
	        libro26.setEditorial("The Russian Messenger");
	        libro26.setGenero("Novela psicológica");
	        libro26.setTitulo("Crime and Punishment");
	        libro26.setPortada("https://th.bing.com/th/id/OIP.xubLFZxG2Hf41rdjGzW7XQHaLH?w=202&h=303&c=7&r=0&o=5&dpr=1.3&pid=1.7");
	        libro26.setDisponibilidad(false);
	        daoLibro.insert(libro26);

	        // Libro 27
	        Libros libro27 = new Libros();
	        libro27.setAutor("Hermann Hesse");
	        libro27.setDescripcion("La novela sigue a Siddhartha mientras busca la iluminación espiritual y recorre un camino de auto-descubrimiento en la India.");
	        libro27.setEditorial("Suhrkamp Verlag");
	        libro27.setGenero("Novela de aprendizaje");
	        libro27.setTitulo("Siddhartha");
	        libro27.setPortada("https://th.bing.com/th/id/OIP.CFF0wA45o0IjLDOq0INBOAHaJl?w=202&h=265&c=7&r=0&o=5&dpr=1.3&pid=1.7");
	        libro27.setDisponibilidad(true);
	        daoLibro.insert(libro27);

	        // Libro 28
	        Libros libro28 = new Libros();
	        libro28.setAutor("Oscar Wilde");
	        libro28.setDescripcion("La novela es una sátira social que critica la superficialidad de la sociedad victoriana y explora temas de belleza, vanidad y moralidad.");
	        libro28.setEditorial("Lippincott's Monthly Magazine");
	        libro28.setGenero("Ficción filosófica");
	        libro28.setTitulo("El retrato de Dorian Gray");
	        libro28.setPortada("https://th.bing.com/th/id/OIP.U11gT9V2F0l6kGZouuOvhgHaIu?w=202&h=236&c=7&r=0&o=5&dpr=1.3&pid=1.7");
	        libro28.setDisponibilidad(false);
	        daoLibro.insert(libro28);

	        // Libro 29
	        Libros libro29 = new Libros();
	        libro29.setAutor("Yukio Mishima");
	        libro29.setDescripcion("La novela sigue la vida de Isao, un joven que se une a una organización militarista en Japón y se enfrenta a cuestiones de identidad, tradición y modernidad.");
	        libro29.setEditorial("Shinchosha");
	        libro29.setGenero("Ficción política");
	        libro29.setTitulo("El marino que perdió la gracia del mar");
	        libro29.setPortada("https://th.bing.com/th/id/OIP.tI_4JotEkRZ3UetJY5xWkAHaKX?w=202&h=286&c=7&r=0&o=5&dpr=1.3&pid=1.7");
	        libro29.setDisponibilidad(true);
	        daoLibro.insert(libro29);

	        // Libro 30
	        Libros libro30 = new Libros();
	        libro30.setAutor("Miguel de Cervantes");
	        libro30.setDescripcion("La novela sigue las aventuras de Don Quijote, quien se embarca en una serie de aventuras en busca de justicia y honor.");
	        libro30.setEditorial("Juan de la Cuesta");
	        libro30.setGenero("Novela de caballerías");
	        libro30.setTitulo("Don Quijote de la Mancha");
	        libro30.setPortada("https://th.bing.com/th/id/OIP._5EhZjP3xyb5o9W-HEjDyQHaKe?w=202&h=285&c=7&r=0&o=5&dpr=1.3&pid=1.7");
	        libro30.setDisponibilidad(false);
	        daoLibro.insert(libro30);
	    }

}
