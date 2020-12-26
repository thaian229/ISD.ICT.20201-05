package controllerTest.SessionScreenControllerTest;

import controller.SessionScreenController;
import model.bike.*;
import model.payment.paymentCard.creditCard.CreditCard;
import model.payment.transaction.PaymentTransaction;
import model.session.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class calculateCurrentRentingFeesTest {
    SessionScreenController sessionScreenController;
    PaymentTransaction tmpTransaction;
    CreditCard card;
    LocalDateTime now;


    @BeforeEach
    void Setup() {
        sessionScreenController = new SessionScreenController();
        card = new CreditCard("123", "MH", 123, "0824");
        tmpTransaction = new PaymentTransaction("01", card, "01", "temp", 123, "03/32");
        now = LocalDateTime.now();
    }
//
    @Test
    public void Test1() {
        Bike bike = new StandardBike(01);
        Session session = new Session(bike, card, tmpTransaction);
        session.setStartTime(now.minusSeconds(7));
        session.setLastResumeTime(session.getStartTime());
        session.setActive(true);
        System.out.println(session.getSessionLength());
        Assertions.assertEquals(0, sessionScreenController.calculateCurrentRentingFees(session));
    }

    @Test
    public void Test2() {
        Bike bike = new StandardElectricalBike(02,02,02);
        Session session = new Session(bike, card, tmpTransaction);
        session.setStartTime(now.minusSeconds(25));
        session.setLastResumeTime(session.getStartTime());
        session.setActive(true);
        Assertions.assertEquals(20000, sessionScreenController.calculateCurrentRentingFees(session));
    }

    @Test
    public void Test3() {
        Bike bike = new TwinElectricalBike(04,04,04);
        Session session = new Session(bike, card, tmpTransaction);
        session.setStartTime(now.minusSeconds(52));
        session.setLastResumeTime(session.getStartTime());
        session.setActive(true);
        Assertions.assertEquals(32000, sessionScreenController.calculateCurrentRentingFees(session));
    }

    @Test
    public void Test4() {
        Bike bike = new StandardBike(01);
        Session session = new Session(bike, card, tmpTransaction);
        session.setStartTime(now.minusSeconds(61));
        session.setLastResumeTime(session.getStartTime());
        session.setActive(true);
        Assertions.assertEquals(19000, sessionScreenController.calculateCurrentRentingFees(session));
    }
}
