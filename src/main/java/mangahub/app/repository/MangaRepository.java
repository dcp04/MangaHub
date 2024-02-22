package mangahub.app.repository;

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
}
