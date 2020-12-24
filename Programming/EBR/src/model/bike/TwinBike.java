package model.bike;

/**
 * Model for twin bike
 *
 * @author Bui Tu Hoang
 * <p>
 * creted at: 20/12/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 - K62
 */
public class TwinBike extends Bike{
    public TwinBike(int barcode) {
        this.barcode = barcode;
        this.saddle = 2;
        this.rearSeat = 1;
        this.pairOfPedals = 2;
        this.deposit = 3000000;
        this.charge = 20000;
    }

    /**
     * Constructor
     * @param barcode Twin bike's barcode
     * @param value Twin bike's value
     * @param charge Twin bike's renting fee
     */
    public TwinBike(int barcode, int value, int charge) {
        this.barcode = barcode;
        this.saddle = 2;
        this.rearSeat = 1;
        this.pairOfPedals = 2;
        this.value = value;
        this.deposit = value/10;
        this.charge = charge;
    }

    /**
     * Most use constructor
     * @param id Twin bike's uuid
     * @param barcode Twin bike's barcode
     * @param value Twin bike's value
     * @param charge Twin bike's renting fee
     */
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
