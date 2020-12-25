package controller;

import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import common.exception.cardException.FormException;
import model.invoice.Invoice;
import model.payment.paymentCard.creditCard.CreditCard;
import model.payment.paymentCard.creditCard.CreditCardManager;
import model.payment.transaction.PaymentTransaction;
import subsystem.InterbankSubsystem;

import java.sql.SQLException;
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
//            Bike bike = session.getBike();
            Long sLength = invoice.getSession().getSessionLength();
            int totalFees;
            if (sLength < 10) {
                totalFees = 0;
            } else if (sLength >= 10 && sLength < 30) {
                totalFees = 10000;
            } else {
                totalFees = (int) (10000.0 + 3000 * Math.ceil((sLength - 30.0) / 15.0));
            }
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
        return invoice.getSession().getSessionLength();
    }

    public PaymentTransaction refund(int amount, String contents, String cardNumber, String cardHolderName,
                                     String expirationDate, String securityCode) throws PaymentException, UnrecognizedException{
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
            throw ex;
        }
        System.out.println(result);
        return returnTransaction;
    }


    public boolean validateReturned(int returned) {
        if (returned < 0) return false;
        return true;
    }

    public CreditCard getCardByCardNum(String cardOwner, String cardNumber, String securityCode, String expDate) {
        CreditCard card = CreditCardManager.getInstance().getCardByCardNumber(cardNumber);
        if (card == null) {
            card = new CreditCard(cardNumber, cardOwner, Integer.parseInt(securityCode), expDate);
            CreditCardManager.getInstance().saveCreditCard(card);
        }
        else {
            card.setSecurityCode(Integer.parseInt(securityCode));
        }
        return card;
    }

    public CreditCard getCardByCardNum(String cardNumber) {
        return CreditCardManager.getInstance().getCardByCardNumber(cardNumber);
    }

    public void validateCreditCardForm(HashMap<String, String> cardInfo) throws FormException {
        CardFormController.validateCreditCardForm(cardInfo);
    }
}