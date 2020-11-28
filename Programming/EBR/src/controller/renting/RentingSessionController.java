package controller.renting;

import controller.BaseController;
import model.bike.Bike;
import model.payment.PaymentTransaction;
import model.session.Session;
import model.session.SessionManager;

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
        int sessionLength = endTime.compareTo(startTime);
        int totalCharge = sessionLength * bike.getCharge();
        return totalCharge;
    }
}
