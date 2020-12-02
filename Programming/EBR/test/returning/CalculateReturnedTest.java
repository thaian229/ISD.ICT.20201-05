package returning;

import controller.returning.InvoiceScreenController;
import model.bike.Bike;
import model.invoice.Invoice;
import model.session.SessionManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class CalculateReturnedTest {
    InvoiceScreenController invoiceScreenController;


    @BeforeEach
    void Setup() {
        Bike bike = new Bike();
        invoiceScreenController = new InvoiceScreenController();
        String invoiceIdTest = "001";
        String sessionIdTest = "001";
        bike.setDeposit(100000);
        //SessionManager.getInstance().getSessionById("001").getBike().setDeposit(100000);

    }

    @Test
    public void Test1() {
        Invoice invoice = new Invoice("001","001",20000);
        assertEquals(10000, invoiceScreenController.calculateReturned(invoice));
    }

    @Test
    public void Test2() {
        Invoice invoice = new Invoice("001","001",50000);
        assertEquals(10000, invoiceScreenController.calculateReturned(invoice));
    }

    @Test
    public void Test3() {
        Invoice invoice = new Invoice("001","001",30000);
        assertEquals(10000, invoiceScreenController.calculateReturned(invoice));
    }

    
}
