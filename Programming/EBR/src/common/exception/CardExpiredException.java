package common.exception;

public class CardExpiredException extends FormException {
    public CardExpiredException() {
        super("INVALID: Card is expired!");
    }
}
