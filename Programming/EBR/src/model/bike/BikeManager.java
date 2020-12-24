package model.bike;

import model.db.EBRDB;
import model.dock.DockManager;

import java.sql.*;
import java.util.ArrayList;

/**
 * Model to manage all bikes and handle database connections
 * that related to bikes' info
 *
 * @author Bui Tu Hoang, mHoang99
 * <p>
 * creted at: 20/12/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 - K62
 */
public class BikeManager {
    private static BikeManager instance;
    private ArrayList<Bike> bikeList;
    public static final int STANDARD_BIKE_CODE = 1;
    public static final int TWIN_BIKE_CODE = 2;
    public static final int STANDARD_ELECTRICAL_BIKE_CODE = 3;
    public static final int TWIN_ELECTRICAL_BIKE_CODE = 4;

    /**
     * Constructor
     */

    public BikeManager() {
        this.bikeList = new ArrayList<>();
        refreshBikeList();
    }

    /**
     * Get the instance of BikeManager
     * @return BikeManager instance
     */
    public static BikeManager getInstance() {
        if (instance == null) {
            instance = new BikeManager();
        }
        return instance;
    }

    public ArrayList<Bike> getBikeList() {
        return bikeList;
    }

    public void setBikeList(ArrayList<Bike> bikeList) {
        this.bikeList = bikeList;
    }

    /**
     * Add a bike to bike list
     * @param bike Instance of bike to be put in
     */
    public void addBike(Bike bike) {
        bikeList.add(bike);
    }

    /**
     * Remove a bike from bike list
     *
     * @param bike Instance of bike to be taken out
     */
    public void removeBike(Bike bike) {
        bikeList.remove(bike);
    }

    /**
     * Updating local bike list with the current info in database
     */
    public void refreshBikeList() {
        String sql = "SELECT * FROM bike\n" +
                "LEFT JOIN e_bike on bike.id = e_bike.bike_id";
        try (Connection connection = EBRDB.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Bike bike = null;
                switch (resultSet.getInt("type")) {
                    case (STANDARD_BIKE_CODE):
                        bike = createStandardBike(resultSet);
                        break;
                    case (TWIN_BIKE_CODE):
                        bike = createTwinBike(resultSet);
                        break;
                    case (STANDARD_ELECTRICAL_BIKE_CODE):
                        bike = createStandardElectricalBike(resultSet);
                        break;
                    case (TWIN_ELECTRICAL_BIKE_CODE):
                        bike = createTwinElectricalBike(resultSet);
                        break;
                    default:
                        break;
                }
                if (bike != null) {
                    bikeList.add(bike);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Get bike list in chosen dock
     * @param dockId id of chosen dock
     * @return list of bikes in dock
     */
    public ArrayList<Bike> getBikeListInDock(String dockId) {
        String sql = "SELECT * FROM bike\n" +
                "LEFT JOIN e_bike on bike.id = e_bike.bike_id\n" +
                "WHERE dock_id = ?::uuid";
        ArrayList<Bike> bikeListOfDock = new ArrayList<>();

        try (Connection conn = EBRDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dockId);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Bike bike = null;
                switch (resultSet.getInt("type")) {
                    case (STANDARD_BIKE_CODE):
                        bike = createStandardBike(resultSet);
                        break;
                    case (TWIN_BIKE_CODE):
                        bike = createTwinBike(resultSet);
                        break;
                    case (STANDARD_ELECTRICAL_BIKE_CODE):
                        bike = createStandardElectricalBike(resultSet);
                        break;
                    case (TWIN_ELECTRICAL_BIKE_CODE):
                        bike = createTwinElectricalBike(resultSet);
                        break;
                    default:
                        break;
                }
                if (bike != null) {
                    bikeListOfDock.add(bike);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return bikeListOfDock;
    }

    /**
     * Create standard bike
     * @param resultSet result
     * @return standard bike
     * @throws SQLException SQL error
     */
    private StandardBike createStandardBike(ResultSet resultSet) throws SQLException {
        StandardBike bike = new StandardBike(
                resultSet.getString("id"),
                resultSet.getInt("barcode"),
                resultSet.getInt("value"),
                resultSet.getInt("rental_fees")
        );
        bike.setPairOfPedals(resultSet.getInt("pedal_num"));
        bike.setSaddle(resultSet.getInt("saddle_num"));
        bike.setDockId(resultSet.getString("dock_id"));

        bike.setRearSeat(resultSet.getInt("rear_seat_num"));
        bike.setImageURL(resultSet.getString("image_url"));

        return bike;
    }
    /**
     * Create twin bike
     * @param resultSet result
     * @return twin bike
     * @throws SQLException SQL error
     */
    private TwinBike createTwinBike(ResultSet resultSet) throws SQLException {
        TwinBike bike = new TwinBike(resultSet.getString("id"),
                resultSet.getInt("barcode"),
                resultSet.getInt("value"),
                resultSet.getInt("rental_fees"));
        bike.setPairOfPedals(resultSet.getInt("pedal_num"));
        bike.setSaddle(resultSet.getInt("saddle_num"));
        bike.setRearSeat(resultSet.getInt("rear_seat_num"));
        bike.setDockId(resultSet.getString("dock_id"));

        bike.setImageURL(resultSet.getString("image_url"));

        return bike;
    }
    /**
     * Create standard electrical bike
     * @param resultSet result
     * @return standard electrical bike
     * @throws SQLException SQL error
     */
    private StandardElectricalBike createStandardElectricalBike(ResultSet resultSet) throws SQLException {
        StandardElectricalBike bike = new StandardElectricalBike(resultSet.getString("id"),
                resultSet.getInt("barcode"),
                resultSet.getInt("value"),
                resultSet.getInt("rental_fees"));
        bike.setPairOfPedals(resultSet.getInt("pedal_num"));
        bike.setSaddle(resultSet.getInt("saddle_num"));
        bike.setRearSeat(resultSet.getInt("rear_seat_num"));
        bike.setImageURL(resultSet.getString("image_url"));
        bike.setBattery(resultSet.getFloat("battery"));
        bike.setDockId(resultSet.getString("dock_id"));
        bike.setTimeLeft(resultSet.getInt("time_remain"));
        return bike;
    }
    /**
     * Create twin electrical bike
     * @param resultSet result
     * @return twin electrical bike
     * @throws SQLException SQL error
     */
    private TwinElectricalBike createTwinElectricalBike(ResultSet resultSet) throws SQLException {
        TwinElectricalBike bike = new TwinElectricalBike(resultSet.getString("id"),
                resultSet.getInt("barcode"),
                resultSet.getInt("value"),
                resultSet.getInt("rental_fees"));
        bike.setPairOfPedals(resultSet.getInt("pedal_num"));
        bike.setSaddle(resultSet.getInt("saddle_num"));
        bike.setRearSeat(resultSet.getInt("rear_seat_num"));
        bike.setImageURL(resultSet.getString("image_url"));
        bike.setDockId(resultSet.getString("dock_id"));
        bike.setBattery(resultSet.getFloat("battery"));
        bike.setTimeLeft(resultSet.getInt("time_remain"));
        return bike;
    }


    /**
     * Get bike by bike's id
     * @param id Bike's id
     * @return instance of that bike, null if not found
     */
    public Bike getBikeById(String id) {
        for (Bike bike : bikeList) {
            if (bike.getId().equals(id)) return bike;
        }
        return null;
    }

    /**
     * Get bike by bike's barcode
     * @param barcode Bike's barcode
     * @return instance of that bike, null if not found
     */
    public Bike getBikeByBarcode(int barcode) {
        for (Bike bike : bikeList) {
            if (bike.getBarcode() == barcode) return bike;
        }
        return null;
    }

    /**
     * Update dock of bike after renting or returning this bike
     * @param bike bike in use
     * @param dockId dock's id
     */
    public void updateDockOfBike(Bike bike, String dockId) {
        String SQL = "UPDATE bike "
                + "SET dock_id = ?::uuid "
                + "WHERE id = ?::uuid";
        if (dockId == null || dockId.equals("")) {
            SQL = "UPDATE bike "
                    + "SET dock_id = NULL "
                    + "WHERE id = ?::uuid";
        }

        // Update
        try (Connection conn = EBRDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {
            // Set up parameters
            if (dockId == null || dockId.equals("")) {
                pstmt.setString(1, bike.getId());
            } else {
                pstmt.setString(1, dockId);
                pstmt.setString(2, bike.getId());
            }
            // Handle update
            int affectedRows = pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (dockId != null && !dockId.equals("")) {
            DockManager.getInstance().getDockById(dockId).getBikeList().add(bike);
        }
    }
}