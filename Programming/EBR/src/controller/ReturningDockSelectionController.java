package controller;

import model.dock.Dock;
import model.dock.DockManager;

import java.util.ArrayList;

public class ReturningDockSelectionController extends BaseController{

    public ArrayList<Dock> getDockList() {
        return DockManager.getInstance().getDockList();
    }

    public ArrayList<Dock> getDockListByKeyword(String keyword) {
        return DockManager.getInstance().searchDockByKeyword(keyword);
    }
}
