package vn.longvan.tranning.spring.user.exception;

public enum UserErrorCode {
    USER_EXCEPTION(510),
    USER_ALREADY_EXITS(511),
    USER_NOT_EXITS(512)
    ;

    UserErrorCode(int code) {
        this.code = code;
    }

    public final int code;
}
