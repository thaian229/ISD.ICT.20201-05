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

    public TwinBike(int barcode, int deposit, int charge) {
        this.barcode = barcode;
        this.saddle = 2;
        this.rearSeat = 1;
        this.pairOfPedals = 2;
        this.deposit = deposit;
        this.charge = charge;
    }
}
