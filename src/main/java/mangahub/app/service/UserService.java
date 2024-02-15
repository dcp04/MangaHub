package mangahub.app.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import mangahub.app.dto.response.user.UsuarioResponse;

public interface UserService {
	UserDetailsService userDetailsService();

	List<UsuarioResponse> getAllUsers();
}
