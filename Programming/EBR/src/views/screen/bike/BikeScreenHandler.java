package views.screen.bike;

import controller.PaymentScreenController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.bike.Bike;
import model.bike.StandardElectricalBike;
import model.bike.TwinElectricalBike;
import utils.Configs;
import utils.Path;
import views.screen.BaseScreenHandler;
import views.screen.payment.PaymentScreenHandler;

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

    @FXML
    private Button rentNowButton;

    private Bike bike;

    public BikeScreenHandler(Stage stage, String screenPath, Bike bike) throws IOException {
        super(stage, screenPath);
        this.bike = bike;
        super.screenTitle = "Bike Screen";
        displayBike();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setImages();

        logo.setOnMouseClicked(e -> homeScreenHandler.show());
        back.setOnMouseClicked(e -> {
            BaseScreenHandler previousScreen = this.getPreviousScreen();
            previousScreen.setScreenTitle(previousScreen.getScreenTitle());
            previousScreen.show();
        });

        rentNowButton.setOnMouseClicked(e -> {
            System.out.println("Button clicked");
            try {
                PaymentScreenController paymentScreenController = new PaymentScreenController(bike);
                PaymentScreenHandler paymentScreenHandler = new PaymentScreenHandler(this.stage, Path.PAYMENT_SCREEN_PATH);
                paymentScreenHandler.setBController(paymentScreenController);
                paymentScreenHandler.setHomeScreenHandler(this.homeScreenHandler);
                paymentScreenHandler.setPreviousScreen(this);
                paymentScreenHandler.setScreenTitle("Payment Screen");
                paymentScreenHandler.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
    }

    private void displayBike() {
        try {
            bikeBarcode.setText(Integer.toString(bike.getBarcode()));
            bikeDeposit.setText(bike.getDeposit() + " " + Configs.CURRENCY);
            bikeCharge.setText(bike.getCharge() + " " + Configs.CURRENCY);
            bikeDockName.setText(bike.getDock().getName());
            if (bike instanceof StandardElectricalBike) {
                bikeBattery.setText(((StandardElectricalBike) bike).getBattery() + "%");
                bikeUsage.setText(((StandardElectricalBike) bike).getTimeLeft() + " seconds");
            } else if (bike instanceof TwinElectricalBike) {
                bikeBattery.setText(((TwinElectricalBike) bike).getBattery() + "%");
                bikeUsage.setText(((TwinElectricalBike) bike).getTimeLeft() + " seconds");
            } else {
                bikeBattery.setVisible(false);
                bikeUsage.setVisible(false);
            }
            File fileBikeImage = new File(bike.getImageURL());
            Image img = new Image(fileBikeImage.toURI().toString());
            bikeImage.setImage(img);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void setImages() {
        setImage(logo, Path.LOGO_ICON);
        setImage(back, Path.BACK_NAV_ICON);
    }
}
