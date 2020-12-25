package controller;

import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import common.exception.cardException.FormException;
import controller.strategy.RentingFeeBySecondsCalculator;
import controller.strategy.RentingFeeCalculator;
import model.invoice.Invoice;
import model.payment.paymentCard.creditCard.CreditCard;
import model.payment.paymentCard.creditCard.CreditCardManager;
import model.payment.transaction.PaymentTransaction;
import model.payment.transaction.PaymentTransactionManager;
import subsystem.InterbankSubsystem;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * class for controller of the invoice screen
 *
 * @author khang
 * <p>
 * created_at: 20/12/2020
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

    RentingFeeCalculator feeCalculator = new RentingFeeBySecondsCalculator();

    public void setFeeCalculator(RentingFeeCalculator feeCalculator) {
        this.feeCalculator = feeCalculator;
    }

    //    public void confirmInvoice(Invoice invoice) throws SQLException {
//        //user clicked to confirm invoice
//    }
//    // private int isValidReturned = 0;


//    private HashMap<String, String> cardInfo = new HashMap<>();


    /**
     * This method is for calculating the total renting fees
     * @param invoice invoice to be computed fee
     * @return amount of money that customer has to pay until the session end
     */

    public int calculateTotalFees(Invoice invoice) {
        return feeCalculator.calculateTotalFees(invoice);
    }


    /**
     * This method is for calculating the returned deposit
     * @param invoice invoice to be computed fee
     * @return the amount of money that EBR has to return to user
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

    /**
     * This method is for calculating the rented session length
     * @param invoice - invoice to be computed fee
     * @return session length
     */
    public long calculateSessionLength(Invoice invoice) {
        return invoice.getSession().getSessionLength();
    }

    /**
     * This method is used to refund deposit
     * @param amount returned amount
     * @param contents contents of transaction
     * @param cardNumber card number
     * @param cardHolderName card owner
     * @param expirationDate card expiration date
     * @param securityCode card security code
     * @return respond
     * @throws PaymentException payment error
     * @throws UnrecognizedException unrecognized error
     */
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

    /**
     * This method is used to validate returned deposit
     * @param returned returned deposit to be validated
     * @return true if valid
     */
    public boolean validateReturned(int returned) {
        if (returned < 0) return false;
        return true;
    }

    /**
     * This method is used to get credit card using card information
     * @param cardOwner card owner
     * @param cardNumber card number
     * @param securityCode card security code
     * @param expDate card expiration date
     * @return credit card corresponding to input information
     */
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

    /**
     * This method is used to get credit card by using card number
     * @param cardNumber card number
     * @return credit card corresponding to input number
     */
    public CreditCard getCardByCardNum(String cardNumber) {
        return CreditCardManager.getInstance().getCardByCardNumber(cardNumber);
    }

    public void validateCreditCardForm(HashMap<String, String> cardInfo) throws FormException {
        CardFormController.validateCreditCardForm(cardInfo);
    }

    public PaymentTransaction pay(int amount, String contents, String cardNumber, String cardHolderName,
                                  String expirationDate, String securityCode) {
        PaymentTransaction returnTransaction = null;
        Map<String, String> result = new HashMap<String, String>();
        result.put("RESULT", "PAYMENT FAILED!");
        try {
            CreditCard card = new CreditCard(cardNumber, cardHolderName, Integer.parseInt(securityCode),
                    expirationDate);

            InterbankSubsystem interbank = new InterbankSubsystem();
            returnTransaction = interbank.payOrder(card, amount, contents);

            result.put("RESULT", "PAYMENT SUCCESSFUL!");
            result.put("MESSAGE", "You have successfully paid the deposit!");
        } catch (PaymentException | UnrecognizedException ex) {
            result.put("MESSAGE", ex.getMessage());
            throw ex;
        }
        System.out.println(result);
        return returnTransaction;
    }

    public PaymentTransaction makeTransaction(int amount, CreditCard card) {
        if (amount >= 0) {
            return this.refund(amount, "refund",
                    card.getCardNum(), card.getCardOwner(),
                    card.getExpDate(), Integer.toString(card.getSecurityCode()));

        } else {
            return this.pay(-amount, "pay over amount",
                    card.getCardNum(), card.getCardOwner(),
                    card.getExpDate(), Integer.toString(card.getSecurityCode()));
        }
    }
}