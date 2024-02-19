package mangahub.app.servicios.usuario;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mangahub.app.entities.EstadoReserva;
import mangahub.app.entities.Manga;
import mangahub.app.entities.Reserva;
import mangahub.app.entities.Usuario;
import mangahub.app.error.exception.MangaNotFoundException;
import mangahub.app.repository.MangaRepository;
import mangahub.app.repository.ReservaRepository;
import mangahub.app.service.impl.MangasServiceImpl;

@ExtendWith(MockitoExtension.class)
public class MangasServicioTest {

    @Mock
    private MangaRepository mangaRepository;

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private MangasServiceImpl mangasService;

    @Test
    public void testAgregarManga() {
        Manga manga = new Manga();
        manga.setTitulo("One Piece");
        manga.setAutor("Eiichiro Oda");
        manga.setIsbn("9784088739359");

        when(mangaRepository.save(manga)).thenReturn(manga);

        Manga result = mangasService.agregarManga(manga);

        assertEquals(manga, result);
    }

    @Test
    public void testObtenerMangaPorIdExiste() {
        Long id = 1L;
        Manga manga = new Manga();
        manga.setId(id);

        when(mangaRepository.findById(id)).thenReturn(Optional.of(manga));

        Manga result = mangasService.obtenerMangaPorId(id);

        assertEquals(manga, result);
    }

    @Test
    public void testObtenerMangaPorIdNoExiste() {
        Long id = 1L;

        when(mangaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(MangaNotFoundException.class, () -> mangasService.obtenerMangaPorId(id));
    }

    @Test
    public void testActualizarManga() {
        Long id = 1L;
        Manga manga = new Manga();
        manga.setId(id);
        manga.setTitulo("Naruto");
        manga.setAutor("Masashi Kishimoto");
        manga.setIsbn("9784088739358");

        Manga detallesManga = new Manga();
        detallesManga.setTitulo("Naruto Shippuden");
        detallesManga.setAutor("Masashi Kishimoto");
        detallesManga.setIsbn("9784088739360");

        when(mangaRepository.findById(id)).thenReturn(Optional.of(manga));
        when(mangaRepository.save(manga)).thenReturn(manga);

        Manga result = mangasService.actualizarManga(id, detallesManga);

        assertEquals(detallesManga.getTitulo(), result.getTitulo());
    }

    @Test
    public void testEliminarManga() {
        Long id = 1L;

        mangasService.eliminarManga(id);

        verify(mangaRepository, times(1)).deleteById(id);
    }

    @Test
    public void testListarTodosLosMangas() {
        Pageable pageable = Pageable.unpaged();
        Page<Manga> page = mock(Page.class);

        when(mangaRepository.findAll(pageable)).thenReturn(page);

        Page<Manga> result = mangasService.listarTodosLosMangas(pageable);

        assertEquals(page, result);
    }

    @Test
    public void testListarMangasPrestadosPorUsuario() {
        Long usuarioId = 1L;
        Pageable pageable = Pageable.unpaged();
        Page<Manga> page = mock(Page.class);

        when(reservaRepository.findMangasPrestadosPorUsuarioId(usuarioId, pageable)).thenReturn(page);

        Page<Manga> result = mangasService.listarMangasPrestadosPorUsuario(usuarioId, pageable);

        assertEquals(page, result);
    }

    @Test
    public void testReservarManga() {
        Manga manga = new Manga();
        Usuario usuario = new Usuario();

        // No implementado, retorna null
        assertNull(mangasService.reservarManga(manga, usuario));
    }

    @Test
    public void testDevolverManga() {
        Long reservaId = 1L;
        Reserva reserva = new Reserva();
        reserva.setId(reservaId);
        reserva.setEstadoReserva(EstadoReserva.PENDIENTE);
        when(reservaRepository.findById(reservaId)).thenReturn(Optional.of(reserva));

       
        mangasService.devolverManga(reservaId);

        verify(reservaRepository).save(reserva);
    }}
