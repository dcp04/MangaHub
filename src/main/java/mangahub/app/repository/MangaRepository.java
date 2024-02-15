package mangahub.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mangahub.app.entities.Manga;

@Repository
public interface MangaRepository extends JpaRepository<Manga, Long> {
	/*
	 * @Query("SELECT p.manga FROM Prestamo p WHERE p.usuario.id = :usuarioId")
	 * List<Manga> findMangasPrestadosPorUsuario(Integer usuarioId);
	 */
}
