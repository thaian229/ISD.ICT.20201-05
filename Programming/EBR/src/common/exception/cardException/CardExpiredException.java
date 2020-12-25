package common.exception.cardException;

public class CardExpiredException extends FormException {
    public CardExpiredException() {
        super("Card is expired!");
    }
}
