package mangahub.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mangahub.app.entities.Manga;
import mangahub.app.entities.Reserva;
import mangahub.app.entities.Usuario;

/**
 * Interfaz que define los servicios relacionados con los mangas.
 */
public interface MangasService {

    /**
     * Agrega un nuevo manga.
     *
     * @param manga El manga a agregar.
     * @return El manga agregado.
     */
    Manga agregarManga(Manga manga);

    /**
     * Obtiene una página de todos los mangas.
     *
     * @param pageable La información de paginación.
     * @return Una página de mangas.
     */
    Page<Manga> listarTodosLosMangas(Pageable pageable);

    /**
     * Obtiene un manga por su ID.
     *
     * @param id El ID del manga.
     * @return El manga encontrado, o null si no existe.
     */
    Manga obtenerMangaPorId(Long id);

    /**
     * Actualiza un manga existente.
     *
     * @param id    El ID del manga a actualizar.
     * @param manga Los datos actualizados del manga.
     * @return El manga actualizado.
     */
    Manga actualizarManga(Long id, Manga manga);

    /**
     * Elimina un manga por su ID.
     *
     * @param id El ID del manga a eliminar.
     */
    void eliminarManga(Long id);

    /**
     * Obtiene una página de mangas prestados por un usuario.
     *
     * @param usuarioId El ID del usuario.
     * @param pageable  La información de paginación.
     * @return Una página de mangas prestados por el usuario.
     */
    Page<Manga> listarMangasPrestadosPorUsuario(Long usuarioId, Pageable pageable);

    /**
     * Reserva un manga para un usuario.
     *
     * @param manga   El manga a reservar.
     * @param usuario El usuario que realiza la reserva.
     * @return La reserva creada.
     */
    Reserva reservarManga(Manga manga, Usuario usuario);

    /**
     * Devuelve un manga reservado.
     *
     * @param reservaId El ID de la reserva del manga.
     */
    void devolverManga(Long reservaId);

}
