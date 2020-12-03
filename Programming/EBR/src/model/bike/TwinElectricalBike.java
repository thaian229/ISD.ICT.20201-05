package model.bike;

public class TwinElectricalBike extends Bike{
    private float battery;
    private String timeLeft;

    public TwinElectricalBike(int barcode){
        this.barcode = barcode;
        this.saddle = 2;
        this.rearSeat = 1;
        this.pairOfPedals = 2;
        this.deposit = 5000000;
        this.charge = 50000;
        this.battery = 100;
        this. timeLeft = "6 hours";
    }

    public TwinElectricalBike(int barcode, int value, int charge){
        this.barcode = barcode;
        this.saddle = 1;
        this.rearSeat = 1;
        this.pairOfPedals = 1;
        this.value = value;
        this.deposit = value/10;
        this.charge = charge;
        this.battery = 100;
        this. timeLeft = "6 hours";
    }

    public TwinElectricalBike(String id, int barcode, int value, int charge){
        this.id = id;
        this.barcode = barcode;
        this.saddle = 1;
        this.rearSeat = 1;
        this.pairOfPedals = 1;
        this.value = value;
        this.deposit = value/10;
        this.charge = charge;
        this.battery = 100;
        this. timeLeft = "6 hours";
    }

    public float getBattery() { return battery; }

    public void setBattery(float battery) { this.battery = battery; }

    public String getTimeLeft() { return timeLeft; }

    public void setTimeLeft(String timeLeft) { this.timeLeft = timeLeft; }

}

