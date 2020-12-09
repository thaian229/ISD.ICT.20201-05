package views.screen.bike;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.bike.Bike;
import model.dock.Dock;
import model.dock.DockManager;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BikeScreenHandler extends BaseScreenHandler implements Initializable {

    @FXML
    private ImageView logo;

    @FXML
    private ImageView back;

    @FXML
    private Button barcodeButton;

    @FXML
    private Text bikeBarcode;

    @FXML
    private Text bikeDockName;

    @FXML
    private Text bikeBattery;

    @FXML
    private Text bikeUsage;

    @FXML
    private Text bikeDeposit;

    @FXML
    private Text bikeCharge;

    @FXML
    private ImageView bikeImage;

    private Bike bike;

    public BikeScreenHandler(Stage stage, String screenPath, Bike bike) throws IOException {
        super(stage, screenPath);
        this.bike = bike;
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
            bikeBarcode.setText(Integer.toString(bike.getBarcode()));
            bikeDeposit.setText(Integer.toString(bike.getDeposit()));
            bikeCharge.setText(Integer.toString((bike.getCharge())));
            Dock dock = new DockManager().getDockById(bike.getDockId());
            bikeDockName.setText(dock.getName());
            bikeBattery.setVisible(false);
            bikeUsage.setVisible(false);
            File fileBikeImage = new File(bike.getImageURL());
            Image img = new Image(fileBikeImage.toURI().toString());
            bikeImage.setImage(img);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

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
