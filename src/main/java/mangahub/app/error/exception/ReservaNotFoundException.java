package mangahub.app.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReservaNotFoundException extends RuntimeException {
    
    public ReservaNotFoundException(String mensaje) {
        super(mensaje);
    }
    
    public ReservaNotFoundException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
