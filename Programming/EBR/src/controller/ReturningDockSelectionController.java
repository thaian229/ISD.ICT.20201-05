package controller;

import model.dock.Dock;
import model.dock.DockManager;

import java.util.ArrayList;

/**
 * controller for returning dock selection
 *
 * @author khang
 * <p>
 * creted at: 20/12/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 - K62
 */

public class ReturningDockSelectionController extends BaseController{
    /**
     * This method is used to get dock list
     * @return list of dock
     */
    public ArrayList<Dock> getDockList() {
        return DockManager.getInstance().getDockList();
    }

    /**
     * This method is used to get dock list by using keyword
     * @param keyword - keyword
     * @return list of dock corresponding to keyword
     */
    public ArrayList<Dock> getDockListByKeyword(String keyword) {
        return DockManager.getInstance().searchDockByKeyword(keyword);
    }
}
