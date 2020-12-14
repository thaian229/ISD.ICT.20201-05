package common.exception;

public class NullCardNumberException extends FormException {
    public NullCardNumberException() {
        super("ERROR: Card number is not filled!");
    }
}
