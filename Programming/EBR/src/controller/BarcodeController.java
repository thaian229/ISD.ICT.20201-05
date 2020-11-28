package controller;

import model.bike.Bike;

import java.io.IOException;
import java.util.HashMap;

public class BarcodeController extends BaseController{

//    public Bike bikeSearch(int barcode){
//        DockList.getDock().searchBikeByBarcode(barcode);
//    }

    public void validateBarcodeForm(HashMap<String, String> BarcodeForm){

    }
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
