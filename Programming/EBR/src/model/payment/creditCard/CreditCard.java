package model.payment.creditCard;

public class CreditCard {

    private String id;
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

    public String getId() {
        return id;
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
}
