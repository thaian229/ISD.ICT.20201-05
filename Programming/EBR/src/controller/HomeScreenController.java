package controller;

import controller.BaseController;
import model.dock.Dock;
import model.dock.DockManager;

import java.util.ArrayList;

/**
 * class for controller of the home screen
 *
 * @author mHoang
 * <p>
 * created_at: 4/12/2020
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
    public HomeScreenController() {
    }

    /**
     * This method is used to get list of docks for the home screen to display
     * @author mHoang
     * @return list of dock
     */
    public ArrayList<Dock> getDockList() {
        return DockManager.getInstance().getDockList();
    }


    public ArrayList<Dock> getDockListByKeyword(String text) {
        return DockManager.getInstance().searchDockByKeyword(text);
    }
}
