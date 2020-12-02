package model.dock;

import model.bike.Bike;

import java.util.ArrayList;

public class Dock {

    private String name;
    private String location;
    private int capacity;
    private int numberOfAvailableBike;
    private String id;
    private String imageUrl;

    private ArrayList<Bike> bikeList;

    public Dock() {
        this.name = "unknown";
        this.location = "unknown";
        this.capacity = 200;
        this.numberOfAvailableBike = 0;
        this.bikeList = new ArrayList<>();
    }

    public Dock(String name, String location, int capacity, ArrayList<Bike> bikeList) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.bikeList = bikeList;
        this.numberOfAvailableBike = bikeList.size();
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

    public ArrayList<Bike> getBikeList() {
        return bikeList;
    }

    public void setBikeList(ArrayList<Bike> bikeList) {
        this.bikeList = bikeList;
        this.numberOfAvailableBike = bikeList.size();
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
            return dock.getName().equals(this.name) && dock.getLocation().equals(this.location);
        }
        return false;
    }

}
