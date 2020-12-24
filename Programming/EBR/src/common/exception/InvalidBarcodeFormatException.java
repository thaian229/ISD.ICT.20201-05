package common.exception;

public class InvalidBarcodeFormatException extends FormException{
    public InvalidBarcodeFormatException() {
        super("Barcode must contains only number");
    }
}
