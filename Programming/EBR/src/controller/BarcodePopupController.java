package controller;

import common.exception.BarcodeNotFoundException;
import common.exception.InvalidBarcodeFormatException;
import model.bike.Bike;
import model.bike.BikeManager;

/**
 * Controller for barcode popup screen
 *
 * @author Bui Tu Hoang
 * <p>
 * creted at: 20/12/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 - K62
 * <p>
 * helpers: teacher's teaching assistants
 */
public class BarcodePopupController extends BaseController {


    /**
     * This method is used to validate barcode input
     * @return true if barcode is valid
     * @throws InvalidBarcodeFormatException wrong barcode format
     */

    public int validateBarcodeInput(String barcode) throws InvalidBarcodeFormatException {
        try {
            return Integer.parseInt(barcode);
        } catch (NumberFormatException e) {
            throw new InvalidBarcodeFormatException();
        }
    }

    /**
     * This method is used to get bike from barcode
     * @param barcode barcode
     * @return bike corresponding to barcode
     * @throws BarcodeNotFoundException barcode not found error
     */

    public Bike getBikeByBarcode(int barcode) throws BarcodeNotFoundException {
        Bike bike = BikeManager.getInstance().getBikeByBarcode(barcode);
        if (bike == null) {
            throw new BarcodeNotFoundException();
        }
        return bike;
    }
}
