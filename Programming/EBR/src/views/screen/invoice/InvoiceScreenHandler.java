package views.screen.invoice;

import controller.BaseController;
import controller.InvoiceScreenController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.invoice.Invoice;
import model.invoice.InvoiceManager;
import model.payment.creditCard.CreditCard;
import model.payment.creditCard.CreditCardManager;
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
    TextField cardNumberTextField;

    @FXML
    TextField cardOwnerTextField;

    @FXML
    TextField expDateTextField;

    @FXML
    TextField securityCodeTextField;

    @FXML
    CheckBox changeCardCheckbox;

    @FXML
    Button confirmButton;

    private boolean changeCardState;


    public InvoiceScreenHandler(Stage stage, String screenPath, Invoice invoice, InvoiceScreenController controller) throws IOException {
        super(stage, screenPath);
        this.invoice = invoice;
        this.setBController(controller) ;
        this.setImages();
        this.setTextFields();
        this.changeCardState = changeCardCheckbox.isSelected();
        this.handleCheckBox();
        confirmButton.setOnMouseClicked(e -> {
            try {
                handleConfirmButton();
                homeScreenHandler.show();
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        });
        changeCardCheckbox.setOnMouseClicked(e -> {
            handleCheckBox();
        });

    }

    private void handleCheckBox() {
        CreditCard card = this.invoice.getCard();

        if (!this.changeCardState) {
            cardNumberTextField.setText(card.getCardNum());
            cardOwnerTextField.setText(card.getCardOwner());
            expDateTextField.setText(card.getExpDate());
            securityCodeTextField.setText("***");
            cardNumberTextField.setDisable(!this.changeCardState);
            cardOwnerTextField.setDisable(!this.changeCardState);
            expDateTextField.setDisable(!this.changeCardState);
            securityCodeTextField.setDisable(!this.changeCardState);
        } else {
            cardNumberTextField.setText("");
            cardOwnerTextField.setText("");
            expDateTextField.setText("");
            securityCodeTextField.setText("");
            cardNumberTextField.setDisable(!this.changeCardState);
            cardOwnerTextField.setDisable(!this.changeCardState);
            expDateTextField.setDisable(!this.changeCardState);
            securityCodeTextField.setDisable(!this.changeCardState);
        }

        this.changeCardState = !this.changeCardState;
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

            invoiceSessionLength.setText(getBController().calculateSessionLength(this.invoice) + " minutes");
            invoiceDeposit.setText(invoice.getDeposit() + " " + Configs.CURRENCY);
            invoiceTotalFees.setText(getBController().calculateTotalFees(this.invoice) + " " + Configs.CURRENCY);
            invoiceReturned.setText(getBController().calculateReturned(this.invoice) + " " + Configs.CURRENCY);

        } catch (NullPointerException exp) {
            exp.printStackTrace();
        }
    }

    private void handleConfirmButton() throws IOException {

        String contents = "refund";
        CreditCard tmpCard = this.invoice.getCard();

        if (!this.changeCardState) {
            String cardOwner = this.cardOwnerTextField.getText();
            String cardNumber = this.cardNumberTextField.getText();
            String expDate = this.expDateTextField.getText();
            String securityCode = this.securityCodeTextField.getText();

            tmpCard = getBController().getCardByCardNum(cardOwner, cardNumber, securityCode, expDate);
        }

        PaymentTransaction returnTransaction = this.getBController().refund(this.getBController().calculateReturned(this.invoice), contents,
                tmpCard.getCardNum(), tmpCard.getCardOwner(),
                tmpCard.getExpDate(), Integer.toString(tmpCard.getSecurityCode()));

        returnTransaction.setMethod("Credit Card");
        returnTransaction.setType("return");
        returnTransaction.setCard(tmpCard);

        String id = PaymentTransactionManager.getInstance().savePaymentTransaction(returnTransaction);
        SessionManager.getInstance().endSession(SessionManager.getInstance().getSessionById(this.invoice.getSessionId()), returnTransaction);
        InvoiceManager.getInstance().finalInvoice(this.invoice, this.getBController().calculateTotalFees(this.invoice));
    }

    @Override
    public InvoiceScreenController getBController() {
        return (InvoiceScreenController) super.getBController();
    }
}
