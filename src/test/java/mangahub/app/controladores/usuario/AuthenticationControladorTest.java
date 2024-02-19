package mangahub.app.controladores.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import mangahub.app.controller.user.AuthenticationController;
import mangahub.app.dto.request.SignUpRequest;
import mangahub.app.dto.request.SigninRequest;
import mangahub.app.dto.response.user.JwtAuthenticationResponse;
import mangahub.app.service.user.AuthenticationService;

@ExtendWith(MockitoExtension.class)
public class AuthenticationControladorTest {

    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private AuthenticationService authenticationService;

    @Test
    public void testSignup() {
        // Datos de ejemplo para la solicitud de registro
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setFirstName("testUser");
        signUpRequest.setPassword("testPassword");
        // Simulando la respuesta del servicio de autenticación
        JwtAuthenticationResponse authenticationResponse = new JwtAuthenticationResponse("testToken");
        when(authenticationService.signup(signUpRequest)).thenReturn(authenticationResponse);

        // Llamando al método del controlador
        ResponseEntity<JwtAuthenticationResponse> response = authenticationController.signup(signUpRequest);

        // Verificando el resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(authenticationResponse, response.getBody());
    }

    @Test
    public void testSignin() {
        // Datos de ejemplo para la solicitud de inicio de sesión
        SigninRequest signinRequest = new SigninRequest();
        signinRequest.setEmail("testUser");
        signinRequest.setPassword("testPassword");
        // Simulando la respuesta del servicio de autenticación
        JwtAuthenticationResponse authenticationResponse = new JwtAuthenticationResponse("testToken");
        when(authenticationService.signin(signinRequest)).thenReturn(authenticationResponse);

        // Llamando al método del controlador
        ResponseEntity<JwtAuthenticationResponse> response = authenticationController.signin(signinRequest);

        // Verificando el resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(authenticationResponse, response.getBody());
    }
}
