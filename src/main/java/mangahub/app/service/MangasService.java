package mangahub.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mangahub.app.entities.Manga;
import mangahub.app.entities.Reserva;
import mangahub.app.entities.Usuario;

public interface MangasService {

	Manga agregarManga(Manga manga);

	Page<Manga> listarTodosLosMangas(Pageable pageable);

	Manga obtenerMangaPorId(Long id);

	Manga actualizarManga(Long id, Manga manga);

	void eliminarManga(Long id);

	Page<Manga> listarMangasPrestadosPorUsuario(Long usuarioId, Pageable pageable);

	Reserva reservarManga(Manga manga, Usuario usuario);

	void devolverManga(Long reservaId);

}