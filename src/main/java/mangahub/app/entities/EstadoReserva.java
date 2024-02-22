package mangahub.app.entities;

/**
 * Enumera los diferentes estados de una reserva.
 */
public enum EstadoReserva {
    /**
     * Estado de reserva pendiente.
     */
    PENDIENTE,

    /**
     * Estado de reserva confirmada.
     */
    CONFIRMADA,

    /**
     * Estado de reserva cancelada.
     */
    CANCELADA,

    /**
     * Estado de reserva expirada.
     */
    EXPIRADA,

    /**
     * Estado de reserva completada.
     */
    COMPLETADA
}
