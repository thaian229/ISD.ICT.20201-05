package common.exception.cardException;

import common.exception.FormException;

public class InvalidCardNumberFormatException extends FormException {
    public InvalidCardNumberFormatException() {
        super("Card number must contains only letters, digits and underscores!");
    }
}
