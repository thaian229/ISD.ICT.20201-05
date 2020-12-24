package views.screen;

import model.payment.transaction.PaymentTransaction;

import java.io.IOException;

/**
 * Interface for transaction result popup
 *
 * @author mhong99transaction result popup
 * <p>
 * creted at: 20/12/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 - K62
 */
public interface WithTransactionPopupMethods {
    /**
     * Show transaction popup then auto-close
     * @param paymentTransaction transaction to be shown
     * @throws IOException IO error
     */
    void continueAfterPopupClosed(PaymentTransaction paymentTransaction) throws IOException;
}
