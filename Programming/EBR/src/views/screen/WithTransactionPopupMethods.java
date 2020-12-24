package views.screen;

import model.payment.transaction.PaymentTransaction;

import java.io.IOException;

public interface WithTransactionPopupMethods {
    void continueAfterPopupClosed(PaymentTransaction paymentTransaction) throws IOException;
}
