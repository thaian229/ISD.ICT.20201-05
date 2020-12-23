package model.payment.transaction;

import model.payment.creditCard.CreditCard;

/**
 * Model to store Payment Transaction's info
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
public class PaymentTransaction {

	private String errorCode;


	private CreditCard card;
	private String transactionId;
	private String transactionContent;
	private int amount;
	private String createdAt;
	private String id;
	private String type;
	private String method;
	
	public PaymentTransaction(String errorCode, CreditCard card, String transactionId, String transactionContent,
			int amount, String createdAt) {
		this.errorCode = errorCode;
		this.card = card;
		this.transactionId = transactionId;
		this.transactionContent = transactionContent;
		this.amount = amount;
		this.createdAt = createdAt;
	}

	/**
	 * constructor for create from database's info
	 * @param id transaction's uuid
	 * @param type its type
	 * @param amount transfer amount
	 * @param method payment method
	 */
	public PaymentTransaction(String id,String transactionId, String type, int amount, String method) {
		this.id = id;
		this.type = type;
		this.amount = amount;
		this.method = method;
		this.transactionId = transactionId;
	}

	public PaymentTransaction(String type, int amount, String method) {
		this.type = type;
		this.amount = amount;
		this.method = method;
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CreditCard getCard() {
		return card;
	}

	public void setCard(CreditCard card) {
		this.card = card;
	}


	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getTransactionContent() {
		return transactionContent;
	}

	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionContent(String transactionContent) {
		this.transactionContent = transactionContent;
	}
}
