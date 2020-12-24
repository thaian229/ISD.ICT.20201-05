package common.exception.cardException;

import common.exception.FormException;

public class NullCardOwnerException extends FormException {
    public NullCardOwnerException() {
        super("Card owner is not filled!");
    }
}
