package mangahub.app.service.user;

import mangahub.app.dto.request.SignUpRequest;
import mangahub.app.dto.request.SigninRequest;
import mangahub.app.dto.response.user.JwtAuthenticationResponse;

public interface AuthenticationService {

	JwtAuthenticationResponse signup(SignUpRequest request);

	JwtAuthenticationResponse signin(SigninRequest request);
}
