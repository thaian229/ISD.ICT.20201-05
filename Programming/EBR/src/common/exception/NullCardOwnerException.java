package common.exception;

public class NullCardOwnerException extends FormException {
    public NullCardOwnerException() {
        super("ERROR: Card owner is not filled!");
    }
}
