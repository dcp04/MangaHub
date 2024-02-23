package mangahub.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mangahub.app.entities.Manga;

/**
 * Repositorio para la entidad Manga que gestiona las operaciones de base de datos relacionadas con los mangas.
 */
@Repository
public interface MangaRepository extends JpaRepository<Manga, Long> {
    /*
     * Ejemplo de consulta personalizada usando JPQL
     * 
     * @Query("SELECT p.manga FROM Prestamo p WHERE p.usuario.id = :usuarioId")
     * List<Manga> findMangasPrestadosPorUsuario(Integer usuarioId);
     */
	 /**
     * Busca mangas por su título, ignorando mayúsculas y minúsculas.
     * 
     * @param titulo   El título del manga a buscar.
     * @param pageable La información de paginación.
     * @return Una página de mangas que contienen el título especificado.
     */
    Page<Manga> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);

    /**
     * Busca mangas por su autor, ignorando mayúsculas y minúsculas.
     * 
     * @param autor    El autor del manga a buscar.
     * @param pageable La información de paginación.
     * @return Una página de mangas que contienen el autor especificado.
     */
    Page<Manga> findByAutorContainingIgnoreCase(String autor, Pageable pageable);

    /**
     * Busca mangas por título y autor, ignorando mayúsculas y minúsculas.
     * 
     * @param titulo   El título del manga a buscar.
     * @param autor    El autor del manga a buscar.
     * @param pageable La información de paginación.
     * @return Una página de mangas que contienen el título y autor especificados.
     */
    Page<Manga> findByTituloContainingIgnoreCaseAndAutorContainingIgnoreCase(String titulo, String autor,
            Pageable pageable);

}
