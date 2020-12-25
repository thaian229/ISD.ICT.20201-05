package ControllerTest.InvocieScreenController;

import controller.InvoiceScreenController;
import model.payment.transaction.PaymentTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class controls the flow of place rush  order usecase in our AIMS project
 *
 * @author khang
 * <p>
 * Create at: 12/14/2020
 * <p>
 * Project name: EBR
 * <p>
 * Teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * Class name: TT.CNTT ICT 02 K62
 * <p>
 * Helpers: Teaching assistants
 */
public class RefundTest {
    InvoiceScreenController invoiceScreenController;
    @BeforeEach
    void Setup() {
        invoiceScreenController = new InvoiceScreenController();
    }
    @Test
    public void Test1() {
//        Invoice invoice = new Invoice(invoiceId,sessionId,20000);
//        invoice.setDepositForTest(100000);
//        assertEquals(80000, invoiceScreenController.calculateReturned(invoice));
        String contents = "refund";
        PaymentTransaction returnTransaction = this.invoiceScreenController.refund(1000000000, contents,
                "121319_group5_2020", "Group 5",
                "1125", "721");

    }

}
