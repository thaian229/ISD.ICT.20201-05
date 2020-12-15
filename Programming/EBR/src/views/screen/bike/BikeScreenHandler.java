package views.screen.bike;

import controller.renting.PaymentScreenController;
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
import views.screen.BaseScreenHandler;
import views.screen.payment.PaymentScreenHandler;
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
        this.setImage();

        logo.setOnMouseClicked(e -> homeScreenHandler.show());
        back.setOnMouseClicked(e -> getPreviousScreen().show());

        barcodeButton.setOnMouseClicked(e -> {
            try {
                PopupScreen.display();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });



        rentNowButton.setOnMouseClicked(e -> {
            System.out.println("Button clicked");
            try {
                PaymentScreenController paymentScreenController = new PaymentScreenController(bike);
                PaymentScreenHandler paymentScreenHandler = new PaymentScreenHandler(this.stage, Configs.PAYMENT_SCREEN_PATH);
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
            bikeDeposit.setText(Integer.toString(bike.getDeposit()) + " VND");
            bikeCharge.setText(Integer.toString(bike.getCharge()) + " VND");
            bikeDockName.setText(bike.getDock().getName());
            if (bike instanceof StandardElectricalBike) {
                bikeBattery.setText(((StandardElectricalBike) bike).getBattery() + "%");
                bikeUsage.setText(((StandardElectricalBike) bike).getTimeLeft() + " minutes left");
            }
            else if (bike instanceof TwinElectricalBike){
                bikeBattery.setText(((TwinElectricalBike) bike).getBattery() + "%");
                bikeUsage.setText(((TwinElectricalBike) bike).getTimeLeft() + " minutes left");
            }
            else {
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

    private void setImage() {
        File file1 = new File(Configs.IMAGE_PATH + "/" + "LOGO.png");
        Image img1 = new Image(file1.toURI().toString());
        logo.setImage(img1);

        File file2 = new File(Configs.IMAGE_PATH + "/" + "backButton.png");
        Image img2 = new Image(file2.toURI().toString());
        back.setImage(img2);
    }
}
