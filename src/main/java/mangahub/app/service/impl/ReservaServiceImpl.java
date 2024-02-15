package mangahub.app.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import mangahub.app.entities.EstadoReserva;
import mangahub.app.entities.Manga;
import mangahub.app.entities.Reserva;
import mangahub.app.entities.Usuario;
import mangahub.app.error.exception.MangaNotFoundException;
import mangahub.app.error.exception.UserNotFoundException;
import mangahub.app.repository.MangaRepository;
import mangahub.app.repository.ReservaRepository;
import mangahub.app.repository.UserRepository;
import mangahub.app.service.user.ReservaService;

@Service
public class ReservaServiceImpl implements ReservaService {
	@Autowired
	private ReservaRepository reservaRepositorio;

	@Autowired
	private MangaRepository mangaRepositorio;

	@Autowired
	private UserRepository usuarioRepositorio;

	@Override
	@Transactional
	public Reserva crearReserva(Long mangaId, Long usuarioId, LocalDate fechaReserva, LocalDate fechaExpiracion) {

		Manga manga = mangaRepositorio.findById(mangaId)
				.orElseThrow(() -> new MangaNotFoundException("Manga no encontrado"));

		if (!esMangaDisponibleParaReserva(mangaId)) {
			throw new IllegalStateException("El manga no está disponible para reserva");
		}

		Usuario usuario = usuarioRepositorio.findById(usuarioId)
				.orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

		boolean reservaExistente = reservaRepositorio.existsByMangaAndUsuarioAndEstadoReserva(manga, usuario,
				EstadoReserva.PENDIENTE);

		if (reservaExistente) {
			throw new IllegalStateException("Ya existe una reserva pendiente para este manga y usuario.");
		}

		Reserva reserva = new Reserva();
		reserva.setManga(manga);
		reserva.setUsuario(usuario);
		reserva.setFechaReserva(fechaReserva);
		reserva.setFechaExpiracion(fechaExpiracion);
		reserva.setEstadoReserva(EstadoReserva.PENDIENTE);

		return reservaRepositorio.save(reserva);
	}

	@Override
	public Reserva cancelarReserva(Long reservaId) {
		return null;
	}

	@Override
	public Reserva actualizarEstadoReserva(Long reservaId, EstadoReserva nuevoEstado) {
		return null;
	}

	@Override
	public Optional<Reserva> obtenerReservaPorId(Long reservaId) {
		return Optional.empty();
	}

	@Override
	public List<Reserva> listarTodasLasReservas() {
		return null;
	}

	@Override
	public List<Reserva> listarReservasPorUsuario(Long usuarioId) {
		return null;
	}

	@Override
	public boolean esMangaDisponibleParaReserva(Long mangaId) {
		return true;
	}

}
