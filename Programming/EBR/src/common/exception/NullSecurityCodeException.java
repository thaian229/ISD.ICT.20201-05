package common.exception;

public class NullSecurityCodeException extends FormException {
    public NullSecurityCodeException() {
        super("ERROR: Security code is not found!");
    }
}
