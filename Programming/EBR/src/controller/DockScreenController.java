package controller;

import model.bike.Bike;
import model.bike.BikeManager;

import java.util.ArrayList;
/**
 * Controller for dock screen
 *
 * @author mHoang99
 * <p>
 * creted at: 10/12/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 - K62
 */
public class DockScreenController extends BaseController {

    public DockScreenController() {
    }

    public ArrayList<Bike> getBikeList() {
        return BikeManager.getInstance().getBikeList();
    }

    public ArrayList<Bike> getBikeListOfDock(String id) {
        return BikeManager.getInstance().getBikeListInDock(id);
    }
}
