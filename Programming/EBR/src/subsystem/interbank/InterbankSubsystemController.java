package subsystem.interbank;

import common.exception.*;
import model.payment.creditCard.CreditCard;
import model.payment.transaction.PaymentTransaction;
import utils.Configs;
import utils.MyMap;
import utils.Utils;

import java.util.Map;

public class InterbankSubsystemController {

	private static final String PUBLIC_KEY = "BDcKeK6XjQg=";
	private static final String SECRET_KEY = "BBbFAE8bdPY=";
	private static final String PAY_COMMAND = "pay";
	private static final String REFUND_COMMAND = "refund";
	private static final String VERSION = "1.0.1";

	private static final InterbankBoundary interbankBoundary = new InterbankBoundary();

	public PaymentTransaction refund(CreditCard card, int amount, String contents) {
		// set up transaction
		Map<String, Object> transaction = new MyMap();
		transaction.put("command", REFUND_COMMAND);
		transaction.put("cardCode", card.getCardNum());
		transaction.put("owner", card.getCardOwner());
		transaction.put("cvvCode", card.getSecurityCode());
		transaction.put("dateExpired", card.getExpDate());
		transaction.put("transactionContent", contents);
		transaction.put("amount", amount);
		transaction.put("createdAt", Utils.getToday());

		// generate hash code
		String hashCode;
		Map<String, Object> toBeHash = new MyMap();
		toBeHash.put("secretKey", SECRET_KEY);
		toBeHash.put("transaction", transaction);
		hashCode = Utils.md5(generateData(toBeHash));

		// set up body
		Map<String, Object> requestMap = new MyMap();
		requestMap.put("version", VERSION);
		requestMap.put("transaction", transaction);
		requestMap.put("appCode", PUBLIC_KEY);
		requestMap.put("hashCode", hashCode);

		String responseText = interbankBoundary.query(Configs.PROCESS_TRANSACTION_URL, generateData(requestMap));
		MyMap response = null;
		try {
			response = MyMap.toMyMap(responseText, 0);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new UnrecognizedException();
		}

		return makePaymentTransaction(response);
	}

	public PaymentTransaction payDeposit(CreditCard card, int amount, String contents) {
		// set up transaction
		Map<String, Object> transaction = new MyMap();
		transaction.put("command", PAY_COMMAND);
		transaction.put("cardCode", card.getCardNum());
		transaction.put("owner", card.getCardOwner());
		transaction.put("cvvCode", card.getSecurityCode());
		transaction.put("dateExpired", card.getExpDate());
		transaction.put("transactionContent", contents);
		transaction.put("amount", amount);
		transaction.put("createdAt", Utils.getToday());

		// generate hash code
		String hashCode;
		Map<String, Object> toBeHash = new MyMap();
		toBeHash.put("secretKey", SECRET_KEY);
		toBeHash.put("transaction", transaction);
		hashCode = Utils.md5(generateData(toBeHash));

		// set up body
		Map<String, Object> requestMap = new MyMap();
		requestMap.put("version", VERSION);
		requestMap.put("transaction", transaction);
		requestMap.put("appCode", PUBLIC_KEY);
		requestMap.put("hashCode", hashCode);

		String responseText = interbankBoundary.query(Configs.PROCESS_TRANSACTION_URL, generateData(requestMap));
		MyMap response = null;
		try {
			response = MyMap.toMyMap(responseText, 0);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new UnrecognizedException();
		}

		return makePaymentTransaction(response);
	}

	private String generateData(Map<String, Object> data) {
		return ((MyMap) data).toJSON();
	}

	private PaymentTransaction makePaymentTransaction(MyMap response) {
		if (response == null)
			return null;
		MyMap transcation = (MyMap) response.get("transaction");
		CreditCard card = new CreditCard((String) transcation.get("cardCode"), (String) transcation.get("owner"),
				Integer.parseInt((String) transcation.get("cvvCode")), (String) transcation.get("dateExpired"));
		PaymentTransaction trans = new PaymentTransaction((String) response.get("errorCode"), card,
				(String) transcation.get("transactionId"), (String) transcation.get("transactionContent"),
				Integer.parseInt((String) transcation.get("amount")), (String) transcation.get("createdAt"));

		switch (trans.getErrorCode()) {
		case "00":
			break;
		case "01":
			throw new InvalidCardException();
		case "02":
			throw new NotEnoughBalanceException();
		case "03":
			throw new InternalServerErrorException();
		case "04":
			throw new SuspiciousTransactionException();
		case "05":
			throw new NotEnoughTransactionInfoException();
		case "06":
			throw new InvalidVersionException();
		case "07":
			throw new InvalidTransactionAmountException();
		default:
			throw new UnrecognizedException();
		}

		return trans;
	}

}
