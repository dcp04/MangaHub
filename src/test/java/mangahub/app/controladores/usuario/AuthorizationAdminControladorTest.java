package mangahub.app.controladores.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import mangahub.app.controller.user.AuthorizationAdminController;
import mangahub.app.dto.response.user.UsuarioResponse;
import mangahub.app.service.UserService;

@ExtendWith(MockitoExtension.class)
public class AuthorizationAdminControladorTest {

	@InjectMocks
	private AuthorizationAdminController authorizationAdminController;

	@Mock
	private UserService userService;

	@Test
	public void testShowUsers() {
		List<UsuarioResponse> usuarios = new ArrayList<>();
		usuarios.add(new UsuarioResponse("user1", "User 1", "user1@example.com", "ROLE_USER"));
		usuarios.add(new UsuarioResponse("user2", "User 2", "user2@example.com", "ROLE_ADMIN"));

		when(userService.getAllUsers()).thenReturn(usuarios);

		ResponseEntity<List<UsuarioResponse>> response = authorizationAdminController.showUsers();

		// Verifica el resultado
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(usuarios, response.getBody());
	}
}
