package model.dock;

import model.db.EBRDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DockManager {

    private static DockManager instance;   // singleton
    private final ArrayList<Dock> dockList;

    public DockManager() {
        this.dockList = new ArrayList<>();
        refreshDockList();
    }

    public static DockManager getInstance() {
        if (instance == null) {
            instance = new DockManager();
        }

        return instance;
    }

    public void refreshDockList() {
        this.dockList.clear();
        System.out.println("refreshing");
        // query for all Docks
        String SQL = "SELECT * FROM dock";

        try (Connection conn = EBRDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                Dock dock = new Dock(rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getInt("capacity"),
                        rs.getString("image_url"));
                dockList.add(dock);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Dock> getDockList() {
        return dockList;
    }

    public Dock getDockById(String id) {
        for (Dock dock : dockList) {
            if (dock.getId().equals(id))
                return dock;
        }
        return null;
    }

    public ArrayList<Dock> searchDockByName(String nameOrAddress) {
        ArrayList<Dock> searchedDock = new ArrayList<>();
        for (Dock dock : dockList) {
            if (dock.getName().trim().toUpperCase().contains(nameOrAddress.trim().toUpperCase()) ||
                    dock.getLocation().trim().toUpperCase().contains(nameOrAddress.trim().toUpperCase()))
                searchedDock.add(dock);
        }
        return searchedDock;
    }

}
