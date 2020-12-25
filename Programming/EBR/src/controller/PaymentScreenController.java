package controller;

import common.exception.cardException.*;
import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import model.bike.Bike;
import model.payment.paymentCard.creditCard.CreditCard;
import model.payment.transaction.PaymentTransaction;
import model.session.Session;
import model.session.SessionManager;
import subsystem.InterbankSubsystem;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller to execute all logic required for taking payment method
 * and start a new renting session
 *
 * @author Nguyen Thai An
 * <p>
 * creted at: 25/11/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 - K62
 */
public class PaymentScreenController extends BaseController {

    private Bike bike;
    private HashMap<String, String> cardInfo;

    public PaymentScreenController(Bike bike) {
        this.bike = bike;
        cardInfo = new HashMap<>();
    }

    public PaymentScreenController(Bike bike, HashMap<String, String> cardInfo) {
        this.bike = bike;
        this.cardInfo = cardInfo;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }

    public HashMap<String, String> getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(HashMap<String, String> cardInfo) {
        this.cardInfo = cardInfo;
    }

    /**
     * Pay Deposit to start Renting bike
     *
     * @param amount         deposit amount
     * @param contents       content of transaction
     * @param cardNumber     card number
     * @param cardHolderName card owner's name
     * @param expirationDate date of expiration of the credit card
     * @param securityCode   CVV code
     * @return respond
     */
    public PaymentTransaction payDeposit(int amount, String contents, String cardNumber, String cardHolderName,
                                         String expirationDate, String securityCode) throws PaymentException, UnrecognizedException {
        PaymentTransaction rentTransaction = null;
        Map<String, String> result = new HashMap<String, String>();
        result.put("RESULT", "PAYMENT FAILED!");
        try {
            CreditCard card = new CreditCard(cardNumber, cardHolderName, Integer.parseInt(securityCode),
                    expirationDate);

            InterbankSubsystem interbank = new InterbankSubsystem();
            rentTransaction = interbank.payOrder(card, amount, contents);

            result.put("RESULT", "PAYMENT SUCCESSFUL!");
            result.put("MESSAGE", "You have successfully paid the deposit!");
        } catch (PaymentException | UnrecognizedException ex) {
            result.put("MESSAGE", ex.getMessage());
            throw ex;
        }
        System.out.println(result);
        return rentTransaction;
    }

    /**
     * validating format of card info form
     *
     * @param creditCardForm all fields of form forward from View
     * @return
     */
    public void validateCreditCardForm(HashMap<String, String> creditCardForm) throws FormException{
        CardFormController.validateCreditCardForm(creditCardForm);
    }

    public void validateCardUnused(String cardNumber) {
        for (Session session : SessionManager.getInstance().getSessions()) {
            if (session.getEndTime() == null && session.getCard().getCardNum().equals(cardNumber)) {
                throw new CardUsedException();
            }
        }
    }

}
