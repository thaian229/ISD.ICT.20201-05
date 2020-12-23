package common.exception;

public class BarcodeNotFoundException extends UtilityException {
    public BarcodeNotFoundException() {
        super("Bike with this barcode is not exist!");
    }
}
