package mangahub.app.dto.request;

/**
 * Clase que representa la solicitud de registro de usuario.
 */
public class SignUpRequest {
    private String firstName; // Nombre del usuario
    private String lastName; // Apellido del usuario
    private String email; // Correo electrónico del usuario
    private String password; // Contraseña del usuario

    /**
     * Método getter para obtener el nombre del usuario.
     * @return String El nombre del usuario.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Método setter para establecer el nombre del usuario.
     * @param firstName El nombre a establecer.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Método getter para obtener el apellido del usuario.
     * @return String El apellido del usuario.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Método setter para establecer el apellido del usuario.
     * @param lastName El apellido a establecer.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Método getter para obtener el correo electrónico del usuario.
     * @return String El correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Método setter para establecer el correo electrónico del usuario.
     * @param email El correo electrónico a establecer.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Método getter para obtener la contraseña del usuario.
     * @return String La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Método setter para establecer la contraseña del usuario.
     * @param password La contraseña a establecer.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
