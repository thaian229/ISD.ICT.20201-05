package returning;

import controller.InvoiceScreenController;
import model.invoice.Invoice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class CalculateReturnedTest {
    InvoiceScreenController invoiceScreenController;
    String invoiceId;
    String sessionId;

    @BeforeEach
    void Setup() {

        invoiceScreenController = new InvoiceScreenController();

    }

    @Test
    public void Test1() {
        Invoice invoice = new Invoice(invoiceId,sessionId,20000);
        invoice.setDepositForTest(100000);
        assertEquals(80000, invoiceScreenController.calculateReturned(invoice));
    }

    @Test
    public void Test2() {
        Invoice invoice = new Invoice(invoiceId,sessionId,50000);
        invoice.setDepositForTest(100000);
        assertEquals(50000, invoiceScreenController.calculateReturned(invoice));
    }

    @Test
    public void Test3() {
        Invoice invoice = new Invoice(invoiceId,sessionId,30000);
        invoice.setDepositForTest(100000);
        assertEquals(70000, invoiceScreenController.calculateReturned(invoice));
    }

    
}
