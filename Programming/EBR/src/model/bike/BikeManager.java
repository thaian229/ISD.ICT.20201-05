package model.bike;


public class BikeManager {

    private static BikeManager instance;

    public static BikeManager getInstance() {
        if (instance == null) {
            instance = new BikeManager();
        }
        return instance;
    }

    public Bike getBikeById(String bike_id) {
        return null;
    }
}
