package model.payment;

public class CreditCard {
	private String cardNum;
	private String cardOwner;
	private int securityCode;
	private String expDate;

	public CreditCard(String cardNum, String cardOwner, int securityCode, String expDate) {
		super();
		this.cardNum = cardNum;
		this.cardOwner = cardOwner;
		this.securityCode = securityCode;
		this.expDate = expDate;
	}

	public String getCardNum() {
		return cardNum;
	}
}
