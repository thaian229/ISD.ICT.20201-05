package common.exception.cardException;

import common.exception.FormException;

public class NullExpDateException extends FormException {
    public NullExpDateException() {
        super("ERROR: Expiry date is not filled!");
    }
}
