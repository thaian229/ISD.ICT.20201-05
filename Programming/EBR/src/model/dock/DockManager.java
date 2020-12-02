package model.dock;

import model.bike.Bike;

import java.util.ArrayList;

public class DockManager {

    private static DockManager instance;   // singleton
    private ArrayList<Dock> dockList;

    public DockManager() {
        this.dockList = new ArrayList<>();
    }

    public DockManager getInstance() {
        if (instance == null) {
            instance = new DockManager();
        }

        return instance;
    }

    public ArrayList<Dock> getDockList() {
        return dockList;
    }

    public void setDockList(ArrayList<Dock> dockList) {
        this.dockList = dockList;
    }

    public void addDock(Dock dock) {
        dockList.add(dock);
    }

    public void removeDock(Dock dock) {
        dockList.remove(dock);
    }

    public Bike searchBikeByBarcode(int barcode) {
        for (Dock dock : dockList) {
            ArrayList<Bike> bikeList = dock.getBikeList();
            for (Bike bike : bikeList) {
                if (bike.getBarcode() == barcode) return bike;
            }
        }
        return null;
    }

}
