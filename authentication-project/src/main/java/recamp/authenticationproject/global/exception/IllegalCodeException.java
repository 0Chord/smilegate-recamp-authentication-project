package recamp.authenticationproject.global.exception;

public class IllegalCodeException extends RuntimeException {
    public IllegalCodeException() {
        super();
    }

    public IllegalCodeException(String message) {
        super(message);
    }

    public IllegalCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalCodeException(Throwable cause) {
        super(cause);
    }

    protected IllegalCodeException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
