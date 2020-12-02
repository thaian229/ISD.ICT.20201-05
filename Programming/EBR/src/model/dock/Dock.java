package model.dock;

import model.bike.Bike;

import java.util.ArrayList;

public class Dock {

    private String name;
    private String location;
    private int capacity;
    private int numberOfAvailableBike;
    private String id;
    private String imageURL;

    private final ArrayList<Bike> bikeList;

    public Dock(String id, String name, String location, int capacity, String imageURL) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.imageURL = imageURL;
        this.bikeList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getNumberOfAvailableBike() {
        return numberOfAvailableBike;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Bike> getBikeList() {
        return bikeList;
    }

    public void addBike(Bike bike) {
        this.bikeList.add(bike);
        this.numberOfAvailableBike = bikeList.size();
    }

    public void removeBike(Bike bike) {
        this.bikeList.remove(bike);
        this.numberOfAvailableBike = bikeList.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Dock) {
            Dock dock = (Dock) obj;
            return dock.getId().equals(this.id);
        }
        return false;
    }

}
