package model.bike;

import model.dock.Dock;

public class Bike {

    protected String id;
    protected int barcode;
    protected int saddle;
    protected int rearSeat;
    protected int pairOfPedals;
    protected Dock dock;
    protected String dockId;
    protected int deposit;
    protected int charge;

    public Bike() { }

    public Bike(int barcode){
        this.barcode = barcode;
        this.saddle = 1;
        this.rearSeat = 1;
        this.pairOfPedals = 1;
        this.deposit = 0;
        this.charge = 0;
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDockId() {
        return dockId;
    }

    public void setDockId(String dockId) {
        this.dockId = dockId;
    }

    public int getDeposit() { return deposit; }

    public void setDeposit(int deposit) { this.deposit = deposit; }

    public int getCharge() { return charge; }

    public void setCharge(int charge) { this.charge = charge; }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Bike) {
            Bike bike = (Bike) obj;
            return bike.getId().equals(this.id);
        }
        return false;
    }

}
