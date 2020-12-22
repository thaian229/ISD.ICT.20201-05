package common.exception.cardException;

import common.exception.FormException;

public class NullCardOwnerException extends FormException {
    public NullCardOwnerException() {
        super("ERROR: Card owner is not filled!");
    }
}
