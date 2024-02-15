package mangahub.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mangahub.app.entities.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByEmail(String email);

	Optional<Usuario> findById(Long id);

	Boolean existsByEmail(String email);
}
