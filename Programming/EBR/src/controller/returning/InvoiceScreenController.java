package controller.returning;

import controller.BaseController;
import model.bike.Bike;
import model.invoice.Invoice;
import utils.Utils;


import java.sql.SQLException;
import java.time.LocalDateTime;

public class InvoiceScreenController extends BaseController {
    public void confirmInvoice(Invoice invoice) throws SQLException {
        //user clicked to confirm invoice
    }
   // private int isValidReturned = 0;
    public int calculateTotalFees(Invoice invoice){
        try{
            LocalDateTime startTime = invoice.getStartTimeForTest();
            LocalDateTime endTime = invoice.getEndTimeForTest();
            int hoursUsed = (int) Math.ceil(Utils.minusLocalDateTime(startTime, endTime) / 60.0);
            int totalFees = hoursUsed * invoice.getBikeForTest().getCharge();
            invoice.setTotalFees(totalFees);
            return totalFees;
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public int calculateReturned(Invoice invoice) {
    try {
        int deposit = invoice.getDepositForTest();
        int totalFees = invoice.getTotalFees();
        int returned = deposit - totalFees;
        invoice.setReturned(returned);
        return returned;
    }
    catch (NullPointerException e){
        return 0;
    }
    }
    public boolean validateReturned(int returned){
        if(returned < 0 ) return false;
        return true;
    }

}