package vn.longvan.tranning.spring.user.exception;

import vn.longvan.tranning.spring.user.model.User;

public class UserAlreadyExistException extends UserException {
    User user;

    public UserAlreadyExistException(String message, User user) {
        super(message, UserErrorCode.USER_ALREADY_EXITS);
        this.user = user;
    }

    public UserAlreadyExistException(String message) {
        super(message, UserErrorCode.USER_ALREADY_EXITS);
    }


}
