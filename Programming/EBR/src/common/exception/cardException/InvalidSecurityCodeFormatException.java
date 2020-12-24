package common.exception.cardException;

import common.exception.FormException;

public class InvalidSecurityCodeFormatException extends FormException {
    public InvalidSecurityCodeFormatException() {
        super("Security code must contains only 3 digits!");
    }
}
