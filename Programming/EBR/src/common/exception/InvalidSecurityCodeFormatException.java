package common.exception;

public class InvalidSecurityCodeFormatException extends FormException {
    public InvalidSecurityCodeFormatException() {
        super("INVALID: Security code must contains only 3 digits!");
    }
}
