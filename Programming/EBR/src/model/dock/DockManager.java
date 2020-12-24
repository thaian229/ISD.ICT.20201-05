package model.dock;

import model.bike.Bike;
import model.db.EBRDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Model to manage all docks and handle database connections
 * that related to docks' info
 *
 * @author Nguyen Thai An
 * <p>
 * creted at: 24/11/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 - K62
 */
public class DockManager {

    private static DockManager instance;   // singleton
    private final ArrayList<Dock> dockList;

    public DockManager() {
        this.dockList = new ArrayList<>();
        refreshDockList();
    }

    /**
     * get the instance of DockManager
     * @return DockManager instance
     */
    public static DockManager getInstance() {
        if (instance == null) {
            instance = new DockManager();
        }

        return instance;
    }

    /**
     * Updating local dock list with the current info in database
     */
    public void refreshDockList() {
        this.dockList.clear();
        System.out.println("refreshing");
        // query for all Docks
        String SQL = "SELECT * FROM dock ORDER BY dock.name";

        try (Connection conn = EBRDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                Dock dock = new Dock(rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getInt("capacity"),
                        rs.getString("image_url"));
                dock.setNumberOfAvailableBike(rs.getInt("taken_slot"));
                dockList.add(dock);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Dock> getDockList() {
        return dockList;
    }

    /**
     * get dock instance by dock's id
     * @param id dock's id
     * @return instance of that Dock, null if id not found
     */
    public Dock getDockById(String id) {
        for (Dock dock : dockList) {
            if (dock.getId().equals(id))
                return dock;
        }
        return null;
    }

    /**
     * Search dock by its name or address
     * @param nameOrAddress dock's name or dock's address
     * @return searched dock
     */
    public ArrayList<Dock> searchDockByKeyword(String nameOrAddress) {
        ArrayList<Dock> searchedDock = new ArrayList<>();
        for (Dock dock : dockList) {
            if (dock.getName().trim().toUpperCase().contains(nameOrAddress.trim().toUpperCase()) ||
                    dock.getLocation().trim().toUpperCase().contains(nameOrAddress.trim().toUpperCase()))
                searchedDock.add(dock);
        }
        return searchedDock;
    }

}
