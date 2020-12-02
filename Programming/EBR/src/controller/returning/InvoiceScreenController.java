package controller.returning;

import controller.BaseController;
import model.invoice.Invoice;


import java.sql.SQLException;

public class InvoiceScreenController extends BaseController {
    public void confirmInvoice(Invoice invoice) throws SQLException {
        //user clicked to confirm invoice
    }
   // private int isValidReturned = 0;


    public int calculateReturned(Invoice invoice) {
       // Bike bike = Invoice.getBike();
//        LocalDateTime startTime = invoice.getStartTime();
//        LocalDateTime endTime = invoice.getEndTime();
        int deposit = invoice.getDeposit();
        int totalFees = invoice.getTotalFees();
        int returned = deposit - totalFees;
        invoice.setReturned();
        return returned;
    }
    public boolean validateReturned(int returned){
        if(returned < 0 ) return false;
        return true;
    }

}