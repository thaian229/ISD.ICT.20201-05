package views.screen.payment;

import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import controller.PaymentScreenController;
import controller.SessionScreenController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.payment.paymentCard.creditCard.CreditCard;
import model.payment.paymentCard.creditCard.CreditCardManager;
import model.payment.transaction.PaymentTransaction;
import model.payment.transaction.PaymentTransactionManager;
import model.session.Session;
import model.session.SessionManager;
import utils.Configs;
import utils.Path;
import views.screen.component.NavBarHandler;
import views.screen.BaseScreenHandlerWithTransactionPopup;
import views.screen.popup.AlertPopup;
import views.screen.popup.PaymentResultPopup;
import views.screen.session.SessionScreenHandler;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * handler for Payment Confirmation Screen
 *
 * @author Nguyen Thai An
 * <p>
 * creted at: 03/12/2020
 * <p>
 * project name: EBR
 * <p>
 * teacher's name: Dr. Nguyen Thi Thu Trang
 * <p>
 * class name: TT.CNTT ICT 02 - K62
 */
public class PaymentConfirmationScreenHandler extends BaseScreenHandlerWithTransactionPopup implements Initializable {

    private PaymentScreenController controller;

    @FXML
    private Pane navbar;

    @FXML
    private ImageView paymentConfirmationBikeImage;

    @FXML
    private Text barcode;

    @FXML
    private TextField cardNumber;
    @FXML
    private TextField cardOwner;
    @FXML
    private Text bikeType;
    @FXML
    private TextField expDate;
    @FXML
    private TextField securityCode;

    @FXML
    private Text deposit;

    @FXML
    private Text rentalFee;

    @FXML
    private Text hold;

    @FXML
    private Button paymentConfirmationCancelButton;

    @FXML
    private Button paymentConfirmationConfirmButton;

    @FXML
    private ImageView paymentConfirmationCancelButtonImage;

    @FXML
    private ProgressIndicator submitLoadingIndicator;

    /**
     * Create and setup new Handler
     * @param stage {@link Stage}
     * @param screenPath path to .fxml file
     * @param controller {@link PaymentScreenController}
     * @throws IOException IO errors
     */
    public PaymentConfirmationScreenHandler(Stage stage, String screenPath, PaymentScreenController controller) throws IOException {
        super(stage, screenPath);
        this.setBController(controller);
        this.controller = controller;
        super.screenTitle = "Payment Confirmation";
        // set all text and image
        setBikeImage();
        setTextLabels();
        navbar.getChildren().add(new NavBarHandler(this, false, true, true).getContent());

        // set up extra event
        paymentConfirmationConfirmButton.setOnMousePressed(e -> {
            try {
                paymentConfirmationConfirmButton.setText("Loading");
                paymentConfirmationConfirmButton.setDisable(true);
                submitLoadingIndicator.setVisible(true);
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        });
        paymentConfirmationConfirmButton.setOnMouseReleased(e -> {
            try {
                handleRentingConfirmation();
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set event listener
        paymentConfirmationCancelButton.setOnMouseClicked(e -> homeScreenHandler.show());
    }

    /**
     * set image for bike
     */
    private void setBikeImage() {
        try {
            setImage(paymentConfirmationBikeImage, this.controller.getBike().getImageURL());
            setImage(paymentConfirmationCancelButtonImage, Path.CANCEL_BUTTON_ICON);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    /**
     * set value for all labels
     */
    private void setTextLabels() {
        try {
            barcode.setText(Integer.toString(this.controller.getBike().getBarcode()));
            cardNumber.setText(this.controller.getCardInfo().get("cardNumber"));
            cardOwner.setText(this.controller.getCardInfo().get("cardOwner"));
            expDate.setText(this.controller.getCardInfo().get("expDate"));
            securityCode.setText(this.controller.getCardInfo().get("securityCode"));
            bikeType.setText(this.controller.getBike().getBikeType());
            deposit.setText(this.controller.getBike().getDeposit() + " " + Configs.CURRENCY);
            rentalFee.setText(this.controller.getBike().getCharge() + " " + Configs.CURRENCY);
            hold.setText(this.controller.getBike().getDeposit() + " " + Configs.CURRENCY);
        } catch (NullPointerException exp) {
            exp.printStackTrace();
        }
    }

    /**
     * Make transaction for renting and save
     * @throws IOException IO errors
     */
    private void handleRentingConfirmation() throws IOException {
        String contents = "pay order";
        try {
            PaymentTransaction rentTransaction = this.controller.payDeposit(this.controller.getBike().getDeposit(), contents,
                    this.controller.getCardInfo().get("cardNumber"), this.controller.getCardInfo().get("cardOwner"),
                    this.controller.getCardInfo().get("expDate"), this.controller.getCardInfo().get("securityCode"));

            if (rentTransaction == null) {
                getPreviousScreen().show();
            } else {
                // Take bike out of dock
                this.controller.getBike().takeBikeOutOfDock();
                // Save then change screen
                rentTransaction.setMethod("Credit Card");
                rentTransaction.setType("rent");
                saveTransaction(rentTransaction);
            }
        } catch (PaymentException | UnrecognizedException e) {
            this.getPreviousScreen().show();
            AlertPopup.error(e.getMessage());
        }
    }

    /**
     * create and start new renting session, then go to session screen
     * @param rentTransaction {@link PaymentTransaction} pay deposit transaction
     */
    private void transitionToSessionScreen(PaymentTransaction rentTransaction) {
        try {
            // Create and save card

            // Create new renting session
            Session session = SessionManager.getInstance().createSession(this.controller.getBike(), rentTransaction.getCard(), rentTransaction);
            SessionScreenController sessionScreenController = new SessionScreenController();
            SessionScreenHandler sessionScreenHandler = new SessionScreenHandler(this.stage,
                    Path.SESSION_SCREEN_PATH, session, sessionScreenController);
            sessionScreenHandler.setHomeScreenHandler(homeScreenHandler);
            sessionScreenHandler.setPreviousScreen(homeScreenHandler);
            sessionScreenHandler.setScreenTitle(this.getScreenTitle());
            sessionScreenHandler.show();

        } catch (IOException exp) {
            exp.printStackTrace();
        }
    }

    /**
     * Save the transaction via Manager class
     * @param rentTransaction {@link PaymentTransaction}
     * @throws IOException IO errors
     */
    private void saveTransaction(PaymentTransaction rentTransaction) throws IOException {
        CreditCard card = new CreditCard(this.controller.getCardInfo().get("cardNumber"), this.controller.getCardInfo().get("cardOwner"),
                Integer.parseInt(this.controller.getCardInfo().get("securityCode")), this.controller.getCardInfo().get("expDate"));

        String cardId = CreditCardManager.getInstance().saveCreditCard(card);
        card.setId(cardId);

        rentTransaction.setCard(card);

        // Save Renting Transaction
        String transactionId = PaymentTransactionManager.getInstance().savePaymentTransaction(rentTransaction);
        PaymentResultPopup.display(this, rentTransaction, "PAYMENT SUCCESSFUL");
    }

    @FXML
    void setPaymentConfirmationConfirmButtonClickListener(MouseEvent e) {
        try {
            System.out.println("here");
            paymentConfirmationConfirmButton.setText("Loading");
            paymentConfirmationConfirmButton.setDisable(true);
            submitLoadingIndicator.setVisible(true);
            handleRentingConfirmation();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    @Override
    public void continueAfterPopupClosed(PaymentTransaction paymentTransaction) throws IOException {
        this.transitionToSessionScreen(paymentTransaction);
    }
}
