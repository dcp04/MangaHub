package mangahub.app.controller;

import java.time.LocalDate;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;
import mangahub.app.dto.response.error.DetailsResponse;
import mangahub.app.dto.response.error.ErrorDetailsResponse;
import mangahub.app.entities.Manga;
import mangahub.app.entities.Reserva;
import mangahub.app.entities.Usuario;
import mangahub.app.error.exception.ExcepcionCampoVacio;
import mangahub.app.service.MangasService;
import mangahub.app.service.user.ReservaService;

@RestController
@RequestMapping("/api/v1/mangas")
public class MangaController {

	private static final Logger logger = LoggerFactory.getLogger(MangaController.class);

	@Autowired
	private MangasService mangasService;

	@Autowired
	private ReservaService reservaService;

	// Endpoint para obtener un listado de mangas, accesible solo por ROLE_USER
	@GetMapping
	@PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<Manga>> listarTodosLosMangas(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		logger.info("MangasController :: listarTodosLosMangas");
		Pageable pageable = PageRequest.of(page, size);
		Page<Manga> mangas = mangasService.listarTodosLosMangas(pageable);

		return new ResponseEntity<>(mangas, HttpStatus.OK);
	}

	// Leer un manga por ID
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
	public Manga getMangaById(@PathVariable Long id) {
		return mangasService.obtenerMangaPorId(id);
	}

	// CRUD endpoints, accesibles solo por ROLE_ADMIN
	// Crear un nuevo manga
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Manga createManga(@RequestBody Manga manga) {
		if (manga.getTitulo() == null || manga.getTitulo().isEmpty()) {
			throw new ExcepcionCampoVacio("titulo", "El título no puede estar vacío");
		}

		if (manga.getAutor() == null || manga.getAutor().isEmpty()) {
			throw new ExcepcionCampoVacio("autor", "El autor no puede estar vacío");
		}

		if (manga.getIsbn() == null || manga.getIsbn().isEmpty()) {
			throw new ExcepcionCampoVacio("isbn", "El ISBN no puede estar vacío");
		}
		return mangasService.agregarManga(manga);
	}

	// Actualizar un manga
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Manga updateManga(@PathVariable Long id, @RequestBody Manga mangaDetails) {
		return mangasService.actualizarManga(id, mangaDetails);
	}

	// Eliminar un manga
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteManga(@PathVariable Long id) {
		mangasService.eliminarManga(id);
	}

	@PostMapping("/{mangaId}/reservar")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> realizarReserva(@PathVariable Long mangaId, @AuthenticationPrincipal Usuario usuario) {
		try {
			// Agregar log de la operación
			logger.info("MangasController :: realizarReserva id Manga: {} Usuario: {}", mangaId, usuario.getUsername());

			if (!reservaService.esMangaDisponibleParaReserva(mangaId)) {
				ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(new Date(), "Conflicto",
						"El manga no está disponible para reserva.");
				return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
			}

			LocalDate fechaReserva = LocalDate.now();
			LocalDate fechaExpiracion = fechaReserva.plusDays(7);

			Long usuarioId = usuario.getId();

			Reserva reserva = reservaService.crearReserva(mangaId, usuarioId, fechaReserva, fechaExpiracion);
			DetailsResponse details_reserva = new DetailsResponse(new Date(),
					"Reservado:'" + reserva.getManga().getTitulo() + "', " + reserva.getManga().getAutor(),
					"Expiración reserva:'" + reserva.getFechaExpiracion() + "'"

			);
			return ResponseEntity.status(HttpStatus.CREATED).body(details_reserva);
		} catch (EntityNotFoundException e) {
			ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(new Date(), "No encontrado", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
		} catch (Exception e) {
			ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(new Date(), "Error interno del servidor",
					e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
		}
	}
}
