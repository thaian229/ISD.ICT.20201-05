package returning;

import controller.returning.InvoiceScreenController;
import model.bike.*;
import model.invoice.Invoice;
import model.session.SessionManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class CalculateTotalFeesTest {
    InvoiceScreenController invoiceScreenController;
    LocalDateTime now;
    String invoiceId;
    String sessionId;
    int totalFees;

    @BeforeEach
    void Setup() {

        invoiceScreenController = new InvoiceScreenController();

        now = LocalDateTime.now();
    }

    @Test
    public void Test1() {
        Bike bike = new StandardBike(01);
        Invoice invoice = new Invoice(invoiceId,sessionId,totalFees);
        invoice.setBikeForTest(bike);
        invoice.setStartTimeForTest(now.minusMinutes(59));
        invoice.setEndTimeForTest(now);
        assertEquals(25000, invoiceScreenController.calculateTotalFees(invoice));
    }

    @Test
    public void Test2() {
        Bike bike = new StandardElectricalBike(02);
        Invoice invoice = new Invoice(invoiceId,sessionId,totalFees);
        invoice.setBikeForTest(bike);
        invoice.setStartTimeForTest(now.minusMinutes(61));
        invoice.setEndTimeForTest(now);
        assertEquals(100000, invoiceScreenController.calculateTotalFees(invoice));
    }

    @Test
    public void Test3() {
        Bike bike = new TwinBike(03);
        Invoice invoice = new Invoice(invoiceId,sessionId,totalFees);
        invoice.setBikeForTest(bike);
        invoice.setStartTimeForTest(now.minusMinutes(2400));
        invoice.setEndTimeForTest(now);
        assertEquals(800000, invoiceScreenController.calculateTotalFees(invoice));
    }
    @Test
    public void Test4() {
        Bike bike = new TwinElectricalBike(04);
        Invoice invoice = new Invoice(invoiceId,sessionId,totalFees);
        invoice.setBikeForTest(bike);
        invoice.setStartTimeForTest(now.minusMinutes(320));
        invoice.setEndTimeForTest(now);
        assertEquals(300000, invoiceScreenController.calculateTotalFees(invoice));
    }


}
