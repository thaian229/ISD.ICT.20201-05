package views.screen.invoice;

import common.exception.PaymentException;
import common.exception.cardException.FormException;
import controller.InvoiceScreenController;
import controller.PaymentScreenController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.invoice.Invoice;
import model.payment.paymentCard.creditCard.CreditCard;
import model.payment.transaction.PaymentTransaction;
import utils.Configs;
import utils.Utils;
import views.screen.component.NavBarHandler;
import views.screen.BaseScreenHandlerWithTransactionPopup;
import views.screen.popup.AlertPopup;
import views.screen.popup.PaymentResultPopup;

import java.io.IOException;
import java.util.HashMap;

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

public class InvoiceScreenHandler extends BaseScreenHandlerWithTransactionPopup {
    private Invoice invoice;
    @FXML
    private Pane navbar;
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
    Text errorText;

    @FXML
    Text amountLabelText;

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


    /**
     * Create new invoice screen and do initial setup
     *
     * @param stage      {@link Stage}
     * @param screenPath path to fxml
     * @param invoice    {@link Invoice}
     * @param controller {@link InvoiceScreenController}
     * @throws IOException IO error
     */
    public InvoiceScreenHandler(Stage stage, String screenPath, Invoice invoice, InvoiceScreenController controller) throws IOException {
        super(stage, screenPath);
        this.invoice = invoice;
        this.setBController(controller);
        super.screenTitle = "Invoice Screen";
        this.setImages();
        this.setTextFields();
        this.changeCardState = changeCardCheckbox.isSelected();
        this.handleCheckBox();
        navbar.getChildren().add(new NavBarHandler(this, false, false, false).getContent());

        confirmButton.setOnMouseClicked(e -> {
            try {
                handleConfirmButton();
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        });
        changeCardCheckbox.setOnMouseClicked(e -> {
            handleCheckBox();
        });
    }

    /**
     * switch between new or old card info
     */
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

    private void setImages() {
        setImage(invoiceBikeImage, this.invoice.getBike().getImageURL());
    }

    /**
     * set value for all text fields
     */
    private void setTextFields() {
        try {
            CreditCard card = this.invoice.getCard();

            invoiceCardNumber.setText(card.getCardNum());
            invoiceStartTime.setText(this.invoice.getStartTime().format(Utils.DATE_FORMATER_FOR_DISPLAY));
            invoiceEndTime.setText(this.invoice.getEndTime().format(Utils.DATE_FORMATER_FOR_DISPLAY));
            invoiceSessionLength.setText(getBController().calculateSessionLength(this.invoice) + " seconds");
            invoiceDeposit.setText(invoice.getDeposit() + " " + Configs.CURRENCY);
            invoiceTotalFees.setText(getBController().calculateTotalFees(this.invoice) + " " + Configs.CURRENCY);

            int amount = this.getBController().calculateReturned(this.invoice);
            if (amount >= 0)
                invoiceReturned.setText(amount + " " + Configs.CURRENCY);
            else {
                invoiceReturned.setText(-amount + " " + Configs.CURRENCY);
                amountLabelText.setText("PAY AMOUNT");
            }

        } catch (NullPointerException exp) {
            exp.printStackTrace();
        }
    }

    /**
     * Start returning bike process
     *
     * @throws IOException IO errors
     */
    private void handleConfirmButton() throws IOException {
        errorText.setVisible(false);
        CreditCard tmpCard = this.invoice.getCard();
        HashMap<String, String> cardInfo;
        PaymentScreenController paymentScreenController = new PaymentScreenController(invoice.getBike());

        try {
            if (!this.changeCardState) {
                String cardOwner = this.cardOwnerTextField.getText();
                String cardNumber = this.cardNumberTextField.getText();
                String expDate = this.expDateTextField.getText();
                String securityCode = this.securityCodeTextField.getText();
                cardInfo = new HashMap<>();
                cardInfo.put("cardOwner", cardOwner.trim());
                cardInfo.put("cardNumber", cardNumber.trim());
                cardInfo.put("expDate", expDate.trim());
                cardInfo.put("securityCode", securityCode.trim());
                this.getBController().validateCreditCardForm(cardInfo);
                tmpCard = getBController().getCardByCardNum(cardOwner, cardNumber, securityCode, expDate);
            } else {
                tmpCard = getBController().getCardByCardNum(this.invoice.getCard().getCardNum());
            }

            int amount = this.getBController().calculateReturned(this.invoice);
            PaymentTransaction returnTransaction;

            returnTransaction = this.getBController().makeTransaction(amount, tmpCard);
            returnTransaction.setMethod("Credit Card");
            returnTransaction.setType("return");
            returnTransaction.setCard(tmpCard);


            this.getBController().returnBikeProcessing(this.invoice, returnTransaction);
            PaymentResultPopup.display(this, returnTransaction, "PAYMENT SUCCESSFUL");

        } catch (FormException e) {
            errorText.setText(e.getMessage());
            errorText.setVisible(true);
        } catch (PaymentException e) {
            AlertPopup.error(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public InvoiceScreenController getBController() {
        return (InvoiceScreenController) super.getBController();
    }

    @Override
    public void continueAfterPopupClosed(PaymentTransaction paymentTransaction) throws IOException {
            homeScreenHandler.show();
    }
}
