package model.payment.transaction;


public class PaymentTransactionManager {
    private static PaymentTransactionManager instance;

    public static PaymentTransactionManager getInstance() {
        if (instance == null) {
            instance = new PaymentTransactionManager();
        }
        return instance;
    }


    public PaymentTransaction getTransactionById(String rent_transactionid) {
        return null;
    }
}
