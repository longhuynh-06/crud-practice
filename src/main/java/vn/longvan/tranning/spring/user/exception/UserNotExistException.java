package vn.longvan.tranning.spring.user.exception;

public class UserNotExistException extends UserException {
    private String userId;

    public UserNotExistException(String message, String userId) {
        super(message, UserErrorCode.USER_NOT_EXITS);
        this.userId = userId;
    }

    public UserNotExistException(String message) {
        super(message, UserErrorCode.USER_NOT_EXITS);
    }

    public String getUserId() {
        return userId;
    }
}
