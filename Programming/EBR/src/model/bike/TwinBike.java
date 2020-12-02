package model.bike;

public class TwinBike extends Bike{
    public TwinBike(int barcode) {
        this.barcode = barcode;
        this.saddle = 2;
        this.rearSeat = 1;
        this.pairOfPedals = 2;
        this.deposit = 3000000;
        this.charge = 20000;
    }

    public TwinBike(int barcode, int value, int charge) {
        this.barcode = barcode;
        this.saddle = 2;
        this.rearSeat = 1;
        this.pairOfPedals = 2;
        this.value = value;
        this.deposit = value/10;
        this.charge = charge;
    }

    public TwinBike(String id, int barcode, int value, int charge) {
        this.id = id;
        this.barcode = barcode;
        this.saddle = 2;
        this.rearSeat = 1;
        this.pairOfPedals = 2;
        this.value = value;
        this.deposit = value/10;
        this.charge = charge;
    }
}
