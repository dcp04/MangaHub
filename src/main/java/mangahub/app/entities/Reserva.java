package mangahub.app.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;

/**
 * Representa una reserva de manga.
 */
@Entity
public class Reserva {
    /**
     * Identificador único de la reserva.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Estado de la reserva.
     */
    @Enumerated(EnumType.STRING)
    private EstadoReserva estadoReserva;

    /**
     * Usuario que realiza la reserva.
     */
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    /**
     * Manga reservado.
     */
    @ManyToOne
    @JoinColumn(name = "manga_id")
    private Manga manga;

    /**
     * Fecha de la reserva.
     */
    @PastOrPresent
    private LocalDate fechaReserva;

    /**
     * Fecha de expiración de la reserva.
     */
    @FutureOrPresent
    private LocalDate fechaExpiracion;

    /**
     * Fecha de recogida del manga.
     */
    @FutureOrPresent
    private LocalDate fechaRecogida;

    /**
     * Fecha de cancelación de la reserva.
     */
    @FutureOrPresent
    private LocalDate fechaCancelada;

    /**
     * Fecha de devolución del manga.
     */
    @FutureOrPresent
    private LocalDate fechaDevolucion;

    /**
     * Obtiene el identificador de la reserva.
     *
     * @return El identificador de la reserva.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador de la reserva.
     *
     * @param id El identificador de la reserva.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el estado de la reserva.
     *
     * @return El estado de la reserva.
     */
    public EstadoReserva getEstadoReserva() {
        return estadoReserva;
    }

    /**
     * Establece el estado de la reserva.
     *
     * @param estadoReserva El estado de la reserva.
     */
    public void setEstadoReserva(EstadoReserva estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    /**
     * Obtiene el usuario que realizó la reserva.
     *
     * @return El usuario que realizó la reserva.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario que realizó la reserva.
     *
     * @param usuario El usuario que realizó la reserva.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene el manga reservado.
     *
     * @return El manga reservado.
     */
    public Manga getManga() {
        return manga;
    }

    /**
     * Establece el manga reservado.
     *
     * @param manga El manga reservado.
     */
    public void setManga(Manga manga) {
        this.manga = manga;
    }

    /**
     * Obtiene la fecha de la reserva.
     *
     * @return La fecha de la reserva.
     */
    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    /**
     * Establece la fecha de la reserva.
     *
     * @param fechaReserva La fecha de la reserva.
     */
    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    /**
     * Obtiene la fecha de expiración de la reserva.
     *
     * @return La fecha de expiración de la reserva.
     */
    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    /**
     * Establece la fecha de expiración de la reserva.
     *
     * @param fechaExpiracion La fecha de expiración de la reserva.
     */
    public void setFechaExpiracion(LocalDate fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    /**
     * Obtiene la fecha de recogida del manga.
     *
     * @return La fecha de recogida del manga.
     */
    public LocalDate getFechaRecogida() {
        return fechaRecogida;
    }

    /**
     * Establece la fecha de recogida del manga.
     *
     * @param fechaRecogida La fecha de recogida del manga.
     */
    public void setFechaRecogida(LocalDate fechaRecogida) {
        this.fechaRecogida = fechaRecogida;
    }

    /**
     * Obtiene la fecha de cancelación de la reserva.
     *
     * @return La fecha de cancelación de la reserva.
     */
    public LocalDate getFechaCancelada() {
        return fechaCancelada;
    }

    /**
     * Establece la fecha de cancelación de la reserva.
     *
     * @param fechaCancelada La fecha de cancelación de la reserva.
     */
    public void setFechaCancelada(LocalDate fechaCancelada) {
        this.fechaCancelada = fechaCancelada;
    }

    /**
     * Obtiene la fecha de devolución del manga.
     *
     * @return La fecha de devolución del manga.
     */
    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    /**
     * Establece la fecha de devolución del manga.
     *
     * @param fechaDevolucion La fecha de devolución del manga.
     */
    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}
