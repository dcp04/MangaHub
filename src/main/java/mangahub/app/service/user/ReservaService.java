package mangahub.app.service.user;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import mangahub.app.entities.EstadoReserva;
import mangahub.app.entities.Reserva;

/**
 * Interfaz que define los servicios relacionados con las reservas de usuarios.
 */
public interface ReservaService {

    /**
     * Crea una nueva reserva para un manga por parte de un usuario.
     *
     * @param mangaId         El ID del manga a reservar.
     * @param usuarioId       El ID del usuario que realiza la reserva.
     * @param fechaReserva    La fecha de reserva.
     * @param fechaExpiracion La fecha de expiración de la reserva.
     * @return La reserva creada.
     */
    Reserva crearReserva(Long mangaId, Long usuarioId, LocalDate fechaReserva, LocalDate fechaExpiracion);

    /**
     * Cancela una reserva existente.
     *
     * @param reservaId El ID de la reserva a cancelar.
     * @return La reserva cancelada.
     */
    Reserva cancelarReserva(Long reservaId);

    /**
     * Actualiza el estado de una reserva.
     *
     * @param reservaId   El ID de la reserva a actualizar.
     * @param nuevoEstado El nuevo estado de la reserva.
     * @return La reserva actualizada.
     */
    Reserva actualizarEstadoReserva(Long reservaId, EstadoReserva nuevoEstado);

    /**
     * Obtiene una reserva por su ID.
     *
     * @param reservaId El ID de la reserva.
     * @return La reserva encontrada.
     */
    Reserva obtenerReservaPorId(Long reservaId);

    /**
     * Obtiene una lista de todas las reservas.
     *
     * @return La lista de todas las reservas.
     */
    List<Reserva> listarTodasLasReservas();

    /**
     * Obtiene una reserva por el ID de usuario.
     *
     * @param usuarioId El ID del usuario.
     * @return La reserva encontrada para el usuario.
     */
    Optional<Reserva> listarReservasPorUsuario(Long usuarioId);

    /**
     * Verifica si un manga está disponible para ser reservado.
     *
     * @param mangaId El ID del manga.
     * @return true si el manga está disponible para reserva, false de lo contrario.
     */
    boolean esMangaDisponibleParaReserva(Long mangaId);
}
