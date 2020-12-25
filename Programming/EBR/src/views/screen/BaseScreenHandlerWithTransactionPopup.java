package views.screen;

import javafx.stage.Stage;
import model.payment.transaction.PaymentTransaction;

import java.io.IOException;

public class BaseScreenHandlerWithTransactionPopup extends BaseScreenHandler {

    public BaseScreenHandlerWithTransactionPopup(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    public void continueAfterPopupClosed(PaymentTransaction paymentTransaction) throws IOException {

    }
}
