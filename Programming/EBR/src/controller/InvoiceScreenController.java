package controller;

import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import controller.BaseController;
import model.bike.Bike;
import model.invoice.Invoice;
import model.payment.creditCard.CreditCard;
import model.payment.transaction.PaymentTransaction;
import model.session.Session;
import subsystem.InterbankSubsystem;
import utils.Utils;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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


    private HashMap<String, String> cardInfo = new HashMap<>();


    /**
     * This method is for calculating the total renting fees
     *
     * @param invoice invoice to be computed fee
     * @return totalCharge - the amount of money that customer has to pay until the session end
     * @author khang
     */

    public int calculateTotalFees(Invoice invoice) {
        try {
            LocalDateTime startTime = invoice.getStartTime();
            LocalDateTime endTime = invoice.getEndTime();
            int hoursUsed = (int) Math.ceil(Utils.minusLocalDateTime(startTime, endTime) / 60.0);
            int totalFees = hoursUsed * invoice.getBike().getCharge();
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
            int deposit = invoice.getSession().getBike().getDeposit();
            int totalFees = invoice.getTotalFees();
            int returned = deposit - totalFees;
            // invoice.setReturned(returned);
            return returned;
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public long calculateSessionLength(Invoice invoice) {
        try {
            LocalDateTime startTime = invoice.getStartTime();
            LocalDateTime endTime = invoice.getEndTime();
            return Utils.minusLocalDateTime(startTime, endTime);
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public PaymentTransaction refund(int amount, String contents, String cardNumber, String cardHolderName,
                                     String expirationDate, String securityCode) {
        PaymentTransaction returnTransaction = null;
        Map<String, String> result = new HashMap<String, String>();
        result.put("RESULT", "PAYMENT FAILED!");
        try {
            CreditCard card = new CreditCard(cardNumber, cardHolderName, Integer.parseInt(securityCode),
                    expirationDate);

            InterbankSubsystem interbank = new InterbankSubsystem();
            returnTransaction = interbank.refund(card, amount, contents);

            result.put("RESULT", "PAYMENT SUCCESSFUL!");
            result.put("MESSAGE", "You have successfully paid the deposit!");
        } catch (PaymentException | UnrecognizedException ex) {
            result.put("MESSAGE", ex.getMessage());
        }
        System.out.println(result);
        return returnTransaction;
    }


    public boolean validateReturned(int returned) {
        if (returned < 0) return false;
        return true;
    }

}