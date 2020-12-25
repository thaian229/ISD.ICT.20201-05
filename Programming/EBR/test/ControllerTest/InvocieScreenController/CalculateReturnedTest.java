package ControllerTest.InvocieScreenController;

import controller.InvoiceScreenController;
import model.bike.Bike;
import model.bike.StandardBike;
import model.invoice.Invoice;
import model.payment.creditCard.CreditCard;
import model.payment.transaction.PaymentTransaction;
import model.session.Session;
import model.session.SessionManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CalculateReturnedTest {
    InvoiceScreenController invoiceScreenController;
    PaymentTransaction tmpTransaction;
    CreditCard card;
    Bike bike;
    Session session;

    @BeforeEach
    void Setup() {
        bike = new StandardBike(01);
        card = new CreditCard("123", "MH", 123, "0824");
        tmpTransaction = new PaymentTransaction("01", card, "01", "temp", 123, "03/32");
        invoiceScreenController = new InvoiceScreenController();
    }

    @ParameterizedTest
    @CsvSource({
            "100000, 20000, 80000",
            "100000, 50000, 50000",
            "100000, 30000, 70000"
    })
    public void Test(int bikeDeposit, int totalCharge, int expected) {
        bike.setDeposit(bikeDeposit);
        session = SessionManager.getInstance().createSession(bike, card, tmpTransaction);
        Invoice invoice = new Invoice("1",session.getId(),totalCharge);
        Assertions.assertEquals(expected, invoiceScreenController.calculateReturned(invoice));
    }
}
