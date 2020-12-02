package model.bike;

import model.dock.Dock;

import java.util.Objects;

public class Bike {
    protected String id;
    protected int barcode;
    protected int saddle;
    protected int pairOfPedals;
    protected int rearSeat;
    protected Dock dock;
    protected String dockId;
    protected int value;
    protected int deposit;
    protected int charge;
    protected String imageURL;

    public Bike() { }

    public Bike(int barcode, int value, int charge){
        this.barcode = barcode;
        this.saddle = 1;
        this.rearSeat = 1;
        this.pairOfPedals = 1;
        this.value = value;
        this.deposit = value/10;
        this.charge = charge;
    }

    public Bike(String id, int barcode, int value, int charge){
        this.id = id;
        this.barcode = barcode;
        this.saddle = 1;
        this.rearSeat = 1;
        this.pairOfPedals = 1;
        this.value = value;
        this.deposit = value/10;
        this.charge = charge;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public int getBarcode() { return barcode; }

    public void setBarcode(int barcode) { this.barcode = barcode; }

    public int getSaddle() { return saddle; }

    public void setSaddle(int saddle) { this.saddle = saddle; }

    public int getRearSeat() { return rearSeat; }

    public void setRearSeat(int rearSeat) { this.rearSeat = rearSeat; }

    public int getPairOfPedals() { return pairOfPedals; }

    public void setPairOfPedals(int pairOfPedals) { this.pairOfPedals = pairOfPedals; }

    public Dock getDock() { return dock; }

    public void setDock(Dock dock) { this.dock = dock; }

    public String getDockId() { return dockId; }

    public void setDockId(String dockId) { this.dockId = dockId; }

    public int getValue() { return value; }

    public void setValue(int value) { this.value = value; }

    public int getDeposit() { return deposit; }

    public void setDeposit(int deposit) { this.deposit = deposit; }

    public int getCharge() { return charge; }

    public void setCharge(int charge) { this.charge = charge; }

    public String getImageURL() { return imageURL; }

    public void setImageURL(String imageURL) { this.imageURL = imageURL; }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Bike) {
            Bike bike = (Bike) obj;
            return bike.getId().equals(this.id);
        }
        return false;
    }

}
