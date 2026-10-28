package vn.longvan.tranning.spring.user.exception;

public class UserException extends RuntimeException {
    private UserErrorCode errorCode;

    public UserException(String message, UserErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public UserException(String message, Throwable cause, UserErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}
