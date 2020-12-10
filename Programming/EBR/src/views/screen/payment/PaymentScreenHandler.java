package views.screen.payment;

import controller.renting.PaymentScreenController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * handler for Payment Screen
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
public class PaymentScreenHandler extends BaseScreenHandler implements Initializable {

    @FXML
    private TextField cardOwner;

    @FXML
    private TextField cardNumber;

    @FXML
    private TextField expDate;

    @FXML
    private TextField securityCode;

    @FXML
    private Button paymentCancelButton;

    @FXML
    private Button paymentConfirmButton;

    @FXML
    private ImageView logo;

    @FXML
    private ImageView back;

    public PaymentScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        super.screenTitle = "Payment Screen";
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logo.setOnMouseClicked(e -> backToHomeScreen());
        back.setOnMouseClicked(e -> backToPreviousScreen());
        paymentCancelButton.setOnMouseClicked(e -> backToHomeScreen());

        paymentConfirmButton.setOnMouseClicked(e -> {
            try {
                handleCardInfoSubmit();
                goToConfirmationScreen();
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        });
    }

    private void handleCardInfoSubmit() {
        // Read in all fields
        HashMap<String, String> cardInfo = new HashMap<>();
        cardInfo.put("cardOwner", cardOwner.getText().trim());
        cardInfo.put("cardNumber", cardNumber.getText().trim());
        cardInfo.put("expDate", expDate.getText().trim());
        cardInfo.put("securityCode", securityCode.getText().trim());

        // Take controller
        PaymentScreenController paymentScreenController = (PaymentScreenController) getBController();

        // Validate card info then process to confirmation screen
        paymentScreenController.setCardInfo(cardInfo);
        paymentScreenController.validateCreditCardForm(cardInfo);
    }

    private void goToConfirmationScreen() throws IOException {
        // Transition to PaymentConfirmationScreen
        PaymentScreenController paymentScreenController = (PaymentScreenController) getBController();
        PaymentConfirmationScreenHandler paymentConfirmationScreenHandler = new PaymentConfirmationScreenHandler(this.stage,
                Configs.PAYMENT_CONFIRMATION_SCREEN_PATH, paymentScreenController);
        paymentConfirmationScreenHandler.setPreviousScreen(this);
        paymentConfirmationScreenHandler.setHomeScreenHandler(homeScreenHandler);
        paymentConfirmationScreenHandler.setScreenTitle("Renting Confirmation Screen");
        paymentConfirmationScreenHandler.show();
    }

    private void backToHomeScreen() {
        try {
            homeScreenHandler.show();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    private void backToPreviousScreen() {
        getPreviousScreen().show();
    }
}
