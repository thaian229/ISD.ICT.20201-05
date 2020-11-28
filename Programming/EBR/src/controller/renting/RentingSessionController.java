package controller.renting;

import com.sun.webkit.network.Util;
import controller.BaseController;
import model.bike.Bike;
import model.bike.Dock;
import model.session.Session;
import utils.Utils;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class RentingSessionController extends BaseController {
    public boolean returnBike(Session session, Dock dock) {
        try {
            dock.addBike(session.getBike());
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

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
