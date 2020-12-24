package views.screen.payment;

import common.exception.FormException;
import controller.BaseController;
import controller.PaymentScreenController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Path;
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
    private Text errorText;

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


    public PaymentScreenHandler(Stage stage, String screenPath, PaymentScreenController paymentScreenController) throws IOException {
        super(stage, screenPath);
        super.screenTitle = "Payment Screen";
        this.setImages();
        this.setBController(paymentScreenController);
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
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        });
    }

    private void setImages() {
        try {
            setImage(logo, Path.LOGO_ICON);
            setImage(back, Path.BACK_NAV_ICON);
            setImage(backButtonImage, Path.CANCEL_BUTTON_ICON);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    private void handleCardInfoSubmit() throws IOException {
        // Read in all fields
        errorText.setVisible(false);
        HashMap<String, String> cardInfo = new HashMap<>();
        cardInfo.put("cardOwner", cardOwner.getText().trim());
        cardInfo.put("cardNumber", cardNumber.getText().trim());
        cardInfo.put("expDate", expDate.getText().trim());
        cardInfo.put("securityCode", securityCode.getText().trim());
        // Validate card info then process to confirmation screen
        this.getBController().setCardInfo(cardInfo);
        try {
            this.getBController().validateCreditCardForm(cardInfo);
//            this.getBController().validateCardUnused(cardNumber.getText().trim());
            this.goToConfirmationScreen();
        } catch (FormException e) {
            errorText.setVisible(true);
            errorText.setText(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void goToConfirmationScreen() throws IOException {
        // Transition to PaymentConfirmationScreen
        PaymentConfirmationScreenHandler paymentConfirmationScreenHandler = new PaymentConfirmationScreenHandler(this.stage, Path.PAYMENT_CONFIRMATION_SCREEN_PATH, this.getBController());
        paymentConfirmationScreenHandler.setPreviousScreen(this);
        paymentConfirmationScreenHandler.setHomeScreenHandler(homeScreenHandler);
        paymentConfirmationScreenHandler.setScreenTitle("Renting Confirmation Screen");
        paymentConfirmationScreenHandler.show();
    }

    @Override
    public PaymentScreenController getBController() {
        return (PaymentScreenController) super.getBController();
    }
}
