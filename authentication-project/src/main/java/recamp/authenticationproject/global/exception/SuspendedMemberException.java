package recamp.authenticationproject.global.exception;

public class SuspendedMemberException extends RuntimeException {
    public SuspendedMemberException() {
        super();
    }

    public SuspendedMemberException(String message) {
        super(message);
    }

    public SuspendedMemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public SuspendedMemberException(Throwable cause) {
        super(cause);
    }

    protected SuspendedMemberException(String message, Throwable cause, boolean enableSuppression,
                                       boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
