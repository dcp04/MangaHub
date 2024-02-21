package mangahub.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mangahub.app.entities.EstadoReserva;
import mangahub.app.entities.Manga;
import mangahub.app.entities.Reserva;
import mangahub.app.entities.Usuario;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
	@Query("SELECT r FROM Reserva r WHERE r.usuario.id = :usuarioId")
	Page<Manga> findMangasPrestadosPorUsuarioId(@Param("usuarioId") Long usuarioId, Pageable pageable);
    List<Reserva> findByUsuarioId(Long usuarioId);
	boolean existsByMangaAndUsuarioAndEstadoReserva(Manga manga, Usuario usuario, EstadoReserva estadoReserva);
}
