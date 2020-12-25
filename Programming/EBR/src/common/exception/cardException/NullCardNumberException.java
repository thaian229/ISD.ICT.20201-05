package common.exception.cardException;

public class NullCardNumberException extends FormException {
    public NullCardNumberException() {
        super("CARD NUMBER IS NOT FILLED!");
    }
}
