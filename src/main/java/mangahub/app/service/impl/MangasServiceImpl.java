package mangahub.app.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import mangahub.app.entities.EstadoReserva;
import mangahub.app.entities.Manga;
import mangahub.app.entities.Reserva;
import mangahub.app.entities.Usuario;
import mangahub.app.error.exception.MangaNotFoundException;
import mangahub.app.error.exception.ReservaNotFoundException;
import mangahub.app.repository.MangaRepository;
import mangahub.app.repository.ReservaRepository;
import mangahub.app.service.MangasService;

/**
 * Implementación de los servicios relacionados con los mangas.
 */
@Service
public class MangasServiceImpl implements MangasService {

    @Autowired
    private MangaRepository mangaRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    /**
     * Agrega un nuevo manga.
     *
     * @param manga El manga a agregar.
     * @return El manga agregado.
     */
    @Override
    public Manga agregarManga(@Valid Manga manga) {
        return mangaRepository.save(manga);
    }

    /**
     * Obtiene un manga por su ID.
     *
     * @param id El ID del manga.
     * @return El manga encontrado.
     * @throws MangaNotFoundException Si no se encuentra el manga con el ID especificado.
     */
    @Override
    public Manga obtenerMangaPorId(Long id) {
        return mangaRepository.findById(id).orElseThrow(() -> new MangaNotFoundException("Manga no encontrado"));
    }

    /**
     * Actualiza un manga existente.
     *
     * @param id            El ID del manga a actualizar.
     * @param detallesManga Los detalles actualizados del manga.
     * @return El manga actualizado.
     * @throws MangaNotFoundException Si no se encuentra el manga con el ID especificado.
     */
    @Override
    public Manga actualizarManga(Long id, @Valid Manga detallesManga) {
        Manga manga = obtenerMangaPorId(id);
        manga.setTitulo(detallesManga.getTitulo());
        manga.setAutor(detallesManga.getAutor());
        manga.setIsbn(detallesManga.getIsbn());
        return mangaRepository.save(manga);
    }

    /**
     * Elimina un manga por su ID.
     *
     * @param id El ID del manga a eliminar.
     */
    @Override
    public void eliminarManga(Long id) {
        mangaRepository.deleteById(id);
    }

    /**
     * Obtiene una página de todos los mangas.
     *
     * @param pageable La información de paginación.
     * @return Una página de mangas.
     */
    @Override
    public Page<Manga> listarTodosLosMangas(Pageable pageable) {
        return mangaRepository.findAll(pageable);
    }

    /**
     * Obtiene una página de mangas prestados por un usuario.
     *
     * @param usuarioId El ID del usuario.
     * @param pageable  La información de paginación.
     * @return Una página de mangas prestados por el usuario.
     */
    @Override
    public Page<Manga> listarMangasPrestadosPorUsuario(Long usuarioId, Pageable pageable) {
        return reservaRepository.findMangasPrestadosPorUsuarioId(usuarioId, pageable);
    }

    /**
     * Reserva un manga para un usuario.
     *
     * @param manga   El manga a reservar.
     * @param usuario El usuario que realiza la reserva.
     * @return La reserva creada.
     */
    @Override
    public Reserva reservarManga(Manga manga, Usuario usuario) {
        Reserva reserva = new Reserva();
        reserva.setManga(manga);
        reserva.setUsuario(usuario);
        reserva.setEstadoReserva(EstadoReserva.PENDIENTE); // O cualquier otro estado que desees
        return reservaRepository.save(reserva);
    }

    /**
     * Marca una reserva como completada, es decir, el manga ha sido devuelto.
     *
     * @param reservaId El ID de la reserva del manga.
     * @throws ReservaNotFoundException Si no se encuentra la reserva con el ID especificado.
     */
    @Override
    public void devolverManga(Long reservaId) {
        Optional<Reserva> reservaOptional = reservaRepository.findById(reservaId);
        if (reservaOptional.isPresent()) {
            Reserva reserva = reservaOptional.get();
            reserva.setEstadoReserva(EstadoReserva.COMPLETADA);
            reservaRepository.save(reserva);
        } else {
            throw new ReservaNotFoundException("Reserva no encontrada con ID: " + reservaId);
        }
    }
}
