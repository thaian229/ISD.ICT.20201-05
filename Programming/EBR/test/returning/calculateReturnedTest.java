package returning;

import controller.returning.InvoiceScreenController;
import model.bike.*;
import model.invoice.Invoice;
import model.payment.CreditCard;
import model.payment.PaymentTransaction;
import model.session.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class calculateReturnedTest {
    InvoiceScreenController invoiceScreenController;
    PaymentTransaction tmpTransaction;
    CreditCard card;

    @BeforeEach
    void Setup() {
        invoiceScreenController = new InvoiceScreenController();
        card = new CreditCard("103", "KHANG", 123, "03/25");
        tmpTransaction = new PaymentTransaction("01", card, "01", "temp", 123, "03/32");
    }

    @Test
    public void Test1() {
        Bike bike = new Bike();
        Session session = new Session(bike, card, tmpTransaction);
        Invoice invoice = new Invoice(session,bike, card, tmpTransaction);
        invoice.setTotalFees(invoice.getDeposit()-10000);
        assertEquals(10000, invoiceScreenController.calculateReturned(invoice));
    }

    @Test
    public void Test2() {
        Bike bike = new Bike();
        Session session = new Session(bike, card, tmpTransaction);
        Invoice invoice = new Invoice(session,bike, card, tmpTransaction);
        invoice.setTotalFees(invoice.getDeposit()-20000);
        assertEquals(20000, invoiceScreenController.calculateReturned(invoice));
    }

    @Test
    public void Test3() {
        Bike bike = new Bike();
        Session session = new Session(bike, card, tmpTransaction);
        Invoice invoice = new Invoice(session,bike, card, tmpTransaction);
        invoice.setTotalFees(invoice.getDeposit()-30000);
        assertEquals(30000, invoiceScreenController.calculateReturned(invoice));
    }

    
}
