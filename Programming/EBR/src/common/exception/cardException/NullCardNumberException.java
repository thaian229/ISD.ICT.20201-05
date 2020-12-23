package common.exception.cardException;

import common.exception.FormException;

public class NullCardNumberException extends FormException {
    public NullCardNumberException() {
        super("Card number is not filled!");
    }
}
