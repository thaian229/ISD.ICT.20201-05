package views.screen.popup;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import utils.Configs;
import views.screen.BaseScreenHandler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * class for ...
 *
 * @author mHoang
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

public class PopupScreen extends BaseScreenHandler implements Initializable {


    @FXML
    ImageView logo;

    @FXML
    TextField barcodeInput;

    @FXML
    Button continueBtn;

    public PopupScreen(Stage stage) throws IOException {
        super(stage, Configs.POPUP_PATH);
    }

    private static PopupScreen popup() throws IOException {
        PopupScreen popup = new PopupScreen(new Stage());
        popup.stage.initStyle(StageStyle.UNDECORATED);
        return popup;
    }

    public static void display() throws IOException {
        popup().show();
    }

    private void setImage() {
        File file = new File(Configs.IMAGE_PATH + "/LOGO_R.png");
        Image image = new Image(file.toURI().toString());
        logo.setImage(image);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setImage();
        continueBtn.setOnMouseClicked(e -> {
            //Goto different page
        });
    }
}
