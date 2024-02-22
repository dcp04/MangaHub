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

/**
 * Repositorio para la entidad Reserva que gestiona las operaciones de base de datos relacionadas con las reservas.
 */
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    /**
     * Busca los mangas prestados por un usuario paginados.
     *
     * @param usuarioId El ID del usuario.
     * @param pageable  La información de paginación.
     * @return Una página de mangas prestados por el usuario.
     */
    @Query("SELECT r.manga FROM Reserva r WHERE r.usuario.id = :usuarioId")
    Page<Manga> findMangasPrestadosPorUsuarioId(@Param("usuarioId") Long usuarioId, Pageable pageable);

    /**
     * Busca las reservas realizadas por un usuario.
     *
     * @param usuarioId El ID del usuario.
     * @return Una lista de reservas realizadas por el usuario.
     */
    List<Reserva> findByUsuarioId(Long usuarioId);

    /**
     * Comprueba si existe una reserva para un manga y un usuario con un estado de reserva específico.
     *
     * @param manga         El manga.
     * @param usuario       El usuario.
     * @param estadoReserva El estado de reserva.
     * @return true si existe una reserva con los parámetros especificados, false de lo contrario.
     */
    boolean existsByMangaAndUsuarioAndEstadoReserva(Manga manga, Usuario usuario, EstadoReserva estadoReserva);
}
