package mangahub.app.dto.response.user;

/**
 * Representa la respuesta de un usuario.
 */
public class UsuarioResponse {

    /**
     * El nombre del usuario.
     */
    private String firstName;

    /**
     * El apellido del usuario.
     */
    private String lastName;

    /**
     * El correo electrónico del usuario.
     */
    private String email;

    /**
     * El rol del usuario.
     */
    private String rol;

    /**
     * Construye un objeto UsuarioResponse con la información proporcionada.
     *
     * @param firstName El nombre del usuario.
     * @param lastName  El apellido del usuario.
     * @param email     El correo electrónico del usuario.
     * @param rol       El rol del usuario.
     */
    public UsuarioResponse(String firstName, String lastName, String email, String rol) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.rol = rol;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param firstName El nombre del usuario.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Obtiene el apellido del usuario.
     *
     * @return El apellido del usuario.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Establece el apellido del usuario.
     *
     * @param lastName El apellido del usuario.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return El correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param email El correo electrónico del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el rol del usuario.
     *
     * @return El rol del usuario.
     */
    public String getRol() {
        return rol;
    }

    /**
     * Establece el rol del usuario.
     *
     * @param rol El rol del usuario.
     */
    public void setRol(String rol) {
        this.rol = rol;
    }
}
