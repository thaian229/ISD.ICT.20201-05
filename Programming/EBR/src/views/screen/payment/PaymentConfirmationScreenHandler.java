package views.screen.payment;

import controller.renting.PaymentScreenController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
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
public class PaymentConfirmationScreenHandler extends BaseScreenHandler implements Initializable {

    private PaymentScreenController controller;

    @FXML
    private ImageView paymentConfirmationBikeImage;

    @FXML
    private Text barcode;

    @FXML
    private Text cardNumber;

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
    private ImageView logo;

    @FXML
    private ImageView back;

    public PaymentConfirmationScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    public PaymentConfirmationScreenHandler(Stage stage, String screenPath, PaymentScreenController controller) throws IOException {
        super(stage, screenPath);
        this.setBController(controller);
        this.controller = controller;
        // set all text and image
        // setBikeImage();
        setTextLabels();
        // set up extra event
        paymentConfirmationConfirmButton.setOnMouseClicked(e -> {
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
        logo.setOnMouseClicked(e -> homeScreenHandler.show());
        back.setOnMouseClicked(e -> getPreviousScreen().show());
        paymentConfirmationCancelButton.setOnMouseClicked(e -> homeScreenHandler.show());
    }

    private void setBikeImage() {
        try {
            File file = new File(this.controller.getBike().getImageURL());
            Image image = new Image(file.toURI().toString());
            paymentConfirmationBikeImage.setImage(image);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    private void setTextLabels() {
        try {
            barcode.setText(Integer.toString(this.controller.getBike().getBarcode()));
            cardNumber.setText(this.controller.getCardInfo().get("cardNumber"));
            deposit.setText(Integer.toString(this.controller.getBike().getDeposit()));
            rentalFee.setText(Integer.toString(this.controller.getBike().getCharge()));
            hold.setText(Integer.toString(this.controller.getBike().getDeposit()));
        } catch (NullPointerException exp) {
            exp.printStackTrace();
        }
    }

    private void handleRentingConfirmation() {
        String contents = "pay order";
        Map<String, String> respond = this.controller.payDeposit(this.controller.getBike().getDeposit(), contents,
                this.controller.getCardInfo().get("cardNumber"), this.controller.getCardInfo().get("cardOwner"),
                this.controller.getCardInfo().get("expDate"), this.controller.getCardInfo().get("securityCode"));

        if (respond.get("RESULT").equalsIgnoreCase("PAYMENT FAILED!")) {
            getPreviousScreen().show();
        } else {
            homeScreenHandler.show();
        }
    }

}
