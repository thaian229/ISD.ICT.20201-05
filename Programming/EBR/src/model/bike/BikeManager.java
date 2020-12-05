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

    public BikeManager() {
        this.bikeList = new ArrayList<>();
        refreshBikeList();
    }

    /**
     * Get the instance of BikeManager
     * @return BikeManager instance
     */
    public static BikeManager getInstance() {
        if(instance == null) {
            instance = new BikeManager();
        }
        return instance;
    }

    public ArrayList<Bike> getBikeList() { return bikeList; }

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
     * @param bike Instance of bike to be taken out
     */
    public void removeBike(Bike bike) {
        bikeList.remove(bike);
    }

    /**
     * Updating local bike list with the current info in database
     */
    public void refreshBikeList() {
        String sql = "SELECT * FROM bike";
        try (Connection connection = EBRDB.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Bike bike = new Bike(resultSet.getString("id"),
                        resultSet.getInt("barcode"),
                        resultSet.getInt("value"),
                        resultSet.getInt("rental_fees"));
                bikeList.add(bike);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Get bike by bike's id
     * @param id Bike's id
     * @return instance of that bike, null if not found
     */
    public Bike getBikeById(String id){
        for(Bike bike : bikeList){
            if(bike.getId().equals(id)) return bike;
        }
        return null;
    }

    /**
     * Get bike by bike's barcode
     * @param barcode Bike's barcode
     * @return instance of that bike, null if not found
     */
    public Bike getBikeByBarcode(int barcode){
        for(Bike bike : bikeList){
            if(bike.getBarcode() == barcode) return bike;
        }
        return null;
    }
}
