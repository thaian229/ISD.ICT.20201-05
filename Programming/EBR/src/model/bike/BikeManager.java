package model.bike;

import model.db.EBRDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BikeManager {
    private static BikeManager instance;
    private ArrayList<Bike> bikeList;

    public BikeManager() {
        this.bikeList = new ArrayList<>();
        refreshBikeList();
    }

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

    public void addBike(Bike bike) {
        bikeList.add(bike);
    }

    public void removeBike(Bike bike) {
        bikeList.remove(bike);
    }

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

    public Bike getBikeById(String id){
        for(Bike bike : bikeList){
            if(bike.getId().equals(id)) return bike;
        }
        return null;
    }

    public Bike getBikeByBarcode(int barcode){
        for(Bike bike : bikeList){
            if(bike.getBarcode() == barcode) return bike;
        }
        return null;
    }
}
