package controller.renting.RentingSessionControllerTest;

import controller.renting.RentingSessionController;
import model.bike.*;
import model.payment.creditCard.CreditCard;
import model.payment.transaction.PaymentTransaction;
import model.session.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class calculateCurrentRentingFeesTest {
    RentingSessionController rentingSessionController;
    PaymentTransaction tmpTransaction;
    CreditCard card;
    LocalDateTime now;


    @BeforeEach
    void Setup() {
        rentingSessionController = new RentingSessionController();
        card = new CreditCard("123", "MH", 123, "08/24");
        tmpTransaction = new PaymentTransaction("01", card, "01", "temp", 123, "03/32");
        now = LocalDateTime.now();
    }

    @Test
    public void Test1() {
        Bike bike = new StandardBike(01);
        Session session = new Session(bike, card, tmpTransaction);
        session.setStartTime(now.minusMinutes(59));
        assertEquals(25000, rentingSessionController.calulateCurrentRentingFees(session));
    }

    @Test
    public void Test2() {
        Bike bike = new StandardElectricalBike(02);
        Session session = new Session(bike, card, tmpTransaction);
        session.setStartTime(now.minusMinutes(61));
        assertEquals(100000, rentingSessionController.calulateCurrentRentingFees(session));
    }

    @Test
    public void Test3() {
        Bike bike = new TwinBike(03);
        Session session = new Session(bike, card, tmpTransaction);
        session.setStartTime(now.minusMinutes(2400));
        assertEquals(800000, rentingSessionController.calulateCurrentRentingFees(session));
    }

    @Test
    public void Test4() {
        Bike bike = new TwinElectricalBike(04);
        Session session = new Session(bike, card, tmpTransaction);
        session.setStartTime(now.minusMinutes(320));
        assertEquals(300000, rentingSessionController.calulateCurrentRentingFees(session));
    }

    @Test
    public void Test5() {
        Bike bike = new StandardBike(01);
        Session session = new Session(bike, card, tmpTransaction);
        session.setStartTime(now.minusMinutes(10));
        assertEquals(25000, rentingSessionController.calulateCurrentRentingFees(session));
    }
}
