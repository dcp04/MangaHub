package mangahub.app.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import mangahub.app.entities.EstadoReserva;
import mangahub.app.entities.Manga;
import mangahub.app.entities.Reserva;
import mangahub.app.entities.Usuario;

public class ReservaTest {

    @Test
    public void testGetterAndSetter() {
        // Datos de ejemplo
        Long id = 1L;
        EstadoReserva estadoReserva = EstadoReserva.CONFIRMADA;
        Usuario usuario = new Usuario();
        Manga manga = new Manga();
        LocalDate fechaReserva = LocalDate.now();
        LocalDate fechaExpiracion = LocalDate.now().plusDays(7);
        LocalDate fechaRecogida = LocalDate.now().plusDays(1);
        LocalDate fechaCancelada = LocalDate.now().plusDays(2);
        LocalDate fechaDevolucion = LocalDate.now().plusDays(3);

        // Creando una instancia de Reserva
        Reserva reserva = new Reserva();
        reserva.setId(id);
        reserva.setEstadoReserva(estadoReserva);
        reserva.setUsuario(usuario);
        reserva.setManga(manga);
        reserva.setFechaReserva(fechaReserva);
        reserva.setFechaExpiracion(fechaExpiracion);
        reserva.setFechaRecogida(fechaRecogida);
        reserva.setFechaCancelada(fechaCancelada);
        reserva.setFechaDevolucion(fechaDevolucion);

        // Verificando los valores
        assertEquals(id, reserva.getId());
        assertEquals(estadoReserva, reserva.getEstadoReserva());
        assertEquals(usuario, reserva.getUsuario());
        assertEquals(manga, reserva.getManga());
        assertEquals(fechaReserva, reserva.getFechaReserva());
        assertEquals(fechaExpiracion, reserva.getFechaExpiracion());
        assertEquals(fechaRecogida, reserva.getFechaRecogida());
        assertEquals(fechaCancelada, reserva.getFechaCancelada());
        assertEquals(fechaDevolucion, reserva.getFechaDevolucion());
    }
}
