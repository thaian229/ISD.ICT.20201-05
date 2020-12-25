package controller;

import controller.strategy.RentingFeeBySecondsCalculator;
import controller.strategy.RentingFeeCalculator;
import model.dock.Dock;
import model.session.Session;
import model.session.SessionManager;

/**
 * class for controller of the session screen
 *
 * @author mHoang
 * <p>
 * created_at: 4/12/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 K62
 * <p>
 * helpers: teacher's teaching assistants
 */

public class SessionScreenController extends BaseController {

    private RentingFeeCalculator feeCalculator = new RentingFeeBySecondsCalculator();

    public void setFeeCalculator(RentingFeeCalculator feeCalculator) {
        this.feeCalculator = feeCalculator;
    }

    /**
     * This method is for return a bike to a chosen dock
     * @param session current renting session
     * @param dock    dock that bike will be put in
     * @return true if bike returned successfully
     */
    public boolean returnBike(Session session, Dock dock) {
        try {
            dock.addBike(session.getBike());
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    /**
     * This method is for calculating the current renting fees
     * @param session session to be computed fees
     * @return amount of money that customer has to pay until now
     */
    public int calculateCurrentRentingFees(Session session) {
        return feeCalculator.calculateCurrentRentingFees(session);
    }

    public long calculateSessionLength(Session session) {
        return session.getSessionLength();
    }

    /**
     * This method is used to change lock state of bike
     * @param session session in use
     */
    public void changeBikeLockState(Session session) {
        SessionManager.getInstance().switchSessionState(session);
    }
}
