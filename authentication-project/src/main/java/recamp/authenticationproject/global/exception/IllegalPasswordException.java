package recamp.authenticationproject.global.exception;

public class IllegalPasswordException extends RuntimeException{
    public IllegalPasswordException() {
        super();
    }

    public IllegalPasswordException(String message) {
        super(message);
    }

    public IllegalPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalPasswordException(Throwable cause) {
        super(cause);
    }

    protected IllegalPasswordException(String message, Throwable cause, boolean enableSuppression,
                                       boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
