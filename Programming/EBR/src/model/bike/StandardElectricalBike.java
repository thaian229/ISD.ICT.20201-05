package model.bike;

public class StandardElectricalBike extends Bike {
    private float battery;
    private String timeLeft;

    public StandardElectricalBike(int barcode){
        this.barcode = barcode;
        this.saddle = 1;
        this.rearSeat = 1;
        this.pairOfPedals = 1;
        this.deposit = 4000000;
        this.charge = 50000;
        this.battery = (float) 100;
        this. timeLeft = "6 hours";
    }

    public StandardElectricalBike(int barcode, int value, int charge){
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

    public StandardElectricalBike(String id, int barcode, int value, int charge){
        this.id = id;
        this.barcode = barcode;
        this.saddle = 1;
        this.rearSeat = 1;
        this.pairOfPedals = 1;
        this.value = value;
        this.deposit = value/10;
        this.charge = charge;
        this.battery = (float) 100;
        this. timeLeft = "6 hours";
    }

    public float getBattery() { return battery; }

    public void setBattery(float battery) { this.battery = battery; }

    public String getTimeLeft() { return timeLeft; }

    public void setTimeLeft(String timeLeft) { this.timeLeft = timeLeft; }

}
