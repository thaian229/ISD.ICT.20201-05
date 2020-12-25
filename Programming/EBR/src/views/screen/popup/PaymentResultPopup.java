package views.screen.popup;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.payment.transaction.PaymentTransaction;
import utils.Path;
import utils.Utils;
import views.screen.BaseScreenHandlerWithTransactionPopup;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PaymentResultPopup extends Popup implements Initializable {

    @FXML
    ImageView logo;

    @FXML
    Button closeBtn;

    @FXML
    Text cardNumber;

    @FXML
    Text transactionDetails;

    @FXML
    Text amount;

    @FXML
    Text paymentMethod;

    @FXML
    Text transactionResultSuccess;

    @FXML
    Text transactionResultFail;


    private BaseScreenHandlerWithTransactionPopup screenHandler;
    private PaymentTransaction paymentTransaction;

    private PaymentResultPopup(Stage stage, BaseScreenHandlerWithTransactionPopup screenHandler, PaymentTransaction paymentTransaction, String paymentMessage) throws IOException {
        super(stage, Path.PAYMENT_RESULT_POPUP_PATH);
        this.screenHandler = screenHandler;
        this.paymentTransaction = paymentTransaction;
        setTransactionInfo(paymentTransaction, paymentMessage);
    }

    /**
     * Init then return new Payment result popup
     * @param paymentTransaction {@link PaymentTransaction}
     * @param screenHandler {@link BaseScreenHandlerWithTransactionPopup}
     * @param paymentMessage message result
     * @return {@link PaymentTransaction} newly created popup
     * @throws IOException IO errors
     */
    private static PaymentResultPopup popup(PaymentTransaction paymentTransaction, BaseScreenHandlerWithTransactionPopup screenHandler, String paymentMessage) throws IOException {
        PaymentResultPopup popup = new PaymentResultPopup(new Stage(), screenHandler, paymentTransaction, paymentMessage);
        popup.stage.initStyle(StageStyle.UNDECORATED);
        return popup;
    }

    /**
     * set value for text display
     * @param paymentTransaction {@link PaymentTransaction}
     * @param paymentMessage result message of the payment
     */
    private void setTransactionInfo(PaymentTransaction paymentTransaction, String paymentMessage) {
        cardNumber.setText(paymentTransaction.getCard().getCardNum());
        transactionDetails.setText(paymentTransaction.getTransactionContent());
        amount.setText(Utils.getCurrencyFormat(paymentTransaction.getAmount()));
        paymentMethod.setText(paymentTransaction.getMethod());
        if (paymentTransaction.getErrorCode().equals("00")) {
            transactionResultSuccess.setVisible(true);
            transactionResultFail.setVisible(false);
            transactionResultSuccess.setText(paymentMessage);
        } else {
            transactionResultSuccess.setVisible(false);
            transactionResultFail.setVisible(true);
            transactionResultFail.setText(paymentMessage);
        }
    }

    /**
     * Create new then display the popup
     * @param screenHandler {@link BaseScreenHandlerWithTransactionPopup}
     * @param paymentTransaction {@link PaymentTransaction paymentTransaction}
     * @param paymentMessage message go with the payment result
     * @throws IOException IO errors
     */
    public static void display(BaseScreenHandlerWithTransactionPopup screenHandler, PaymentTransaction paymentTransaction, String paymentMessage) throws IOException {
        popup(paymentTransaction, screenHandler, paymentMessage).show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setImages();
    }

    private void setImages() {
        setImage(logo, Path.LOGO_R_ICON);
    }

    @FXML
    private void closeBtnHandler() {
        try {
            this.stage.close();
            if (this.paymentTransaction.getErrorCode().equals("00"))
                screenHandler.continueAfterPopupClosed(this.paymentTransaction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
