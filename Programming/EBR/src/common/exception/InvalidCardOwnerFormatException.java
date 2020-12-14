package common.exception;

public class InvalidCardOwnerFormatException extends FormException {
    public InvalidCardOwnerFormatException() {
        super("INVALID: Card owner must contains only letters, digits and space!");
    }
}
