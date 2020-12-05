package controller.returning;

import controller.BaseController;
import model.invoice.Invoice;
import utils.Utils;

import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * class for controller of the invoice screen
 *
 * @author khang
 * <p>
 * created_at: 4/12/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 K62
 * <p>
 * helpers: teacher's teaching assistants
 */

public class InvoiceScreenController extends BaseController {
    public void confirmInvoice(Invoice invoice) throws SQLException {
        //user clicked to confirm invoice
    }
   // private int isValidReturned = 0;



    /**
     * This method is for calculating the total renting fees
     *
     * @param invoice invoice to be computed fee
     * @return totalCharge - the amount of money that customer has to pay until the session end
     * @author khang
     */

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



    /**
     * This method is for calculating the returned deposit
     *
     * @param invoice invoice to be computed fee
     * @return returned - the amount of money that EBR has to return to user
     * @author khang
     */


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