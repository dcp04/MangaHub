package mangahub.app.controladores;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import mangahub.app.controller.AuthorizationController;
import mangahub.app.dto.response.user.UsuarioResponse;
import mangahub.app.entities.Role;
import mangahub.app.entities.Usuario;

/**
 * Clase de prueba para el controlador de autorización.
 */
@ExtendWith(MockitoExtension.class)
class AuthorizationControladorTest {

    @InjectMocks
    private AuthorizationController authorizationController;

    @Mock
    private Usuario usuario;

    /**
     * Prueba para verificar el método miPerfil del controlador.
     */
    @Test
    void testMiPerfil() {
        // Datos de ejemplo para el usuario
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";

        // Creando un set de roles
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER); // Añadiendo un rol del enum

        // Configurando el comportamiento del mock de usuario
        when(usuario.getFirstName()).thenReturn(firstName);
        when(usuario.getLastName()).thenReturn(lastName);
        when(usuario.getEmail()).thenReturn(email);
        when(usuario.getRoles()).thenReturn(roles);

        // Llamando al método del controlador
        ResponseEntity<UsuarioResponse> response = authorizationController.miPerfil(usuario);

        // Verificando el resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        UsuarioResponse usuarioResponse = response.getBody();
        assertEquals(firstName, usuarioResponse.getFirstName());
        assertEquals(lastName, usuarioResponse.getLastName());
        assertEquals(email, usuarioResponse.getEmail());
        assertEquals("[ROLE_USER]", usuarioResponse.getRol());
    }
}
