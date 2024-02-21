/**
 * Este paquete contiene la clase principal de la aplicación.
 */
package mangahub.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * La clase principal para iniciar la aplicación MangaHub.
 */
@SpringBootApplication
@EnableJpaRepositories
public class MangahubApplication {

    /**
     * El método principal para iniciar la aplicación MangaHub.
     * 
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        SpringApplication.run(MangahubApplication.class, args);
    }

}
