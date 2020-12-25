package controller;

import model.bike.Bike;
import model.bike.BikeManager;

import java.util.ArrayList;
/**
 * Controller for dock screen
 *
 * @author Nguyen Thai An
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
public class DockScreenController extends BaseController {
    /**
     * Constructor
     */
    public DockScreenController() {
    }

//    public ArrayList<Bike> getBikeList() {
//        return BikeManager.getInstance().getBikeList();
//    }
    /**
     * This method is used to get bike list in a dock
     * @param id dock id
     * @return bike list in dock
     */
    public ArrayList<Bike> getBikeListOfDock(String id) {
        return BikeManager.getInstance().getBikeListInDock(id);
    }
}
