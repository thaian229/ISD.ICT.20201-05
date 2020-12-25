package views.screen;

import javafx.stage.Stage;
import model.payment.transaction.PaymentTransaction;

import java.io.IOException;
/**
 * transaction result popup
 *
 * @author mhong99
 * <p>
 * creted at: 20/12/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 - K62
 */
public class BaseScreenHandlerWithTransactionPopup extends BaseScreenHandler {

    /**
     * contructor
     * @param stage {@link Stage stage}
     * @param screenPath URI to fxml
     * @throws IOException IO error
     */
    public BaseScreenHandlerWithTransactionPopup(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    /**
     * Show transaction popup then auto-close
     * @param paymentTransaction transaction to be shown
     * @throws IOException IO error
     */
    public void continueAfterPopupClosed(PaymentTransaction paymentTransaction) throws IOException {

    }
}
