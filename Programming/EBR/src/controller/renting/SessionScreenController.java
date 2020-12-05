package controller.renting;

import controller.BaseController;
import model.bike.Bike;
import model.dock.Dock;
import model.session.Session;
import utils.Utils;

import java.time.LocalDateTime;

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

    /**
     * This method is for return a bike to a chosen dock
     *
     * @param session
     * @param dock
     * @return true if bike returned successfully
     * @author mHoang
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
     *
     * @param session
     * @return totalCharge - the amount of money that customer has to pay until now
     * @author mHoang
     */
    public int calulateCurrentRentingFees(Session session) {
        try {
            Bike bike = session.getBike();
            LocalDateTime startTime = session.getStartTime();
            LocalDateTime currentTime = LocalDateTime.now();
            int hoursUsed = (int) Math.ceil(Utils.minusLocalDateTime(startTime, currentTime) / 60.0);
            int totalCharge = hoursUsed * bike.getCharge();
            return totalCharge;
        } catch (NullPointerException e) {
            return 0;
        }

    }
}
