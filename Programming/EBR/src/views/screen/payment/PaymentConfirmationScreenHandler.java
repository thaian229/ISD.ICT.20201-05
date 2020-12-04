package views.screen.payment;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * description
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
