package mangahub.app.controller;

import java.time.LocalDate;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import jakarta.validation.Valid;
import mangahub.app.dto.response.error.DetailsResponse;
import mangahub.app.dto.response.error.ErrorDetailsResponse;
import mangahub.app.entities.Manga;
import mangahub.app.entities.Reserva;
import mangahub.app.entities.Usuario;
import mangahub.app.error.exception.ExcepcionCampoVacio;
import mangahub.app.service.MangasService;
import mangahub.app.service.user.ReservaService;

/**
 * Controlador para las operaciones relacionadas con los mangas.
 */
@RestController
@RequestMapping("/api/v1/mangas")
public class MangaController {

	private static final Logger logger = LoggerFactory.getLogger(MangaController.class);

	@Autowired
	private MangasService mangasService;

	@Autowired
	private ReservaService reservaService;

	/**
	 * Endpoint para obtener un listado de mangas, accesible solo por ROLE_USER
	 * 
	 * @param titulo El título del manga a buscar (opcional)
	 * @param autor  El autor del manga a buscar (opcional)
	 * @param page   El número de página a obtener
	 * @param size   El tamaño de la página
	 * @return Una respuesta que contiene una página de mangas
	 */
	@GetMapping
	@PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<Manga>> listarTodosLosMangas(@RequestParam(required = false) String titulo,
			@RequestParam(required = false) String autor, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "titulo") String sortBy,
			@RequestParam(defaultValue = "asc") String sortDirection) {

		logger.info("MangasController :: listarTodosLosMangas");
		Pageable pageable;

		Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
		pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

		Page<Manga> mangas;

		if (titulo != null && autor != null) {
			mangas = mangasService.filtrarPorTituloYAutor(titulo, autor, pageable);
		} else if (titulo != null) {
			mangas = mangasService.filtrarPorTitulo(titulo, pageable);
		} else if (autor != null) {
			mangas = mangasService.filtrarPorAutor(autor, pageable);
		} else {
			mangas = mangasService.listarTodosLosMangas(pageable);
		}

		return new ResponseEntity<>(mangas, HttpStatus.OK);
	}

	/**
	 * Endpoint para obtener un manga por su ID.
	 * 
	 * @param id El ID del manga a buscar
	 * @return El manga correspondiente al ID proporcionado
	 */
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
	public Manga getMangaById(@PathVariable Long id) {
		return mangasService.obtenerMangaPorId(id);
	}

	/**
	 * Endpoint para crear un nuevo manga.
	 * 
	 * @param manga El manga a crear
	 * @return El manga creado
	 */
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Manga createManga(@Valid @RequestBody Manga manga) {
		return mangasService.agregarManga(manga);
	}

	/**
	 * Endpoint para actualizar un manga existente.
	 * 
	 * @param id           El ID del manga a actualizar
	 * @param mangaDetails Los detalles actualizados del manga
	 * @return El manga actualizado
	 */
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Manga updateManga(@PathVariable Long id, @RequestBody Manga mangaDetails) {
		return mangasService.actualizarManga(id, mangaDetails);
	}

	/**
	 * Endpoint para eliminar un manga existente.
	 * 
	 * @param id El ID del manga a eliminar
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteManga(@PathVariable Long id) {
		mangasService.eliminarManga(id);
	}

	/**
	 * Endpoint para realizar una reserva de un manga, accesible solo por ROLE_USER
	 * 
	 * @param mangaId El ID del manga a reservar
	 * @param usuario El usuario que realiza la reserva
	 * @return Una respuesta que contiene detalles sobre la reserva
	 */
	@PostMapping("/{mangaId}/reservar")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> realizarReserva(@PathVariable Long mangaId, @AuthenticationPrincipal Usuario usuario) {
		try {
			// Agregar log de la operación
			logger.info("MangasController :: realizarReserva id Manga: {} Usuario: {}", mangaId, usuario.getUsername());

			// Verificar si el manga está disponible para reserva
			if (!reservaService.esMangaDisponibleParaReserva(mangaId)) {
				ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(new Date(), "Conflicto",
						"El manga no está disponible para reserva.");
				return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
			}

			// Obtener la fecha actual y calcular la fecha de expiración de la reserva
			LocalDate fechaReserva = LocalDate.now();
			LocalDate fechaExpiracion = fechaReserva.plusDays(7);

			Long usuarioId = usuario.getId();

			// Crear la reserva
			Reserva reserva = reservaService.crearReserva(mangaId, usuarioId, fechaReserva, fechaExpiracion);
			DetailsResponse details_reserva = new DetailsResponse(new Date(),
					"Reservado:'" + reserva.getManga().getTitulo() + "', " + reserva.getManga().getAutor(),
					"Expiración reserva:'" + reserva.getFechaExpiracion() + "'");
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
