package mangahub.app.service.user.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.Builder;
import mangahub.app.dto.request.SignUpRequest;
import mangahub.app.dto.request.SigninRequest;
import mangahub.app.dto.response.user.JwtAuthenticationResponse;
import mangahub.app.entities.Role;
import mangahub.app.entities.Usuario;
import mangahub.app.repository.UserRepository;
import mangahub.app.service.user.AuthenticationService;
import mangahub.app.service.user.JwtService;

/**
 * Implementación del servicio de autenticación.
 */
@Builder
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * Constructor de la implementación del servicio de autenticación.
     *
     * @param userRepository        Repositorio de usuarios.
     * @param passwordEncoder       Codificador de contraseñas.
     * @param jwtService            Servicio JWT.
     * @param authenticationManager Administrador de autenticación.
     */
    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
            JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param request Los detalles de registro del usuario.
     * @return La respuesta de autenticación JWT.
     * @throws IllegalArgumentException si el email ya está en uso.
     */
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already in use.");
        }
        Usuario user = new Usuario();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    /**
     * Inicia sesión para un usuario existente en el sistema.
     *
     * @param request Los detalles de inicio de sesión del usuario.
     * @return La respuesta de autenticación JWT.
     * @throws IllegalArgumentException si el email o la contraseña son inválidos.
     */
    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        Usuario user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
