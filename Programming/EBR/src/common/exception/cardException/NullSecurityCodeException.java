package common.exception.cardException;

public class NullSecurityCodeException extends FormException {
    public NullSecurityCodeException() {
        super("SECURITY CODE IS NOT FILLED");
    }
}


