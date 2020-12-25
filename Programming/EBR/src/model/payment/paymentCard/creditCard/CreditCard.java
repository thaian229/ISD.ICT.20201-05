package model.payment.paymentCard.creditCard;

import model.payment.paymentCard.PaymentCard;

/**
 * Model for credit card
 *
 * @author Vu Minh Hoang, Nguyen Thai An
 * <p>
 * creted at: 24/11/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 - K62
 */
public class CreditCard extends PaymentCard {

    private String cardNum;
    private String cardOwner;
    private int securityCode;
    private String expDate;

    public CreditCard(String cardNum, String cardOwner, int securityCode, String expDate) {
        this.cardNum = cardNum;
        this.cardOwner = cardOwner;
        this.securityCode = securityCode;
        this.expDate = expDate;
    }

    /**
     * constructor use to create card from database's info
     * @param id card's uuid
     * @param cardNum card's number
     * @param cardOwner card's owner/holder's name
     * @param securityCode card's CVV code
     * @param expDate card's expiration date
     */
    public CreditCard(String id, String cardNum, String cardOwner, int securityCode, String expDate) {
        this.id = id;
        this.cardNum = cardNum;
        this.cardOwner = cardOwner;
        this.securityCode = securityCode;
        this.expDate = expDate;
    }

    public String getCardNum() {
        return cardNum;
    }

    public String getCardOwner() {
        return cardOwner;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }
}
