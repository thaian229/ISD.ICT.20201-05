package controller;

import controller.BaseController;
import model.dock.Dock;
import model.dock.DockManager;

import java.util.ArrayList;

/**
 * class for controller of the home screen
 *
 * @author mHoang99
 * <p>
 * created_at: 20/12/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 K62
 * <p>
 * helpers: teacher's teaching assistants
 */
public class HomeScreenController extends BaseController {

    /**
     * Constructor
     */
    public HomeScreenController() {
    }

    /**
     * This method is used to get list of docks for the home screen to display
     * @return list of docks
     */
    public ArrayList<Dock> getDockList() {
        return DockManager.getInstance().getDockList();
    }

    /**
     * This method is used to get list of docks for the home screen to display by using keyword
     * @param text keyword
     * @return list of docks corresponding to keyword
     */
    public ArrayList<Dock> getDockListByKeyword(String text) {
        return DockManager.getInstance().searchDockByKeyword(text);
    }
}
