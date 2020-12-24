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
    private Text saddleNum;

    @FXML
    private Text pedalsNum;

    @FXML
    private Text rearSeatNum;

    @FXML
    private ImageView bikeImage;
    @FXML
    private ImageView saddleImg;
    @FXML
    private ImageView pedalsImg;
    @FXML
    private ImageView rearSeatImg;
    @FXML
    private ImageView batteryImg;

    @FXML
    private Button rentNowButton;

    private Bike bike;

    public BikeScreenHandler(Stage stage, String screenPath, Bike bike) throws IOException {
        super(stage, screenPath);
        this.bike = bike;
        super.screenTitle = "Bike Screen";
        this.setImages();
        displayBike();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
                PaymentScreenHandler paymentScreenHandler = new PaymentScreenHandler(this.stage, Path.PAYMENT_SCREEN_PATH, paymentScreenController);
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
            bikeCharge.setText(bike.getCharge() + " " + Configs.CURRENCY + "/h");
            bikeDockName.setText(bike.getDock().getName());
            pedalsNum.setText(bike.getPairOfPedals() + "");
            saddleNum.setText(bike.getSaddle() + "");
            rearSeatNum.setText(bike.getRearSeat() + "");
            if (bike instanceof StandardElectricalBike) {
                bikeBattery.setText(((StandardElectricalBike) bike).getBattery() + "%");
                bikeUsage.setText(((StandardElectricalBike) bike).getTimeLeft() + " minutes");
            } else {
                bikeBattery.setVisible(false);
                bikeUsage.setVisible(false);
                batteryImg.setVisible(false);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void setImages() {
        setImage(logo, Path.LOGO_ICON);
        setImage(back, Path.BACK_NAV_ICON);
        setImage(saddleImg, Path.SADDLE_ICON);
        setImage(pedalsImg, Path.PEDALS_ICON);
        setImage(rearSeatImg, Path.REAR_SEAT_ICON);
        setImage(batteryImg, Path.BATTERY_ICON);
        setImage(bikeImage, bike.getImageURL());
    }
}
