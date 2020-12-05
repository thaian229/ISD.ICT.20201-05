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
		super();
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
	public PaymentTransaction(String id, String type, int amount, String method) {
		this.id = id;
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

	public int getAmount() {
		return amount;
	}

	public String getType() {
		return type;
	}

	public String getMethod() {
		return method;
	}
}
