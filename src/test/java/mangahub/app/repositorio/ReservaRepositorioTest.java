package mangahub.app.repositorio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mangahub.app.entities.Reserva;
import mangahub.app.error.exception.ReservaNotFoundException;
import mangahub.app.repository.ReservaRepository;
import mangahub.app.service.impl.ReservaServiceImpl;
import mangahub.app.service.user.ReservaService;

@ExtendWith(MockitoExtension.class)
public class ReservaRepositorioTest {

	@Mock
	private ReservaRepository reservaRepository;

	@InjectMocks
	private ReservaService reservaService = new ReservaServiceImpl();

	@Test
	public void testObtenerReservaPorId() {
		Long reservaId = 1L;
		Reserva reservaMock = new Reserva();
		reservaMock.setId(reservaId);

		when(reservaRepository.findById(reservaId)).thenReturn(Optional.of(reservaMock));

		Reserva result = reservaService.obtenerReservaPorId(reservaId);

		// Verificando el resultado
		assertEquals(reservaMock, result);
	}

	@Test
	public void testObtenerReservaPorIdNoExiste() {
		Long reservaId = 1L;

		// Configurando el comportamiento simulado del repositorio
		when(reservaRepository.findById(reservaId)).thenReturn(Optional.empty());

		// Llamando al método que estamos probando y verificando que lance una excepción
		assertThrows(ReservaNotFoundException.class, () -> {
			reservaService.obtenerReservaPorId(reservaId);
		});
	}
}
