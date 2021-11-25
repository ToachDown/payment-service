package template.exception;

public class ApiSystemException extends RuntimeException {

    public ApiSystemException() {
    }

    public ApiSystemException(String message) {
        super(message);
    }

    public ApiSystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiSystemException(Throwable cause) {
        super(cause);
    }
}
