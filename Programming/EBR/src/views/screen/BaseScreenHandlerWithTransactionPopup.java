package views.screen;

import javafx.stage.Stage;
import model.payment.transaction.PaymentTransaction;

import java.io.IOException;

public class BaseScreenHandlerWithTransactionPopup extends BaseScreenHandler implements  WithTransactionPopupMethods{

    public BaseScreenHandlerWithTransactionPopup(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    @Override
    public void continueAfterPopupClosed(PaymentTransaction paymentTransaction) throws IOException {

    }
}
