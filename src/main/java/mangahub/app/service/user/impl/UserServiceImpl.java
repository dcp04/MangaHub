package mangahub.app.service.user.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mangahub.app.dto.response.user.UsuarioResponse;
import mangahub.app.repository.UserRepository;
import mangahub.app.service.UserService;

/**
 * Implementación del servicio UserService que proporciona operaciones relacionadas con los usuarios.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Retorna un UserDetailsService para la autenticación de usuarios.
     *
     * @return Un UserDetailsService.
     */
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            }
        };
    }

    /**
     * Obtiene todos los usuarios y los convierte en una lista de respuestas de usuario.
     *
     * @return Una lista de UsuarioResponse que contiene los detalles de todos los usuarios.
     */
    @Override
    public List<UsuarioResponse> getAllUsers() {
        List<UsuarioResponse> allUsers = userRepository.findAll().stream()
                .map(usuario -> new UsuarioResponse(usuario.getFirstName(), usuario.getLastName(), usuario.getEmail(),
                        usuario.getRoles().toString()))
                .collect(Collectors.toList());
        return allUsers;
    }
}
