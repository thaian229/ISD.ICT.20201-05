package model.bike;

import model.db.EBRDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Model to manage all bikes and handle database connections
 * that related to bikes' info
 *
 * @author Bui Tu Hoang
 * <p>
 * creted at: 24/11/2020
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


    public BikeManager() {
        this.bikeList = new ArrayList<>();
        refreshBikeList();
    }

    /**
     * Get the instance of BikeManager
     *
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
     *
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

    private StandardBike createStandardBike(ResultSet resultSet) throws SQLException {
        StandardBike bike = new StandardBike(resultSet.getString("id"),
                resultSet.getInt("barcode"),
                resultSet.getInt("value"),
                resultSet.getInt("rental_fees"));
        bike.setPairOfPedals(resultSet.getInt("pedal_num"));
        bike.setSaddle(resultSet.getInt("saddle_num"));
        bike.setRearSeat(resultSet.getInt("rear_seat_num"));
        bike.setImageURL(resultSet.getString("image_url"));

        return bike;
    }

    private TwinBike createTwinBike(ResultSet resultSet) throws SQLException {
        TwinBike bike = new TwinBike(resultSet.getString("id"),
                resultSet.getInt("barcode"),
                resultSet.getInt("value"),
                resultSet.getInt("rental_fees"));
        bike.setPairOfPedals(resultSet.getInt("pedal_num"));
        bike.setSaddle(resultSet.getInt("saddle_num"));
        bike.setRearSeat(resultSet.getInt("rear_seat_num"));
        bike.setImageURL(resultSet.getString("image_url"));

        return bike;
    }

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
        bike.setTimeLeft(resultSet.getInt("time_remain"));
        return bike;
    }

    private TwinElectricalBike createTwinElectricalBike(ResultSet resultSet) throws SQLException {
        TwinElectricalBike bike = new TwinElectricalBike(resultSet.getString("id"),
                resultSet.getInt("barcode"),
                resultSet.getInt("value"),
                resultSet.getInt("rental_fees"));
        bike.setPairOfPedals(resultSet.getInt("pedal_num"));
        bike.setSaddle(resultSet.getInt("saddle_num"));
        bike.setRearSeat(resultSet.getInt("rear_seat_num"));
        bike.setImageURL(resultSet.getString("image_url"));
        bike.setBattery(resultSet.getFloat("battery"));
        bike.setTimeLeft(resultSet.getInt("time_remain"));
        return bike;
    }



    /**
     * Get bike by bike's id
     *
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
     *
     * @param barcode Bike's barcode
     * @return instance of that bike, null if not found
     */
    public Bike getBikeByBarcode(int barcode) {
        for (Bike bike : bikeList) {
            if (bike.getBarcode() == barcode) return bike;
        }
        return null;
    }
}
