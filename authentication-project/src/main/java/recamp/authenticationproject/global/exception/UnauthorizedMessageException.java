package recamp.authenticationproject.global.exception;

public class UnauthorizedMessageException extends RuntimeException {
    public UnauthorizedMessageException() {
    }

    public UnauthorizedMessageException(String message) {
        super(message);
    }

    public UnauthorizedMessageException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedMessageException(Throwable cause) {
        super(cause);
    }

    protected UnauthorizedMessageException(String message, Throwable cause, boolean enableSuppression,
                                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
