package controller.home;

import controller.BaseController;
import model.dock.Dock;
import model.dock.DockManager;

import java.util.ArrayList;

/**
 * class for ...
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

    public ArrayList<Dock> getDockList() {
        DockManager.getInstance().refreshDockList();
        return DockManager.getInstance().getDockList();
    }
}
