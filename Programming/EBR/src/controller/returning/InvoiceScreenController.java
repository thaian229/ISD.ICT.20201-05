package controller.returning;

import controller.BaseController;
import model.bike.Bike;
import model.invoice.Invoice;
import model.payment.PaymentTransaction;



import java.sql.SQLException;
import java.time.LocalDateTime;

public class InvoiceScreenController extends BaseController {
    public void confirmInvoice(Invoice invoice) throws SQLException {
        //user clicked to confirm invoice
    }

    public int calculateReturned(Invoice invoice) {
       // Bike bike = Invoice.getBike();
//        LocalDateTime startTime = invoice.getStartTime();
//        LocalDateTime endTime = invoice.getEndTime();
        int deposit = invoice.getDeposit();
        int totalFees = invoice.getTotalFees();
        int returned = deposit - totalFees;
        return returned;
    }
}