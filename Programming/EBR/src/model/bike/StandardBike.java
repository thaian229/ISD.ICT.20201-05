package model.bike;

/**
 * Model for standard bike
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
public class StandardBike extends Bike {
    public StandardBike(int barcode) {
        this.barcode = barcode;
        this.saddle = 1;
        this.rearSeat = 1;
        this.pairOfPedals = 1;
        this.deposit = 1000000;
        // this.charge = 10000;
    }

    /**
     * Constructor
     * @param barcode Standard bike's barcode
     * @param value Standard bike's value
     * @param charge Standard bike's renting fee
     */
    public StandardBike(int barcode, int value, int charge) {
        this.barcode = barcode;
        this.saddle = 1;
        this.rearSeat = 1;
        this.pairOfPedals = 1;
        this.value = value;
        this.deposit = value/10;
        // this.charge = charge;
    }

    /**
     * Most use constructor
     * @param id Standard bike's uuid
     * @param barcode Standard bike's barcode
     * @param value Standard bike's value
     * @param charge Standard bike's renting fee
     */
    public StandardBike(String id, int barcode, int value, int charge) {
        this.id = id;
        this.barcode = barcode;
        this.saddle = 1;
        this.rearSeat = 1;
        this.pairOfPedals = 1;
        this.value = value;
        this.deposit = value/10;
        // this.charge = charge;
    }

    @Override
    public String getBikeType() {
        return "Standard Bike";
    }
}
