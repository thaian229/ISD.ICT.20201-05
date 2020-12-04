package views.screen.payment;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
