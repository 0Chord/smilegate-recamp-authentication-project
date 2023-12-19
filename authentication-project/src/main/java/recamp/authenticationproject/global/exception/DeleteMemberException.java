package recamp.authenticationproject.global.exception;

public class DeleteMemberException extends RuntimeException{
    public DeleteMemberException() {
        super();
    }

    public DeleteMemberException(String message) {
        super(message);
    }

    public DeleteMemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteMemberException(Throwable cause) {
        super(cause);
    }

    protected DeleteMemberException(String message, Throwable cause, boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
