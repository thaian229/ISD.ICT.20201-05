package common.exception;

import common.exception.cardException.FormException;

public class NullBarcodeException extends FormException {
    public NullBarcodeException() {
        super("ERROR: Barcode is not filled!");
    }
}
