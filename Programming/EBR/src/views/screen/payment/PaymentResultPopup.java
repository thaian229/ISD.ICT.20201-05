package views.screen.payment;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.bike.Bike;
import model.bike.BikeManager;
import model.dock.DockManager;
import model.payment.transaction.PaymentTransaction;
import model.session.Session;
import model.session.SessionManager;
import utils.Path;
import views.screen.BaseScreenHandler;
import views.screen.BaseScreenHandlerWithTransactionPopup;
import views.screen.FXMLScreenHandler;
import views.screen.WithTransactionPopupMethods;
import views.screen.home.BarcodePopup;
import views.screen.home.HomeScreenHandler;
import views.screen.invoice.InvoiceScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PaymentResultPopup extends BaseScreenHandler implements Initializable {

    @FXML
    ImageView logo;

    @FXML
    Button closeBtn;

    private BaseScreenHandlerWithTransactionPopup screenHandler;
    private PaymentTransaction paymentTransaction;

    private PaymentResultPopup(Stage stage, BaseScreenHandlerWithTransactionPopup screenHandler, PaymentTransaction paymentTransaction) throws IOException {
        super(stage, Path.PAYMENT_RESULT_POPUP_PATH);
        this.screenHandler = screenHandler;
        this.paymentTransaction = paymentTransaction;
    }

    private static PaymentResultPopup popup(PaymentTransaction paymentTransaction, BaseScreenHandlerWithTransactionPopup screenHandler) throws IOException {
        PaymentResultPopup popup = new PaymentResultPopup(new Stage(), screenHandler, paymentTransaction);
        popup.stage.initStyle(StageStyle.UNDECORATED);
        setTransactionInfo(paymentTransaction);
        return popup;
    }

    private static void setTransactionInfo(PaymentTransaction paymentTransaction) {

    }

    public static void display(BaseScreenHandlerWithTransactionPopup screenHandler, PaymentTransaction paymentTransaction) throws IOException {
        popup(paymentTransaction, screenHandler).show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setImages();
    }

    private void setImages() {
//        setImage(logo, Path.LOGO_R_ICON);
    }

    @FXML
    private void closeBtnHandler() {
        try {
            this.stage.close();
            screenHandler.continueAfterPopupClosed(this.paymentTransaction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
