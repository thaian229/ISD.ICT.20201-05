package common.exception;

public class NullExpDateException extends FormException{
    public NullExpDateException() {
        super("ERROR: Expiry date is not filled!");
    }
}
