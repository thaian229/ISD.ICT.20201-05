package common.exception.cardException;

import common.exception.FormException;

public class InvalidExpDateFormatException extends FormException {
    public InvalidExpDateFormatException() {
        super("Expiry date must contains only 4 digits!");
    }
}
