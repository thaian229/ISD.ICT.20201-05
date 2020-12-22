package common.exception.cardException;

import common.exception.PaymentException;

;

public class InvalidCardException extends PaymentException {
	public InvalidCardException() {
		super("ERROR: Invalid card!");
	}
}
