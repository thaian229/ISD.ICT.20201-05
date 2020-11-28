package model.payment;

public class CreditCard {
	private String cardNum;
	private String owner;
	private int cvvCode;
	private String dateExpired;

	public CreditCard(String cardNum, String owner, int cvvCode, String dateExpired) {
		super();
		this.cardNum = cardNum;
		this.owner = owner;
		this.cvvCode = cvvCode;
		this.dateExpired = dateExpired;
	}
	public String getCardNum(){
		return cardNum;
	}
}
