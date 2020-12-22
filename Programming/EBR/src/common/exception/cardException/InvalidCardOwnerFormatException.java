package common.exception.cardException;

import common.exception.FormException;

public class InvalidCardOwnerFormatException extends FormException {
    public InvalidCardOwnerFormatException() {
        super("INVALID: Card owner must contains only letters, digits and space!");
    }
}
