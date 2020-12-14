package common.exception;

public class NullBarcodeException extends FormException{
    public NullBarcodeException() {
        super("ERROR: Barcode is not filled!");
    }
}
