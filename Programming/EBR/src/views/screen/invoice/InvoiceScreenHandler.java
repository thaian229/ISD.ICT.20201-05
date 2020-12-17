package views.screen.invoice;

import controller.InvoiceScreenController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.invoice.Invoice;
import model.invoice.InvoiceManager;
import model.payment.creditCard.CreditCard;
import model.payment.transaction.PaymentTransaction;
import model.payment.transaction.PaymentTransactionManager;
import model.session.SessionManager;
import utils.Configs;
import utils.Path;
import utils.Utils;
import views.screen.BaseScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Handler for the Invoice Screen
 *
 * @author khang
 * <p>
 * created_at: 4/12/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 K62
 * <p>
 * helpers: teacher's teaching assistants
 */

public class InvoiceScreenHandler extends BaseScreenHandler implements Initializable {
    private Invoice invoice;
    private InvoiceScreenController controller;
    @FXML
    ImageView logo;

    @FXML
    ImageView invoiceBikeImage;

    @FXML
    Text invoiceCardNumber;


    @FXML
    Text invoiceStartTime;

    @FXML
    Text invoiceEndTime;

    @FXML
    Text invoiceSessionLength;

    @FXML
    Text invoiceDeposit;

    @FXML
    Text invoiceTotalFees;

    @FXML
    Text invoiceReturned;

    @FXML
    Button returnHomeButton;


    public InvoiceScreenHandler(Stage stage, String screenPath, Invoice invoice, InvoiceScreenController controller) throws IOException {
        super(stage, screenPath);
        this.invoice = invoice;
        this.controller = controller;
        this.setImages();
        this.setTextFields();

        returnHomeButton.setOnMouseClicked(e -> {
            try {
                handleReturnHome();
                homeScreenHandler.show();
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logo.setOnMouseClicked(e -> homeScreenHandler.show());
    }

    private void setImages() {
        setImage(logo, Path.LOGO_ICON);
        setImage(invoiceBikeImage, this.invoice.getBike().getImageURL());
    }

    private void setTextFields() {
        try {
            CreditCard card = this.invoice.getCard();
            invoiceCardNumber.setText(card.getCardNum());
            invoiceStartTime.setText(this.invoice.getStartTime().format(Utils.DATE_FORMATER_FOR_DISPLAY));
            invoiceEndTime.setText(this.invoice.getEndTime().format(Utils.DATE_FORMATER_FOR_DISPLAY));

            invoiceSessionLength.setText(controller.calculateSessionLength(this.invoice) + " minutes");
            invoiceDeposit.setText(invoice.getDeposit() + " " + Configs.CURRENCY);
            invoiceTotalFees.setText(controller.calculateTotalFees(this.invoice) + " " + Configs.CURRENCY);
            invoiceReturned.setText(controller.calculateReturned(this.invoice) + " " + Configs.CURRENCY);

        } catch (NullPointerException exp) {
            exp.printStackTrace();
        }
    }

    private void handleReturnHome() throws IOException {

        String contents = "refund";
        PaymentTransaction returnTransaction = this.controller.refund(this.controller.calculateReturned(this.invoice), contents,
                this.invoice.getCard().getCardNum(), this.invoice.getCard().getCardOwner(),
                this.invoice.getCard().getExpDate(), Integer.toString(this.invoice.getCard().getSecurityCode()));
        returnTransaction.setMethod("Credit Card");
        returnTransaction.setType("return");
        String id = PaymentTransactionManager.getInstance().savePaymentTransaction(returnTransaction);
        SessionManager.getInstance().endSession(SessionManager.getInstance().getSessionById(this.invoice.getSessionId()), returnTransaction);
        InvoiceManager.getInstance().finalInvoice(this.invoice, this.controller.calculateTotalFees(this.invoice));
    }
}
