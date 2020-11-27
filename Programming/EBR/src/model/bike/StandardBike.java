package model.bike;

public class StandardBike extends Bike {
    public StandardBike(int barcode) {
        this.barcode = barcode;
        this.saddle = 1;
        this.rearSeat = 1;
        this.pairOfPedals = 1;
        this.deposit = 1000000;
        this.charge = 25000;
    }

    public StandardBike(int barcode, int deposit, int charge) {
        this.barcode = barcode;
        this.saddle = 1;
        this.rearSeat = 1;
        this.pairOfPedals = 1;
        this.deposit = deposit;
        this.charge = charge;
    }
}
