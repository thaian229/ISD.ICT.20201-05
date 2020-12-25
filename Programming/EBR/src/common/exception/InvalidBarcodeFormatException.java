package common.exception;

import common.exception.cardException.FormException;

public class InvalidBarcodeFormatException extends FormException {
    public InvalidBarcodeFormatException() {
        super("Barcode must contains only number");
    }
}
