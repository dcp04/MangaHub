package mangahub.app.repositorio;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import mangahub.app.entities.Usuario;
import mangahub.app.repository.UserRepository;

@SpringBootTest
@TestPropertySource(locations="classpath:application.properties")
public class UserRepositorioTest {

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testFindByEmail() {
        // Arrange
        String email = "test@example.com";
        Usuario user = new Usuario();
        user.setId(1L);
        user.setEmail(email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // Act
        Optional<Usuario> foundUser = userRepository.findByEmail(email);

        // Assert
        assertTrue(foundUser.isPresent());
        assertEquals(email, foundUser.get().getEmail());
    }

    @Test
    public void testFindById() {
        // Arrange
        long userId = 1L;
        Usuario user = new Usuario();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        Optional<Usuario> foundUser = userRepository.findById(userId);

        // Assert
        assertTrue(foundUser.isPresent());
        assertEquals(userId, foundUser.get().getId());
    }

    @Test
    public void testExistsByEmail() {
        // Arrange
        String email = "test@example.com";
        when(userRepository.existsByEmail(email)).thenReturn(true);

        // Act
        boolean exists = userRepository.existsByEmail(email);

        // Assert
        assertTrue(exists);
    }
}
