package controller;

import model.bike.Bike;

import java.io.IOException;
import java.util.HashMap;

/**
 * Controller for barcode input
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
public class BarcodeController extends BaseController{

//    public Bike bikeSearch(int barcode){
//        DockList.getDock().searchBikeByBarcode(barcode);
//    }

    /**
     * This method is used to validate barcode input
     * @return true if barcode is valid
     */
    public boolean validateBarcodeInput(String barcode) {
        // check the barcode input is not null
        if(barcode == null) return  false;

        // Check if barcode input contains only number
        for(int i=0; i<barcode.length(); i++){
            if(Character.isDigit(barcode.charAt(i)) == false) return false;
        }

        return true;
    }

}
