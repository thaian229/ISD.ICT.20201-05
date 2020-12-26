package model.dock;

import model.bike.Bike;

import java.util.ArrayList;

/**
 * Model for dock
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
public class Dock {

    private String name;
    private String location;
    private int capacity;
    private int numberOfAvailableBike;
    private String id;
    private String imageURL;

    private final ArrayList<Bike> bikeList;

    /**
     * most use constructor
     * @param id dock's uuid
     * @param name dock's name
     * @param location dock's address
     * @param capacity total bike slots
     * @param imageURL URL to dock's image
     */
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
        return this.numberOfAvailableBike;
    }

    public void setNumberOfAvailableBike(int numberOfAvailableBike) {
        this.numberOfAvailableBike = numberOfAvailableBike;
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

    /**
     * add a bike to dock
     * @param bike instance of bike to be put in
     */
    public void addBike(Bike bike) {
        this.bikeList.add(bike);
        bike.setDock(this);
        this.numberOfAvailableBike = bikeList.size();
    }

    /**
     * remove a bike from dock
     * @param bike instance of bike to be taken out
     */
    public void removeBike(Bike bike) {
        this.bikeList.remove(bike);
        bike.setDock(null);
        this.numberOfAvailableBike = bikeList.size();
    }

    /**
     * Clear all current bike list
     */
    public void clearBikeList() {
        this.bikeList.clear();
    }

    /**
     * Add a collection of bikes into current bike list
     * @param list {@link ArrayList} bikes to be added
     */
    public void addListOfBikes(ArrayList<Bike> list) {
        this.bikeList.addAll(list);
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
