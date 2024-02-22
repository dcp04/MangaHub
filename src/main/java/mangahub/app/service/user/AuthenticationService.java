package mangahub.app.service.user;

import mangahub.app.dto.request.SignUpRequest;
import mangahub.app.dto.request.SigninRequest;
import mangahub.app.dto.response.user.JwtAuthenticationResponse;

/**
 * Interfaz que define los servicios de autenticación de usuarios.
 */
public interface AuthenticationService {

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param request Los detalles de registro del usuario.
     * @return La respuesta de autenticación JWT.
     */
    JwtAuthenticationResponse signup(SignUpRequest request);

    /**
     * Inicia sesión para un usuario existente en el sistema.
     *
     * @param request Los detalles de inicio de sesión del usuario.
     * @return La respuesta de autenticación JWT.
     */
    JwtAuthenticationResponse signin(SigninRequest request);
}
