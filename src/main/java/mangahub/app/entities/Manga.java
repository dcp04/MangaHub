package mangahub.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Representa un manga.
 */
@Entity
@Table(name = "mangas")
public class Manga {

    /**
     * Identificador único del manga.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Título del manga.
     */
    @NotBlank(message = "El título no puede estar vacío")
    private String titulo;

    /**
     * Autor del manga.
     */
    @NotBlank(message = "El autor no puede estar vacío")
    private String autor;

    /**
     * Indica si el manga está disponible para reserva.
     */
    private boolean disponibleParaReserva;

    /**
     * ISBN del manga.
     */
    @Column(unique = true)
    @NotBlank(message = "El ISBN no puede estar vacío")
    @Size(min = 10, max = 13, message = "El ISBN debe tener entre 10 y 13 caracteres")
    @Pattern(regexp = "[0-9]+", message = "El ISBN solo debe contener números")
    private String isbn;

    /**
     * Obtiene el identificador del manga.
     *
     * @return El identificador del manga.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del manga.
     *
     * @param id El identificador del manga.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el título del manga.
     *
     * @return El título del manga.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título del manga.
     *
     * @param titulo El título del manga.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene el autor del manga.
     *
     * @return El autor del manga.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Establece el autor del manga.
     *
     * @param autor El autor del manga.
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Obtiene el ISBN del manga.
     *
     * @return El ISBN del manga.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Obtiene si el manga está disponible para reserva.
     *
     * @return true si el manga está disponible para reserva, false de lo contrario.
     */
    public boolean isDisponibleParaReserva() {
        return disponibleParaReserva;
    }

    /**
     * Establece si el manga está disponible para reserva.
     *
     * @param disponibleParaReserva true si el manga está disponible para reserva, false de lo contrario.
     */
    public void setDisponibleParaReserva(boolean disponibleParaReserva) {
        this.disponibleParaReserva = disponibleParaReserva;
    }

    /**
     * Establece el ISBN del manga.
     *
     * @param isbn El ISBN del manga.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

}
