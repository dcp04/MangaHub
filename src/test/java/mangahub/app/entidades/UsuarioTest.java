package mangahub.app.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import mangahub.app.entities.Role;
import mangahub.app.entities.Usuario;

public class UsuarioTest {

	@Test
	public void testGetterAndSetter() {
		// Datos de ejemplo
		Long id = 1L;
		String firstName = "John";
		String lastName = "Doe";
		String email = "john.doe@example.com";
		String password = "password";
		Set<Role> roles = new HashSet<>();
		roles.add(Role.ROLE_USER);

		// Creando una instancia de Usuario
		Usuario usuario = new Usuario();
		usuario.setId(id);
		usuario.setFirstName(firstName);
		usuario.setLastName(lastName);
		usuario.setEmail(email);
		usuario.setPassword(password);
		usuario.setRoles(roles);

		// Verificando los valores
		assertEquals(id, usuario.getId());
		assertEquals(firstName, usuario.getFirstName());
		assertEquals(lastName, usuario.getLastName());
		assertEquals(email, usuario.getEmail());
		assertEquals(password, usuario.getPassword());
		assertEquals(roles, usuario.getRoles());
	}

	@Test
	public void testGetAuthorities() {
		// Datos de ejemplo
		Set<Role> roles = new HashSet<>();
		roles.add(Role.ROLE_USER);
		roles.add(Role.ROLE_ADMIN);

		// Creando una instancia de Usuario
		Usuario usuario = new Usuario();
		usuario.setRoles(roles);

		// Obteniendo las autoridades
		var authorities = usuario.getAuthorities();

		// Verificando las autoridades
		assertEquals(2, authorities.size());
		assertEquals(true, authorities.contains(new SimpleGrantedAuthority("ROLE_USER")));
		assertEquals(true, authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
	}
}
