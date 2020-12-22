package common.exception.cardException;

import common.exception.FormException;

public class NullSecurityCodeException extends FormException {
    public NullSecurityCodeException() {
        super("ERROR: Security code is not found!");
    }
}


