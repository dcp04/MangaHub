package mangahub.app.service.user;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import mangahub.app.entities.EstadoReserva;
import mangahub.app.entities.Reserva;

public interface ReservaService {
	Reserva crearReserva(Long mangaId, Long usuarioId, LocalDate fechaReserva, LocalDate fechaExpiracion);

	Reserva cancelarReserva(Long reservaId);

	Reserva actualizarEstadoReserva(Long reservaId, EstadoReserva nuevoEstado);

	Optional<Reserva> obtenerReservaPorId(Long reservaId);

	List<Reserva> listarTodasLasReservas();

	List<Reserva> listarReservasPorUsuario(Long usuarioId);

	boolean esMangaDisponibleParaReserva(Long mangaId);

}
