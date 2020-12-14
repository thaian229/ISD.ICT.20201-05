package common.exception;

public class InvalidBarcodeFormatException extends FormException{
    public InvalidBarcodeFormatException() {
        super("INVALID: Barcode must contains only number");
    }
}
