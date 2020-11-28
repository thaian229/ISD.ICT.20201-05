package controller.renting;

import com.sun.webkit.network.Util;
import controller.BaseController;
import model.bike.Bike;
import model.payment.PaymentTransaction;
import model.session.Session;
import model.session.SessionManager;
import utils.Utils;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class RentingSessionController extends BaseController {
    public void returnBike(Session session) throws SQLException {
        //user clicked return bike
    }

    public int calulateCurrentRentingFees(Session session) {
        Bike bike = session.getBike();
        LocalDateTime startTime = session.getStartTime();
        LocalDateTime endTime = session.getEndTime();
        int hoursUsed = (int) Math.ceil(Utils.minusLocalDateTime(session.getStartTime(),session.getEndTime())/60.0);
        int totalCharge = hoursUsed * bike.getCharge();
        return totalCharge;
    }
}
