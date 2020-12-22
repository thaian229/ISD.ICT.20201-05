package controller;

import model.dock.Dock;
import model.dock.DockManager;

import java.util.ArrayList;

/**
 * controller for returning dock selection
 *
 * @author mHoang99
 * <p>
 * creted at: 03/12/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 - K62
 */

public class ReturningDockSelectionController extends BaseController{

    public ArrayList<Dock> getDockList() {
        return DockManager.getInstance().getDockList();
    }

    public ArrayList<Dock> getDockListByKeyword(String keyword) {
        return DockManager.getInstance().searchDockByKeyword(keyword);
    }
}
