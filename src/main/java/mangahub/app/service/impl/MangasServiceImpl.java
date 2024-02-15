package mangahub.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import mangahub.app.entities.Manga;
import mangahub.app.entities.Reserva;
import mangahub.app.entities.Usuario;
import mangahub.app.error.exception.MangaNotFoundException;
import mangahub.app.repository.MangaRepository;
import mangahub.app.repository.ReservaRepository;
import mangahub.app.service.MangasService;

@Service
public class MangasServiceImpl implements MangasService {

	@Autowired
	private MangaRepository mangaRepository;

	@Autowired
	private ReservaRepository reservaRepository;

	@Override
	public Manga agregarManga(@Valid Manga manga) {
		return mangaRepository.save(manga);
	}

	@Override
	public Manga obtenerMangaPorId(Long id) {
		return mangaRepository.findById(id).orElseThrow(() -> new MangaNotFoundException("Manga no encontrado"));
	}

	@Override
	public Manga actualizarManga(Long id, @Valid Manga detallesManga) {
		Manga manga = obtenerMangaPorId(id);
		manga.setTitulo(detallesManga.getTitulo());
		manga.setAutor(detallesManga.getAutor());
		manga.setIsbn(detallesManga.getIsbn());
		return mangaRepository.save(manga);
	}

	@Override
	public void eliminarManga(Long id) {
		mangaRepository.deleteById(id);
	}

	@Override
	public Page<Manga> listarTodosLosMangas(Pageable pageable) {
		return mangaRepository.findAll(pageable);
	}

	@Override
	public Page<Manga> listarMangasPrestadosPorUsuario(Long usuarioId, Pageable pageable) {
		return reservaRepository.findMangasPrestadosPorUsuarioId(usuarioId, pageable);

	}

	@Override
	public Reserva reservarManga(Manga manga, Usuario usuario) {

		return null;
	}

	@Override
	public void devolverManga(Long reservaId) {

	}

}