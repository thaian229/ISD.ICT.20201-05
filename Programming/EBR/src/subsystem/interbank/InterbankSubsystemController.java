package subsystem.interbank;

import common.exception.*;
import common.exception.InvalidCardException;
import model.payment.paymentCard.creditCard.CreditCard;
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

    public PaymentTransaction refund(CreditCard card, int amount, String contents) throws PaymentException, UnrecognizedException {
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

    public PaymentTransaction payDeposit(CreditCard card, int amount, String contents) throws PaymentException, UnrecognizedException {
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
//        System.out.println(response.toJSON());
        PaymentTransaction trans = null;
        if (response == null)
            return null;
        else if (response.get("errorCode").equals("00")) {
            MyMap transaction = (MyMap) response.get("transaction");
            CreditCard card = new CreditCard((String) transaction.get("cardCode"), (String) transaction.get("owner"),
                    Integer.parseInt((String) transaction.get("cvvCode")), (String) transaction.get("dateExpired"));
            trans = new PaymentTransaction((String) response.get("errorCode"), card,
                    (String) transaction.get("transactionId"), (String) transaction.get("transactionContent"),
                    Integer.parseInt((String) transaction.get("amount")), (String) transaction.get("createdAt"));

        } else {
            Object errorCode = response.get("errorCode");
            if ("00".equals(errorCode)) {
            } else if ("01".equals(errorCode)) {
                throw new InvalidCardException();
            } else if ("02".equals(errorCode)) {
                throw new NotEnoughBalanceException();
            } else if ("03".equals(errorCode)) {
                throw new InternalServerErrorException();
            } else if ("04".equals(errorCode)) {
                throw new SuspiciousTransactionException();
            } else if ("05".equals(errorCode)) {
                throw new NotEnoughTransactionInfoException();
            } else if ("06".equals(errorCode)) {
                throw new InvalidVersionException();
            } else if ("07".equals(errorCode)) {
                throw new InvalidTransactionAmountException();
            } else {
                throw new UnrecognizedException();
            }
        }

        return trans;

    }

}
