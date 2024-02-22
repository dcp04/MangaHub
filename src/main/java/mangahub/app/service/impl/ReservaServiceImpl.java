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
import mangahub.app.error.exception.ReservaNotFoundException;
import mangahub.app.error.exception.UserNotFoundException;
import mangahub.app.repository.MangaRepository;
import mangahub.app.repository.ReservaRepository;
import mangahub.app.repository.UserRepository;
import mangahub.app.service.user.ReservaService;

/**
 * Implementación de ReservaService.
 */
@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepositorio;

    @Autowired
    private MangaRepository mangaRepositorio;

    @Autowired
    private UserRepository usuarioRepositorio;

    /**
     * Crea una nueva reserva para un manga y un usuario.
     *
     * @param mangaId        El ID del manga.
     * @param usuarioId      El ID del usuario.
     * @param fechaReserva   La fecha de reserva.
     * @param fechaExpiracion La fecha de expiración.
     * @return La reserva creada.
     * @throws MangaNotFoundException  Si el manga no se encuentra.
     * @throws UserNotFoundException   Si el usuario no se encuentra.
     * @throws IllegalStateException    Si el manga no está disponible para reserva o ya existe una reserva pendiente para el manga y el usuario.
     */
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

    /**
     * Cancela una reserva existente.
     *
     * @param reservaId El ID de la reserva a cancelar.
     * @return La reserva cancelada.
     * @throws ReservaNotFoundException Si la reserva no se encuentra.
     */
    @Override
    public Reserva cancelarReserva(Long reservaId) {
        Optional<Reserva> reservaOptional = reservaRepositorio.findById(reservaId);
        if (reservaOptional.isPresent()) {
            Reserva reserva = reservaOptional.get();
            reserva.setEstadoReserva(EstadoReserva.CANCELADA);
            return reservaRepositorio.save(reserva);
        } else {
            throw new ReservaNotFoundException("Reserva no encontrada con ID: " + reservaId);
        }
    }

    /**
     * Actualiza el estado de una reserva existente.
     *
     * @param reservaId   El ID de la reserva a actualizar.
     * @param nuevoEstado El nuevo estado de la reserva.
     * @return La reserva actualizada.
     * @throws ReservaNotFoundException Si la reserva no se encuentra.
     */
    @Override
    public Reserva actualizarEstadoReserva(Long reservaId, EstadoReserva nuevoEstado) {
        Optional<Reserva> reservaOptional = reservaRepositorio.findById(reservaId);
        if (reservaOptional.isPresent()) {
            Reserva reserva = reservaOptional.get();
            reserva.setEstadoReserva(nuevoEstado);
            return reservaRepositorio.save(reserva);
        } else {
            throw new ReservaNotFoundException("Reserva no encontrada con ID: " + reservaId);
        }
    }

    /**
     * Obtiene una reserva por su ID.
     *
     * @param reservaId El ID de la reserva.
     * @return La reserva encontrada.
     * @throws ReservaNotFoundException Si la reserva no se encuentra.
     */
    @Override
    public Reserva obtenerReservaPorId(Long reservaId) {
        return reservaRepositorio.findById(reservaId)
                .orElseThrow(() -> new ReservaNotFoundException("Reserva no encontrada con ID: " + reservaId));
    }

    /**
     * Obtiene una lista de todas las reservas.
     *
     * @return Una lista de todas las reservas.
     */
    @Override
    public List<Reserva> listarTodasLasReservas() {
        return reservaRepositorio.findAll();
    }

    /**
     * Obtiene las reservas de un usuario por su ID.
     *
     * @param usuarioId El ID del usuario.
     * @return Una lista de reservas del usuario.
     */
    @Override
    public Optional<Reserva> listarReservasPorUsuario(Long usuarioId) {
        return reservaRepositorio.findById(usuarioId);
    }

    /**
     * Verifica si un manga está disponible para reserva.
     *
     * @param mangaId El ID del manga.
     * @return true si el manga está disponible para reserva, false de lo contrario.
     */
    @Override
    public boolean esMangaDisponibleParaReserva(Long mangaId) {
        Optional<Manga> mangaOptional = mangaRepositorio.findById(mangaId);
        return mangaOptional.isPresent() && mangaOptional.get().isDisponibleParaReserva();
    }
}
