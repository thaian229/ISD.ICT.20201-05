package common.exception;

public class InvalidCardNumberFormatException extends FormException {
    public InvalidCardNumberFormatException(String message) {
        super("INVALID: Card number must contains only letters, digits and underscores!");
    }
}
