package model.bike;

public class StandardElectricalBike extends Bike {
    private int battery;
    private String timeLeft;

    public StandardElectricalBike(int barcode){
        this.barcode = barcode;
        this.saddle = 1;
        this.rearSeat = 1;
        this.pairOfPedals = 1;
        this.deposit = 4000000;
        this.charge = 50000;
        this.battery = 100;
        this. timeLeft = "6 hours";
    }

    public StandardElectricalBike(int barcode, int deposit, int charge){
        this.barcode = barcode;
        this.saddle = 1;
        this.rearSeat = 1;
        this.pairOfPedals = 1;
        this.deposit = deposit;
        this.charge = charge;
        this.battery = 100;
        this. timeLeft = "6 hours";
    }

    public int getBattery() { return battery; }

    public void setBattery(int battery) { this.battery = battery; }

    public String getTimeLeft() { return timeLeft; }

    public void setTimeLeft(String timeLeft) { this.timeLeft = timeLeft; }

}