package controller;

import common.exception.BarcodeNotFoundException;
import common.exception.InvalidBarcodeFormatException;
import model.bike.Bike;
import model.bike.BikeManager;

public class BarcodePopupController extends BaseController {
    public int validateBarcodeInput(String barcode) throws InvalidBarcodeFormatException {
        try {
            return Integer.parseInt(barcode);
        } catch (NumberFormatException e) {
            throw new InvalidBarcodeFormatException();
        }
    }

    public Bike getBikeByBarcode(int barcode) throws BarcodeNotFoundException {
        Bike bike = BikeManager.getInstance().getBikeByBarcode(barcode);
        if (bike == null) {
            throw new BarcodeNotFoundException();
        }
        return bike;
    }
}
