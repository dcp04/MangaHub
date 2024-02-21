package mangahub.app.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import mangahub.app.entities.Manga;
import mangahub.app.entities.Role;
import mangahub.app.entities.Usuario;
import mangahub.app.repository.MangaRepository;
import mangahub.app.repository.UserRepository;

/**
 * Esta clase se encarga de inicializar datos de demostración para la aplicación.
 */
@Profile("demo")
@Component
public class InitializationData implements CommandLineRunner {

    @Autowired
    private UserRepository usuarioRepository;

    private final boolean borrarMangas = false; // Variable para controlar el borrado de datos

    @Autowired
    private MangaRepository mangaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Método que se ejecuta al iniciar la aplicación.
     * 
     * @param args Argumentos de línea de comandos.
     * @throws Exception Si ocurre algún error durante la inicialización de datos.
     */
    @Override
    public void run(String... args) throws Exception {

        if (borrarMangas) {
            mangaRepository.deleteAll(); // Borra todos los mangas existentes
        }

        try {
            // Usuario 1 - Rol USER
            Usuario usuario1 = new Usuario();
            usuario1.setFirstName("Alice");
            usuario1.setLastName("Johnson");
            usuario1.setEmail("alice.johnson@example.com");
            usuario1.setPassword(passwordEncoder.encode("password123"));
            usuario1.getRoles().add(Role.ROLE_USER);
            usuarioRepository.save(usuario1);

            // Usuario 2 - Rol ADMIN
            Usuario usuario2 = new Usuario();
            usuario2.setFirstName("Bob");
            usuario2.setLastName("Smith");
            usuario2.setEmail("bob.smith@example.com");
            usuario2.setPassword(passwordEncoder.encode("password456"));
            usuario2.getRoles().add(Role.ROLE_ADMIN);
            usuarioRepository.save(usuario2);

            // Usuario 3 - Rol USER
            Usuario usuario3 = new Usuario();
            usuario3.setFirstName("Carol");
            usuario3.setLastName("Davis");
            usuario3.setEmail("carol.davis@example.com");
            usuario3.setPassword(passwordEncoder.encode("password789"));
            usuario3.getRoles().add(Role.ROLE_USER);
            usuarioRepository.save(usuario3);

        } catch (Exception e) {
            // Manejar cualquier error que pueda ocurrir al guardar los usuarios
        }
        Faker faker = new Faker(new Locale("es"));
        for (int i = 0; i < 10; i++) { // Generar 10 mangas ficticios
            Manga manga = new Manga();
            manga.setTitulo(faker.book().title());
            manga.setAutor(faker.book().author());
            manga.setIsbn(faker.number().digits(10)); // Genera un ISBN ficticio
            manga.setDisponibleParaReserva(true);

            mangaRepository.save(manga);
        }

    }
}
