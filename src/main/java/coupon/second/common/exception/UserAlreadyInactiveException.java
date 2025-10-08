package coupon.second.common.exception;

public class UserAlreadyInactiveException extends RuntimeException {
    public UserAlreadyInactiveException(String email) {
        super(email + " is already inactive");
    }
}
