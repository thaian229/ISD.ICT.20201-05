package model.bike;

/**
 * Model for standard electrical bike
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
public class StandardElectricalBike extends Bike {
    protected float battery;
    protected int timeLeft;

    /**
     * Constructor
     * @param barcode Standard electrical bike's barcode
     * @param value Standard electrical bike's value
     * @param charge Standard electrical bike's renting fee
     */
    public StandardElectricalBike(int barcode, int value, int charge){
        this.barcode = barcode;
        this.saddle = 1;
        this.rearSeat = 1;
        this.pairOfPedals = 1;
        this.value = value;
        this.deposit = value/10;
        this.charge = charge;
        this.battery = 100;
        this. timeLeft = 360;
    }

    /**
     * Most use constructor
     * @param id Standard electrical bike's uuid
     * @param barcode Standard electrical bike's barcode
     * @param value Standard electrical bike's value
     * @param charge Standard electrical bike's renting fee
     */
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
        this. timeLeft = 360;
    }

    public float getBattery() { return battery; }

    public void setBattery(float battery) { this.battery = battery; }

    public int getTimeLeft() { return timeLeft; }

    public void setTimeLeft(int timeLeft) { this.timeLeft = timeLeft; }

    @Override
    public String getBikeType() {
        return "E-Bike";
    }
}
