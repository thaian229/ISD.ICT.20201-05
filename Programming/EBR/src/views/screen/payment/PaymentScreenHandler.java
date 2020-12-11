package views.screen.payment;

import controller.renting.PaymentScreenController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import utils.Path;
import views.screen.BaseScreenHandler;

import java.io.File;
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
    private ImageView backButtonImage;

    @FXML
    private ImageView back;

    public PaymentScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        super.screenTitle = "Payment Screen";
        this.setImages();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logo.setOnMouseClicked(e -> homeScreenHandler.show());
        back.setOnMouseClicked(e -> getPreviousScreen().show());
        paymentCancelButton.setOnMouseClicked(e -> homeScreenHandler.show());
        backButtonImage.setOnMouseClicked(e -> homeScreenHandler.show());

        paymentConfirmButton.setOnMouseClicked(e -> {
            try {
                handleCardInfoSubmit();
                goToConfirmationScreen();
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        });
    }

    private void setImages() {
        try {
            File file = new File(Path.LOGO_ICON);
            Image image = new Image(file.toURI().toString());
            logo.setImage(image);

            file = new File(Path.BACK_NAV_ICON);
            image = new Image(file.toURI().toString());
            back.setImage(image);

            file = new File(Path.CANCEL_BUTTON_ICON);
            image = new Image(file.toURI().toString());
            backButtonImage.setImage(image);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
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

}
