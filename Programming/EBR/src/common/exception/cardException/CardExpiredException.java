package common.exception.cardException;

import common.exception.FormException;

public class CardExpiredException extends FormException {
    public CardExpiredException() {
        super("Card is expired!");
    }
}
