package Application.exception;

public class InvalidJwtAuthenticationExcepting extends RuntimeException {
    public InvalidJwtAuthenticationExcepting(String message) {
        super(message);
    }
}
