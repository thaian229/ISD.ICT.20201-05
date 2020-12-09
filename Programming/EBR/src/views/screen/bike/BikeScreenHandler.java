package views.screen.bike;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.bike.Bike;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BikeScreenHandler extends BaseScreenHandler implements Initializable {

    @FXML
    private javafx.scene.image.ImageView logo;

    @FXML
    private javafx.scene.image.ImageView back;

    @FXML
    private Button barcodeButton;

    private Bike bike;

    public BikeScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        super.screenTitle = "Bike Screen";
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setImage();

        back.setOnMouseClicked(e ->{
            BaseScreenHandler previousScreen = this.getPreviousScreen();
            previousScreen.setScreenTitle(previousScreen.getScreenTitle());
            previousScreen.show();
        });

        barcodeButton.setOnMouseClicked(e -> {
            try {
                PopupScreen.display();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        displayBike();
    }

    private void displayBike() {
        try {

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }

    private void set

    private void setImage() {
        // fix image path caused by fxml
        File file1 = new File(Configs.IMAGE_PATH + "/" + "LOGO.png");
        Image img1 = new Image(file1.toURI().toString());
        logo.setImage(img1);

        File file2 = new File(Configs.IMAGE_PATH + "/" + "backButton.png");
        Image img2 = new Image(file2.toURI().toString());
        back.setImage(img2);
    }
}
