package common.exception.cardException;

public class NullCardOwnerException extends FormException {
    public NullCardOwnerException() {
        super("CARD OWNER IS NOT FILLED");
    }
}
